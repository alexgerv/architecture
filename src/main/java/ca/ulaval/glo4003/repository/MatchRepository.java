package ca.ulaval.glo4003.repository;

import java.util.List;
import java.util.Map;

import ca.ulaval.glo4003.model.Match;

public interface MatchRepository {

    public Match getMatchByIdentifier(String matchIdentifier);

    public Map<String, Match> getMatchesByIdentifier(List<String> matchesIdentifier);

    public Map<String, Match> getAllLoadedEntries();

    public void add(Match match);
}
