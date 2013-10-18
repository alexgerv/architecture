package ca.ulaval.glo4003.matchCatalog;

import java.util.Map;

import ca.ulaval.glo4003.model.Match;

public interface MatchCatalog {

    public Map<String, Match> getMatchesFromQuery(MatchQuery aMatchQuery);

    public void add(Match match);

}
