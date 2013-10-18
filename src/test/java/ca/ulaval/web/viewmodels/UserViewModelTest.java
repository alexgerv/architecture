package ca.ulaval.web.viewmodels;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.web.viewmodels.UserViewModel;

public class UserViewModelTest {

    private static final String A_PASSWORD = "A_PASSWORD";
    private static final String A_USERNAME = "A_USER";
    private UserViewModel user;

    @Before
    public void setUp() {
        user = new UserViewModel();
        user.setUsername(A_USERNAME);
        user.setPassword(A_PASSWORD);
    }

    @Test
    public void canGetUsername() {
        assertEquals(user.getUsername(), A_USERNAME);
    }

    @Test
    public void canGetPassword() {
        assertEquals(user.getPassword(), A_PASSWORD);
    }

    @Test
    public void canSetUsername() {
        String newUsername = "NEW_USERNAME";
        user.setPassword(newUsername);
        assertEquals(user.getPassword(), newUsername);
    }

    @Test
    public void canSetPassword() {
        String newPassword = "NEW_PASSWORD";
        user.setPassword(newPassword);
        assertEquals(user.getPassword(), newPassword);
    }
}
