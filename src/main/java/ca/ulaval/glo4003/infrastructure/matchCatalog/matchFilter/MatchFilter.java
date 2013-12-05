package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.index.Filter;
import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public abstract class MatchFilter extends Filter<MatchAttribute, Match> {

    public MatchFilter(MatchAttribute category) {
        super(category);
    }

    protected abstract String getAttributeValue(Match aMatch);

}
