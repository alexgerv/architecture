package ca.ulaval.glo4003.fileAccess;

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ulaval.glo4003.searchEngine.MatchIndex;

public class JSONMatchIndexConverter extends JSONConverter<MatchIndex> implements MatchIndexConverter{
    public JSONMatchIndexConverter() {
        super(MatchIndex.class);
    }
    
    public MatchIndex load(String path) throws FileNotFoundException {
        return super.load(path);
    }
    
    public void save(MatchIndex index, String path) throws IOException {
        super.save(index, path);
    }
}
