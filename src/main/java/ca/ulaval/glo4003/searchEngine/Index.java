package ca.ulaval.glo4003.searchEngine;

import java.util.Set;

public interface Index<T> {

    public void add(T item);

    public Set<Integer> getIndexesFromQuery(MatchQuery query);
}
