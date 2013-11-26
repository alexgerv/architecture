package ca.ulaval.glo4003.domain.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TicketTest {

    private static final AdmissionType AN_ADMISSION_TYPE = AdmissionType.GENERAL;
    private static final float SECTION_PRICE = 10.5f;
    private static final int anID = 1;

    @Mock
    private MatchInformation matchInformation;

    Ticket aTicket;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aTicket = new Ticket(anID, true, matchInformation, SECTION_PRICE, AN_ADMISSION_TYPE);
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

    @Test
    public void canGetPrice() {
        float sectionPrice = aTicket.getPrice();
        assertEquals(SECTION_PRICE, sectionPrice, 0.001);
    }

    @Test
    public void canGetAdmissionType() {
        AdmissionType admissionType = aTicket.getAdmissionType();
        assertEquals(AN_ADMISSION_TYPE, admissionType);
    }

    @Test
    public void canGetDate() {
        aTicket.getDate();
        verify(matchInformation).getFormatedDate();
    }

    @Test
    public void canGetHomeTeam() {
        aTicket.getHomeTeam();
        verify(matchInformation).getHomeTeam();
    }

    @Test
    public void canGetSex() {
        aTicket.getSex();
        verify(matchInformation).getSex();
    }

    @Test
    public void canGetSport() {
        aTicket.getSport();
        verify(matchInformation).getSport();
    }

    @Test
    public void canGetVenue() {
        aTicket.getVenue();
        verify(matchInformation).getVenue();
    }

    @Test
    public void canGetVisitorTeam() {
        aTicket.getVisitorTeam();
        verify(matchInformation).getVisitorTeam();
    }

    @Test
    public void canGetID() {
        int id = aTicket.getID();
        assertEquals(id, anID);
    }
}
