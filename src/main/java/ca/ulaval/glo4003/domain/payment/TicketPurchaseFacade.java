package ca.ulaval.glo4003.domain.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.ui.Model;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.NoAvailableTicketsException;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class TicketPurchaseFacade {

    @Inject
    MatchRepository matchRepository;
    @Inject
    TransactionManager transactionManager;
    @Inject
    TransactionService transactionService;

    private SectionViewConverter sectionConverter;

    public TicketPurchaseFacade(SectionViewConverter sectionConverter) {
        this.sectionConverter = sectionConverter;
    }

    public SectionViewModel retriveSectionInformations(String venue, String date, String sectionName, int quantity) {
        SectionViewModel viewModel = sectionConverter.convert(matchRepository.getMatchByIdentifier(venue + "/" + date)
                                                                             .getSectionByName(sectionName));
        viewModel.setPurchaseQuantity(quantity);

        return viewModel;
    }

    public float computePurchaseTotal(SectionViewModel viewModel, int quantity) {
        return quantity * viewModel.getPrice();
    }

    public String processPurchase(Model model, String venue, String date, String sectionName, int quantity,
                                  CreditCardViewModel creditCard) {
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
        transactionManager.processTransaction(creditCard.getNumber(), creditCard.getType(), ticketsToBuy,
                                              transactionService);
    }

    private List<Ticket> reserveTickets(String venue, String date, String sectionName, int quantity) {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
        List<Ticket> ticketsToBuy = match.reserveTickets(quantity, sectionName);
        return ticketsToBuy;
    }

    public String processCartPurchase(Model model, ShoppingCart shoppingCart, CreditCardViewModel creditCard) {
        Map<Section, List<Ticket>> cartContents = shoppingCart.getCartContent();
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

        transactionManager.processTransaction(creditCard.getNumber(), creditCard.getType(), ticketsToBuy,
                                              transactionService);
    }

    // For test purpose only
    protected TicketPurchaseFacade(SectionViewConverter sectionConverter, MatchRepository matchRepository,
            TransactionManager transactionManager, TransactionService transactionService) {
        this.sectionConverter = sectionConverter;
        this.matchRepository = matchRepository;
        this.transactionManager = transactionManager;
        this.transactionService = transactionService;
    }
}
