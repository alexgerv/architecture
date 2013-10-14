package ca.ulaval.glo4003.searchEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4003.matchFilter.DateFilterIndex;
import ca.ulaval.glo4003.matchFilter.MatchFilterIndex;
import ca.ulaval.glo4003.matchFilter.SexFilterIndex;
import ca.ulaval.glo4003.matchFilter.SportFilterIndex;
import ca.ulaval.glo4003.matchFilter.VenueFilterIndex;
import ca.ulaval.glo4003.matchFilter.VisitorTeamFilterIndex;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.utils.Utils;

public class MatchIndex implements Index<Match> {

    private Set<Integer> indexes = new HashSet<Integer>();
    private Map<MatchFilter, MatchFilterIndex> filters = new HashMap<MatchFilter, MatchFilterIndex>();
    

    public MatchIndex(){
        filters.put(MatchFilter.SPORT, new SportFilterIndex());
        filters.put(MatchFilter.VENUE, new VenueFilterIndex());
        filters.put(MatchFilter.DATE, new DateFilterIndex());
        filters.put(MatchFilter.HOME_TEAM, new SportFilterIndex());
        filters.put(MatchFilter.VISITOR_TEAM, new VisitorTeamFilterIndex());
        filters.put(MatchFilter.SEX, new SexFilterIndex());        
    }
    
    public Integer add(Match newMatch) {
        Integer newMatchId = indexes.size();
        indexMatch(newMatch, newMatchId);
        return newMatchId;
    }

    private void indexMatch(Match match, int id) {
        indexes.add(id);
        for(MatchFilter filter: MatchFilter.values()){
            filters.get(filter).indexFilter(match, id);
        }
    }

    public Set<Integer> getIndexesFromQuery(MatchQuery query) {
        Set<Integer> returnedIndexes = indexes;
        Map<MatchFilter, List<Object>> criterias = query.getQuery();
        for (MatchFilter filter : criterias.keySet()) {
            Set<Integer> criteriaMatch = getIndexesFromFilterValues(filter, criterias.get(filter));
            System.out.println(filter.toString());
            returnedIndexes = Utils.getIntersection(returnedIndexes, criteriaMatch);
        }
        return returnedIndexes;
    }

    private Set<Integer> getIndexesFromFilterValues(MatchFilter criteria, List<Object> list) {
        Set<Integer> criteriaMatch = new HashSet<Integer>();
        for (Object val : list) {
            criteriaMatch.addAll(getIndexesFromFilterValue(criteria, val));
        }
        return criteriaMatch;
    }

    private Set<Integer> getIndexesFromFilterValue(MatchFilter criteria, Object value) {
        return filters.get(criteria).getIndexes(value);
    }
}
