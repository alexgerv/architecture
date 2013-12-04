package ca.ulaval.glo4003.domain.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SectionTest {

    private static final String A_SECTION_NAME = "A";

    private static final float SECTION_PRICE = 10.5f;

    private static final AdmissionType AN_ADMISSION_TYPE = AdmissionType.GENERAL;

    private static final int A_QUANTITY_OF_AVAILABLE_TICKETS = 1;

    private static final int MORE_THAN_AVAILABLE_TICKETS = 2;

    private static final int A_NEGATIVE_NUMBER = -1;

    private Section aSection;

    private List<Ticket> tickets = new ArrayList<Ticket>();

    @Mock
    private MatchInformation matchInformation;
    @Mock
    private Ticket anAvailableTicket;
    @Mock
    private Ticket anUnavailableTicket;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        tickets.add(anAvailableTicket);
        tickets.add(anUnavailableTicket);

        aSection = new Section(A_SECTION_NAME, tickets, matchInformation, SECTION_PRICE, AN_ADMISSION_TYPE);
    }

    @Test
    public void numberOfAvailableTicketsReturnsTheRightNumberOfAvailableTickets() {
        doReturn(true).when(anAvailableTicket).isAvailable();
        doReturn(false).when(anUnavailableTicket).isAvailable();
        int numberOfAvailableTickets = aSection.getNumberOfAvailableTickets();

        assertEquals(numberOfAvailableTickets, 1);
    }

    @Test
    public void getNameReturnsRightSectionName() {
        String sectionName = aSection.getName();

        assertEquals(sectionName, A_SECTION_NAME);
    }

    @Test
    public void hasUsernameReturnsTrueWhenUsernamesAreSame() {
        assertTrue(aSection.hasSameName(A_SECTION_NAME));
    }

    @Test
    public void hasUsernameReturnsFalseWhenUsernamesAreSame() {
        assertFalse(aSection.hasSameName(""));
    }

    @Test
    public void canGetName() {
        String sectionName = aSection.getName();
        assertEquals(A_SECTION_NAME, sectionName);
    }

    @Test
    public void canGetPrice() {
        float sectionPrice = aSection.getPrice();
        assertEquals(SECTION_PRICE, sectionPrice, 0.001);
    }

    @Test
    public void canGetAdmissionType() {
        AdmissionType admissionType = aSection.getAdmissionType();
        assertEquals(AN_ADMISSION_TYPE, admissionType);
    }

    @Test
    public void canGetDate() {
        aSection.getDate();
        verify(matchInformation).getFormatedDate();
    }

    @Test
    public void canGetHomeTeam() {
        aSection.getHomeTeam();
        verify(matchInformation).getHomeTeam();
    }

    @Test
    public void canGetSex() {
        aSection.getSex();
        verify(matchInformation).getSex();
    }

    @Test
    public void canGetSport() {
        aSection.getSport();
        verify(matchInformation).getSport();
    }

    @Test
    public void canGetVenue() {
        aSection.getVenue();
        verify(matchInformation).getVenue();
    }

    @Test
    public void canGetVisitorTeam() {
        aSection.getVisitorTeam();
        verify(matchInformation).getVisitorTeam();
    }

    @Test(expected = InvalidQuantityException.class)
    public void anInvalidQuantityExceptionIsThrownIfGettingANegativeNumberOfTickets() {
        aSection.reserveTickets(A_NEGATIVE_NUMBER);
    }

    @Test(expected = NoAvailableTicketsException.class)
    public void whenGettingMoreThanAvailableTicketsANoAvailableTicketsException() {
        aSection.reserveTickets(MORE_THAN_AVAILABLE_TICKETS);
    }

    @Test
    public void reserveTicketsReturnsNewlyReservedTickets() {
        doReturn(true).when(anAvailableTicket).isAvailable();
        List<Ticket> tickets = aSection.reserveTickets(A_QUANTITY_OF_AVAILABLE_TICKETS);
        assertTrue(tickets.contains(anAvailableTicket));
        assertEquals(tickets.size(), A_QUANTITY_OF_AVAILABLE_TICKETS);
    }

    @Test
    public void reserveTicketReservesTickets() {
        doReturn(true).when(anAvailableTicket).isAvailable();
        aSection.reserveTickets(A_QUANTITY_OF_AVAILABLE_TICKETS);
        verify(anAvailableTicket).reserve();
    }
}
