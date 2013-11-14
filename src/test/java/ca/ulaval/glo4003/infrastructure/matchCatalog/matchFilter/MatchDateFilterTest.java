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
import ca.ulaval.glo4003.domain.matchCatalog.matchFilter.MatchDateFilter;
import ca.ulaval.glo4003.domain.matchCatalog.matchFilter.MatchSportFilter;


public class MatchDateFilterTest {

    private static final String A_MATCH_IDENTIFIER = "/a/unique/identifier";
    private static final String ANOTHER_MATCH_IDENTIFIER = "/another/unique/identifier";
    private static final String A_DATE = "2013-05-30";

    @Mock
    private Match aMatch;

    @Mock
    private Match anotherMatch;

    private MatchDateFilter matchDateFilter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matchDateFilter = new MatchDateFilter();
    }
    
    @Test
    public void whenNoMatchesOfACertainDateHaveBeenAddedToTheFilterThereIsNoIdentifiersForThisValue(){
        List<String> correspondingIdentifiers = matchDateFilter.getIdentifiersFor(A_DATE);
        assertTrue(correspondingIdentifiers.size() == 0);
    }

    @Test
    public void whenAddingAMatchItAccessesTheMatchsDateAttribute() {
        matchDateFilter.add(aMatch);
        verify(aMatch, times(1)).getDate();
    }

    @Test
    public void whenAddingADateItsIdentifierIsSavedForTheRightFilterValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_DATE).when(aMatch).getDate();

        matchDateFilter.add(aMatch);

        List<String> correspondingIdentifiers = matchDateFilter.getIdentifiersFor(A_DATE);

        assertTrue(correspondingIdentifiers.contains(A_MATCH_IDENTIFIER));
    }

    @Test
    public void whenAddingTwoMatchesWithSameDateValueBothMatchesAreFoundForThatValue() {
        doReturn(A_MATCH_IDENTIFIER).when(aMatch).getIdentifier();
        doReturn(A_DATE).when(aMatch).getDate();
        matchDateFilter.add(aMatch);

        doReturn(ANOTHER_MATCH_IDENTIFIER).when(anotherMatch).getIdentifier();
        doReturn(A_DATE).when(anotherMatch).getDate();
        matchDateFilter.add(anotherMatch);

        List<String> correspondingIdentifiers = matchDateFilter.getIdentifiersFor(A_DATE);

        boolean theTwoIdentifiersAreInTheFilter = correspondingIdentifiers.contains(A_MATCH_IDENTIFIER)
                                                  && correspondingIdentifiers.contains(ANOTHER_MATCH_IDENTIFIER);
        assertTrue(theTwoIdentifiersAreInTheFilter);
    }

}
