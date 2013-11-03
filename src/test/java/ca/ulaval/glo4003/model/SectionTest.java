package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SectionTest {

    private static final String A_SECTION_NAME = "A";

    private static final Doge doge = new Doge("A_SPORT", "A_VENUE", new Date(), "HOME_TEAM", "VISITOR_TEAM", Sex.MIXED);

    private static final float SECTION_PRICE = 10.5f;

    private static final AdmissionType AN_ADMISSION_TYPE = AdmissionType.GENERAL;

    private Section aSection;

    private List<Ticket> tickets = new ArrayList<Ticket>();

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

}
