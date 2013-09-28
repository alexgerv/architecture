package ca.ulaval.glo4003.repository;

import java.util.HashMap;
import java.util.Map;

public class MatchQuery {

    private Map<String, Object> criterias = new HashMap<String, Object>();

    public void addFilter(String filterName, Object filterValue) {
        criterias.put(filterName, filterValue);
    }

    public Map<String, Object> getQuery() {
        return criterias;
    }
}
