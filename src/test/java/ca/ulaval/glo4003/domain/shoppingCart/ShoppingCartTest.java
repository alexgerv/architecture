package ca.ulaval.glo4003.domain.shoppingCart;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.Ticket;

public class ShoppingCartTest {

    private static final int A_TICKET_QUANTITY = 1;
    private static final String A_SECTION_NAME = "A_SECTION_NAME";

    @Mock
    Match aMatch;

    @Mock
    Ticket aTicket;

    List<Ticket> tickets = new ArrayList<Ticket>();

    ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tickets.add(aTicket);
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void getTicketListFromMatch() {
        shoppingCart.addTickets(aMatch, A_TICKET_QUANTITY, A_SECTION_NAME);
        Mockito.verify(aMatch).getTickets(A_TICKET_QUANTITY, A_SECTION_NAME);
    }

    @Test
    public void canGetAddedTickets() {
        Mockito.when(aMatch.getTickets(A_TICKET_QUANTITY, A_SECTION_NAME)).thenReturn(tickets);
        shoppingCart.addTickets(aMatch, A_TICKET_QUANTITY, A_SECTION_NAME);

        List<Ticket> cartTickets = shoppingCart.getTickets();
        assertEquals(cartTickets, tickets);
    }
}
