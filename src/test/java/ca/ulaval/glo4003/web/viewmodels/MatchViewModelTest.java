package ca.ulaval.glo4003.web.viewmodels;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.model.Sex;

public class MatchViewModelTest {

    private static final Sex A_SEX = Sex.MEN;
    private final String A_MATCH_IDENTIFIER = "Venue/2013/10/31";
    private final String A_SPORT = "aSport";
    private final String A_VENUE = "Venue";
    private final String A_DATE = "2013/10/31";
    private final String A_HOME_TEAM = "HomeTeam";
    private final String A_VISITOR_TEAM = "VisitorTeam";
    private final String FIRST_SECTION_NAME = "Section A";
    private final String SECOND_SECTION_NAME = "Section B";

    private MatchViewModel aMatchViewModel;

    private static int[] avalaibleTicketsInFirstSection = { 1, 2, 3, 4 };
    private static int[] unavalaibleTicketsInFirstSection = { 11, 12, 13, 14 };
    private static int[] avalaibleTicketsInSecondSection = { 5, 6, 7 };
    private static int[] unavalaibleTicketsInSecondSection = { 15 };

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
        aMatchViewModel = new MatchViewModel();
        aMatchViewModel.setMatchIdentifier(A_MATCH_IDENTIFIER);
        aMatchViewModel.setSport(A_SPORT);
        aMatchViewModel.setVenue(A_VENUE);
        aMatchViewModel.setDate(A_DATE);
        aMatchViewModel.setHomeTeam(A_HOME_TEAM);
        aMatchViewModel.setVisitorTeam(A_VISITOR_TEAM);
        aMatchViewModel.setSex(A_SEX);
        aMatchViewModel.setTicketsBySection(tickets);
        aMatchViewModel.setTotalNumberOfTickets(INITIAL_NUMBER_OF_AVALAIBLE_TICKETS);
    }

    @Test
    public void canGetTheIdentifierOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getMatchIdentifier(), A_MATCH_IDENTIFIER);
    }

    @Test
    public void canGetTheSportOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getSport(), A_SPORT);
    }

    @Test
    public void canGetTheVenueOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getVenue(), A_VENUE);
    }

    @Test
    public void canGetTheDateOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getDate(), A_DATE);
    }

    @Test
    public void canGetTheHomeTeamOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getHomeTeam(), A_HOME_TEAM);
    }

    @Test
    public void canGetTheVisitorTeamOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getVisitorTeam(), A_VISITOR_TEAM);
    }

    @Test
    public void canGetTheSexOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getSex(), A_SEX);
    }

    @Test
    public void canGetTheAvailableTicketsBySectionOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getAvailableTicketsBySection(), tickets);
    }

    @Test
    public void canGetTheTotalNumberOfAvailableTicketsOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getTotalNumberOfTickets(), INITIAL_NUMBER_OF_AVALAIBLE_TICKETS);
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
