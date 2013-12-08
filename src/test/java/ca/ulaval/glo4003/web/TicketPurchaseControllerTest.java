package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

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
import ca.ulaval.glo4003.domain.payment.InvalidCreditCardException;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class TicketPurchaseControllerTest {

    private static final String SECTION_DETAIL = "sectionDetails";
    private static final String A_VENUE = "Stade Telus";
    private static final String A_DATE = "09/09/2013";
    private static final String MATCH_IDENTIFIER = A_VENUE + "/" + A_DATE;
    private static final String A_SECTION_NAME = "A";
    private static final int A_NUMBER_OF_TICKET_TO_BUY = 10;
    private static final String SECTION_IDENTIFIER = "sections";
    private static final String RECEIPT_PAGE = "ticketPurchaseReceipt";
    private static final Object NOT_ENOUGH_TICKETS_MESSAGE = "There are not enough available tickets";

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
    ShoppingCart shoppingCart;

    ArrayList<SectionViewModel> expectedSectionViewModel = new ArrayList<SectionViewModel>();

    @InjectMocks
    private TicketPurchaseController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedSectionViewModel.add(sectionViewModel);
        controller = new TicketPurchaseController(matchRepository,
                                                  sectionConverter,
                                                  transactionManager,
                                                  transactionService,
                                                  shoppingCart);
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doReturn(section).when(match).getSectionByName(A_SECTION_NAME);
        doReturn(sectionViewModel).when(sectionConverter).convert(section);
    }

    @Test
    public void whenReviewingAPurchaseTheSectionInformationsArePassedToTheView() {
        controller.reviewSelectedTicketsForSection(A_VENUE,
                                                   A_DATE,
                                                   A_SECTION_NAME,
                                                   A_NUMBER_OF_TICKET_TO_BUY,
                                                   model,
                                                   creditCardViewModel);
        verify(model, times(1)).addAttribute(SECTION_IDENTIFIER, expectedSectionViewModel);
    }

    @Test
    public void whenPurchasingTicketsThatAreAvailableWeAreRedirectedToTheReceiptPage() {
        assertEquals(RECEIPT_PAGE, controller.purchaseSelectedTicketsForSection(A_VENUE,
                                                                                A_DATE,
                                                                                A_SECTION_NAME,
                                                                                A_NUMBER_OF_TICKET_TO_BUY,
                                                                                model,
                                                                                creditCardViewModel));
    }

    @Test(expected = NoAvailableTicketsException.class)
    public void whenPurchasingTicketsThatAreNotAvailableWeAreRedirectedToTheSectionDetailsView() {
        doThrow(NoAvailableTicketsException.class).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        assertEquals("sectionDetails", controller.purchaseSelectedTicketsForSection(A_VENUE,
                                                                                    A_DATE,
                                                                                    A_SECTION_NAME,
                                                                                    A_NUMBER_OF_TICKET_TO_BUY,
                                                                                    model,
                                                                                    creditCardViewModel));
    }

    @Test
    public void aMessageIsAddedToTheModelWhenBuyMoreThanAvailableTickets() {
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doThrow(NoAvailableTicketsException.class).when(match)
                                                  .reserveTickets(A_NUMBER_OF_TICKET_TO_BUY, A_SECTION_NAME);

        controller.purchaseSelectedTicketsForSection(A_VENUE,
                                                     A_DATE,
                                                     A_SECTION_NAME,
                                                     A_NUMBER_OF_TICKET_TO_BUY,
                                                     model,
                                                     creditCardViewModel);

        verify(model).addAttribute("message", NOT_ENOUGH_TICKETS_MESSAGE);
    }

    @Test
    public void aMessageIsAddedToTheModelWhenTheCreditCardIsInvalid() throws InvalidCreditCardException {
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doThrow(InvalidCreditCardException.class).when(transactionManager)
                                                 .processTransaction(anyLong(),
                                                                     anyString(),
                                                                     anyList(),
                                                                     (TransactionService) anyObject());

        controller.purchaseSelectedTicketsForSection(A_VENUE,
                                                     A_DATE,
                                                     A_SECTION_NAME,
                                                     A_NUMBER_OF_TICKET_TO_BUY,
                                                     model,
                                                     creditCardViewModel);
        assertEquals(SECTION_DETAIL, controller.purchaseSelectedTicketsForSection(A_VENUE,
                                                                                  A_DATE,
                                                                                  A_SECTION_NAME,
                                                                                  A_NUMBER_OF_TICKET_TO_BUY,
                                                                                  model,
                                                                                  creditCardViewModel));
    }

    @Test
    public void whenPurchasingCartThatAreAvailableWeAreRedirectedToTheHomeView() {
        assertEquals(RECEIPT_PAGE, controller.purchaseCartContent(model, creditCardViewModel));
    }

    @Test
    public void aMessageIsAddedToTheModelWhenTheCreditCardIsInvalidFromCart() throws InvalidCreditCardException {
        doReturn(match).when(matchRepository).getMatchByIdentifier(MATCH_IDENTIFIER);
        doThrow(InvalidCreditCardException.class).when(transactionManager)
                                                 .processTransaction(anyLong(),
                                                                     anyString(),
                                                                     anyList(),
                                                                     (TransactionService) anyObject());

        controller.purchaseCartContent(model, creditCardViewModel);

        assertEquals(SECTION_DETAIL, controller.purchaseSelectedTicketsForSection(A_VENUE,
                                                                                  A_DATE,
                                                                                  A_SECTION_NAME,
                                                                                  A_NUMBER_OF_TICKET_TO_BUY,
                                                                                  model,
                                                                                  creditCardViewModel));
    }

}
