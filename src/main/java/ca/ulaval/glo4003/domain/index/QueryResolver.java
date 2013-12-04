package ca.ulaval.glo4003.domain.index;

import java.util.ArrayList;
import java.util.List;

public class QueryResolver<E extends Enum<E>, O extends Indexable<E>> {

    private Index<E, O> index;

    public QueryResolver(Index<E, O> index) {
        this.index = index;
    }

    public List<String> resolve(Query<E> query) {
        List<String> identifiers = new ArrayList<String>();
        for (E filterCategory : query.getValidFilterCategories()) {
            if (identifiers.isEmpty()) {
                identifiers.addAll(getIdentifiersOfCategory(query, filterCategory));
            } else {
                identifiers.retainAll(getIdentifiersOfCategory(query, filterCategory));
            }
        }
        return identifiers;
    }

    private List<String> getIdentifiersOfCategory(Query<E> query, E filterCategory) {
        List<String> identifierInCategory = new ArrayList<String>();

        for (String filterValue : query.getFilterValuesForCategory(filterCategory)) {
            List<String> identifierOfFilterValue = index.getIdentifiersFor(filterCategory, filterValue);
            identifierInCategory.addAll(identifierOfFilterValue);
        }

        return identifierInCategory;
    }

}
