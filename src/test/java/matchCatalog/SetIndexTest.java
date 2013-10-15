package matchCatalog;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class SetIndexTest {
    
    private enum FilterCategories { CATEGORY1, CATEGORY2, CATEGORY3 }
    
    private static final String A_FILTER_FOR_CATEGORY1 = "filter";

    Index<FilterCategories> anIndex;
    private List<Filter<FilterCategories>> filterListByCategories = new ArrayList<Filter<FilterCategories>>();
    
    @Mock
    Filter<FilterCategories> filterOfCategory1;
    @Mock
    Filter<FilterCategories> filterOfCategory2;
    @Mock
    Filter<FilterCategories> filterOfCategory3;
    @Mock
    Indexable<FilterCategories> anIndexable;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        filterListByCategories.add(filterOfCategory1);
        filterListByCategories.add(filterOfCategory2);
        filterListByCategories.add(filterOfCategory3);
        
        anIndex = new SetIndex<FilterCategories>(filterListByCategories);
    }

    @Test
    public void whenAddingAnIndexableItIsAddedForAFilterInEachCategory(){
        anIndex.add(anIndexable);
        
        verifyFiltersHaveAddedTheIndexable();        
    }

    private void verifyFiltersHaveAddedTheIndexable() {
        for(Filter<FilterCategories> filter : filterListByCategories){
            verify(filter).add(anIndexable);
        }
    }
    
    @Test
    public void whenAskingIdentifiersForAFilterTheRightFilterGetAskedForTheIdentifiers(){
        doReturn(true).when(filterOfCategory1).isOfCategory(FilterCategories.CATEGORY1);
        
        anIndex.getIdentifiersFor(FilterCategories.CATEGORY1, A_FILTER_FOR_CATEGORY1);
        
        verify(filterOfCategory1).getIdentifiersFor(A_FILTER_FOR_CATEGORY1);
    }
    
    @Test(expected = FilterCategoryException.class)
    public void whenAskingIdentifiersForAFilterAnExceptionIsThrownIfTheCategoryDoesNotExist(){
        anIndex.getIdentifiersFor(FilterCategories.CATEGORY1, A_FILTER_FOR_CATEGORY1);
    }
    
}
