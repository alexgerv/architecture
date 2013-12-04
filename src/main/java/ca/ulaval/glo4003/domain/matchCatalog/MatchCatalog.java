package ca.ulaval.glo4003.domain.matchCatalog;

import java.util.List;
import java.util.Map;

import ca.ulaval.glo4003.domain.index.Index;
import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;
import ca.ulaval.glo4003.domain.match.MatchRepository;

public abstract class MatchCatalog {

    private MatchQueryResolver queryResolver;

    private Index<MatchAttribute> index;

    private MatchRepository matchRepository;

    public MatchCatalog(MatchQueryResolver queryResolver, MatchIndex index, MatchRepository matchRepository) {
        this.queryResolver = queryResolver;
        this.index = index;
        this.matchRepository = matchRepository;
    }

    public Map<String, Match> getMatchesFromQuery(MatchQuery aMatchQuery) {
        List<String> matchesIdentifiers = queryResolver.resolve(aMatchQuery);

        return matchRepository.getMatchesByIdentifier(matchesIdentifiers);
    }

    public void add(Match match) {
        index.add(match);
        matchRepository.save(match);
    }

}
