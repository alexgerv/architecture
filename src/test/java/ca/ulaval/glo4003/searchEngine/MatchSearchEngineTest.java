package ca.ulaval.glo4003.searchEngine;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
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
    private static final int AN_INDEX_FOR_NEW_MATCH = 0;
    
    MatchSearchEngine aSearchEngine;

    @Mock
    private MatchRepository aMatchRepository;
    @Mock
    private MatchIndex aMatchIndex;
    @Mock
    private MatchQuery aMatchQuery;
    @Mock
    private Match aMatch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aSearchEngine = new MatchSearchEngine(aMatchIndex, aMatchRepository, NUMBER_OF_RESULT_PER_PAGE);
    }

    @Test
    public void whenGettingMatchesForASpecificPageRightRageOfMatchIsAskedToTheRepository() {
        doReturn(A_LIST_OF_INDEXES_FROM_QUERY).when(aMatchIndex).getIndexesFromQuery(aMatchQuery);

        aSearchEngine.getMatchesForSpecifiedPageFromQuery(aMatchQuery, A_PAGE_NUMBER);
        
        verify(aMatchRepository, times(1)).getMatchesById(EXPECTED_INDEXES_FOR_SPECIFIED_PAGE);
    }
    
    @Test
    public void whenAMatchIsAddedItIsIndexed(){
        aSearchEngine.add(aMatch);
        
        verify(aMatchIndex, times(1)).add(aMatch);
    }
    
    @Test
    public void whenAMatchIsAddedItIsAddedToTheRepository(){
        doReturn(AN_INDEX_FOR_NEW_MATCH).when(aMatchIndex).add(aMatch);
        
        aSearchEngine.add(aMatch);
        
        verify(aMatchRepository, times(1)).add(aMatch, AN_INDEX_FOR_NEW_MATCH);        
    }
}
