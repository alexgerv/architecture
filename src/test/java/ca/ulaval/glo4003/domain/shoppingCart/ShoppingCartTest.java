package ca.ulaval.glo4003.domain.shoppingCart;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.Section;
import ca.ulaval.glo4003.domain.match.Ticket;

public class ShoppingCartTest {

    private static final int A_TICKET_QUANTITY = 5;
    private static final int A_GREATER_TICKET_QUANTITY = A_TICKET_QUANTITY * 2;
    private static final int A_LOWER_TICKET_QUANTITY = 2;
    private static final String A_SECTION_NAME = "A_SECTION_NAME";

    @Mock
    Match aMatch;

    @Mock
    Ticket aTicket;

    @Mock
    Section aSection;

    List<Ticket> tickets = new ArrayList<Ticket>();

    ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        for (int i = 0; i < A_TICKET_QUANTITY; i++) {
            tickets.add(aTicket);
        }
        shoppingCart = new ShoppingCart();

        Mockito.when(aMatch.reserveTickets(A_TICKET_QUANTITY, A_SECTION_NAME)).thenReturn(tickets);
        Mockito.when(aMatch.getSectionByName(A_SECTION_NAME)).thenReturn(aSection);
        shoppingCart.addTicketsQuantity(aMatch, A_SECTION_NAME, A_TICKET_QUANTITY);
    }

    @Test
    public void canGetCartContent() {
        Map<Section, List<Ticket>> cartContent = shoppingCart.getCartContent();

        Map<Section, List<Ticket>> expectedCartContent = new HashMap<Section, List<Ticket>>();
        expectedCartContent.put(aSection, tickets);
        assertEquals(cartContent, expectedCartContent);
    }

    @Test
    public void whenAddingTicketsQuantityTheExpectedNumberIsAdded() {
        shoppingCart.addTicketsQuantity(aMatch, A_SECTION_NAME, A_TICKET_QUANTITY);
        Map<Section, List<Ticket>> cartContent = shoppingCart.getCartContent();

        Map<Section, List<Ticket>> expectedCartContent = new HashMap<Section, List<Ticket>>();
        tickets.addAll(tickets);
        expectedCartContent.put(aSection, tickets);
        assertEquals(cartContent, expectedCartContent);
    }

    @Test
    public void whenAddingTicketsQuantityTheExpectedNumberIsReserved() {
        shoppingCart.addTicketsQuantity(aMatch, A_SECTION_NAME, A_TICKET_QUANTITY);

        verify(aMatch, atLeast(1)).reserveTickets(A_GREATER_TICKET_QUANTITY - A_TICKET_QUANTITY, A_SECTION_NAME);
    }

    @Test
    public void whenChangingTicketsQuantityByAddingTicketsTheExpectedNumberIsAdded() {
        shoppingCart.changeTicketsQuantity(aMatch, A_SECTION_NAME, A_GREATER_TICKET_QUANTITY);
        Map<Section, List<Ticket>> cartContent = shoppingCart.getCartContent();

        Map<Section, List<Ticket>> expectedCartContent = new HashMap<Section, List<Ticket>>();
        tickets.addAll(tickets);
        expectedCartContent.put(aSection, tickets);
        assertEquals(cartContent, expectedCartContent);
    }

    @Test
    public void whenChangingTicketsQuantityByAddingTicketsTheExpectedNumberIsReserved() {
        shoppingCart.changeTicketsQuantity(aMatch, A_SECTION_NAME, A_GREATER_TICKET_QUANTITY);

        verify(aMatch, atLeast(1)).reserveTickets(A_GREATER_TICKET_QUANTITY - A_TICKET_QUANTITY, A_SECTION_NAME);
    }

    @Test
    public void whenChangingTicketsQuantityByRemovingTicketsTheExpectedNumberIsRemoved() {
        shoppingCart.changeTicketsQuantity(aMatch, A_SECTION_NAME, A_LOWER_TICKET_QUANTITY);

        verify(aTicket, times(A_TICKET_QUANTITY - A_LOWER_TICKET_QUANTITY)).free();
    }
}
