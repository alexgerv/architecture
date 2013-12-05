package ca.ulaval.glo4003.domain.matchCatalog;

import ca.ulaval.glo4003.domain.index.QueryResolver;
import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchQueryResolver extends QueryResolver<MatchAttribute, Match> {

    public MatchQueryResolver(MatchIndex index) {
        super(index);
    }

}
