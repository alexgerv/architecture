package ca.ulaval.glo4003.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.NoAvailableTicketsException;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.payment.InvalidCreditCardException;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.domain.user.UserRepository;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

@Controller
@Scope("session")
public class TicketPurchaseController {

    @Inject
    MatchRepository matchRepository;
    @Inject
    TransactionService transactionService;
    @Inject
    TransactionManager transactionManager;
    @Inject
    UserRepository userRepository;

    @Inject
    ShoppingCart shoppingCart;

    private SectionViewConverter sectionConverter = new SectionViewConverter();

    public TicketPurchaseController() {

    }

    @RequestMapping(value = "/purchaseReview/{venue}/{date}/{sectionName}", method = RequestMethod.GET)
    public String reviewSelectedTicketsForSection(@PathVariable String venue,
                                                  @PathVariable String date,
                                                  @PathVariable String sectionName,
                                                  @RequestParam(value = "quantity", required = true) int quantity,
                                                  Model model,
                                                  @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {
        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));
        viewModel.setPurchaseQuantity(quantity);
        ArrayList<SectionViewModel> sections = new ArrayList<SectionViewModel>();
        sections.add(viewModel);

        float purchaseTotal = quantity * viewModel.getPrice();
        model.addAttribute("purchaseTotal", purchaseTotal);
        model.addAttribute("sections", sections);
        model.addAttribute("quantity", quantity);

        model.addAttribute("purchaseURL", String.format("/purchaseReceipt/%s/%s/%s", venue, date, sectionName));
        return "ticketPurchaseReview";
    }

    @RequestMapping(value = "/purchaseReceipt/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String purchaseSelectedTicketsForSection(@PathVariable String venue,
                                                    @PathVariable String date,
                                                    @PathVariable String sectionName,
                                                    @RequestParam(value = "quantity", required = true) int quantity,
                                                    Model model,
                                                    @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {

        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));
        viewModel.setPurchaseQuantity(quantity);

        ArrayList<SectionViewModel> sections = new ArrayList<SectionViewModel>();
        sections.add(viewModel);

        float purchaseTotal = quantity * viewModel.getPrice();
        model.addAttribute("purchaseTotal", purchaseTotal);
        model.addAttribute("sections", sections);

        try {
            buyTickets(venue, date, sectionName, quantity, creditCard);

        } catch (NoAvailableTicketsException e) {
            String message = "There are not enough available tickets";
            model.addAttribute("message", message);
            return "sectionDetails";
        } catch (InvalidCreditCardException e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "sectionDetails";
        }

        return "ticketPurchaseReceipt";
    }

    private void buyTickets(String venue, String date, String sectionName, int quantity, CreditCardViewModel creditCard) throws InvalidCreditCardException {
        List<Ticket> ticketsToBuy = reserveTickets(venue, date, sectionName, quantity);
        transactionManager.processTransaction(creditCard.getNumber(),
                                              creditCard.getType(),
                                              ticketsToBuy,
                                              transactionService);
    }

    private List<Ticket> reserveTickets(String venue, String date, String sectionName, int quantity) {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
        List<Ticket> ticketsToBuy = match.reserveTickets(quantity, sectionName);
        return ticketsToBuy;
    }

    @RequestMapping(value = "/purchase/cart", method = RequestMethod.POST)
    public String purchaseCartContent(Model model,
                                      @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {

        Map<Section, List<Ticket>> cartContents = shoppingCart.getCartContent();
        List<SectionViewModel> sectionsInCart = sectionConverter.convert(cartContents);

        model.addAttribute("purchaseTotal", shoppingCart.getCartValue());
        model.addAttribute("sections", sectionsInCart);

        try {
            buyTicketsFromCart(creditCard, cartContents);

        } catch (InvalidCreditCardException e) {
            String message = e.getMessage();
            model.addAttribute("message", message);
            return "sectionDetails";
        }

        shoppingCart.empty();

        return "ticketPurchaseReceipt";
    }

    private void buyTicketsFromCart(CreditCardViewModel creditCard, Map<Section, List<Ticket>> cartContents) throws InvalidCreditCardException {
        List<Ticket> ticketsToBuy = new ArrayList<Ticket>();
        for (List<Ticket> ticketsInSection : cartContents.values()) {
            ticketsToBuy.addAll(ticketsInSection);
        }

        transactionManager.processTransaction(creditCard.getNumber(),
                                              creditCard.getType(),
                                              ticketsToBuy,
                                              transactionService);
    }

    // For tests purpose only
    protected TicketPurchaseController(MatchRepository matchRepository, SectionViewConverter sectionConverter,
                                       TransactionManager transactionManager, TransactionService transactionService,
                                       ShoppingCart shoppingCart) {
        this.matchRepository = matchRepository;
        this.sectionConverter = sectionConverter;
        this.transactionManager = transactionManager;
        this.transactionService = transactionService;
        this.shoppingCart = shoppingCart;
    }
}