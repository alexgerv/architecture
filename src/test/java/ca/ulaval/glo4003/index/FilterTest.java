package ca.ulaval.glo4003.index;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.index.Filter;
import ca.ulaval.glo4003.index.Indexable;

public class FilterTest {

    private enum FilterCategories {
        CATEGORY1, CATEGORY2, CATEGORY3
    }

    private static final String A_FILTER_VALUE = "filter";

    private static final String AN_IDENTIFIER = "/an/unique/key/for/indexable";
    private static final String ANOTHER_IDENTIFIER = "/another/unique/key/for/indexable";

    private Filter<FilterCategories> aFilterOfCategory1;

    @Mock
    Indexable<FilterCategories> anIndexable;
    @Mock
    Indexable<FilterCategories> anotherIndexable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        aFilterOfCategory1 = new Filter<FilterCategories>(FilterCategories.CATEGORY1);
    }

    @Test
    public void canSayIfFilterIsInASpecifiedCategory() {
        assertTrue(aFilterOfCategory1.isOfCategory(FilterCategories.CATEGORY1));
    }

    @Test
    public void whenAddingAnIndexableFilterAskIndexableForTheFilterValueForHisCategory() {
        aFilterOfCategory1.add(anIndexable);

        verify(anIndexable).getFilterValueOfCategory(FilterCategories.CATEGORY1);
    }

    @Test
    public void whenAddingAnIndexableItIdentifierIsSavedForTheRightFilterValue() {
        doReturn(A_FILTER_VALUE).when(anIndexable).getFilterValueOfCategory(FilterCategories.CATEGORY1);
        doReturn(AN_IDENTIFIER).when(anIndexable).getIdentifier();
        aFilterOfCategory1.add(anIndexable);

        List<String> correspondingIdentifiers = aFilterOfCategory1.getIdentifiersFor(A_FILTER_VALUE);

        assertTrue(correspondingIdentifiers.contains(AN_IDENTIFIER));
    }

    @Test
    public void whenAddingTwoFilterValuesForASingleFilterTheTwoFiltersAreKept() {
        doReturn(A_FILTER_VALUE).when(anIndexable).getFilterValueOfCategory(FilterCategories.CATEGORY1);
        doReturn(AN_IDENTIFIER).when(anIndexable).getIdentifier();
        aFilterOfCategory1.add(anIndexable);

        doReturn(A_FILTER_VALUE).when(anotherIndexable).getFilterValueOfCategory(FilterCategories.CATEGORY1);
        doReturn(ANOTHER_IDENTIFIER).when(anotherIndexable).getIdentifier();
        aFilterOfCategory1.add(anotherIndexable);

        List<String> correspondingIdentifiers = aFilterOfCategory1.getIdentifiersFor(A_FILTER_VALUE);

        boolean theTwoIdentifiersAreInTheFilter =
                                                  correspondingIdentifiers.contains(AN_IDENTIFIER)
                                                          && correspondingIdentifiers.contains(ANOTHER_IDENTIFIER);
        assertTrue(theTwoIdentifiersAreInTheFilter);
    }

    @Test
    public void isOfCategoryReturnsTrueIfTheFilterIsOfTheRequiredCategory() {
        boolean filterIsOfCategory = aFilterOfCategory1.isOfCategory(FilterCategories.CATEGORY1);
        assertTrue(filterIsOfCategory);
    }

    @Test
    public void isOfCategoryReturnsFalseIfTheFilterIsNotOfTheRequiredCategory() {
        boolean filterIsOfCategory = aFilterOfCategory1.isOfCategory(FilterCategories.CATEGORY2);
        assertFalse(filterIsOfCategory);
    }
}
