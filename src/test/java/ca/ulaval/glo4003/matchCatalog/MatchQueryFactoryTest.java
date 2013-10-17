package ca.ulaval.glo4003.matchCatalog;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MatchQueryFactoryTest {

    private static final String A_VALID_JSON_STRING = "{\"SPORT\":[{\"name\":\"basket\",\"value\":\"on\"}]}";
    private static final MatchFilterCategories EXPECTED_FILTER_CATEGORY = MatchFilterCategories.SPORT;
    private static final String EXPECTED_FILTER_VALUE = "filter1";
    private static final String A_VALID_JSON_STRING_WITH_MORE_THAN_ONE_FILTER = "{\"SPORT\":[{\"name\":\"basket\",\"value\":\"on\"}],"
                                                                                + "\"VENUE\":[{\"name\":\"tenis\",\"value\":\"on\"}]}";
    private static final MatchFilterCategories NEXT_EXPECTED_FILTER_CATEGORY = MatchFilterCategories.VENUE;
    private static final String NEXT_EXPECTED_FILTER_VALUE = "filter1";

    private MatchQueryFactory matchQueryFactory;

    @Mock
    Query<MatchFilterCategories> aQuery;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        matchQueryFactory = new MatchQueryFactory();
    }

    @Test
    public void whenGivenAJSONStringTheFactoryCanAddAFilterToTheQuery() {
        matchQueryFactory.create(A_VALID_JSON_STRING);

        verify(aQuery).addFilterValue(EXPECTED_FILTER_CATEGORY, EXPECTED_FILTER_VALUE);
    }

    @Test
    public void whenGivenAJSONStringAllTheFilterAreAddedToTheQuery() {
        matchQueryFactory.create(A_VALID_JSON_STRING_WITH_MORE_THAN_ONE_FILTER);

        verify(aQuery).addFilterValue(EXPECTED_FILTER_CATEGORY, EXPECTED_FILTER_VALUE);
        verify(aQuery).addFilterValue(NEXT_EXPECTED_FILTER_CATEGORY, NEXT_EXPECTED_FILTER_VALUE);
    }

}
