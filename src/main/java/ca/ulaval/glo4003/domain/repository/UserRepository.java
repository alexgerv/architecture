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

    public User getUser(String emailAddress) {
        for (User user : users) {
            if (user.hasEmailAddress(emailAddress)) {
                return user;
            }
        }
        throw new RepositoryException("User \"" + emailAddress + "\" is not found");
    }

    public void addNewUser(String emailAddress, String password, Integer access) throws ExistingUsernameException {
        emailAddress = emailAddress.toLowerCase();
        if (!emailAddressIsAvailable(emailAddress)) {
            throw new ExistingUsernameException("Username \"" + emailAddress + "\" is already taken");
        }
        User user = new User(emailAddress, password, access);
        users.add(user);
        saveUser(user);
    }

    private boolean emailAddressIsAvailable(String emailAddress) {
        for (User user : users) {
            if (user.hasEmailAddress(emailAddress)) {
                return false;
            }
        }
        return true;
    }

    protected abstract void saveUser(User user);
}