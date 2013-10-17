package ca.ulaval.glo4003.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.matchCatalog.MatchFilterCategories;
import ca.ulaval.glo4003.matchCatalog.MatchQueryFactory;
import ca.ulaval.glo4003.matchCatalog.Query;
import ca.ulaval.glo4003.persistence.JSONMatchQueryFactory;

public class JSONMatchQueryFactoryTest {

    private static final String A_VALID_JSON_STRING = "{\"SPORT\":[{\"name\":\"basketball\",\"value\":\"on\"}]}";
    private static final MatchFilterCategories EXPECTED_FILTER_CATEGORY = MatchFilterCategories.SPORT;
    private static final String EXPECTED_FILTER_VALUE = "basketball";
    private static final String A_VALID_JSON_STRING_WITH_MORE_THAN_ONE_FILTER =
                                                                                "{\"SPORT\":[{\"name\":\"basketball\",\"value\":\"on\"}],"
                                                                                        + "\"VENUE\":[{\"name\":\"tenis\",\"value\":\"on\"}]}";
    private static final MatchFilterCategories NEXT_EXPECTED_FILTER_CATEGORY = MatchFilterCategories.VENUE;
    private static final String NEXT_EXPECTED_FILTER_VALUE = "tenis";

    private MatchQueryFactory matchQueryFactory;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        matchQueryFactory = new JSONMatchQueryFactory();
    }

    @Test
    public void whenGivenAJSONStringTheFactoryCanAddAFilterToTheQuery() throws Exception {
        Query<MatchFilterCategories> query = matchQueryFactory.create(A_VALID_JSON_STRING);

        String filterValue = query.getFilterValuesForCategory(EXPECTED_FILTER_CATEGORY).get(0);
        assertEquals(EXPECTED_FILTER_VALUE, filterValue);
    }

    @Test
    public void whenGivenAJSONStringAllTheFilterAreAddedToTheQuery() {
        Query<MatchFilterCategories> query = matchQueryFactory.create(A_VALID_JSON_STRING_WITH_MORE_THAN_ONE_FILTER);

        String filterValue = query.getFilterValuesForCategory(EXPECTED_FILTER_CATEGORY).get(0);
        assertEquals(EXPECTED_FILTER_VALUE, filterValue);

        String nextFilterValue = query.getFilterValuesForCategory(NEXT_EXPECTED_FILTER_CATEGORY).get(0);
        assertEquals(NEXT_EXPECTED_FILTER_VALUE, nextFilterValue);
    }

}
