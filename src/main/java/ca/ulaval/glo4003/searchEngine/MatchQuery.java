package ca.ulaval.glo4003.searchEngine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import matchCatalog.MatchFilterCategories;

public class MatchQuery implements Query<MatchFilterCategories> {

    private Map<MatchFilterCategories, List<Object>> criterias = new HashMap<MatchFilterCategories, List<Object>>();

    public void addFilter(MatchFilterCategories matchFilter, Object filterValue) {
        if (!criterias.containsKey(matchFilter)) {
            criterias.put(matchFilter, new LinkedList<Object>());
        }
        criterias.get(matchFilter).add(filterValue);
    }

    public Map<MatchFilterCategories, List<Object>> getQuery() {
        return criterias;
    }
}
