package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.searchEngine.Sex;

public class MatchTest {

    private static final int INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION = 4;
    private static final int INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION = 6;
    private static final int INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION
                                                                   + INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION;
    private static final Sex A_SEX = Sex.MEN;
    private final String A_SPORT = "aSport";
    private final String A_HOME_TEAM = "HomeTeam";
    private final String A_VISITOR_TEAM = "VisitorTeam";
    private final String A_VENUE = "Venue";
    private final String FIRST_SECTION_NAME = "Section A";
    private final String SECOND_SECTION_NAME = "Section B";
    private final Date A_DATE = new Date();

    private Match aMatch;
    private Map<String, Integer> tickets = new HashMap<String, Integer>();

    @Before
    public void setup() {
        tickets.put(FIRST_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION);
        tickets.put(SECOND_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION);
        aMatch = new Match(A_SPORT, A_VENUE, A_DATE, A_HOME_TEAM, A_VISITOR_TEAM, A_SEX, tickets);
    }

    @Test
    public void canTellItsTotalNumberOfAvalaibleTickets() {
        int numberOfAvalaibleTickets = aMatch.getTotatNumberOfAvailableTickets();
        assertEquals(numberOfAvalaibleTickets, INITIAL_NUMBER_OF_AVALAIBLE_TICKETS);
    }
}
