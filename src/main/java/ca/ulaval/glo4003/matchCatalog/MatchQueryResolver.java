package ca.ulaval.glo4003.matchCatalog;

import ca.ulaval.glo4003.index.QueryResolver;
import ca.ulaval.glo4003.model.MatchAttribute;

public class MatchQueryResolver extends QueryResolver<MatchAttribute> {

    public MatchQueryResolver(MatchIndex index) {
        super(index);
    }

}
