package ca.ulaval.glo4003.searchEngine;

import java.util.List;
import java.util.Map;

public interface Query<T> {

    public void addFilter(T filter, Object filterValue);

    public Map<T, List<Object>> getQuery();

}
