package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchRepository;
import ca.ulaval.glo4003.domain.match.NoAvailableTicketsException;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.CreditCardViewModel;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class CartControllerTest {

    private static final int ERROR_CODE = 500;
    private static final String A_SECTION_NAME = "A_SECTION_NAME";
    private static final String A_DATE = "02/02/2013";
    private static final String A_VENUE = "A_VENUE";
    private static final int A_QUANTITY = 2;

    @Mock
    Match aMatch;
    @Mock
    HttpServletResponse aResponse;
    @Mock
    Model model;
    @Mock
    TransactionService transactionService;
    @Mock
    TransactionManager transactionManager;
    @Mock
    ShoppingCart shoppingCart;
    @Mock
    SectionViewConverter sectionConverter;
    @Mock
    MatchRepository matchRepository;
    @Mock
    CreditCardViewModel creditCardViewModel;

    private CartController cartController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartController = new CartController(transactionService,
                                            transactionManager,
                                            shoppingCart,
                                            sectionConverter,
                                            matchRepository);
    }

    @Test
    public void canGetCart() {
        assertEquals("cart", cartController.cart(model));
    }

    @Test
    public void theCartIsAddedToTheModel() {
        HashMap<Section, List<Ticket>> cartContent = new HashMap<Section, List<Ticket>>();
        List<SectionViewModel> sectionViewModel = new ArrayList<SectionViewModel>();
        doReturn(cartContent).when(shoppingCart).getCartContent();
        doReturn(sectionViewModel).when(sectionConverter).convert(cartContent);

        cartController.cart(model);

        verify(model).addAttribute("cartContent", sectionViewModel);
    }

    @Test
    public void cartIsEmptiedWhenEmptyCartIsCakked() throws IOException {
        cartController.emptyCart(model);
        verify(shoppingCart).empty();
    }

    @Test
    public void changingTicketQuantityChangesCartTicketQuantity() throws IOException {
        doReturn(aMatch).when(matchRepository).getMatchByIdentifier(anyString());
        cartController.changeQuantity(A_VENUE, A_DATE, A_SECTION_NAME, A_QUANTITY, model, aResponse);
        verify(shoppingCart).changeTicketsQuantity(aMatch, A_SECTION_NAME, A_QUANTITY);
    }

    @Test
    public void anErrorCodeIsReturnedWhenTryingToChangeQuantityWhenThereAreNotEnoughTicketsAvailable() throws IOException {
        doReturn(aMatch).when(matchRepository).getMatchByIdentifier(anyString());
        doThrow(NoAvailableTicketsException.class).when(shoppingCart).changeTicketsQuantity(aMatch,
                                                                                            A_SECTION_NAME,
                                                                                            A_QUANTITY);

        cartController.changeQuantity(A_VENUE, A_DATE, A_SECTION_NAME, A_QUANTITY, model, aResponse);

        verify(aResponse).sendError(ERROR_CODE);
    }

    @Test
    public void removingASectionRemovesTheSectionFromTheCart() throws IOException {
        doReturn(aMatch).when(matchRepository).getMatchByIdentifier(anyString());
        cartController.removeATicketFromCart(A_VENUE, A_DATE, A_SECTION_NAME, model, aResponse);
        verify(shoppingCart).removeSectionFromCart(aMatch, A_SECTION_NAME);
    }

    @Test
    public void test() throws IOException {
        doReturn(aMatch).when(matchRepository).getMatchByIdentifier(anyString());
        cartController.addTicketsToCart(A_VENUE, A_DATE, A_SECTION_NAME, A_QUANTITY, model);
        verify(shoppingCart).addTicketsQuantity(aMatch, A_SECTION_NAME, A_QUANTITY);
    }

    @Test
    public void checkoutReturnsReviewPage() {
        assertEquals("ticketPurchaseReview", cartController.checkout(model, creditCardViewModel));
    }
}
