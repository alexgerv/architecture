package ca.ulaval.glo4003.repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ca.ulaval.glo4003.fileAccess.FileAccessor;
import ca.ulaval.glo4003.fileAccess.JSONUserConverter;
import ca.ulaval.glo4003.fileAccess.UserConverter;
import ca.ulaval.glo4003.model.User;

@Repository
public class UserRepository {

    private static final String ROOT_REPOSITORY = "./users/";
    private List<User> users = new ArrayList<User>();
    private FileAccessor fileAccessor = new FileAccessor();
    private UserConverter userConverter = new JSONUserConverter();

    private static UserRepository instance;

    public UserRepository() {
        this.loadAll();
    }

    public static void load(UserRepository repository) {
        instance = repository;
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public boolean isEmpty() {
        return users.isEmpty();
    };

    public void loadAll() {
        for (String pathToUser : fileAccessor.getFilesNameInDirectory(ROOT_REPOSITORY)) {
            User newUser;
            try {
                newUser = userConverter.load(ROOT_REPOSITORY + pathToUser);
                users.add(newUser);
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public User getUser(String username) {
        for (User user : users) {
            if (user.hasUsername(username)) {
                return user;
            }
        }
        throw new RepositoryException("User \"" + username + "\" is not found");
    }

    public void addNewUser(String username, String password, Integer access) throws ExistingUsernameException {
        username = username.toLowerCase();
        if (!usernameIsAvailable(username)) {
            throw new ExistingUsernameException("Username \"" + username + "\" is already taken");
        }
        User user = new User(username, password, access);
        users.add(user);
        saveUser(user);
    }

    private boolean usernameIsAvailable(String username) {
        for (User user : users) {
            if (user.hasUsername(username)) {
                return false;
            }
        }
        return true;
    }

    private void saveUser(User user) {
        try {
            userConverter.save(user, ROOT_REPOSITORY + "/" + user.getUsername() + ".json");
        } catch (IOException e) {
            // FIXME Should try saving again later.
            e.printStackTrace();
        }
    }

    protected UserRepository(FileAccessor fileAccessor, JSONUserConverter userConverter) {
        this.fileAccessor = fileAccessor;
        this.userConverter = userConverter;
    }

}
