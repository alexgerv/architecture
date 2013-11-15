package ca.ulaval.glo4003.testFixture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
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
    public static final String XPATH_FOR_MATCH_LIST_LINK = "//*[@id='dataTable']//tr//td[8]//a";
    public static final String MATCH_LIST_MENU_LINK_TEXT = "Matches";
    public static final String MATCH_LIST_PAGE_TITLE = "Match List";

    public static final String LOGOUT_LINK_TEXT = "Logout";
    public static final String SIGN_UP_LINK_NAME = "Sign Up";
    public static final String EXPECTED_BUTTON_AFTER_LOGOUT = "Sign Up";

    private static final String MATCH_DETAILS_PAGE_TITLE = "Match Details";
    private static final String XPATH_FOR_MATCH_LIST_VIEW_BY_SECTION_LINK = "//*[@id='dataTable']//tr//td[8]//a";

    private static final String A_PARTICULAR_SPORT = "Football";

    private static final String A_SPORT_WITHOUT_MATCHES = "Cheerleading";
    private static final String SELECTOR_FOR_MATCH_A_SPORT_WITHOUT_MATCHES = "input[name='" + A_SPORT_WITHOUT_MATCHES
                                                                             + "']";
    private static final String XPATH_FOR_MATCH_SPORT = "//*[@id='dataTable']//tr//td[4]";

    private static final String A_NEW_USER_NAME = "olivier_dugas";
    private static final String SELECTOR_SINGUP_SUCCESS_MESSAGE = "div[class=\"alert alert-info\"]";
    private static final String EXPECTED_SUCCESS_MESSAGE = "Successfully created user";
    private static final String EXPECTED_FAIL_SIGNUP_MESSAGE = "Username \"" + A_NEW_USER_NAME + "\" is already taken";
    private static final String PATH_TO_CREATED_USER_FILE = "./users/olivier_dugas.json";

    private static final String XPATH_FOR_MATCH_LIST_TICKETS_NUMBER = "//*[@id='dataTable']//tr//td[8]//strong";
    private static final String CLASS_NAME_FOR_TICKET_BY_SECTION = "ticketsBySection";

    private static final String SELECTOR_FOR_MATCH_A_PARTICULAR_VENUE = "input[name='Stade Telus']";

    private static final String XPATH_FOR_SECTION = "//table[@id='matchDetails']/tbody/tr/td/a/strong";
    private static final String GENERIC_SELECTOR_FOR_SUBMIT = "button[type='submit']";
    private static final String XPATH_FOR_CREDIT_CARD_CHOICE = "//input[@name='credit_type' and @value='Mistercard']";
    private static final String XPATH_FOR_CREDIT_CARD_NUMBER = "//input[@name='creditCard_number']";
    private static final String A_VALID_CREDIT_CARD_NUMBER = "1234123412341234";

    private static final String XPATH_NAME_SECTION = "//*[@class='row']//div//tr[2]//td[1]";
    private static final String XPATH_HOMETEAM_SECTION = "//*[@class='row']//div//tr[2]//td[2]";
    private static final String XPATH_VISITORTEAM_SECTION = "//*[@class='row']//div//tr[3]//td[2]";
    private static final String XPATH_DATE_SECTION = "//*[@class='row']//div[2]//tr//td[2]";
    private static final String XPATH_ADMISSIONTYPE_SECTION = "//*[@class='row']//div//tr[4]//td[2]";
    private static final String XPATH_SECTIONNAME_SECTION = "//*[@class='row']//div[2]//tr[2]//td[2]";

    private static final String SELECTOR_FOR_BUY_BUTTON = "input[value='Buy']";
    private static final String PURCHASE_PAGE_TITLE = "Ticket Purchase";
    private static final String SELECTOR_TICKET_QUANTITY = "input[name='quantity']";

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
    
    public int navigateToMatchDetailsAndReturnTheNumberOfTicketsJustBefore() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_A_PARTICULAR_SPORT)))
                  .click();
        
        int totalTickets = getTotalNumberOfTicketsForAParticularMatch();
        
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_MATCH_LIST_LINK))).click();
        
        return totalTickets;
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

    public void goOnMatchListPage() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_MENU_LINK_TEXT))).click();
    }

    public void SearchForAParticularSport() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_A_PARTICULAR_SPORT)))
                  .click();
    }

    public void assertUserIsOnMatchListPage() {
        assertTrue(driverWait.until(ExpectedConditions.titleIs(MATCH_LIST_PAGE_TITLE)));
    }

    public void assertNumberOfAvailableTicketsForAMatchIsDisplayedForEachSection() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_MATCH_LIST_VIEW_BY_SECTION_LINK)))
                  .click();

        assertTrue(driverWait.until(ExpectedConditions.titleIs(MATCH_DETAILS_PAGE_TITLE)));
    }

    public void searchForASportWithoutAnyMatches() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_SPORT_WITHOUT_MATCHES)))
                  .click();
    }

    public void assertAllShownMatchesAreOfTheSelectedSport() {
        List<WebElement> sports =
                                  driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_MATCH_SPORT)));
        for (WebElement sport : sports) {
            assertEquals(A_PARTICULAR_SPORT, sport.getText());
        }
    }

    public void goOnSignUpPage() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(SIGN_UP_LINK_NAME))).click();
    }

    public void signUp() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(A_NEW_USER_NAME);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT_FIELD_ID)))
                  .sendKeys(A_PASSWORD);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID))).click();
    }

    public void assertSignUpWasSuccessful() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(SELECTOR_SINGUP_SUCCESS_MESSAGE),
                                                                                EXPECTED_SUCCESS_MESSAGE)));
    }

    public void removeCreatedUser() {
        File file = new File(PATH_TO_CREATED_USER_FILE);
        file.delete();
    }

    public void assertSignUpWasNotSuccessulAndAnErrorMessageWasDisplayed() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(SELECTOR_SINGUP_SUCCESS_MESSAGE),
                                                                                EXPECTED_FAIL_SIGNUP_MESSAGE)));
    }

    public int getTotalNumberOfTicketsForAParticularMatch() {
        String totalNumberOfTicketsText =
                                          driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_FOR_MATCH_LIST_TICKETS_NUMBER)))
                                                    .getText();

        return Integer.parseInt(totalNumberOfTicketsText);
    }

    public void clickOnAParticularMatch() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_MATCH_LIST_LINK))).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(CLASS_NAME_FOR_TICKET_BY_SECTION)));
    }

    public int getTotalNumberOfTicketsFromSubSectionsForAParticularMatch() {
        List<WebElement> list = driver.findElements(By.className("ticketsBySection"));
        int totalAvailableTickets = 0;
        for (WebElement element : list) {
            totalAvailableTickets += Integer.parseInt(element.getText());
        }
        return totalAvailableTickets;
    }

    public void SearchForAParticularVenue() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_PARTICULAR_VENUE)))
                  .click();
    }

    public List<WebElement> getSearchResults() {
        return driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_MATCH_SPORT)));
    }

    public void unFilterSearchForAParticularVenue() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_PARTICULAR_VENUE)))
                  .click();
    }

    public void chooseASectionInMatchDetails() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_SECTION))).click();
    }

    public void buyATicket() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_CREDIT_CARD_CHOICE))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_CREDIT_CARD_NUMBER)))
                  .sendKeys(A_VALID_CREDIT_CARD_NUMBER);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(GENERIC_SELECTOR_FOR_SUBMIT))).click();
    }

    public void assertPriceOpposingTeamsDateAdmissionTypeAndSectionAreDisplayed() {
        String priceText =
                           driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_NAME_SECTION)))
                                     .getText();
        String homeTeamText =
                              driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_HOMETEAM_SECTION)))
                                        .getText();
        String visitorTeamText =
                                 driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_VISITORTEAM_SECTION)))
                                           .getText();
        String dateText =
                          driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_DATE_SECTION)))
                                    .getText();
        String admissionText =
                               driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_ADMISSIONTYPE_SECTION)))
                                         .getText();
        String sectionText =
                             driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_SECTIONNAME_SECTION)))
                                       .getText();

        assertTrue(priceText.length() != 0);
        assertTrue(homeTeamText.length() != 0);
        assertTrue(visitorTeamText.length() != 0);
        assertTrue(dateText.length() != 0);
        assertTrue(admissionText.length() != 0);
        assertTrue(sectionText.length() != 0);
    }

    public void selectANumberOfTicketsForCurrentSection(String aNumberOfTickets) {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SELECTOR_TICKET_QUANTITY)))
                  .sendKeys("\b" + aNumberOfTickets);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_BUY_BUTTON))).click();
    }

    public void assertOnPurchasePage() {
        assertTrue(driverWait.until(ExpectedConditions.titleIs(PURCHASE_PAGE_TITLE)));
    }

    public void assertBuyingTheRightNumberOfTickets(String aNumberOfTickets) {
        String url = driver.getCurrentUrl().split("quantity=")[1];
        assertTrue(url.equals(aNumberOfTickets));
    }

    public void assertNumberOfAvailableTicketsDecreaseByTheNumberOfBoughtTickets(int initialNumberOfTickets, int finalNumberOfTickets, String aNumberOfTickets) {
        assertEquals(String.valueOf(initialNumberOfTickets - finalNumberOfTickets), aNumberOfTickets);
    }

}
