package ca.ulaval.glo4003.matchFilter;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.searchEngine.MatchFilter;

public class VisitorTeamFilterIndex extends MatchFilterIndex<String> {

    public VisitorTeamFilterIndex(){
        this.filter = MatchFilter.VISITOR_TEAM;
    }
    
    String getValue(Match match) {
        return match.getVisitorTeam();
    }

}