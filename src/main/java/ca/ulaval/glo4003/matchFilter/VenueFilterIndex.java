package ca.ulaval.glo4003.matchFilter;

import ca.ulaval.glo4003.model.Match;

public class VenueFilterIndex extends MatchFilterIndex<String> {

    String getValue(Match match) {
        return match.getVenue();
    }

}