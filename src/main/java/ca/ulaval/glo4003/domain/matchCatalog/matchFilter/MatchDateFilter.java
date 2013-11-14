package ca.ulaval.glo4003.domain.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchDateFilter extends MatchFilter {

    public MatchDateFilter() {
        super(MatchAttribute.DATE);
    }

    @Override
    public String getMatchAttributeValue(Match aMatch) {
        return aMatch.getDate();
    }
}
