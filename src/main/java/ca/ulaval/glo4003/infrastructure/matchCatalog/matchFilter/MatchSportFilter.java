package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchSportFilter extends MatchFilter {

    public MatchSportFilter() {
        super(MatchAttribute.SPORT);
    }

    @Override
    public String getMatchAttributeValue(Match aMatch) {
        return aMatch.getSport();
    }

}
