package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchDateFilter extends MatchFilter {

    public MatchDateFilter() {
        super(MatchAttribute.DATE);
    }

    @Override
    public String getAttributeValue(Match aMatch) {
        return aMatch.getDate();
    }
}
