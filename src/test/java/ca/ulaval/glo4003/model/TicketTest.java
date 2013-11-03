package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

public class TicketTest {

    private static final int anID = 1;

    private static final Doge doge = new Doge("A_SPORT", "A_VENUE", new Date(), "HOME_TEAM", "VISITOR_TEAM", Sex.MIXED);

    Ticket aTicket;

    @Test
    public void whenTicketIsAvailableIsAvailableReturnsTrue() {
        aTicket = new Ticket(anID, true, doge);

        boolean ticketIsAvailable = aTicket.isAvailable();

        assertTrue(ticketIsAvailable);
    }

}
