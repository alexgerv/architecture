package ca.ulaval.glo4003.matchFilter;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.searchEngine.MatchFilter;

public class SportFilterIndex extends MatchFilterIndex<String> {

    public SportFilterIndex(){
        this.filter = MatchFilter.SPORT;
    }
    
    String getValue(Match match) {
        return match.getSport();
    }

}