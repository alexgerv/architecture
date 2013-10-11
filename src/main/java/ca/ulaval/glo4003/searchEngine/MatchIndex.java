package ca.ulaval.glo4003.searchEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4003.matchFilter.DateFilterIndex;
import ca.ulaval.glo4003.matchFilter.HomeTeamFilterIndex;
import ca.ulaval.glo4003.matchFilter.SexFilterIndex;
import ca.ulaval.glo4003.matchFilter.SportFilterIndex;
import ca.ulaval.glo4003.matchFilter.VenueFilterIndex;
import ca.ulaval.glo4003.matchFilter.VisitorTeamFilterIndex;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.utils.Utils;

public class MatchIndex implements Index<Match> {

    private Set<Integer> indexes = new HashSet<Integer>();
    private SportFilterIndex sportIndex = new SportFilterIndex();
    private VenueFilterIndex venueIndex = new VenueFilterIndex();
    private DateFilterIndex dateIndex = new DateFilterIndex();
    private HomeTeamFilterIndex homeTeamIndex = new HomeTeamFilterIndex();
    private VisitorTeamFilterIndex visitorTeamIndex = new VisitorTeamFilterIndex();
    private SexFilterIndex sexIndex = new SexFilterIndex();

    public Integer add(Match newMatch) {
        Integer newMatchId = indexes.size();
        indexMatch(newMatch, newMatchId);
        return newMatchId;
    }

    private void indexMatch(Match match, int id) {
        indexes.add(id);
        sportIndex.indexFilter(match, id);
        venueIndex.indexFilter(match, id);
        dateIndex.indexFilter(match, id);
        homeTeamIndex.indexFilter(match, id);
        visitorTeamIndex.indexFilter(match, id);
        sexIndex.indexFilter(match, id);
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
        if (criteria == MatchFilter.SPORT) {
            return sportIndex.get((String) value);
        } else if (criteria == MatchFilter.VENUE) {
            return venueIndex.get((String) value);
        } else if (criteria == MatchFilter.DATE) {
            return dateIndex.get((Date) value);
        } else if (criteria == MatchFilter.HOME_TEAM) {
            return homeTeamIndex.get((String) value);
        } else if (criteria == MatchFilter.VISITOR_TEAM) {
            return visitorTeamIndex.get((String) value);
        } else if (criteria == MatchFilter.SEX) {
            return sexIndex.get((String) value);
        }
        throw new SearchEngineException("Invalid MatchFilter:" + criteria.toString());
    }
}
