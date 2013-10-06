package ca.ulaval.glo4003.searchEngine;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.utils.Utils;

public class MatchIndex implements Index<Match> {

    private Set<Integer> indexes = new HashSet<Integer>();
    private Map<String, Set<Integer>> sportIndex = new HashMap<String, Set<Integer>>();
    private Map<String, Set<Integer>> venueIndex = new HashMap<String, Set<Integer>>();
    private Map<Date, Set<Integer>> dateIndex = new HashMap<Date, Set<Integer>>();
    private Map<String, Set<Integer>> homeTeamIndex = new HashMap<String, Set<Integer>>();
    private Map<String, Set<Integer>> visitorTeamIndex = new HashMap<String, Set<Integer>>();
    private Map<String, Set<Integer>> sexIndex = new HashMap<String, Set<Integer>>();

    public void add(Match newMatch) {
        indexMatch(newMatch, indexes.size());
    }

    private void indexMatch(Match match, int id) {
        indexes.add(id);
        indexSport(match, id);
        indexVenue(match, id);
        indexDate(match, id);
        indexHomeTeam(match, id);
        indexVisitorTeam(match, id);
        indexSex(match, id);
    }

    private void indexSport(Match match, int id) {
        String sport = match.getSport();
        if (!sportIndex.containsKey(sport)) {
            sportIndex.put(sport, new HashSet<Integer>());
        }
        sportIndex.get(sport).add(id);
    }

    private void indexVenue(Match match, int id) {
        String venue = match.getVenue();
        if (!venueIndex.containsKey(venue)) {
            venueIndex.put(venue, new HashSet<Integer>());
        }
        venueIndex.get(venue).add(id);
    }
    
    private void indexDate(Match match, int id) {
        Date date = match.getDate();
        if (!dateIndex.containsKey(date)) {
            dateIndex.put(date, new HashSet<Integer>());
        }
        dateIndex.get(date).add(id);
    }
    
    private void indexHomeTeam(Match match, int id) {
        String homeTeam = match.getHomeTeam();
        if (!homeTeamIndex.containsKey(homeTeam)) {
            homeTeamIndex.put(homeTeam, new HashSet<Integer>());
        }
        homeTeamIndex.get(homeTeam).add(id);
    }
    
    private void indexVisitorTeam(Match match, int id) {
        String visitorTeam = match.getVisitorTeam();
        if (!visitorTeamIndex.containsKey(visitorTeam)) {
            visitorTeamIndex.put(visitorTeam, new HashSet<Integer>());
        }
        visitorTeamIndex.get(visitorTeam).add(id);
    }
    
    private void indexSex(Match match, int id) {
        String sex = match.getSex();
        if (!sexIndex.containsKey(sex)) {
            sexIndex.put(sex, new HashSet<Integer>());
        }
        sexIndex.get(sex).add(id);
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
