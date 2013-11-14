package ca.ulaval.glo4003.domain.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;


public class MatchVenueFilter extends MatchFilter {

    public MatchVenueFilter() {
        super(MatchAttribute.VENUE);
    }

    @Override
    protected String getMatchAttributeValue(Match aMatch) {
        return aMatch.getVenue();
    }

}
