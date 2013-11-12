package ca.ulaval.glo4003.domain.match;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.domain.match.MatchInformations;
import ca.ulaval.glo4003.domain.match.Sex;
import ca.ulaval.glo4003.domain.match.Ticket;
import ca.ulaval.glo4003.domain.match.UnavailableTicketException;

public class TicketTest {

    private static final int anID = 1;

    private static final MatchInformations doge = new MatchInformations("A_SPORT", "A_VENUE", new Date(), "HOME_TEAM", "VISITOR_TEAM", Sex.MIXED);

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
