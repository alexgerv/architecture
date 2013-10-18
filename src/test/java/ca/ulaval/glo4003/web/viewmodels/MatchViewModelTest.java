package ca.ulaval.glo4003.web.viewmodels;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.model.Sex;

public class MatchViewModelTest {

    private static final int INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION = 4;
    private static final int INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION = 6;
    private static final int INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION
                                                                   + INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION;
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
    private Map<String, Integer> availableTicketsBySection = new HashMap<String, Integer>();

    @Before
    public void setup() {
        availableTicketsBySection.put(FIRST_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_FIRST_SECTION);
        availableTicketsBySection.put(SECOND_SECTION_NAME, INITIAL_NUMBER_OF_TICKETS_IN_SECOND_SECTION);
        aMatchViewModel = new MatchViewModel();
        aMatchViewModel.setMatchIdentifier(A_MATCH_IDENTIFIER);
        aMatchViewModel.setSport(A_SPORT);
        aMatchViewModel.setVenue(A_VENUE);
        aMatchViewModel.setDate(A_DATE);
        aMatchViewModel.setHomeTeam(A_HOME_TEAM);
        aMatchViewModel.setVisitorTeam(A_VISITOR_TEAM);
        aMatchViewModel.setSex(A_SEX);
        aMatchViewModel.setAvailableTicketsBySection(availableTicketsBySection);
        aMatchViewModel.setTotalNumberOfAvailableTickets(INITIAL_NUMBER_OF_AVALAIBLE_TICKETS);
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
        assertEquals(aMatchViewModel.getAvailableTicketsBySection(), availableTicketsBySection);
    }
    
    @Test
    public void canGetTheTotalNumberOfAvailableTicketsOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getTotalNumberOfAvailableTickets(), INITIAL_NUMBER_OF_AVALAIBLE_TICKETS);
    }
}
