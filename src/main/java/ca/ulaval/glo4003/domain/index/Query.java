package ca.ulaval.glo4003.domain.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query<E extends Enum<E>> {

    private Map<E, List<String>> filtersByCategories = new HashMap<E, List<String>>();

    public void addFilterValue(E filterCategory, String filterValue) {
        if (!filtersByCategories.containsKey(filterCategory)) {
            filtersByCategories.put(filterCategory, new ArrayList<String>());
        }
        filtersByCategories.get(filterCategory).add(filterValue);
    }

    public List<E> getValidFilterCategories() {
        return new ArrayList<E>(filtersByCategories.keySet());
    }

    public List<String> getFilterValuesForCategory(E filterCategory) {
        return filtersByCategories.get(filterCategory);
    }

}
