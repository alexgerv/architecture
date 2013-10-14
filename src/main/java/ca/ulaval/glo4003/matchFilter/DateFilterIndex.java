package ca.ulaval.glo4003.matchFilter;

import java.util.Date;
import java.util.Set;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.searchEngine.MatchFilter;

public class DateFilterIndex extends MatchFilterIndex<Date> {

    public DateFilterIndex(){
        this.filter = MatchFilter.DATE;
    }
    
    Date getValue(Match match) {
        return match.getDate();
    }
}