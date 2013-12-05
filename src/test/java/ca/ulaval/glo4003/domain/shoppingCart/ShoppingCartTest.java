package ca.ulaval.glo4003.domain.shoppingCart;

import static org.junit.Assert.assertEquals;

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

    private static final int A_TICKET_QUANTITY = 1;
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
        tickets.add(aTicket);
        shoppingCart = new ShoppingCart();
    }

    @Test
    public void canGetCartContent() {
        Mockito.when(aMatch.getSectionByName(A_SECTION_NAME)).thenReturn(aSection);
        shoppingCart.addTickets(aMatch, A_TICKET_QUANTITY, A_SECTION_NAME);
        Map<Section, Integer> expectedCartContent = new HashMap<Section, Integer>();
        expectedCartContent.put(aSection, A_TICKET_QUANTITY);
        Map<Section, Integer> cartContent = shoppingCart.getCartContent();

        assertEquals(cartContent, expectedCartContent);
    }
}
