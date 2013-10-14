package ca.ulaval.glo4003.matchFilter;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.searchEngine.MatchFilter;
import ca.ulaval.glo4003.searchEngine.Sex;

public class SexFilterIndex extends MatchFilterIndex<Sex> {

    public SexFilterIndex(){
        this.filter = MatchFilter.SEX;
    }
    
    Sex getValue(Match match) {
        return match.getSex();
    }

}