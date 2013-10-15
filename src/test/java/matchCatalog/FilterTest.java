package matchCatalog;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doReturn;

public class FilterTest {

    private enum FilterCategories { CATEGORY1, CATEGORY2, CATEGORY3 }
    
    private static final String A_FILTER_VALUE = "filter";

    private static final String AN_IDENTIFIER = "/an/unique/key/for/indexable";
    
    private Filter<FilterCategories> aFilterOfCategory1;
    
    @Mock
    Indexable<FilterCategories> anIndexable;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        aFilterOfCategory1 = new Filter<FilterCategories>(FilterCategories.CATEGORY1);
    }
    
    @Test
    public void canSayIfFilterIsInASpecifiedCategory(){        
        assertTrue(aFilterOfCategory1.isOfCategory(FilterCategories.CATEGORY1));
    }
    
    @Test
    public void whenAddingAnIndexableFilterAskIndexableForTheFilterValueForHisCategory(){
        aFilterOfCategory1.add(anIndexable);
        
        verify(anIndexable).getFilterValueOfCategory(FilterCategories.CATEGORY1);        
    }
    
    @Test
    public void whenAddingAnIndexableItIdentifierIsSavedForTheRightFilterValue(){
        doReturn(A_FILTER_VALUE).when(anIndexable).getFilterValueOfCategory(FilterCategories.CATEGORY1);
        doReturn(AN_IDENTIFIER).when(anIndexable).getIdentifier();
        aFilterOfCategory1.add(anIndexable);
        
        List<String> correspondingIdentifiers = aFilterOfCategory1.getIdentifiersFor(A_FILTER_VALUE);
        
        assertTrue(correspondingIdentifiers.contains(AN_IDENTIFIER));
    }
}
