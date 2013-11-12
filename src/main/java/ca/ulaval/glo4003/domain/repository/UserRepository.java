package ca.ulaval.glo4003.domain.repository;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4003.domain.user.User;

public abstract class UserRepository {

    protected List<User> users = new ArrayList<User>();

    public boolean isEmpty() {
        return users.isEmpty();
    };

    public abstract void loadAll();

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

    protected abstract void saveUser(User user);
}