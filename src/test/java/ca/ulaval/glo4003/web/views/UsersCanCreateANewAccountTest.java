package ca.ulaval.glo4003.web.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.ulaval.glo4003.testFixture.TestFixture;

public class UsersCanCreateANewAccountTest {

    private TestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new TestFixture();
        fixture.init();
        fixture.goOnHomePage();
    }

    @Test
    public void aUserCanCreateANewAccount() throws Exception {
        aUserCanCreateAccountWhenNotLoggedIn();
        anErrorMessageIsShownWhenTryingToCreateAnAccountWithAnExistingUser();
    }

    public void aUserCanCreateAccountWhenNotLoggedIn() throws Exception {
        fixture.goOnSignUpPage();
        fixture.signUp();
        fixture.assertSignUpWasSuccessful();
    }

    public void anErrorMessageIsShownWhenTryingToCreateAnAccountWithAnExistingUser() {
        fixture.goOnSignUpPage();
        fixture.signUp();
        fixture.assertSignUpWasNotSuccessulAndAnErrorMessageWasDisplayed();
    }

    @After
    public void tearDown() throws Exception {
        fixture.close();
        fixture.removeCreatedUser();
    }

}
