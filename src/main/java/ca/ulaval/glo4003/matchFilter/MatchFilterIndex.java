package ca.ulaval.glo4003.matchFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.searchEngine.MatchFilter;

public abstract class MatchFilterIndex<T> {

    private Map<T, Set<Integer>> indexes = new HashMap<T, Set<Integer>>();
    protected MatchFilter filter;

    public void indexFilter(Match match, int matchID) {
        T filter = getValue(match);
        if (!indexes.containsKey(filter)) {
            indexes.put(filter, new HashSet<Integer>());
        }
        indexes.get(filter).add(matchID);
    }
    
    public boolean isConcernedBy(MatchFilter filter){
        return this.filter == filter;
    }
    
    public Set<Integer> getIndexes(T value){
        return indexes.get(value);
    }

    abstract T getValue(Match match);
}