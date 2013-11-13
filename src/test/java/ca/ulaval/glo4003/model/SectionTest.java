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

    private static final int A_QUANTITY_OF_AVAILABLE_TICKETS = 1;

    private static final int MORE_THAN_AVAILABLE_TICKETS = 2;

    private static final int A_NEGATIVE_NUMBER = -1;

    private Section aSection;

    private List<Ticket> tickets = new ArrayList<Ticket>();

    @Mock
    private MatchInformation matchInformations;
    @Mock
    private Ticket anAvailableTicket;
    @Mock
    private Ticket anUnavailableTicket;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        tickets.add(anAvailableTicket);
        tickets.add(anUnavailableTicket);

        aSection = new Section(A_SECTION_NAME, tickets, matchInformations, SECTION_PRICE, AN_ADMISSION_TYPE);
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
        verify(matchInformations).getFormatedDate();
    }

    @Test
    public void canGetHomeTeam() {
        aSection.getHomeTeam();
        verify(matchInformations).getHomeTeam();
    }

    @Test
    public void canGetSex() {
        aSection.getSex();
        verify(matchInformations).getSex();
    }

    @Test
    public void canGetSport() {
        aSection.getSport();
        verify(matchInformations).getSport();
    }

    @Test
    public void canGetVenue() {
        aSection.getVenue();
        verify(matchInformations).getVenue();
    }

    @Test
    public void canGetVisitorTeam() {
        aSection.getVisitorTeam();
        verify(matchInformations).getVisitorTeam();
    }

    @Test
    public void whenBuyingANumberOfTicketsAndTheyAreAvailableTheTicketsAreBought() {
        doReturn(true).when(anAvailableTicket).isAvailable();
        aSection.buyTickets(A_QUANTITY_OF_AVAILABLE_TICKETS);
        verify(anAvailableTicket).buy();
    }

    @Test(expected = NoAvailableTicketsException.class)
    public void whenBuyingMoreThanAvailableTicketsANoAvailableTicketsException() {
        aSection.buyTickets(MORE_THAN_AVAILABLE_TICKETS);
    }

    @Test(expected = InvalidQuantityException.class)
    public void whenBuyingANegativeQuantityOfTicketsAnInvalidQuantityExceptionIsThrown() {
        aSection.buyTickets(A_NEGATIVE_NUMBER);
    }
}
