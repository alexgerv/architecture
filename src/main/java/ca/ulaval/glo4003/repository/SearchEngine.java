package ca.ulaval.glo4003.repository;

import java.util.Set;

public interface SearchEngine<T> {

    public void add(T item, int id);

    public Set<Integer> getIndexesFromQuery(MatchQuery query);
}
