package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.matchCatalog.MatchCatalog;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQuery;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.web.converters.MatchViewConverter;
import ca.ulaval.glo4003.web.viewmodels.MatchViewModel;

public class SearchBarControllerTest {

    private final String SERIALIZED_QUERY =
                                            "{\"SPORT\":[{\"name\":\"Football\",\"value\":\"on\"},{\"name\":\"Cross-country\",\"value\":\"on\"}],\"VENUE\":[{\"name\":\"Sherbrooke\",\"value\":\"on\"}]}";

    @Mock
    private MatchCatalog matchCatalog;

    @Mock
    private MatchQueryFactory matchQueryFactory;

    @Mock
    private MatchViewConverter matchViewConverter;

    @Mock
    private MatchQuery matchQuery;

    @Mock
    private Map<String, Match> matchMap;

    @Mock
    private List<MatchViewModel> matchList;

    @InjectMocks
    private SearchBarController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new SearchBarController(matchCatalog, matchQueryFactory, matchViewConverter);
        doReturn(matchQuery).when(matchQueryFactory).create(SERIALIZED_QUERY);
        doReturn(matchMap).when(matchCatalog).getMatchesFromQuery(matchQuery);
        doReturn(matchList).when(matchViewConverter).convert(matchMap);
    }

    @Test
    public void canGetAMatchListFromQuery() {
        assertEquals(controller.searchResults(SERIALIZED_QUERY), matchList);
    }

}
