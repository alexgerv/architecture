package ca.ulaval.glo4003.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.utils.Utils;

public class MatchSearchEngine implements SearchEngine<Match> {

    private Set<Integer> indexes = new HashSet<Integer>();
    private Map<String, Set<Integer>> sportIndex = new HashMap<String, Set<Integer>>();
    private Map<String, Set<Integer>> venueIndex = new HashMap<String, Set<Integer>>();

    public void add(Match newMatch, int id) {
        indexMatch(newMatch, id);
    }

    private void indexMatch(Match match, int id) {
        indexes.add(id);
        indexSport(match, id);
        indexVenue(match, id);
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

    public Set<Integer> getIndexesFromQuery(MatchQuery query) {
        Set<Integer> returnedIndexes = indexes;
        Map<MatchFilter, List<Object>> criterias = query.getQuery();
        for (MatchFilter filter : criterias.keySet()) {
            Set<Integer> criteriaMatch = getOrIndexes(filter, criterias.get(filter));
            returnedIndexes = Utils.getIntersection(returnedIndexes, criteriaMatch);
        }
        return returnedIndexes;
    }

    private Set<Integer> getIndexes(MatchFilter criteria, Object value) {
        if (criteria == MatchFilter.SPORT) {
            return sportIndex.get((String) value);
        } else if (criteria == MatchFilter.VENUE) {
            return venueIndex.get((String) value);
        }
        throw new RuntimeException(); // TODO create exception
    }

    private Set<Integer> getOrIndexes(MatchFilter criteria, List<Object> list) {
        Set<Integer> criteriaMatch = new HashSet<Integer>();
        for (Object val : list) {
            criteriaMatch.addAll(getIndexes(criteria, val));
        }
        return criteriaMatch;
    }
}
