package ca.ulaval.glo4003.web.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.LoginFixture;

public class UsersCanLogInTest {

    private LoginFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new LoginFixture();
        fixture.init();
        fixture.goOnHomePage();
        fixture.goOnLoginPage();
    }

    @After
    public void tearDown() throws Exception {
        fixture.close();
    }

    @Test
    public void anAnonymousUserCanConnectWithValidUsernameAndPasswordCombination() throws Exception {
        fixture.assertUserIsAnonymous();
        fixture.logInWithRightCredentials();
        fixture.assertUserIsLoggedIn();
    }

    @Test
    public void theUserIsInformedWhenHeTriesToLoginWithAnInvalidUsernamePasswordCombination() {
        fixture.loginWithInvalidPassword();
        fixture.assertAnInvalidPasswordMessageIsShown();
    }

    @Test
    public void aUserCanNavigateTheSiteWhileStayingLoggedIn() {
        fixture.logInWithRightCredentials();
        fixture.navigateToMatchDetails();
        fixture.assertUserIsLoggedIn();
    }

    @Test
    public void aLoggedInUserCanLogOutAtAnyTime() {
        fixture.logInWithRightCredentials();
        fixture.navigateToMatchDetails();
        fixture.logOut();

        fixture.assertUserIsAnonymous();
    }

}
