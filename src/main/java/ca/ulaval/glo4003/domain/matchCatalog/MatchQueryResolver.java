package ca.ulaval.glo4003.domain.matchCatalog;

import ca.ulaval.glo4003.domain.index.QueryResolver;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchQueryResolver extends QueryResolver<MatchAttribute> {

    public MatchQueryResolver(MatchIndex index) {
        super(index);
    }

}
