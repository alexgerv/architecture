package ca.ulaval.glo4003.matchFilter;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.searchEngine.MatchFilter;

public class VenueFilterIndex extends MatchFilterIndex<String> {

    public VenueFilterIndex(){
        this.filter = MatchFilter.VENUE;
    }
    
    String getValue(Match match) {
        return match.getVenue();
    }

}