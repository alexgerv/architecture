package ca.ulaval.glo4003.domain.payment;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
    private Model model;
    @Mock
    private SectionViewConverter sectionConverter;
    @Mock
    private SectionViewModel sectionViewModel;
    @Mock
    private CreditCardViewModel creditCardViewModel;
    @Mock
    private TransactionService transactionService;
    @Mock
    private Ticket ticketToBuy;
    @Mock
    ShoppingCart shoppingCart;

    ArrayList<SectionViewModel> expectedSectionViewModel = new ArrayList<SectionViewModel>();
    List<Ticket> ticketsToBuy;
    Map<Section, List<Ticket>> cartContent = new HashMap<Section, List<Ticket>>();

    @InjectMocks
    private TicketPurchaseFacade facade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedSectionViewModel.add(sectionViewModel);
        facade = new TicketPurchaseFacade(sectionConverter, matchRepository, transactionManager, transactionService);
        ticketsToBuy = Arrays.asList(ticketToBuy);
        cartContent.put(section, ticketsToBuy);
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doReturn(section).when(match).getSectionByName(A_SECTION_NAME);
        doReturn(sectionViewModel).when(sectionConverter).convert(section);
    }

    @Test
    public void whenAskedForTheSectionViewModelTheQuantityIsAddedToTheViewModel() {
        facade.retriveSectionInformations(A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY);

        verify(sectionViewModel).setPurchaseQuantity(A_NUMBER_OF_TICKET_TO_BUY);
    }

    @Test
    public void canReturnTheRightPurchaseTotal() {
        doReturn(SECTION_TICKET_PRICE).when(sectionViewModel).getPrice();
        float purchaseTotal = facade.computePurchaseTotal(sectionViewModel, A_NUMBER_OF_TICKET_TO_BUY);
        assertTrue(purchaseTotal == SECTION_TICKET_PRICE * A_NUMBER_OF_TICKET_TO_BUY);
    }

    @Test
    public void theTransactionIsProcessedWhenTheTicketsAreAvailable() throws InvalidCreditCardException {
        doReturn(ticketsToBuy).when(match).reserveTickets(A_NUMBER_OF_TICKET_TO_BUY, A_SECTION_NAME);
        doReturn(CREDIT_CARD_NUMBER).when(creditCardViewModel).getNumber();
        doReturn(CREDIT_CARD_TYPE).when(creditCardViewModel).getType();
        facade.processPurchase(model, A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, creditCardViewModel);
        verify(transactionManager).processTransaction(CREDIT_CARD_NUMBER, CREDIT_CARD_TYPE, ticketsToBuy,
                                                      transactionService);
    }

    @Test
    public void aMessageIsAddedToTheModelWhenBuyMoreThanAvailableTickets() {
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doThrow(NoAvailableTicketsException.class).when(match)
                                                  .reserveTickets(A_NUMBER_OF_TICKET_TO_BUY, A_SECTION_NAME);

        facade.processPurchase(model, A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, creditCardViewModel);

        verify(model).addAttribute("message", NOT_ENOUGH_TICKETS_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void aMessageIsAddedToTheModelWhenTheCreditCardIsInvalid() throws InvalidCreditCardException {
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doThrow(InvalidCreditCardException.class).when(transactionManager)
                                                 .processTransaction(anyLong(), anyString(), anyList(),
                                                                     (TransactionService) anyObject());

        facade.processPurchase(model, A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, creditCardViewModel);

        verify(model).addAttribute(anyString(), anyString());
    }

    @Test
    public void theCartTransactionIsProcessedWhenTheTicketsAreAvailable() throws InvalidCreditCardException {
        doReturn(cartContent).when(shoppingCart).getCartContent();
        doReturn(CREDIT_CARD_NUMBER).when(creditCardViewModel).getNumber();
        doReturn(CREDIT_CARD_TYPE).when(creditCardViewModel).getType();
        facade.processCartPurchase(model, shoppingCart, creditCardViewModel);
        verify(transactionManager).processTransaction(CREDIT_CARD_NUMBER, CREDIT_CARD_TYPE, ticketsToBuy,
                                                      transactionService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void aMessageIsAddedToTheModelWhenTheCreditCardIsInvalidFromCart() throws InvalidCreditCardException {
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doThrow(InvalidCreditCardException.class).when(transactionManager)
                                                 .processTransaction(anyLong(), anyString(), anyList(),
                                                                     (TransactionService) anyObject());

        facade.processCartPurchase(model, shoppingCart, creditCardViewModel);

        verify(model).addAttribute(anyString(), anyString());
    }
}
