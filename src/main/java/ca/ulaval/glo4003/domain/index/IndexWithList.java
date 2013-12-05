package ca.ulaval.glo4003.domain.index;

import java.util.List;

public class IndexWithList<E extends Enum<E>, O extends Indexable<E>> implements Index<E, O> {

    private List<Filter<E, O>> filterListByCategories;

    public IndexWithList(List<Filter<E, O>> filterListByCategories) {
        this.filterListByCategories = filterListByCategories;
    }

    @Override
    public void add(O indexable) {
        for (Filter<E, O> filter : filterListByCategories) {
            filter.add(indexable);
        }
    }

    @Override
    public List<String> getIdentifiersFor(E category, String filterOfCategory) {
        for (Filter<E, O> filter : filterListByCategories) {
            if (filter.isOfCategory(category)) {
                return filter.getIdentifiersFor(filterOfCategory);
            }
        }
        throw new FilterCategoryException("The specified filter category does not exist in current index.");
    }

}
