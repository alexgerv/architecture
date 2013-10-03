package ca.ulaval.glo4003.dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ulaval.glo4003.model.Match;

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
