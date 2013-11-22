package ca.ulaval.glo4003.domain.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private static final String PASSWORD = "PASSWORD";
    private static final String USERNAME = "USERNAME";
    private static final Integer ACCESS = 0;
    private static final String DIFFERENT_USERNAME = "A_DIFFERENT_USERNAME";
    private User aUser;

    @Before
    public void setup() {
        aUser = new User(USERNAME, PASSWORD, ACCESS);
    }

    @Test
    public void hasUsernameReturnsTrueWhenUsernamesAreSame() {
        assertTrue(aUser.hasEmailAddress(USERNAME));
    }

    @Test
    public void hasUsernameReturnsFalseWhenUsernamesAreSame() {
        assertFalse(aUser.hasEmailAddress(DIFFERENT_USERNAME));
    }

    @Test
    public void canGetUsername() {
        assertEquals(aUser.getEmailAddress(), USERNAME);
    }

    @Test
    public void canGetPassword() {
        assertEquals(aUser.getPassword(), PASSWORD);
    }

    @Test
    public void canGetAccess() {
        assertEquals(aUser.getAccess(), ACCESS);
    }

}
