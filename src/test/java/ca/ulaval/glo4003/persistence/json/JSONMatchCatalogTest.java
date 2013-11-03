package ca.ulaval.glo4003.persistence.json;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.matchCatalog.MatchIndex;
import ca.ulaval.glo4003.matchCatalog.MatchQuery;
import ca.ulaval.glo4003.matchCatalog.MatchQueryResolver;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.persistence.FileAccessor;
import ca.ulaval.glo4003.repository.MatchRepository;

public class JSONMatchCatalogTest {

    private static final List<String> VALID_MATCH_INDENTIFIER = Arrays.asList("stadeTelus/2010-05-30_22:00:00",
                                                                              "peps/2010-05-31 15:15:00",
                                                                              "terrain2/2010-05-30 12:30:00",
                                                                              "terrain3/2010-05-30 20:30:00",
                                                                              "terrain4/2010-05-30 17:30:00");

    MatchCatalog aMatchCatalog;

    @Mock
    private MatchRepository aMatchRepository;
    @Mock
    private MatchQueryResolver queryResolver;
    @Mock
    private MatchIndex anIndex;
    @Mock
    private MatchQuery aQuery;
    @Mock
    private Match aMatch;
    @Mock
    private JSONMatchMarshaller converter;
    @Mock
    private FileAccessor fileAccessor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aMatchCatalog = new JSONMatchCatalog(queryResolver, anIndex, aMatchRepository, converter, fileAccessor);
    }

    @Test
    public void whenGettingMatchesRightMatchAreAskedToTheRepository() {
        doReturn(VALID_MATCH_INDENTIFIER).when(queryResolver).resolve(aQuery);

        aMatchCatalog.getMatchesFromQuery(aQuery);

        verify(aMatchRepository, times(1)).getMatchesByIdentifier(VALID_MATCH_INDENTIFIER);
    }

    @Test
    public void whenAMatchIsAddedItIsIndexed() {
        aMatchCatalog.add(aMatch);

        verify(anIndex, times(1)).add(aMatch);
    }

    @Test
    public void whenAMatchIsAddedItIsAddedToTheRepository() {
        aMatchCatalog.add(aMatch);

        verify(aMatchRepository, times(1)).add(aMatch);
    }

}
