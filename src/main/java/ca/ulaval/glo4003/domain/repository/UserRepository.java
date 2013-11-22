package ca.ulaval.glo4003.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.ulaval.glo4003.domain.user.InvalidEmailAddressException;
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
        if (!validateEmailAddress(emailAddress)) {
            throw new InvalidEmailAddressException("Username \"" + emailAddress + "\" is not a valid email address");
        }
        if (!emailAddressIsAvailable(emailAddress)) {
            throw new ExistingUsernameException("Username \"" + emailAddress + "\" is already taken");
        }
        User user = new User(emailAddress, password, access);
        users.add(user);
        saveUser(user);
    }

    private boolean validateEmailAddress(String emailAddress) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(emailAddress);

        return m.find();
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