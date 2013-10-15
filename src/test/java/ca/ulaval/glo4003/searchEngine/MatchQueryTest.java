package ca.ulaval.glo4003.searchEngine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import matchCatalog.MatchFilterCategories;

import org.junit.Before;
import org.junit.Test;

public class MatchQueryTest {

    private static final MatchFilterCategories A_MATCH_FILTER = MatchFilterCategories.SPORT;
    private static final String A_FILTER_VALUE = "Soccer";
    private static final String ANOTHER_FILTER_VALUE = "Football";
    private static final MatchFilterCategories ANOTHER_MATCH_FILTER = MatchFilterCategories.VENUE;
    private MatchQuery aMatchQuery;

    @Before
    public void setup() {
        aMatchQuery = new MatchQuery();
    }

    @Test
    public void newQueryHasNoFilter() {
        Map<MatchFilterCategories, List<Object>> query = aMatchQuery.getQuery();
        boolean queryHasNoFilters = query.isEmpty();
        assertTrue(queryHasNoFilters);
    }

    @Test
    public void queryHasNewFilterWhenAddingANewFilter() {
        aMatchQuery.addFilter(A_MATCH_FILTER, A_FILTER_VALUE);
        Map<MatchFilterCategories, List<Object>> query = aMatchQuery.getQuery();

        boolean queryHasNoFilters = query.isEmpty();
        assertFalse(queryHasNoFilters);
    }

    @Test
    public void queryCanHaveMoreThanOneValuePerFilter() {
        aMatchQuery.addFilter(A_MATCH_FILTER, A_FILTER_VALUE);
        aMatchQuery.addFilter(A_MATCH_FILTER, ANOTHER_FILTER_VALUE);
        Map<MatchFilterCategories, List<Object>> query = aMatchQuery.getQuery();
        List<Object> filterValues = query.get(A_MATCH_FILTER);

        boolean filterContainsTheTwoValues =
                                             filterValues.contains(A_FILTER_VALUE)
                                                     && filterValues.contains(ANOTHER_FILTER_VALUE);

        assertTrue(filterContainsTheTwoValues);
    }

    @Test
    public void queryCanHaveMoreThanOneFilter() {
        aMatchQuery.addFilter(A_MATCH_FILTER, A_FILTER_VALUE);
        aMatchQuery.addFilter(ANOTHER_MATCH_FILTER, ANOTHER_FILTER_VALUE);
        Map<MatchFilterCategories, List<Object>> query = aMatchQuery.getQuery();

        boolean queryContainsTwoFilters = query.containsKey(A_MATCH_FILTER) && query.containsKey(ANOTHER_MATCH_FILTER);

        assertTrue(queryContainsTwoFilters);
    }
}
