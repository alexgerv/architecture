package ca.ulaval.glo4003.matchFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4003.model.Match;

public abstract class MatchFilterIndex<T> {

    private Map<T, Set<Integer>> indexes = new HashMap<T, Set<Integer>>();

    public void indexFilter(Match match, int matchID) {
        T filter = getValue(match);
        if (!indexes.containsKey(filter)) {
            indexes.put(filter, new HashSet<Integer>());
        }
        indexes.get(filter).add(matchID);
    }

    abstract T getValue(Match match);
}