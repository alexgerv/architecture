package matchCatalog;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchRepository;


public class MatchCatalogTest {

    private static final List<String> VALID_MATCH_INDENTIFIER = Arrays.asList("stadeTelus/2010-05-30_22:00:00",
                                                                              "peps/2010-05-31 15:15:00",
                                                                              "terrain2/2010-05-30 12:30:00",
                                                                              "terrain3/2010-05-30 20:30:00",
                                                                              "terrain4/2010-05-30 17:30:00");
    private static final String AN_IDENTIFIER_FOR_NEW_MATCH = "stadeTelus/2010-05-30_22:00:00";
    
    MatchCatalog aMatchCatalog;

    @Mock
    private MatchRepository aMatchRepository;
    @Mock
    private QueryResolver<MatchFilterCategories> queryResolver;
    @Mock
    private Index<MatchFilterCategories> anIndex;
    @Mock
    private Query<MatchFilterCategories> aQuery;
    @Mock
    private Match aMatch;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aMatchCatalog = new MatchCatalog(queryResolver, anIndex, aMatchRepository);
    }

    @Test
    public void whenGettingMatchesRightMatchAreAskedToTheRepository() {
        doReturn(VALID_MATCH_INDENTIFIER).when(queryResolver).resolve(aQuery);

        aMatchCatalog.getMatchesFromQuery(aQuery);
        
        verify(aMatchRepository, times(1)).getMatchesById(VALID_MATCH_INDENTIFIER);
    }
    
    @Test
    public void whenAMatchIsAddedItIsIndexed(){
        aMatchCatalog.add(aMatch);
        
        verify(anIndex, times(1)).add(aMatch);
    }
    
    @Test
    public void whenAMatchIsAddedItIsAddedToTheRepository(){        
        aMatchCatalog.add(aMatch);
        
        verify(aMatchRepository, times(1)).add(aMatch);        
    }

}
