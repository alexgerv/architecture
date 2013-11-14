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
import ca.ulaval.glo4003.domain.matchCatalog.matchFilter.MatchVisitorTeamFilter;
import ca.ulaval.glo4003.domain.matchCatalog.matchFilter.MatchVisitorTeamFilter;


public class MatchVisitorTeamFilterTest {

    private static final String A_MATCH_IDENTIFIER = "/a/unique/identifier";
    private static final String ANOTHER_MATCH_IDENTIFIER = "/another/unique/identifier";
    private static final String A_VISITOR_TEAM = "BasketBall";

    @Mock
    private Match aMatch;

    @Mock
    private Match anotherMatch;

    private MatchVisitorTeamFilter matchVisitorTeamFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matchVisitorTeamFilter = new MatchVisitorTeamFilter();
    }
    
    @Test
    public void whenNoMatchesOfAVisitorTeamHaveBeenAddedToTheFilterThereIsNoIdentifiersForThisValue(){
        List<String> correspondingIdentifiers = matchVisitorTeamFilter.getIdentifiersFor(A_VISITOR_TEAM);
        assertTrue(correspondingIdentifiers.size() == 0);
    }

    @Test
    public void whenAddingAMatchItAccessesTheMatchsVisitorTeamAttribute() {
        matchVisitorTeamFilter.add(aMatch);
        verify(aMatch, times(1)).getVisitorTeam();
    }

    @Test
    public void whenAddingAVisitorTeamItsIdentifierIsSavedForTheRightFilterValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_VISITOR_TEAM).when(aMatch).getVisitorTeam();

        matchVisitorTeamFilter.add(aMatch);

        List<String> correspondingIdentifiers = matchVisitorTeamFilter.getIdentifiersFor(A_VISITOR_TEAM);

        assertTrue(correspondingIdentifiers.contains(A_MATCH_IDENTIFIER));
    }

    @Test
    public void whenAddingTwoMatchesWithSameVisitorTeamValueBothMatchesAreFoundForThatValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_VISITOR_TEAM).when(aMatch).getVisitorTeam();
        matchVisitorTeamFilter.add(aMatch);

        doReturn(ANOTHER_MATCH_IDENTIFIER).when(anotherMatch).getIdentifier();
        doReturn(A_VISITOR_TEAM).when(anotherMatch).getVisitorTeam();
        matchVisitorTeamFilter.add(anotherMatch);

        List<String> correspondingIdentifiers = matchVisitorTeamFilter.getIdentifiersFor(A_VISITOR_TEAM);

        boolean theTwoIdentifiersAreInTheFilter = correspondingIdentifiers.contains(A_MATCH_IDENTIFIER)
                                                  && correspondingIdentifiers.contains(ANOTHER_MATCH_IDENTIFIER);
        assertTrue(theTwoIdentifiersAreInTheFilter);
    }

}
