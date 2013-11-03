package ca.ulaval.glo4003.persistence.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchMapper;
import ca.ulaval.glo4003.repository.MatchRepository;

@Repository
@Singleton
public class JSONMatchRepository implements MatchRepository {

    private static final String ROOT_REPOSITORY = "./matches/";
    private MatchMapper matchMarshaller = new JSONMatchMarshaller();
    private Map<String, Match> loadedEntries = new HashMap<String, Match>();

    public JSONMatchRepository() {

    }

    public Match getMatchByIdentifier(String matchIdentifier) {
        if (loadedEntries.containsKey(matchIdentifier)) {
            return loadedEntries.get(matchIdentifier);
        } else {
            loadMatch(matchIdentifier);
            return loadedEntries.get(matchIdentifier);
        }
    }

    private void loadMatch(String identifier) {
        Match newMatch;
        try {
            newMatch = matchMarshaller.load(ROOT_REPOSITORY + identifier);
            loadedEntries.put(identifier, newMatch);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
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

    public void add(Match match) {
        String matchIdentifier = match.getIdentifier();
        try {
            matchMarshaller.save(match, ROOT_REPOSITORY + matchIdentifier);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    protected JSONMatchRepository(JSONMatchMarshaller JSONMatchConverter) {
        this.matchMarshaller = JSONMatchConverter;
    }

}
