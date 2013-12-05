package ca.ulaval.glo4003.domain.index;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ListIndexTest {

    private enum FilterCategories {
        CATEGORY1, CATEGORY2, CATEGORY3
    }

    private class ConcreteIndexable implements Indexable<FilterCategories> {

        @Override
        public String getIdentifier() {
            return null;
        }

    }

    private static final String A_FILTER_FOR_CATEGORY1 = "filter";

    Index<FilterCategories, ConcreteIndexable> anIndex;
    private List<Filter<FilterCategories, ConcreteIndexable>> filterListByCategories = new ArrayList<Filter<FilterCategories, ConcreteIndexable>>();

    @Mock
    Filter<FilterCategories, ConcreteIndexable> filterOfCategory1;
    @Mock
    Filter<FilterCategories, ConcreteIndexable> filterOfCategory2;
    @Mock
    Filter<FilterCategories, ConcreteIndexable> filterOfCategory3;
    @Mock
    ConcreteIndexable anIndexable;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        filterListByCategories.add(filterOfCategory1);
        filterListByCategories.add(filterOfCategory2);
        filterListByCategories.add(filterOfCategory3);

        anIndex = new IndexWithList<FilterCategories, ConcreteIndexable>(filterListByCategories);
    }

    @Test
    public void whenAddingAnIndexableItIsAddedForAFilterInEachCategory() {
        anIndex.add(anIndexable);

        verifyFiltersHaveAddedTheIndexable();
    }

    private void verifyFiltersHaveAddedTheIndexable() {
        for (Filter<FilterCategories, ConcreteIndexable> filter : filterListByCategories) {
            verify(filter).add(anIndexable);
        }
    }

    @Test
    public void whenAskingIdentifiersForAFilterTheRightFilterGetAskedForTheIdentifiers() {
        doReturn(true).when(filterOfCategory1).isOfCategory(FilterCategories.CATEGORY1);

        anIndex.getIdentifiersFor(FilterCategories.CATEGORY1, A_FILTER_FOR_CATEGORY1);

        verify(filterOfCategory1).getIdentifiersFor(A_FILTER_FOR_CATEGORY1);
    }

    @Test(expected = FilterCategoryException.class)
    public void whenAskingIdentifiersForAFilterAnExceptionIsThrownIfTheCategoryDoesNotExist() {
        anIndex.getIdentifiersFor(FilterCategories.CATEGORY1, A_FILTER_FOR_CATEGORY1);
    }

}
