package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter.MatchHomeTeamFilter;

public class MatchHomeTeamFilterTest {

    private static final String A_MATCH_IDENTIFIER = "/a/unique/identifier";
    private static final String ANOTHER_MATCH_IDENTIFIER = "/another/unique/identifier";
    private static final String A_HOME_TEAM = "Ulaval";

    @Mock
    private Match aMatch;

    @Mock
    private Match anotherMatch;

    private MatchHomeTeamFilter matchHomeTeamFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matchHomeTeamFilter = new MatchHomeTeamFilter();
    }

    @Test
    public void whenNoMatchesOfAHomeTeamHaveBeenAddedToTheFilterThereIsNoIdentifiersForThisValue() {
        List<String> correspondingIdentifiers = matchHomeTeamFilter.getIdentifiersFor(A_HOME_TEAM);
        assertTrue(correspondingIdentifiers.size() == 0);
    }

    @Test
    public void whenAddingAMatchItAccessesTheMatchsHomeTeamAttribute() {
        matchHomeTeamFilter.add(aMatch);
        verify(aMatch, times(1)).getHomeTeam();
    }

    @Test
    public void whenAddingAHomeTeamItsIdentifierIsSavedForTheRightFilterValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_HOME_TEAM).when(aMatch).getHomeTeam();

        matchHomeTeamFilter.add(aMatch);

        List<String> correspondingIdentifiers = matchHomeTeamFilter.getIdentifiersFor(A_HOME_TEAM);

        assertTrue(correspondingIdentifiers.contains(A_MATCH_IDENTIFIER));
    }

    @Test
    public void whenAddingTwoMatchesWithSameHomeTeamValueBothMatchesAreFoundForThatValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_HOME_TEAM).when(aMatch).getHomeTeam();
        matchHomeTeamFilter.add(aMatch);

        doReturn(ANOTHER_MATCH_IDENTIFIER).when(anotherMatch).getIdentifier();
        doReturn(A_HOME_TEAM).when(anotherMatch).getHomeTeam();
        matchHomeTeamFilter.add(anotherMatch);

        List<String> correspondingIdentifiers = matchHomeTeamFilter.getIdentifiersFor(A_HOME_TEAM);

        boolean theTwoIdentifiersAreInTheFilter = correspondingIdentifiers.contains(A_MATCH_IDENTIFIER)
                                                  && correspondingIdentifiers.contains(ANOTHER_MATCH_IDENTIFIER);
        assertTrue(theTwoIdentifiersAreInTheFilter);
    }

}
