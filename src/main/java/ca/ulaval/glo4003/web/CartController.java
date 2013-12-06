package ca.ulaval.glo4003.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

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
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

@Controller
@Scope("session")
public class CartController {

    @Inject
    TransactionService transactionService;
    @Inject
    TransactionManager transactionManager;
    @Inject
    MatchRepository matchRepository;

    @Inject
    ShoppingCart shoppingCart;

    private SectionViewConverter sectionViewConverter = new SectionViewConverter();

    public CartController() {

    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        List<SectionViewModel> cartContent = sectionViewConverter.convert(shoppingCart.getCartContent());
        model.addAttribute("cartContent", cartContent);
        return "cart";
    }

    @RequestMapping(value = "/cart/add/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String reviewSelectedTicketsForSection(@PathVariable String venue, @PathVariable String date,
                                                  @PathVariable String sectionName,
                                                  @RequestParam(value = "quantity", required = true) int quantity,
                                                  Model model) {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
        shoppingCart.addTicketsQuantity(match, sectionName, quantity);

        List<SectionViewModel> cartContent = sectionViewConverter.convert(shoppingCart.getCartContent());
        model.addAttribute("cartContent", cartContent);

        return "cart";
    }

    @RequestMapping(value = "/cart/changeQuantity/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String changeQuantity(@PathVariable String venue, @PathVariable String date,
                                 @PathVariable String sectionName,
                                 @RequestParam(value = "quantity", required = true) int quantity, Model model,
                                 HttpServletResponse response) throws IOException {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
        try {
            shoppingCart.changeTicketsQuantity(match, sectionName, quantity);
        } catch (NoAvailableTicketsException e) {
            response.sendError(500);
        }
        return cart(model);
    }

    @RequestMapping(value = "/cart/remove/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String removeATicketFromCart(@PathVariable String venue, @PathVariable String date,
                                        @PathVariable String sectionName, Model model, HttpServletResponse response) throws IOException {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);

        shoppingCart.removeSectionFromCart(match, sectionName);

        List<SectionViewModel> cartContent = sectionViewConverter.convert(shoppingCart.getCartContent());
        model.addAttribute("cartContent", cartContent);
        return cart(model);
    }

    @RequestMapping(value = "/cart/checkout", method = RequestMethod.GET)
    public String checkout(Model model, @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {
        List<Ticket> ticketsToBuy = new ArrayList<Ticket>();
        Map<Section, List<Ticket>> cartContents = shoppingCart.getCartContent();
        for (List<Ticket> sectionTickets : cartContents.values()) {
            ticketsToBuy.addAll(sectionTickets);
        }

        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));
        ArrayList<SectionViewModel> sections = new ArrayList<SectionViewModel>();
        sections.add(viewModel);

        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);

        shoppingCart.removeSectionFromCart(match, sectionName);

        List<SectionViewModel> cartContent = sectionViewConverter.convert(shoppingCart.getCartContent());
        model.addAttribute("cartContent", cartContent);
        return cart(model);
    }

    @RequestMapping(value = "/cart/empty", method = RequestMethod.GET)
    public String checkout(@PathVariable String venue, @PathVariable String date, @PathVariable String sectionName,
                           Model model, HttpServletResponse response) throws IOException {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);

        shoppingCart.removeSectionFromCart(match, sectionName);

        List<SectionViewModel> cartContent = sectionViewConverter.convert(shoppingCart.getCartContent());
        model.addAttribute("cartContent", cartContent);
        return cart(model);
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
        ArrayList<SectionViewModel> sections = new ArrayList<SectionViewModel>();
        sections.add(viewModel);

        float purchaseTotal = quantity * viewModel.getPrice();
        model.addAttribute("purchaseTotal", purchaseTotal);
        model.addAttribute("sections", sections);
        model.addAttribute("quantity", quantity);

        model.addAttribute("purchaseURL", String.format("/purchaseReceipt/%s/%s/%s", venue, date, sectionName));
        return "ticketPurchaseReview";
    }

    protected CartController(TransactionService transactionService, TransactionManager transactionManager,
                             ShoppingCart shoppingCart, SectionViewConverter sectionConverter) {
        this.transactionService = transactionService;
        this.transactionManager = transactionManager;
        this.shoppingCart = shoppingCart;
        this.sectionViewConverter = sectionConverter;
    }
}
