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
import ca.ulaval.glo4003.domain.matchCatalog.matchFilter.MatchSportFilter;

public class MatchSportFilterTest {

    private static final String A_MATCH_IDENTIFIER = "/a/unique/identifier";
    private static final String ANOTHER_MATCH_IDENTIFIER = "/another/unique/identifier";
    private static final String A_SPORT = "BasketBall";

    @Mock
    private Match aMatch;

    @Mock
    private Match anotherMatch;

    private MatchSportFilter matchSportFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matchSportFilter = new MatchSportFilter();
    }

    @Test
    public void whenNoMatchesOfASportHaveBeenAddedToTheFilterThereIsNoIdentifiersForThisValue() {
        List<String> correspondingIdentifiers = matchSportFilter.getIdentifiersFor(A_SPORT);
        assertTrue(correspondingIdentifiers.size() == 0);
    }

    @Test
    public void whenAddingAMatchItAccessesTheMatchsSportAttribute() {
        matchSportFilter.add(aMatch);
        verify(aMatch, times(1)).getSport();
    }

    @Test
    public void whenAddingASportItsIdentifierIsSavedForTheRightFilterValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_SPORT).when(aMatch).getSport();

        matchSportFilter.add(aMatch);

        List<String> correspondingIdentifiers = matchSportFilter.getIdentifiersFor(A_SPORT);

        assertTrue(correspondingIdentifiers.contains(A_MATCH_IDENTIFIER));
    }

    @Test
    public void whenAddingTwoMatchesWithSameSportValueBothMatchesAreFoundForThatValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_SPORT).when(aMatch).getSport();
        matchSportFilter.add(aMatch);

        doReturn(ANOTHER_MATCH_IDENTIFIER).when(anotherMatch).getIdentifier();
        doReturn(A_SPORT).when(anotherMatch).getSport();
        matchSportFilter.add(anotherMatch);

        List<String> correspondingIdentifiers = matchSportFilter.getIdentifiersFor(A_SPORT);

        boolean theTwoIdentifiersAreInTheFilter = correspondingIdentifiers.contains(A_MATCH_IDENTIFIER)
                                                  && correspondingIdentifiers.contains(ANOTHER_MATCH_IDENTIFIER);
        assertTrue(theTwoIdentifiersAreInTheFilter);
    }

}
