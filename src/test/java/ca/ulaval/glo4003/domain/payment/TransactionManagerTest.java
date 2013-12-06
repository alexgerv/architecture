package ca.ulaval.glo4003.domain.payment;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.service.mailsender.MailSender;

public class TransactionManagerTest {

    private static final long A_CREDIT_CARD_NUMBER = 999999999;
    private static final String A_CREDIT_CARD_TYPE = "VASI";

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

    @InjectMocks
    private TransactionManager transactionManager;

    private List<Ticket> ticketsToBuy = Arrays.asList(ticket1, ticket2);

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionManager = new TransactionManager(mailSender, matchRepository);
    }

    @Test
    public void whenProcessingATransactionTheTransactionIDIsReturned() throws InvalidCreditCardException {
        long transactionID = transactionManager.processTransaction(A_CREDIT_CARD_NUMBER,
                                                                   A_CREDIT_CARD_TYPE,
                                                                   ticketsToBuy,
                                                                   transactionService);
        assertNotNull(transactionID);
    }
}
