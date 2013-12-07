package ca.ulaval.glo4003.domain.payment;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.Ticket;

public class TransactionManager {

    @Inject
    MatchRepository matchRepository;

    CreditCardFactory creditCardFactory;

    public TransactionManager(CreditCardFactory cerditCardFactory) {
        this.creditCardFactory = cerditCardFactory;
    }

    public long processTransaction(long creditCardNumber, String creditCardType, List<Ticket> ticketsToBuy, TransactionService transactionService) throws InvalidCreditCardException {
        float purchaseTotal = 0;
        for (Ticket ticket : ticketsToBuy) {
            purchaseTotal += ticket.getPrice();
            processTicket(ticket);
        }

        CreditCard creditCard = creditCardFactory.create(creditCardType, creditCardNumber);
        long transactionID = transactionService.processPayment(creditCard, purchaseTotal);
        return transactionID;
    }

    private void processTicket(Ticket ticket) {
        ticket.buy();
        matchRepository.update(ticket.getMatchIdentifier());
    }

    // For test purpose only
    protected TransactionManager(MatchRepository matchRepository,
                                 CreditCardFactory creditCardFactory) {
        this.matchRepository = matchRepository;
        this.creditCardFactory = creditCardFactory;
    }

}
