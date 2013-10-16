package ca.ulaval.glo4003.matchCatalog.index;

import java.util.List;


public class IndexWithList<E extends Enum<E>> implements Index<E> {
    
    private List<Filter<E>> filterListByCategories;

    public IndexWithList(List<Filter<E>> filterListByCategories) {
        this.filterListByCategories = filterListByCategories;
    }

    @Override
    public void add(Indexable<E> indexable) {
        for(Filter<E> filter: filterListByCategories){
            filter.add(indexable);
        }
    }

    @Override
    public List<String> getIdentifiersFor(E category, String filterOfCategory) {
        for(Filter<E> filter: filterListByCategories){
            if(filter.isOfCategory(category)){
                return filter.getIdentifiersFor(filterOfCategory);
            }
        }
        throw new FilterCategoryException("The specified filter category does not exist in current index.");
    }
    
}
