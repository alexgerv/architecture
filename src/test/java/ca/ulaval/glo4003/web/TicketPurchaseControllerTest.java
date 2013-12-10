package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.domain.payment.TicketPurchaseFacade;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class TicketPurchaseControllerTest {

    private static final String SECTION_DETAIL = "sectionDetails";
    private static final String A_VENUE = "Stade Telus";
    private static final String A_DATE = "09/09/2013";
    private static final String A_SECTION_NAME = "A";
    private static final int A_NUMBER_OF_TICKET_TO_BUY = 10;
    private static final String SECTION_IDENTIFIER = "sections";
    private static final String RECEIPT_PAGE = "ticketPurchaseReceipt";

    @Mock
    private Model model;
    @Mock
    private SectionViewConverter sectionConverter;
    @Mock
    private SectionViewModel sectionViewModel;
    @Mock
    private CreditCardViewModel creditCardViewModel;
    @Mock
    private TicketPurchaseFacade ticketPurchaseFacade;
    @Mock
    ShoppingCart shoppingCart;

    ArrayList<SectionViewModel> expectedSectionViewModel = new ArrayList<SectionViewModel>();

    @InjectMocks
    private TicketPurchaseController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedSectionViewModel.add(sectionViewModel);
        controller = new TicketPurchaseController(ticketPurchaseFacade, sectionConverter, shoppingCart);

    }

    @Test
    public void whenReviewingAPurchaseTheSectionInformationsArePassedToTheView() {
        doReturn(sectionViewModel).when(ticketPurchaseFacade).retriveSectionInformations(A_VENUE, A_DATE,
                                                                                         A_SECTION_NAME,
                                                                                         A_NUMBER_OF_TICKET_TO_BUY);
        controller.reviewSelectedTicketsForSection(A_VENUE, A_DATE, A_SECTION_NAME, A_NUMBER_OF_TICKET_TO_BUY, model,
                                                   creditCardViewModel);
        verify(model, times(1)).addAttribute(SECTION_IDENTIFIER, expectedSectionViewModel);
    }

    @Test
    public void whenPurchasingTicketsThatAreAvailableWeAreRedirectedToTheReceiptPage() {
        doReturn(RECEIPT_PAGE).when(ticketPurchaseFacade).processPurchase(model, A_VENUE, A_DATE, A_SECTION_NAME,
                                                                          A_NUMBER_OF_TICKET_TO_BUY,
                                                                          creditCardViewModel);
        assertEquals(RECEIPT_PAGE, controller.purchaseSelectedTicketsForSection(A_VENUE, A_DATE, A_SECTION_NAME,
                                                                                A_NUMBER_OF_TICKET_TO_BUY, model,
                                                                                creditCardViewModel));
    }

    @Test
    public void whenPurchasingTicketsThatAreNotAvailableWeAreRedirectedToTheSectionDetailsView() {
        doReturn(SECTION_DETAIL).when(ticketPurchaseFacade).processPurchase(model, A_VENUE, A_DATE, A_SECTION_NAME,
                                                                            A_NUMBER_OF_TICKET_TO_BUY,
                                                                            creditCardViewModel);
        assertEquals(SECTION_DETAIL, controller.purchaseSelectedTicketsForSection(A_VENUE, A_DATE, A_SECTION_NAME,
                                                                                  A_NUMBER_OF_TICKET_TO_BUY, model,
                                                                                  creditCardViewModel));
    }

    @Test
    public void whenPurchasingCartThatAreAvailableWeAreRedirectedToTheHomeView() {
        doReturn(RECEIPT_PAGE).when(ticketPurchaseFacade).processCartPurchase(model, shoppingCart, creditCardViewModel);
        assertEquals(RECEIPT_PAGE, controller.purchaseCartContent(model, creditCardViewModel));
    }

}
