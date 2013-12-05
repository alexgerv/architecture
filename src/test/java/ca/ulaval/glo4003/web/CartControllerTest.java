package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.SectionViewConverter;
import ca.ulaval.glo4003.web.viewmodels.SectionViewModel;

public class CartControllerTest {

    private static final String A_TICKET_ID_STR = "0";
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

    private CartController cartController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartController = new CartController(transactionService, transactionManager, shoppingCart, sectionConverter);
    }

    @Test
    public void canGetCart() {
        assertEquals("cart", cartController.cart(model));
    }

    @Test
    public void test() {
        HashMap<Section, Integer> cartContent = new HashMap<Section, Integer>();
        List<SectionViewModel> sectionViewModel = new ArrayList<SectionViewModel>();
        doReturn(cartContent).when(shoppingCart).getCartContent();
        doReturn(sectionViewModel).when(sectionConverter).convert(cartContent);
        cartController.cart(model);
        verify(model).addAttribute("cartContent", sectionViewModel);
    }

    @Test
    public void removeTicketFromCartRemovesATicketFromTheShoppingCart() {
        cartController.removeATicketFromCart(model, A_TICKET_ID_STR);
        verify(shoppingCart).remove(Integer.parseInt(A_TICKET_ID_STR));

    }

}
