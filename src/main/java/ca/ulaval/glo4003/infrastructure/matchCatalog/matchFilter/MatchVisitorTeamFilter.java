package ca.ulaval.glo4003.infrastructure.matchCatalog.matchFilter;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.match.MatchAttribute;

public class MatchVisitorTeamFilter extends MatchFilter {

    public MatchVisitorTeamFilter() {
        super(MatchAttribute.VISITOR_TEAM);
    }

    @Override
    public String getAttributeValue(Match aMatch) {
        return aMatch.getVisitorTeam();
    }

}
