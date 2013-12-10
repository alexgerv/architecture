package ca.ulaval.glo4003.domain.payment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;

public class TicketPurchaseFacadeTest {

    private static final String A_VENUE = "Stade Telus";
    private static final String A_DATE = "09/09/2013";
    private static final String MATCH_IDENTIFIER = A_VENUE + "/" + A_DATE;
    private static final String A_SECTION_NAME = "A";
    private static final int A_NUMBER_OF_TICKET_TO_BUY = 10;
    private static final float SECTION_TICKET_PRICE = 50.0f;
    private static final String NOT_ENOUGH_TICKETS_MESSAGE = "There are not enough available tickets";
    private static final String CREDIT_CARD_TYPE = "VASI";
    private static final long CREDIT_CARD_NUMBER = 999888777;

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Match match;
    @Mock
    private Section section;
    @Mock
    private TransactionService transactionService;
    @Mock
    private Ticket ticketToBuy;
    @Mock
    ShoppingCart shoppingCart;

    List<Ticket> ticketsToBuy;
    Map<Section, List<Ticket>> cartContent = new HashMap<Section, List<Ticket>>();

    @InjectMocks
    private TicketPurchaseFacade facade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        facade = new TicketPurchaseFacade(matchRepository, transactionManager, transactionService);
        ticketsToBuy = Arrays.asList(ticketToBuy);
        cartContent.put(section, ticketsToBuy);
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doReturn(section).when(match).getSectionByName(A_SECTION_NAME);
    }

    @Test
    public void desiredSectionIsRetrievedWhenAsked() {
        Section retrievedSection = facade.retriveSection(A_VENUE, A_DATE, A_SECTION_NAME);
        assertEquals(section, retrievedSection);
    }

    @Test
    public void canReturnTheRightPurchaseTotal() {
        float purchaseTotal = facade.computePurchaseTotal(SECTION_TICKET_PRICE, A_NUMBER_OF_TICKET_TO_BUY);
        assertTrue(purchaseTotal == SECTION_TICKET_PRICE * A_NUMBER_OF_TICKET_TO_BUY);
    }

    @Test
    public void theTransactionIsProcessedWhenTheTicketsAreAvailable() throws InvalidCreditCardException {
        doReturn(ticketsToBuy).when(match).reserveTickets(A_NUMBER_OF_TICKET_TO_BUY, A_SECTION_NAME);
        facade.processPurchase(A_VENUE,
                               A_DATE,
                               A_SECTION_NAME,
                               A_NUMBER_OF_TICKET_TO_BUY,
                               CREDIT_CARD_NUMBER,
                               CREDIT_CARD_TYPE);
        verify(transactionManager).processTransaction(CREDIT_CARD_NUMBER,
                                                      CREDIT_CARD_TYPE,
                                                      ticketsToBuy,
                                                      transactionService);
    }

    @Test
    public void theCartTransactionIsProcessedWhenTheTicketsAreAvailable() throws InvalidCreditCardException {
        doReturn(cartContent).when(shoppingCart).getCartContent();
        facade.processCartPurchase(shoppingCart, CREDIT_CARD_NUMBER, CREDIT_CARD_TYPE);
        verify(transactionManager).processTransaction(CREDIT_CARD_NUMBER,
                                                      CREDIT_CARD_TYPE,
                                                      ticketsToBuy,
                                                      transactionService);
    }
}
