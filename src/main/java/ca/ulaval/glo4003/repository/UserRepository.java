package ca.ulaval.glo4003.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.dao.FileAccessor;
import ca.ulaval.glo4003.dao.RepositoryException;
import ca.ulaval.glo4003.model.User;
import ca.ulaval.glo4003.model.UserJSONConverter;

@Repository
@Singleton
public class UserRepository {
    private static final String ROOT_REPOSITORY = "./Users/";
    private List<User> users = new ArrayList<User>();
    private FileAccessor fileAccessor = new FileAccessor();
    private UserJSONConverter userJSONConverter = new UserJSONConverter();
    public UserRepository() {}
    
    public boolean isEmpty() {
        return users.isEmpty();
    };
    
    public void loadAll() {
        for (String pathToUser : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            User newUser;
            try {
                newUser = userJSONConverter.load(ROOT_REPOSITORY + pathToUser);
                users.add(newUser);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    protected UserRepository(FileAccessor fileAccessor, UserJSONConverter userJSONConverter)  {
        this.fileAccessor = fileAccessor;
        this.userJSONConverter = userJSONConverter;
    }

    public User getUser(String username) {
        for (User user : users) {
            if(user.hasUsername(username)) {
                return user;
            }
        }
        throw new RepositoryException("User \"" + username + "\" is not found");
    }

    public void addNewUser(String username) {
        if(!usernameIsAvailable(username)) {
            throw new ExistingUsernameException("Username \"" + username + "\" is already taken");
        }
        User user = new User(username);
        users.add(user);
        saveUser(user);
    }

    private boolean usernameIsAvailable(String username) {
        for(User user : users) {
            if(user.hasUsername(username)) {
                return false;
            }
        }
        return true;
    }

    private void saveUser(User user) {
        try {
            userJSONConverter.save(user, ROOT_REPOSITORY + "/" + user.getUsername());
        } catch (IOException e) {
            //FIXME Should try saving again later.
            e.printStackTrace();
        }
    }

}
