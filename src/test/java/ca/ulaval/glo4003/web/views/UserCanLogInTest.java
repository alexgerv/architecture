package ca.ulaval.glo4003.web.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UserCanLogInTest {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
        fixture.goOnLoginPage();
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

    @After
    public void tearDown() throws Exception {
        fixture.close();
    }

}
