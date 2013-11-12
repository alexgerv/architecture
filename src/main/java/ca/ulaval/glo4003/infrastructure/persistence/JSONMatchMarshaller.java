package ca.ulaval.glo4003.infrastructure.persistence;

import ca.ulaval.domain.persistence.MatchMarshaller;
import ca.ulaval.glo4003.domain.match.Match;

public class JSONMatchMarshaller extends JSONMarshaller<Match> implements MatchMarshaller {

    public JSONMatchMarshaller() {
        super(Match.class);
    }
}
