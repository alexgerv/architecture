package ca.ulaval.glo4003.matchCatalog;

import ca.ulaval.glo4003.index.QueryResolver;

public class MatchQueryResolver extends QueryResolver<MatchFilterCategories> {

    public MatchQueryResolver(MatchIndex index) {
        super(index);
    }

}
