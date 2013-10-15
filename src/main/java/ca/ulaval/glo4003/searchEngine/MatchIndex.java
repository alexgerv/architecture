package ca.ulaval.glo4003.searchEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import matchCatalog.MatchFilterCategories;
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
    private Map<MatchFilterCategories, MatchFilterIndex> filters = new HashMap<MatchFilterCategories, MatchFilterIndex>();
    

    public MatchIndex(){
        filters.put(MatchFilterCategories.SPORT, new SportFilterIndex());
        filters.put(MatchFilterCategories.VENUE, new VenueFilterIndex());
        filters.put(MatchFilterCategories.DATE, new DateFilterIndex());
        filters.put(MatchFilterCategories.HOME_TEAM, new SportFilterIndex());
        filters.put(MatchFilterCategories.VISITOR_TEAM, new VisitorTeamFilterIndex());
        filters.put(MatchFilterCategories.SEX, new SexFilterIndex());        
    }
    
    public Integer add(Match newMatch) {
        Integer newMatchId = indexes.size();
        indexMatch(newMatch, newMatchId);
        return newMatchId;
    }

    private void indexMatch(Match match, int id) {
        indexes.add(id);
        for(MatchFilterCategories filter: MatchFilterCategories.values()){
            filters.get(filter).indexFilter(match, id);
        }
    }

    public Set<Integer> getIndexesFromQuery(MatchQuery query) {
        Set<Integer> returnedIndexes = indexes;
        Map<MatchFilterCategories, List<Object>> criterias = query.getQuery();
        for (MatchFilterCategories filter : criterias.keySet()) {
            Set<Integer> criteriaMatch = getIndexesFromFilterValues(filter, criterias.get(filter));
            System.out.println(filter.toString());
            returnedIndexes = Utils.getIntersection(returnedIndexes, criteriaMatch);
        }
        return returnedIndexes;
    }

    private Set<Integer> getIndexesFromFilterValues(MatchFilterCategories criteria, List<Object> list) {
        Set<Integer> criteriaMatch = new HashSet<Integer>();
        for (Object val : list) {
            criteriaMatch.addAll(getIndexesFromFilterValue(criteria, val));
        }
        return criteriaMatch;
    }

    private Set<Integer> getIndexesFromFilterValue(MatchFilterCategories criteria, Object value) {
        return filters.get(criteria).getIndexes(value);
    }
}
