package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class MatchTest {

    private static final String FIRST_SECTION_NAME = "A";
    private static final String SECCOND_SECTION_NAME = "B";
    private static final int INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION = 4;
    private static final int INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION = 6;
    private static final int INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION
                                                                   + INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION;
    private static final String A_SPORT = "aSport";
    private static final String A_HOME_TEAM = "HomeTeam";
    private static final String A_VISITOR_TEAM = "VisitorTeam";
    private static final String A_VENUE = "Venue";
    private static final Date A_DATE = new Date();

    private MatchBuilder matchBuilder = new MatchBuilder();
    private Match aMatch;

    @Mock
    private Ticket aTicket;

    @Before
    public void setUp() {
        aMatch = matchBuilder.setSport(A_SPORT)
                             .setDate(A_DATE)
                             .setHomeTeam(A_HOME_TEAM)
                             .setVisitorTeam(A_VISITOR_TEAM)
                             .setVenue(A_VENUE)
                             .createSection(FIRST_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION)
                             .createSection(SECCOND_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION)
                             .build();
    }

    @Test
    public void canTellItsTotalNumberOfAvalaibleTickets() {
        int numberOfAvalaibleTickets = aMatch.getTotalNumberOfAvailableTickets();
        assertEquals(INITIAL_NUMBER_OF_AVALAIBLE_TICKETS, numberOfAvalaibleTickets);
    }
    
    @Test
    public void canAddATicketToASection() {
        aMatch.addTicket(FIRST_SECTION_NAME);
        int newTicketCount = aMatch.getAvailableTicketsBySection().get(FIRST_SECTION_NAME);
        assertEquals(INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION + 1, newTicketCount);
    }
    
    @Test
    public void canRemoveATicketFromASection(){
        aMatch.removeTicket(SECCOND_SECTION_NAME);
        int newTicketCount = aMatch.getAvailableTicketsBySection().get(SECCOND_SECTION_NAME);
        assertEquals(INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION - 1, newTicketCount);
    }
    
//    @Test
//    public void 
}
