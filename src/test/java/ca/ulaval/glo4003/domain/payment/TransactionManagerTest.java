package ca.ulaval.glo4003.domain.payment;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.service.mailsender.MailSender;

public class TransactionManagerTest {

    private static final long A_CREDIT_CARD_NUMBER = 999999999;
    private static final String A_CREDIT_CARD_TYPE = "VASI";
    private static final Float TICKET_1_PRICE = 40.00f;
    private static final Float TICKET_2_PRICE = 60.00f;
    private static final String MATCH_IDENTIFIER = "Stade_Tellus/12-30-13";
    private static final long A_TRANSACTION_ID = 8888888;

    @Mock
    private MailSender mailSender;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private Ticket ticket1;
    @Mock
    private Ticket ticket2;
    @Mock
    private TransactionService transactionService;
    @Mock
    private CreditCardFactory creditCardFactory;
    @Mock
    private CreditCard creditCard;

    @InjectMocks
    private TransactionManager transactionManager;

    private List<Ticket> ticketsToBuy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ticketsToBuy = Arrays.asList(ticket1, ticket2);
        transactionManager = new TransactionManager(mailSender, matchRepository, creditCardFactory);
    }

    @Test
    public void whenProcessingATransactionTheTransactionIDIsReturned() throws InvalidCreditCardException {
        long transactionID = transactionManager.processTransaction(A_CREDIT_CARD_NUMBER, A_CREDIT_CARD_TYPE,
                                                                   ticketsToBuy, transactionService);
        assertNotNull(transactionID);
    }

    @Test
    public void whenProcessingATransactionTheTransactionServiceIsProcessWithTheRightPurchaseTotal() throws InvalidCreditCardException {
        Mockito.when(ticket1.getPrice()).thenReturn(TICKET_1_PRICE);
        Mockito.when(ticket2.getPrice()).thenReturn(TICKET_2_PRICE);
        Mockito.when(creditCardFactory.create(A_CREDIT_CARD_TYPE, A_CREDIT_CARD_NUMBER)).thenReturn(creditCard);

        transactionManager.processTransaction(A_CREDIT_CARD_NUMBER, A_CREDIT_CARD_TYPE, ticketsToBuy,
                                              transactionService);

        verify(transactionService).processPayment(creditCard, TICKET_1_PRICE + TICKET_2_PRICE);
    }

    @Test
    public void whenProcessingATransactionAllTicketsAreBought() throws InvalidCreditCardException {
        transactionManager.processTransaction(A_CREDIT_CARD_NUMBER, A_CREDIT_CARD_TYPE, ticketsToBuy,
                                              transactionService);

        for (Ticket ticket : ticketsToBuy) {
            verify(ticket).buy();
        }
    }

    @Test
    public void whenProcessingATrancationAllTicketAssociatedMatchAreUpdated() throws InvalidCreditCardException {
        Mockito.when(ticket1.getMatchIdentifier()).thenReturn(MATCH_IDENTIFIER);
        Mockito.when(ticket2.getMatchIdentifier()).thenReturn(MATCH_IDENTIFIER);

        transactionManager.processTransaction(A_CREDIT_CARD_NUMBER, A_CREDIT_CARD_TYPE, ticketsToBuy,
                                              transactionService);

        verify(matchRepository, times(ticketsToBuy.size())).update(MATCH_IDENTIFIER);
    }

    @Test
    public void whenProcessingATransactionAConfirmationEmailIsSend() throws InvalidCreditCardException {
        Mockito.when(ticket1.getPrice()).thenReturn(TICKET_1_PRICE);
        Mockito.when(ticket2.getPrice()).thenReturn(TICKET_2_PRICE);
        Mockito.when(creditCardFactory.create(A_CREDIT_CARD_TYPE, A_CREDIT_CARD_NUMBER)).thenReturn(creditCard);
        Mockito.when(transactionService.processPayment(creditCard, TICKET_1_PRICE + TICKET_2_PRICE))
               .thenReturn(A_TRANSACTION_ID);

        transactionManager.processTransaction(A_CREDIT_CARD_NUMBER, A_CREDIT_CARD_TYPE, ticketsToBuy,
                                              transactionService);

        verify(mailSender).sendPurchaseConfirmation(A_TRANSACTION_ID);
    }
}
