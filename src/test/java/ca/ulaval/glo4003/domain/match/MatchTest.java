package ca.ulaval.glo4003.domain.match;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MatchTest {

    private static final String DATE_FORMAT_TEMPLATE = "yyyy-MM-dd HH'h'mm";

    private static final Sex A_SEX = Sex.MEN;
    private static final String A_SPORT = "aSport";
    private static final String A_HOME_TEAM = "HomeTeam";
    private static final String A_VISITOR_TEAM = "VisitorTeam";
    private static final String A_VENUE = "Venue";
    private static final Date A_DATE = new Date();
    private static final String A_FORMATED_DATE = DateFormatUtils.format(A_DATE, DATE_FORMAT_TEMPLATE);
    private static final Object INITIAL_NUMBER_OF_AVALAIBLE_TICKETS_IN_SECTION_A = 4;
    private static final Object INITIAL_NUMBER_OF_AVALAIBLE_TICKETS_IN_SECTION_B = 3;
    private static final Object INITIAL_NUMBER_OF_AVALAIBLE_TICKETS = 7;
    private static final String A_VALID_SECTION_NAME = "A";

    private static final int A_VALID_TICKET_QUANTITY = 1;

    private Match aMatch;
    private List<Section> sections = new ArrayList<Section>();

    @Mock
    Section sectionA;
    @Mock
    Section sectionB;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        initializeSectionList();
        aMatch = new Match(A_SPORT, A_VENUE, A_DATE, A_HOME_TEAM, A_VISITOR_TEAM, A_SEX, sections);
    }

    @Test
    public void canTellItsTotalNumberOfAvalaibleTickets() {
        doReturn(INITIAL_NUMBER_OF_AVALAIBLE_TICKETS_IN_SECTION_A).when(sectionA).getNumberOfAvailableTickets();
        doReturn(INITIAL_NUMBER_OF_AVALAIBLE_TICKETS_IN_SECTION_B).when(sectionB).getNumberOfAvailableTickets();
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
        assertEquals(aMatch.getDate(), A_FORMATED_DATE);
    }

    @Test
    public void canGetTheAvailableTicketsBySectionOfAMatch() {
        assertEquals(aMatch.getTicketsBySection(), sections);
    }

    @Test
    public void canGetTheIdentifierOfAMatch() {
        String identifier = A_VENUE + "/" + A_FORMATED_DATE;
        assertEquals(aMatch.getIdentifier(), identifier);
    }

    @Test
    public void whenAskedAValidSectionNameTheRightSectionIsReturned() {
        doReturn(true).when(sectionA).hasSameName(A_VALID_SECTION_NAME);

        Section validSection = aMatch.getSectionByName(A_VALID_SECTION_NAME);

        assertEquals(sectionA, validSection);
    }

    @Test(expected = SectionNotFoundException.class)
    public void aSectionNotFoundExceptionIsThrownIfAccessingAnInvalidSectionName() {
        doReturn(false).when(sectionA).hasSameName(A_VALID_SECTION_NAME);
        doReturn(false).when(sectionB).hasSameName(A_VALID_SECTION_NAME);

        aMatch.getSectionByName(A_VALID_SECTION_NAME);
    }

    @Test
    public void reserveTicketsReserveticketsFromSection() {
        doReturn(true).when(sectionA).hasSameName(A_VALID_SECTION_NAME);
        aMatch.reserveTickets(A_VALID_TICKET_QUANTITY, A_VALID_SECTION_NAME);
        verify(sectionA).reserveTickets(A_VALID_TICKET_QUANTITY);
    }

    @Test
    public void toStringGeneratesAStringInTheRightFormat() {
        String matchString = aMatch.toString();
        String expectedString = String.format("%s's %s, %s VS %s at %s on %s",
                                              A_SEX,
                                              A_SPORT,
                                              A_HOME_TEAM,
                                              A_VISITOR_TEAM,
                                              A_VENUE,
                                              A_FORMATED_DATE);
        assertEquals(matchString, expectedString);
    }

    private void initializeSectionList() {
        sections.add(sectionA);
        sections.add(sectionB);
    }

}
