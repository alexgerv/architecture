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

    private static final Sex A_SEX = Sex.MEN;
    private static final String A_SPORT = "aSport";
    private static final String A_HOME_TEAM = "HomeTeam";
    private static final String A_VISITOR_TEAM = "VisitorTeam";
    private static final String A_VENUE = "Venue";
    private static final String FIRST_SECTION_NAME = "Section A";
    private static final String SECOND_SECTION_NAME = "Section B";
    private static final Date A_DATE = new Date();

    private static int[] avalaibleTicketsInFirstSection = { 1, 2, 3, 4 };
    private static int[] unavalaibleTicketsInFirstSection = { 11, 12, 13, 14 };
    private static int[] avalaibleTicketsInSecondSection = { 5, 6, 7 };
    private static int[] unavalaibleTicketsInSecondSection = { 15 };

    private static Match aMatch;
    private static Map<Integer, Boolean> firstSection = new HashMap<Integer, Boolean>();
    private static Map<Integer, Boolean> secondSection = new HashMap<Integer, Boolean>();
    private static Map<String, Map<Integer, Boolean>> tickets = new HashMap<String, Map<Integer, Boolean>>();

    private static final int INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = avalaibleTicketsInFirstSection.length
                                                                   + avalaibleTicketsInSecondSection.length;

    @Before
    public void setup() {
        initializeFirstSection();
        initializeSecondSection();
        tickets.put(FIRST_SECTION_NAME, firstSection);
        tickets.put(SECOND_SECTION_NAME, secondSection);
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
        assertEquals(aMatch.getTicketsBySection(), tickets);
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

    private void initializeFirstSection() {
        for (int i = 0; i < avalaibleTicketsInFirstSection.length; i++) {
            firstSection.put(avalaibleTicketsInFirstSection[i], true);
        }
        for (int i = 0; i < unavalaibleTicketsInFirstSection.length; i++) {
            firstSection.put(unavalaibleTicketsInFirstSection[i], false);
        }
    }

    private void initializeSecondSection() {
        for (int i = 0; i < avalaibleTicketsInSecondSection.length; i++) {
            secondSection.put(avalaibleTicketsInSecondSection[i], true);
        }
        for (int i = 0; i < unavalaibleTicketsInSecondSection.length; i++) {
            secondSection.put(unavalaibleTicketsInSecondSection[i], false);
        }
    }
}
