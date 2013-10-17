package ca.ulaval.glo4003.repository;

import ca.ulaval.glo4003.model.User;

public interface UserRepository {

    public boolean isEmpty();

    public void loadAll();

    public User getUser(String username);

    public void addNewUser(String username, String password, Integer access) throws ExistingUsernameException;
}