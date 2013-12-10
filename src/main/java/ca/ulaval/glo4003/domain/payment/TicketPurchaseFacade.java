package ca.ulaval.glo4003.domain.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;

public class TicketPurchaseFacade {

    @Inject
    MatchRepository matchRepository;
    @Inject
    TransactionManager transactionManager;
    @Inject
    TransactionService transactionService;

    public TicketPurchaseFacade() {

    }

    public Section retriveSection(String venue, String date, String sectionName) {
        return matchRepository.getMatchByIdentifier(venue + "/" + date).getSectionByName(sectionName);
    }

    public float computePurchaseTotal(float price, int quantity) {
        return quantity * price;
    }

    public void processPurchase(String venue, String date, String sectionName, int quantity, long creditCardNumber,
                                String creditCardType) throws InvalidCreditCardException {
        List<Ticket> ticketsToBuy = reserveTickets(venue, date, sectionName, quantity);
        transactionManager.processTransaction(creditCardNumber, creditCardType, ticketsToBuy, transactionService);
    }

    private List<Ticket> reserveTickets(String venue, String date, String sectionName, int quantity) {
        Match match = matchRepository.getMatchByIdentifier(venue + "/" + date);
        List<Ticket> ticketsToBuy = match.reserveTickets(quantity, sectionName);
        return ticketsToBuy;
    }

    public void processCartPurchase(ShoppingCart shoppingCart, long creditCardNumber, String creditCardType) throws InvalidCreditCardException {
        Map<Section, List<Ticket>> cartContents = shoppingCart.getCartContent();
        buyTicketsFromCart(creditCardNumber, creditCardType, cartContents);
        shoppingCart.empty();
    }

    private void buyTicketsFromCart(long creditCardNumber, String creditCardType,
                                    Map<Section, List<Ticket>> cartContents) throws InvalidCreditCardException {
        List<Ticket> ticketsToBuy = new ArrayList<Ticket>();
        for (List<Ticket> ticketsInSection : cartContents.values()) {
            ticketsToBuy.addAll(ticketsInSection);
        }

        transactionManager.processTransaction(creditCardNumber, creditCardType, ticketsToBuy, transactionService);
    }

    // For test purpose only
    protected TicketPurchaseFacade(MatchRepository matchRepository, TransactionManager transactionManager,
                                   TransactionService transactionService) {
        this.matchRepository = matchRepository;
        this.transactionManager = transactionManager;
        this.transactionService = transactionService;
    }
}
