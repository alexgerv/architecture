package ca.ulaval.glo4003.web.viewmodels;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.domain.match.Sex;

public class MatchViewModelTest {

    private static final int[] avalaibleTicketsInFirstSection = { 1, 2, 3, 4 };
    private static final int[] avalaibleTicketsInSecondSection = { 5, 6, 7 };
    private static final int INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = avalaibleTicketsInFirstSection.length
                                                                   + avalaibleTicketsInSecondSection.length;
    private Sex A_SEX = Sex.MEN;
    private String A_MATCH_IDENTIFIER = "Venue/2013/10/31";
    private String A_SPORT = "aSport";
    private String A_VENUE = "Venue";
    private String A_DATE = "2013/10/31";
    private String A_HOME_TEAM = "HomeTeam";
    private String A_VISITOR_TEAM = "VisitorTeam";

    private MatchViewModel aMatchViewModel;

    private List<SectionViewModel> sections = new ArrayList<SectionViewModel>();

    @Before
    public void setup() {
        aMatchViewModel = new MatchViewModel();
        aMatchViewModel.setMatchIdentifier(A_MATCH_IDENTIFIER);
        aMatchViewModel.setSport(A_SPORT);
        aMatchViewModel.setVenue(A_VENUE);
        aMatchViewModel.setDate(A_DATE);
        aMatchViewModel.setHomeTeam(A_HOME_TEAM);
        aMatchViewModel.setVisitorTeam(A_VISITOR_TEAM);
        aMatchViewModel.setSex(A_SEX);
        aMatchViewModel.setTicketsBySection(sections);
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
        assertEquals(aMatchViewModel.getAvailableTicketsBySection(), sections);
    }

    @Test
    public void canGetTheTotalNumberOfAvailableTicketsOfAMatchViewModel() {
        assertEquals(aMatchViewModel.getTotalNumberOfAvailableTickets(), INITIAL_NUMBER_OF_AVALAIBLE_TICKETS);
    }

}
