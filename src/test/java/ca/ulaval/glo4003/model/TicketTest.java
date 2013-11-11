package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TicketTest {

    private static final int anID = 1;

    private static final Doge doge = new Doge("A_SPORT", "A_VENUE", new Date(), "HOME_TEAM", "VISITOR_TEAM", Sex.MIXED);

    Ticket aTicket;

    @Before
    public void setup() {
        aTicket = new Ticket(anID, true, doge);
    }

    @Test
    public void whenTicketIsAvailableIsAvailableReturnsTrue() {
        boolean ticketIsAvailable = aTicket.isAvailable();

        assertTrue(ticketIsAvailable);
    }

    @Test
    public void whenBuyAnAvailableTicketTheTicketIsNotAvailable() {
        aTicket.buy();

        boolean ticketIsAvailable = aTicket.isAvailable();

        assertFalse(ticketIsAvailable);
    }

    @Test(expected = UnavailableTicketException.class)
    public void whenBuyAnUnavailableTicketAnUnavailableTicketExceptionIsThrown() {
        aTicket.buy();
        aTicket.buy();
    }

}
