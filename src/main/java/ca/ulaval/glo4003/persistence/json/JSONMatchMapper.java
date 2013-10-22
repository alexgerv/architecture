package ca.ulaval.glo4003.persistence.json;

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.persistence.MatchConverter;

public class JSONMatchConverter extends JSONConverter<Match> implements MatchConverter{
    public JSONMatchConverter() {
        super(Match.class);
    }
    
    public Match load(String path) throws FileNotFoundException {
        return super.load(path);
    }
    
    public void save(Match match, String path) throws IOException {
        super.save(match, path);
    }
}
