package ca.ulaval.glo4003.matchFilter;

import ca.ulaval.glo4003.model.Match;

public class SportFilterIndex extends MatchFilterIndex<String> {

    String getValue(Match match) {
        return match.getSport();
    }

}