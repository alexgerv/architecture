package ca.ulaval.glo4003.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4003.model.Match;

public abstract class MatchRepository {

    protected Map<String, Match> loadedEntries = new HashMap<String, Match>();

    public Match getMatchByIdentifier(String matchIdentifier) {
        if (loadedEntries.containsKey(matchIdentifier)) {
            return loadedEntries.get(matchIdentifier);
        } else {
            loadMatch(matchIdentifier);
            return loadedEntries.get(matchIdentifier);
        }
    }

    public Map<String, Match> getMatchesByIdentifier(List<String> matchesIdentifier) {
        Map<String, Match> matches = new HashMap<String, Match>();
        for (String identifier : matchesIdentifier) {
            matches.put(identifier, getMatchByIdentifier(identifier));
        }
        return matches;
    }

    public Map<String, Match> getAllLoadedEntries() {
        return loadedEntries;
    }

    public abstract void add(Match match);

    protected abstract void loadMatch(String identifier);
}
