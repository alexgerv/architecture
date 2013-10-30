package ca.ulaval.glo4003.matchCatalog;

import java.util.List;
import java.util.Map;

import ca.ulaval.glo4003.index.Index;
import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchRepository;

public abstract class MatchCatalog {

    private MatchQueryResolver queryResolver;

    private Index<MatchFilterCategories> index;

    private MatchRepository matchRepository;

    public MatchCatalog(MatchQueryResolver queryResolver, MatchIndex index, MatchRepository matchRepository){
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
        matchRepository.add(match);
    }

}
