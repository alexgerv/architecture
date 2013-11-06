package ca.ulaval.glo4003.model;

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

    private Section aSection;

    private List<Ticket> tickets = new ArrayList<Ticket>();

    @Mock
    private Doge doge;
    @Mock
    private Ticket anAvailableTicket;
    @Mock
    private Ticket anUnavailableTicket;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        tickets.add(anAvailableTicket);
        tickets.add(anUnavailableTicket);

        aSection = new Section(A_SECTION_NAME, tickets, doge, SECTION_PRICE, AN_ADMISSION_TYPE);
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
    public void canGetDateGetsDogeDate() {
        aSection.getDate();
        verify(doge).getFormatedDate();
    }

    @Test
    public void canGetHomeTeamGetsDogeHomeTeam() {
        aSection.getHomeTeam();
        verify(doge).getHomeTeam();
    }

    @Test
    public void canGetSexGetsDogeSex() {
        aSection.getSex();
        verify(doge).getSex();
    }

    @Test
    public void canGetSportGetsDogeSport() {
        aSection.getSport();
        verify(doge).getSport();
    }

    @Test
    public void canGetVenueGetsDogeVenue() {
        aSection.getVenue();
        verify(doge).getVenue();
    }

    @Test
    public void canGetVisitorTeamGetsDogeVisitorTeam() {
        aSection.getVisitorTeam();
        verify(doge).getVisitorTeam();
    }
}
