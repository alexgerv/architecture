package ca.ulaval.glo4003.domain.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.index.Filter;
import ca.ulaval.glo4003.domain.index.Indexable;
import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public abstract class MatchFilter extends Filter<MatchAttribute> {

    public MatchFilter(MatchAttribute category) {
        super(category);
    }
    
    protected abstract String getMatchAttributeValue(Match aMatch);
    
    @Override
    protected String getAttributeValue(Indexable<MatchAttribute> anIndexable){
        Match aMatch = (Match)anIndexable;
        return getMatchAttributeValue(aMatch);
    }

}
