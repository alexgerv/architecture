package ca.ulaval.glo4003.infrastructure.persistence;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.domain.persistence.MatchMarshaller;

public class JSONMatchMarshaller extends JSONMarshaller<Match> implements MatchMarshaller {

    public JSONMatchMarshaller() {
        super(Match.class);
    }
}
