package ca.ulaval.glo4003.searchEngine;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchRepository;

public class MatchSearchEngineTest {

    private static final Set<Integer> A_LIST_OF_INDEXES_FROM_QUERY = new HashSet<Integer>(Arrays.asList(0, 1, 2, 3, 4));
    private static final int NUMBER_OF_RESULT_PER_PAGE = 3;
    private static final int A_PAGE_NUMBER = 1;
    private static final Integer[] EXPECTED_INDEXES_FOR_SPECIFIED_PAGE = {0, 1, 2};

    @Mock
    private MatchRepository aMatchRepository;
    @Mock
    private MatchIndex aMatchIndex;
    @Mock
    private List<Match> aListOfMatches;
    @Mock
    private MatchQuery aMatchQuery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGettingMatchesForASpecificPageRightRageOfMatchIsAskedToTheRepository() {
        MatchSearchEngine aSearchEngine = new MatchSearchEngine(aMatchIndex, aMatchRepository, NUMBER_OF_RESULT_PER_PAGE);
        doReturn(A_LIST_OF_INDEXES_FROM_QUERY).when(aMatchIndex).getIndexesFromQuery(aMatchQuery);

        aSearchEngine.getSpecifiedPageMatchesFromQuery(aMatchQuery, A_PAGE_NUMBER);
        
        verify(aMatchRepository, times(1)).getMatchesByIndex(EXPECTED_INDEXES_FOR_SPECIFIED_PAGE);
    }
}
