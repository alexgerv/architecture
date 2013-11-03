package ca.ulaval.glo4003.persistence.json;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchMarshaller;

public class JSONMatchMarshaller extends JSONMarshaller<Match> implements MatchMarshaller {

    public JSONMatchMarshaller() {
        super(Match.class);
    }
}
