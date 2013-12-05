package ca.ulaval.glo4003.domain.payment;

import java.util.List;

import javax.inject.Inject;

import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.service.mailsender.MailSender;

public class TransactionManager {

    @Inject
    MailSender mailSender;
    @Inject
    MatchRepository matchRepository;

    CreditCardFactory cerditCardFactory;

    public TransactionManager(CreditCardFactory cerditCardFactory) {
        this.cerditCardFactory = cerditCardFactory;
    }

    public long processTransaction(long creditCardNumber, String creditCardType, List<Ticket> ticketsToBuy,
                                   TransactionService transactionService) throws InvalidCreditCardException {
        float purchaseTotal = 0;
        for (Ticket ticket : ticketsToBuy) {
            purchaseTotal += ticket.getPrice();
            processTicket(ticket);
        }

        CreditCard creditCard = cerditCardFactory.create(creditCardType, creditCardNumber);
        long transactionID = transactionService.processPayment(creditCard, purchaseTotal);
        mailSender.sendPurchaseConfirmation();
        return transactionID;
    }

    private void processTicket(Ticket ticket) {
        ticket.buy();
        matchRepository.update(ticket.getMatchIdentifier());
    }

    // For test purpose only
    protected TransactionManager(MailSender mailSender, MatchRepository matchRepository) {
        this.mailSender = mailSender;
        this.matchRepository = matchRepository;
    }

}
