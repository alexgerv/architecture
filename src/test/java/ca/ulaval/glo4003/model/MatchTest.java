package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mock;

public class MatchTest {

    private static final int INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION = 4;
    private static final int INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION = 6;
    private static final int INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION
                                                                   + INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION;
    MatchBuilder matchBuilder = new MatchBuilder();
    private final String A_SPORT = "aSport";
    private final String A_HOME_TEAM = "HomeTeam";
    private final String A_VISITOR_TEAM = "VisitorTeam";
    private final String A_VENUE = "Venue";
    private final String FIRST_SECTION_NAME = "Section A";
    private final String SECOND_SECTION_NAME = "Section B";
    private final Date A_DATE = new Date();

    @Mock
    private Ticket aTicket;

    private Match aMatch = matchBuilder.setSport(A_SPORT).setDate(A_DATE).setHomeTeam(A_HOME_TEAM)
                                       .setVisitorTeam(A_VISITOR_TEAM).setVenue(A_VENUE)
                                       .createSection(FIRST_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION)
                                       .createSection(SECOND_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION).build();

    @Test
    public void canTellItsTotalNumberOfAvalaibleTickets() {
        int numberOfAvalaibleTickets = aMatch.getTotatNumberOfAvailableTickets();
        assertEquals(numberOfAvalaibleTickets, INITIAL_NUMBER_OF_AVALAIBLE_TICKETS);
    }
}
