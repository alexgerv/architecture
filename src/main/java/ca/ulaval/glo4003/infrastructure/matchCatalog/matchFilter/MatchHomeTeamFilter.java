package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchHomeTeamFilter extends MatchFilter {

    public MatchHomeTeamFilter() {
        super(MatchAttribute.HOME_TEAM);
    }

    @Override
    public String getAttributeValue(Match aMatch) {
        return aMatch.getHomeTeam();
    }

}
