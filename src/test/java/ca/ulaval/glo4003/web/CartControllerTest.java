package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.payment.TransactionManager;
import ca.ulaval.glo4003.domain.payment.TransactionService;
import ca.ulaval.glo4003.domain.shoppingCart.ShoppingCart;
import ca.ulaval.glo4003.web.converters.TicketViewConverter;
import ca.ulaval.glo4003.web.viewmodels.TicketViewModel;

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
    TicketViewConverter ticketConverter;

    private CartController cartController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        cartController = new CartController(transactionService, transactionManager, shoppingCart, ticketConverter);
    }

    @Test
    public void canGetCart() {
        assertEquals("cart", cartController.cart(model));
    }

    @Test
    public void test() {
        List<Ticket> tickets = new ArrayList<Ticket>();
        List<TicketViewModel> ticketsViewModel = new ArrayList<TicketViewModel>();
        doReturn(tickets).when(shoppingCart).getTickets();
        doReturn(ticketsViewModel).when(ticketConverter).convert(tickets);
        cartController.cart(model);
        verify(model).addAttribute("tickets", tickets);
    }

    @Test
    public void removeTicketFromCartRemovesATicketFromTheShoppingCart() {
        cartController.removeATicketFromCart(model, A_TICKET_ID_STR);
        verify(shoppingCart).remove(Integer.parseInt(A_TICKET_ID_STR));

    }

}
