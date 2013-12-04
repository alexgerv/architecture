package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchVenueFilter extends MatchFilter {

    public MatchVenueFilter() {
        super(MatchAttribute.VENUE);
    }

    @Override
    protected String getAttributeValue(Match aMatch) {
        return aMatch.getVenue();
    }

}
