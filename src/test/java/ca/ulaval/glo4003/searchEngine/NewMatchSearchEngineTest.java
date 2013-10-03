package ca.ulaval.glo4003.searchEngine;

import static org.mockito.Mockito.doReturn;

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

public class NewMatchSearchEngineTest {
    
    private static final Set<Integer> A_LIST_OF_INDEXES_FROM_QUERY = new HashSet<Integer>(Arrays.asList(0, 1, 2, 3, 4));
    
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
    public void canReturnMatchesFromQueryWithASpecifiedRange(){
        MatchSearchEngine aSearchEngine = new MatchSearchEngine(aMatchIndex, aMatchRepository);
        

        doReturn(A_LIST_OF_INDEXES_FROM_QUERY).when(aMatchIndex).getIndexesFromQuery(aMatchQuery);
        doReturn(aListOfMatches).when(aMatchRepository).getFromQuery(aMatchQuery);
        
    }
}
