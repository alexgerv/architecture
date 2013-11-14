package ca.ulaval.glo4003.domain.index;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ca.ulaval.glo4003.domain.index.Filter;
import ca.ulaval.glo4003.domain.index.Indexable;

public class FilterTest {
    
    private class ConcreteFilter extends Filter<FilterCategories>{

        public ConcreteFilter(FilterCategories category) {
            super(category);
        }

        @Override
        protected String getAttributeValue(Indexable<FilterCategories> anIndexable) {
            return null;
        }
        
    }

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
        aFilterOfCategory1 = new ConcreteFilter(FilterCategories.CATEGORY1);
    }

    @Test
    public void canSayIfFilterIsInASpecifiedCategory() {
        assertTrue(aFilterOfCategory1.isOfCategory(FilterCategories.CATEGORY1));
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
