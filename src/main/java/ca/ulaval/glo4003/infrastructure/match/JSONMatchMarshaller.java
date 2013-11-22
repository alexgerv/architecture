package ca.ulaval.glo4003.infrastructure.match;

import ca.ulaval.glo4003.domain.match.Match;
import ca.ulaval.glo4003.infrastructure.persistence.JSONMarshaller;

public class JSONMatchMarshaller extends JSONMarshaller<Match> {

    public JSONMatchMarshaller() {
        super(Match.class);
    }
}
