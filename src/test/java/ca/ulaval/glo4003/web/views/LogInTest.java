package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInTest {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String LOG_IN_LINK_NAME = "Log In";
    private static final String USER_NAME_INPUT_FIELD_ID = "usernameInput";
    private static final String A_USER_NAME = "user_test";
    private static final String PASSWORD_INPUT_FIELD_ID = "password";
    private static final String A_PASSWORD = "12345";
    private static final String SUBMIT_BUTTON_ID = "submit";
    private static final String SELECTOR_HELLO_MESSAGE = "div[class=\"navbar-form navbar-right\"]";
    private static final String EXPECTED_LOGGED_IN_MESSAGE = "Hello " + A_USER_NAME + " Logout";

    private static final String AN_INVALID_PASSWORD = "abcde";
    private static final String SELECTOR_INVALID_PASSWORD_MESSAGE = "div[class=\"alert alert-info\"]";
    private static final String EXPECTED_FAIL_MESSAGE = "You have entered an invalid username or password!";

    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    private static final String SELECTOR_FOR_A_PARTICULAR_SPORT = "input[name='Football']";
    private static final String XPATH_FOR_MATCH_LIST_LINK = "//*[@id='matchList']//tr//td[8]//a";

    private static final String LOGOUT_LINK_TEXT = "Logout";
    private static final String SIGN_UP_LINK_NAME = "Sign Up";
    private static final String EXPECTED_BUTTON_AFTER_LOGOUT = "Sign Up";

    private WebDriver driver;
    WebDriverWait driverWait;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, 10);

        driver.get(BASE_URL);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(LOG_IN_LINK_NAME))).click();
    }

    @Test
    public void canConnectWithValidUserNamePasswordCombination() throws Exception {
        logIn();
        assertLoggedIn();
    }

    private void logIn() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(A_USER_NAME);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT_FIELD_ID)))
                  .sendKeys(A_PASSWORD);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID))).click();
    }

    private void assertLoggedIn() {
        String successMessage = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SELECTOR_HELLO_MESSAGE)))
                                          .getText();
        assertEquals(EXPECTED_LOGGED_IN_MESSAGE, successMessage);
    }

    @Test
    public void whenAnInvalidCombinationOfUserNamePasswordIsEnteredAMessageIsShown() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(A_USER_NAME);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT_FIELD_ID)))
                  .sendKeys(AN_INVALID_PASSWORD);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID))).click();

        String failMessage = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SELECTOR_INVALID_PASSWORD_MESSAGE)))
                                       .getText();

        assertEquals(EXPECTED_FAIL_MESSAGE, failMessage);

    }

    @Test
    public void whenLoggedInCanChangePageAndStillBeLogged() {
        logIn();
        navigateToMatchDetails();
        assertLoggedIn();
    }

    private void navigateToMatchDetails() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_A_PARTICULAR_SPORT)))
                  .click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_MATCH_LIST_LINK))).click();
    }

    @Test
    public void whenLoggedInCanLogOutAnyTime() {
        logIn();
        navigateToMatchDetails();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(LOGOUT_LINK_TEXT))).click();

        String signUpButtonText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(SIGN_UP_LINK_NAME)))
                                            .getText();

        assertEquals(EXPECTED_BUTTON_AFTER_LOGOUT, signUpButtonText);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
