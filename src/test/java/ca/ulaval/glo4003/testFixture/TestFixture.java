package ca.ulaval.glo4003.testFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestFixture {

    public static final String USER_NAME_INPUT_FIELD_ID = "usernameInput";
    public static final String LOG_IN_LINK_NAME = "Log In";
    public static final String A_PASSWORD = "12345";
    public static final String PASSWORD_INPUT_FIELD_ID = "password";
    public static final String A_USER_NAME = "user_test";
    public static final String SUBMIT_BUTTON_ID = "submit";
    public static final String SELECTOR_HELLO_MESSAGE = "div[class=\"navbar-form navbar-right\"]";
    public static final String EXPECTED_LOGGED_IN_MESSAGE = "Hello " + A_USER_NAME + " Logout";
    public static final String BASE_URL = "http://localhost:8080/";

    public static final String AN_INVALID_PASSWORD = "abcde";
    public static final String SELECTOR_INVALID_PASSWORD_MESSAGE = "div[class=\"alert alert-info\"]";
    public static final String EXPECTED_FAIL_MESSAGE = "You have entered an invalid username or password!";

    public static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    public static final String SELECTOR_FOR_A_PARTICULAR_SPORT = "input[name='Football']";
    public static final String XPATH_FOR_MATCH_LIST_LINK = "//*[@id='matchList']//tr//td[8]//a";
    public static final String MATCH_LIST_MENU_LINK_TEXT = "Matches";
    public static final String MATCH_LIST_PAGE_TITLE = "Match List";

    public static final String LOGOUT_LINK_TEXT = "Logout";
    public static final String SIGN_UP_LINK_NAME = "Sign Up";
    public static final String EXPECTED_BUTTON_AFTER_LOGOUT = "Sign Up";

    private static final String MATCH_DETAILS_PAGE_TITLE = "Match Details";
    private static final String A_PARTICULAR_SPORT = "Football";

    private static final String A_SPORT_WITHOUT_MATCHES = "Cheerleading";
    private static final String SELECTOR_FOR_MATCH_A_SPORT_WITHOUT_MATCHES = "input[name='" + A_SPORT_WITHOUT_MATCHES;
    private static final String XPATH_FOR_MATCH_SPORT = "//*[@id='matchList']//tr//td[4]";
    private static final String ID_MESSAGE_WITH_NO_MATCH = "searchMessage";
    private static final String EXPECTED_NO_MATCH_MESSAGE = "Your search produced no results.";

    public WebDriver driver;
    public WebDriverWait driverWait;
    private StringBuffer verificationErrors = new StringBuffer();

    public void init() {
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, 10);
    }

    public void close() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public void goOnHomePage() {
        driver.get(BASE_URL);
    }

    public void goOnLoginPage() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(LOG_IN_LINK_NAME))).click();
    }

    public void logInWithRightCredentials() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(A_USER_NAME);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT_FIELD_ID)))
                  .sendKeys(A_PASSWORD);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID))).click();
    }

    public void assertUserIsLoggedIn() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(SELECTOR_HELLO_MESSAGE),
                                                                                EXPECTED_LOGGED_IN_MESSAGE)));
    }

    public void loginWithInvalidPassword() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(A_USER_NAME);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT_FIELD_ID)))
                  .sendKeys(AN_INVALID_PASSWORD);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID))).click();
    }

    public void assertAnInvalidPasswordMessageIsShown() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(SELECTOR_INVALID_PASSWORD_MESSAGE),
                                                                                EXPECTED_FAIL_MESSAGE)));
    }

    public void navigateToMatchDetails() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_A_PARTICULAR_SPORT)))
                  .click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_MATCH_LIST_LINK))).click();
    }

    public void logOut() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(LOGOUT_LINK_TEXT))).click();
    }

    public void assertUserIsAnonymous() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.linkText(SIGN_UP_LINK_NAME),
                                                                                EXPECTED_BUTTON_AFTER_LOGOUT)));
    }

    public void clickOnMatchListButton() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
    }

    public void clickOnMatchesButtonInNavigationMenu() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_MENU_LINK_TEXT))).click();
    }

    public void FilterSportsForAParticularSport() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_A_PARTICULAR_SPORT)))
                  .click();
    }

    public void assertUserIsOnMatchListPage() {
        assertTrue(driverWait.until(ExpectedConditions.titleIs(MATCH_LIST_PAGE_TITLE)));
    }

    public void assertNumberOfAvailableTicketsForAMatchIsDisplayedForEachSection() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='matchList']/tbody/tr/td[8]/a")))
                  .click();

        assertTrue(driverWait.until(ExpectedConditions.titleIs(MATCH_DETAILS_PAGE_TITLE)));
    }

    public void searchForASportWithoutAnyMatches() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_SPORT_WITHOUT_MATCHES)))
                  .click();
    }

    public void assertAMessageSayingNoSportsMatchesTheSearchCriteriasIsDisplayed() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.id(ID_MESSAGE_WITH_NO_MATCH),
                                                                                EXPECTED_NO_MATCH_MESSAGE)));
    }

    public void assertAllShownMatchesAreOfTheSelectedSport() {
        List<WebElement> sports =
                                  driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_MATCH_SPORT)));
        for (WebElement sport : sports) {
            assertEquals(A_PARTICULAR_SPORT, sport.getText());
        }
    }

}
