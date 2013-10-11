package ca.ulaval.glo4003.matchFilter;

import java.util.Date;

import ca.ulaval.glo4003.model.Match;

public class DateFilterIndex extends MatchFilterIndex<Date> {

    Date getValue(Match match) {
        return match.getDate();
    }

}