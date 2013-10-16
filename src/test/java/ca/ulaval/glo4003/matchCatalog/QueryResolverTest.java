package ca.ulaval.glo4003.matchCatalog;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.matchCatalog.Query;
import ca.ulaval.glo4003.matchCatalog.QueryResolver;
import ca.ulaval.glo4003.matchCatalog.index.Index;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


public class QueryResolverTest {
    
    private enum FilterCategories { CATEGORY1, CATEGORY2, CATEGORY3 }
    
    private static final List<FilterCategories> A_LIST_OF_ONE_CATEGORY = Arrays.asList(FilterCategories.CATEGORY1);
    private static final List<FilterCategories> A_LIST_OF_VALID_FILTER_CATEGORIES = Arrays.asList(FilterCategories.CATEGORY1, FilterCategories.CATEGORY2);
    private static final String FILTER1 = "filter1";
    private static final String FILTER2 = "filter2";
    private static final List<String> A_LIST_OF_ONE_FILTER_VALUE = Arrays.asList(FILTER1);
    private static final List<String> A_LIST_OF_FILTER_VALUES = Arrays.asList(FILTER1, FILTER2);
    private static final String IDENTIFIER1 = "identifier1";
    private static final String IDENTIFIER2 = "identifier2";
    private static final String IDENTIFIER3 = "identifier3";
    private static final String IDENTIFIER4 = "identifier4";
    private static final String IDENTIFIER5 = "idendifier5";
    private static final List<String> A_LIST_OF_IDENTIFIER_FOR_CATEGORY1_FILTER1 = Arrays.asList(IDENTIFIER1, IDENTIFIER2);
    private static final List<String> A_LIST_OF_IDENTIFIER_FOR_CATEGORY1_FILTER2 = Arrays.asList(IDENTIFIER3, IDENTIFIER4);
    private static final List<String> A_LIST_OF_IDENTIFIER_FOR_CATEGORY2_FILTER1 = Arrays.asList(IDENTIFIER1, IDENTIFIER5);
    
    private QueryResolver<FilterCategories> queryResolver;
    
    @Mock
    Index<FilterCategories> anIndex;
    @Mock
    Query<FilterCategories> aQuery;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        queryResolver = new QueryResolver<FilterCategories>(anIndex);
    }
    
    @Test
    public void whenAskingIdentifiersForAQueryTheIndexIsAskedForEachCriteria(){
        doReturn(A_LIST_OF_VALID_FILTER_CATEGORIES).when(aQuery).getValidFilterCategories();
        doReturn(A_LIST_OF_ONE_FILTER_VALUE).when(aQuery).getFilterValuesForCategory(FilterCategories.CATEGORY1);
        doReturn(A_LIST_OF_ONE_FILTER_VALUE).when(aQuery).getFilterValuesForCategory(FilterCategories.CATEGORY2);
        
        queryResolver.resolve(aQuery);
        
        verify(anIndex).getIdentifiersFor(FilterCategories.CATEGORY1, A_LIST_OF_ONE_FILTER_VALUE.get(0));
        verify(anIndex).getIdentifiersFor(FilterCategories.CATEGORY2, A_LIST_OF_ONE_FILTER_VALUE.get(0));        
    }
    
    @Test
    public void whenAskingIdentifiersForAQueryAllIdentifierForSameCategoryAreReturned(){
        doReturn(A_LIST_OF_ONE_CATEGORY).when(aQuery).getValidFilterCategories();
        doReturn(A_LIST_OF_FILTER_VALUES).when(aQuery).getFilterValuesForCategory(FilterCategories.CATEGORY1);
        doReturn(A_LIST_OF_IDENTIFIER_FOR_CATEGORY1_FILTER1).when(anIndex).getIdentifiersFor(FilterCategories.CATEGORY1, FILTER1);
        doReturn(A_LIST_OF_IDENTIFIER_FOR_CATEGORY1_FILTER2).when(anIndex).getIdentifiersFor(FilterCategories.CATEGORY1, FILTER2);
        
        List<String> identifiersForCategories = queryResolver.resolve(aQuery);
        
        boolean containsExpectedIdentifier = identifiersForCategories.size() == 4 && 
                                             identifiersForCategories.contains(IDENTIFIER1) && 
                                             identifiersForCategories.contains(IDENTIFIER2) && 
                                             identifiersForCategories.contains(IDENTIFIER3) && 
                                             identifiersForCategories.contains(IDENTIFIER4);
        assertTrue(containsExpectedIdentifier);
    }
    
    @Test
    public void whenAskingIdentifiersForAQueryReturnedIdentifiersIsTheIntersectionBetweenCategories(){
        doReturn(A_LIST_OF_VALID_FILTER_CATEGORIES).when(aQuery).getValidFilterCategories();
        doReturn(A_LIST_OF_ONE_FILTER_VALUE).when(aQuery).getFilterValuesForCategory(FilterCategories.CATEGORY1);
        doReturn(A_LIST_OF_ONE_FILTER_VALUE).when(aQuery).getFilterValuesForCategory(FilterCategories.CATEGORY2);
        doReturn(A_LIST_OF_IDENTIFIER_FOR_CATEGORY1_FILTER1).when(anIndex).getIdentifiersFor(FilterCategories.CATEGORY1, FILTER1);
        doReturn(A_LIST_OF_IDENTIFIER_FOR_CATEGORY2_FILTER1).when(anIndex).getIdentifiersFor(FilterCategories.CATEGORY2, FILTER1);
        
        List<String> identifiersForCategories = queryResolver.resolve(aQuery);
        
        boolean containsExpectedIdentifier = identifiersForCategories.size() == 1 &&
                                             identifiersForCategories.contains(IDENTIFIER1);
        assertTrue(containsExpectedIdentifier);        
    }
    
}
