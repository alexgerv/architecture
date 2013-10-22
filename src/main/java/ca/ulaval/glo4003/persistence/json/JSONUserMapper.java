package ca.ulaval.glo4003.persistence.json;

import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ulaval.glo4003.model.User;
import ca.ulaval.glo4003.repository.UserMapper;

public class JSONUserMapper extends JSONConverter<User> implements UserMapper{
    public JSONUserMapper() {
        super(User.class);
    }
    
    public User load(String path) throws FileNotFoundException {
        return super.load(path);
    }
    
    public void save(User user, String path) throws IOException {
        super.save(user, path);
    }
}
