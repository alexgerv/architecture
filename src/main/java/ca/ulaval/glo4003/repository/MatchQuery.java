package ca.ulaval.glo4003.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MatchQuery {

    private Map<MatchFilter, List<Object>> criterias = new HashMap<MatchFilter, List<Object>>();

    public void addFilter(MatchFilter matchFilter, Object filterValue) {
        if (!criterias.containsKey(matchFilter)) {
            criterias.put(matchFilter, new LinkedList<Object>());
        }
        criterias.get(matchFilter).add(filterValue);
    }

    public Map<MatchFilter, List<Object>> getQuery() {
        return criterias;
    }
}
