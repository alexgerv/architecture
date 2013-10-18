package ca.ulaval.glo4003.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;

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

    @Test
    public void canGetTheSportOfAMatch() {
        assertEquals(aMatch.getSport(), A_SPORT);
    }

    @Test
    public void canGetTheVenueOfAMatch() {
        assertEquals(aMatch.getVenue(), A_VENUE);
    }

    @Test
    public void canGetTheHomeTeamOfAMatch() {
        assertEquals(aMatch.getHomeTeam(), A_HOME_TEAM);
    }

    @Test
    public void canGetTheVisitorTeamOfAMatch() {
        assertEquals(aMatch.getVisitorTeam(), A_VISITOR_TEAM);
    }

    @Test
    public void canGetTheSexOfAMatch() {
        assertEquals(aMatch.getSex(), A_SEX);
    }

    @Test
    public void canGetTheDateOfAMatch() {
        assertEquals(aMatch.getDate(), A_DATE);
    }

    @Test
    public void canGetTheAvailableTicketsBySectionOfAMatch() {
        assertEquals(aMatch.getAvailableTicketsBySection(), tickets);
    }

    @Test
    public void canGetTheIdentifierOfAMatch() {
        String formatedDate = DateFormatUtils.format(A_DATE, "yyyy-MM-dd_HH-mm-SS");
        String identifier = A_VENUE + "/" + formatedDate;
        assertEquals(aMatch.getIdentifier(), identifier);
    }

    @Test
    public void canGetASportFilterValue() {
        assertEquals(A_SPORT, aMatch.getFilterValueOfCategory(MatchFilterCategories.SPORT));
    }

    @Test
    public void canGetAVenueFilterValue() {
        assertEquals(A_VENUE, aMatch.getFilterValueOfCategory(MatchFilterCategories.VENUE));
    }

    @Test
    public void canGetADateFilterValue() {
        assertEquals(DateFormatUtils.format(A_DATE, "yyyy-MM-dd_HH-mm-SS"),
                     aMatch.getFilterValueOfCategory(MatchFilterCategories.DATE));
    }

    @Test
    public void canGetAHomeTeamFilterValue() {
        assertEquals(A_HOME_TEAM, aMatch.getFilterValueOfCategory(MatchFilterCategories.HOME_TEAM));
    }

    @Test
    public void canGetAVisitorTeamFilterValue() {
        assertEquals(A_VISITOR_TEAM, aMatch.getFilterValueOfCategory(MatchFilterCategories.VISITOR_TEAM));
    }
}
