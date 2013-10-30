package ca.ulaval.glo4003.persistence.json;

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ulaval.glo4003.model.Match;
import ca.ulaval.glo4003.repository.MatchMapper;

public class JSONMatchMapper extends JSONMapper<Match> implements MatchMapper{
    public JSONMatchMapper() {
        super(Match.class);
    }
    
    public Match load(String path) throws FileNotFoundException {
        return super.load(path);
    }
    
    public void save(Match match, String path) throws IOException {
        super.save(match, path);
    }
}
