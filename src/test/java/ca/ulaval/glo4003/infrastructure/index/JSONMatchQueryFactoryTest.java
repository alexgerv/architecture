package ca.ulaval.glo4003.infrastructure.index;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.index.Query;
import ca.ulaval.glo4003.domain.match.MatchAttribute;
import ca.ulaval.glo4003.domain.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.infrastructure.matchCatalog.JSONMatchQueryFactory;

public class JSONMatchQueryFactoryTest {

    private static final String A_VALID_JSON_STRING = "{\"SPORT\":[{\"name\":\"basketball\",\"value\":\"on\"}]}";
    private static final MatchAttribute EXPECTED_FILTER_CATEGORY = MatchAttribute.SPORT;
    private static final String EXPECTED_FILTER_VALUE = "basketball";
    private static final String A_VALID_JSON_STRING_WITH_MORE_THAN_ONE_FILTER = "{\"SPORT\":[{\"name\":\"basketball\",\"value\":\"on\"}],"
                                                                                + "\"VENUE\":[{\"name\":\"tenis\",\"value\":\"on\"}]}";
    private static final MatchAttribute NEXT_EXPECTED_FILTER_CATEGORY = MatchAttribute.VENUE;
    private static final String NEXT_EXPECTED_FILTER_VALUE = "tenis";

    private MatchQueryFactory matchQueryFactory;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        matchQueryFactory = new JSONMatchQueryFactory();
    }

    @Test
    public void whenGivenAJSONStringTheFactoryCanAddAFilterToTheQuery() throws Exception {
        Query<MatchAttribute> query = matchQueryFactory.create(A_VALID_JSON_STRING);

        String filterValue = query.getFilterValuesForCategory(EXPECTED_FILTER_CATEGORY).get(0);
        assertEquals(EXPECTED_FILTER_VALUE, filterValue);
    }

    @Test
    public void whenGivenAJSONStringAllTheFilterAreAddedToTheQuery() {
        Query<MatchAttribute> query = matchQueryFactory.create(A_VALID_JSON_STRING_WITH_MORE_THAN_ONE_FILTER);

        String filterValue = query.getFilterValuesForCategory(EXPECTED_FILTER_CATEGORY).get(0);
        assertEquals(EXPECTED_FILTER_VALUE, filterValue);

        String nextFilterValue = query.getFilterValuesForCategory(NEXT_EXPECTED_FILTER_CATEGORY).get(0);
        assertEquals(NEXT_EXPECTED_FILTER_VALUE, nextFilterValue);
    }

}
