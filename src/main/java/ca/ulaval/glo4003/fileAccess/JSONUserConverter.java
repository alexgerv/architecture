package ca.ulaval.glo4003.fileAccess;

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ulaval.glo4003.model.DbUser;

public class JSONUserConverter extends JSONConverter<DbUser> implements UserConverter{
    public JSONUserConverter() {
        super(DbUser.class);
    }
    
    public DbUser load(String path) throws FileNotFoundException {
        return super.load(path);
    }
    
    public void save(DbUser user, String path) throws IOException {
        super.save(user, path);
    }
}
