package ca.ulaval.glo4003.persistence.json;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchMapper;

public class JSONMatchMarshaller extends JSONMarshaller<Match> implements MatchMapper {

    public JSONMatchMarshaller() {
        super(Match.class);
    }
}
