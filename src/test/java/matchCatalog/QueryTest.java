package matchCatalog;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class QueryTest {

    private enum FilterCategories {
        CATEGORY1, CATEGORY2, CATEGORY3
    }

    private static final FilterCategories A_FILTER_CATEGORY = FilterCategories.CATEGORY1;
    private static final String A_FILTER_VALUE = "filter1";
    private static final String ANOTHER_FILTER_VALUE = "filter2";
    private static final FilterCategories ANOTHER_FILTER_CATEGORY = FilterCategories.CATEGORY2;

    private Query<FilterCategories> aQuery;

    @Before
    public void setup() {
        aQuery = new Query<FilterCategories>();
    }

    @Test
    public void newQueryHasNoFilter() {
        List<FilterCategories> query = aQuery.getValidFilterCategories();

        boolean queryHasNoFilters = query.isEmpty();
        assertTrue(queryHasNoFilters);
    }

    @Test
    public void queryHasNewFilterWhenAddingANewFilter() {
        aQuery.addFilterValue(A_FILTER_CATEGORY, A_FILTER_VALUE);

        List<FilterCategories> query = aQuery.getValidFilterCategories();

        boolean queryHasNewFilters = query.isEmpty();
        assertFalse(queryHasNewFilters);
    }

    @Test
    public void queryCanHaveMoreThanOneValuePerFilter() {
        aQuery.addFilterValue(A_FILTER_CATEGORY, A_FILTER_VALUE);
        aQuery.addFilterValue(A_FILTER_CATEGORY, ANOTHER_FILTER_VALUE);

        List<FilterCategories> categories = aQuery.getValidFilterCategories();
        List<String> filterValues = aQuery.getFilterValuesForCategory(categories.get(0));

        boolean filterContainsTheTwoValues = filterValues.contains(A_FILTER_VALUE) && 
                                             filterValues.contains(ANOTHER_FILTER_VALUE);
        assertTrue(filterContainsTheTwoValues);
    }

    @Test
    public void queryCanHaveMoreThanOneFilter() {
        aQuery.addFilterValue(A_FILTER_CATEGORY, A_FILTER_VALUE);
        aQuery.addFilterValue(ANOTHER_FILTER_CATEGORY, ANOTHER_FILTER_VALUE);
        
        List<FilterCategories> categories = aQuery.getValidFilterCategories();

        boolean queryContainsTwoFilters = categories.contains(A_FILTER_CATEGORY) && 
                categories.contains(ANOTHER_FILTER_CATEGORY);
        assertTrue(queryContainsTwoFilters);
    }
}
