package ca.ulaval.glo4003.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
import ca.ulaval.glo4003.domain.payment.InvalidCreditCardException;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.domain.user.UserRepository;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.converters.TicketViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;
import ca.ulaval.glo4003.web.viewmodels.TicketViewModel;

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
    JavaMailSenderImpl mailSender;

    @Autowired
    ShoppingCart shoppingCart;

    private SectionViewConverter sectionConverter = new SectionViewConverter();

    private TicketViewConverter ticketConverter = new TicketViewConverter();

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

        if (true) {
            Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
            shoppingCart.addTickets(match, quantity, sectionName);

            List<TicketViewModel> tickets = ticketConverter.convert(shoppingCart.getTickets());
            model.addAttribute("tickets", tickets);

            return "cart";
        } else {
            float purchaseTotal = quantity * viewModel.getPrice();
            model.addAttribute("purchaseTotal", purchaseTotal);
            model.addAttribute("section", viewModel);
            model.addAttribute("quantity", quantity);

            return "ticketPurchaseReview";
        }
    }

    @RequestMapping(value = "/purchaseReview/{venue}/{date}/{sectionName}", method = RequestMethod.POST)
    public String purchaseSelectedTicketsForSection(@PathVariable String venue,
                                                    @PathVariable String date,
                                                    @PathVariable String sectionName,
                                                    @RequestParam(value = "quantity", required = true) int quantity,
                                                    Model model,
                                                    @ModelAttribute(value = "creditCardForm") CreditCardViewModel creditCard) {

        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));

        float purchaseTotal = quantity * viewModel.getPrice();
        model.addAttribute("purchaseTotal", purchaseTotal);
        model.addAttribute("section", viewModel);
        model.addAttribute("quantity", quantity);

        // shoppingCart.str =
        // SecurityContextHolder.getContext().getAuthentication().getName();

        try {
            new TransactionManager();
            Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
            long transactionID = transactionManager.processTransaction(creditCard.getNumber(),
                                                                       creditCard.getType(),
                                                                       match,
                                                                       quantity,
                                                                       sectionName,
                                                                       transactionService);
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

    protected TicketPurchaseController(MatchRepository matchRepository, SectionViewConverter sectionConverter) {
        this.matchRepository = matchRepository;
        this.sectionConverter = sectionConverter;
    }

}