package ca.ulaval.glo4003.matchFilter;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.searchEngine.MatchFilter;

public class HomeTeamFilterIndex extends MatchFilterIndex<String> {

    public HomeTeamFilterIndex(){
        this.filter = MatchFilter.HOME_TEAM;
    }
    
    String getValue(Match match) {
        return match.getHomeTeam();
    }

}