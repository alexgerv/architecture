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
    public static final String A_USER_NAME = "userglo4003@gmail.com";

    public static final String AN_OTHER_USER_NAME = "otheruserglo4003@gmail.com";
    public static final String OTHER_USER_PASSWORD = "54321";

    public static final String SUBMIT_BUTTON_ID = "submit";
    public static final String SELECTOR_HELLO_MESSAGE = "div[class=\"navbar-form navbar-right\"] label";
    public static final String EXPECTED_LOGGED_IN_MESSAGE = "Hello " + A_USER_NAME;
    public static final String BASE_URL = "http://localhost:8080/";

    public static final String AN_INVALID_PASSWORD = "abcde";
    public static final String SELECTOR_INVALID_PASSWORD_MESSAGE = "div[class=\"alert alert-info\"]";
    public static final String EXPECTED_FAIL_MESSAGE_WRONG_MESSAGE = "You have entered an invalid username or password!";

    public static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    public static final String MATCH_LIST_BUTTON = "Matches";
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

    private static final String A_NEW_USER_NAME = "luke@skywalker.com";
    private static final String SELECTOR_SINGUP_SUCCESS_MESSAGE = "div[class=\"alert alert-info\"]";
    private static final String EXPECTED_SUCCESS_MESSAGE = "Successfully created user";
    private static final String EXPECTED_FAIL_SIGNUP_MESSAGE = "Username \"" + A_NEW_USER_NAME + "\" is already taken";
    private static final String PATH_TO_CREATED_USER_FILE = "./users/luke@skywalker.com.json";

    private static final String SELECTOR_BUY_SUCCESS_MESSAGE = "/html/body/div[2]/h2";
    private static final String EXPECTED_PAYMENT_SUCCESS = "Your payment has successfully completed";

    private static final String XPATH_FOR_MATCH_LIST_TICKETS_NUMBER = "//*[@id='dataTable']//tr//td[8]//strong";
    private static final String CLASS_NAME_FOR_TICKET_BY_SECTION = "ticketsBySection";

    private static final String SELECTOR_FOR_MATCH_A_PARTICULAR_VENUE = "input[name='Stade Telus']";

    private static final String XPATH_FOR_SECTION = "//table[@id='matchDetails']/tbody/tr/td/a/strong";
    private static final String GENERIC_SELECTOR_FOR_SUBMIT = "button[type='submit']";

    private static final String BUY_SELECTOR_FOR_SUBMIT = "button[id='buy']";
    private static final String CART_SELECTOR_FOR_SUBMIT = "button[id='cart']";

    private static final String XPATH_FOR_CREDIT_CARD_CHOICE = "//input[@name='type']";
    private static final String XPATH_FOR_CREDIT_CARD_NUMBER = "//input[@name='number']";
    private static final String A_VALID_CREDIT_CARD_NUMBER = "1234123412341234";

    private static final String ID_PRICE_SECTION = "price";
    private static final String XPATH_HOMETEAM_SECTION = "//*[@class='row']//div//tr[2]//td[2]";
    private static final String XPATH_VISITORTEAM_SECTION = "//*[@class='row']//div//tr[3]//td[2]";
    private static final String XPATH_DATE_SECTION = "//*[@class='row']//div[2]//tr//td[2]";
    private static final String XPATH_ADMISSIONTYPE_SECTION = "//*[@class='row']//div//tr[4]//td[2]";
    private static final String XPATH_SECTIONNAME_SECTION = "//*[@class='row']//div[2]//tr[2]//td[2]";

    private static final String PURCHASE_PAGE_TITLE = "Ticket Purchase";
    private static final String SELECTOR_TICKET_QUANTITY = "input[name='quantity']";
    private static final String ID_QUANTITY_STRING = "quantity";
    private static final String XPATH_FOR_TICKET_QUANTITY_FIRST_TYPE = "//table/tbody/tr/td[10]/form/div/input";
    private static final String XPATH_FOR_ANOTHER_SECTION = "//table[@id='matchDetails']/tbody/tr[2]/td/a/strong";
    private static final String CART_LINK_NAME = "View Shopping Cart";
    private static final String SELECTOR_NOT_ENOUGH_TICKETS = "div[class=\"alert alert-info\"]";
    private static final String EXPECTED_FAIL_MESSAGE_NOT_ENOUGH_TICKETS = "There are not enough tickets to purchase ";
    private static final String EMPTY_CART_BUTTON = "Empty cart";
    private static final String SELECTOR_CART_EMPTY = "div[class=\"alert alert-info\"]";
    private static final String EXPECTED_MESSAGE_CART_IS_EMPTY = "Your cart is currently empty";
    private static final String XPATH_FOR_REMOVE_TICKETS_FIRST_TYPE = "//table/tbody/tr/td[11]/button";
    private static final String XPATH_FOR_UPDATE_TICKET_QUANTITY_FIRST_TYPE = "//table/tbody/tr/td[10]/form/a";
    private static final String XPATH_FOR_CART_TICKETS = "//*[@id='cartContent']//tr//td[4]";
    private static final String CHECKOUT_CART_BUTTON = "Checkout";
    private static final String XPATH_FOR_RECEIPT = "/html/body/div[2]/div/div/div/div[2]";

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

    public void logInWithAnOtherRightCredentials() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(AN_OTHER_USER_NAME);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT_FIELD_ID)))
                  .sendKeys(OTHER_USER_PASSWORD);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID))).click();
    }

    public void logInWithNewUserName() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(A_NEW_USER_NAME);
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
                                                                                EXPECTED_FAIL_MESSAGE_WRONG_MESSAGE)));
    }

    public void navigateToMatchDetails() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_BUTTON))).click();
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
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_BUTTON))).click();
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
        List<WebElement> sports = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_MATCH_SPORT)));
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

    public void assertBuyWasSuccessful() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.xpath(SELECTOR_BUY_SUCCESS_MESSAGE),
                                                                                EXPECTED_PAYMENT_SUCCESS)));
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
        String totalNumberOfTicketsText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_FOR_MATCH_LIST_TICKETS_NUMBER)))
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

    public void chooseAnotherSectionInMatchDetails() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_ANOTHER_SECTION))).click();
    }

    public void payForTickets() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_CREDIT_CARD_CHOICE))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_CREDIT_CARD_NUMBER)))
                  .sendKeys(A_VALID_CREDIT_CARD_NUMBER);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(GENERIC_SELECTOR_FOR_SUBMIT))).click();
    }

    public void assertPriceOpposingTeamsDateAdmissionTypeAndSectionAreDisplayed() {
        String priceText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID_PRICE_SECTION)))
                                     .getText();
        String homeTeamText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_HOMETEAM_SECTION)))
                                        .getText();
        String visitorTeamText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_VISITORTEAM_SECTION)))
                                           .getText();
        String dateText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_DATE_SECTION)))
                                    .getText();
        String admissionText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_ADMISSIONTYPE_SECTION)))
                                         .getText();
        String sectionText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_SECTIONNAME_SECTION)))
                                       .getText();

        assertTrue(priceText.length() != 0);
        assertTrue(homeTeamText.length() != 0);
        assertTrue(visitorTeamText.length() != 0);
        assertTrue(dateText.length() != 0);
        assertTrue(admissionText.length() != 0);
        assertTrue(sectionText.length() != 0);
    }

    public void selectATicketQuantityForCurrentSection(String aTicketQuantity) {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SELECTOR_TICKET_QUANTITY)))
                  .sendKeys("\b" + aTicketQuantity);
    }

    public void buySelectedTickets() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(BUY_SELECTOR_FOR_SUBMIT))).click();
    }

    public void assertOnPurchasePage() {
        assertTrue(driverWait.until(ExpectedConditions.titleIs(PURCHASE_PAGE_TITLE)));
    }

    public void assertBuyingTheRightTicketQuantity(String aTicketQuantity) {
        String ticketQuantity = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(ID_QUANTITY_STRING)))
                                          .getText();
        assertTrue(ticketQuantity.equals(aTicketQuantity));
    }

    public void assertNumberOfAvailableTicketsDecreaseByTheNumberOfBoughtTickets(int initialNumberOfTickets,
                                                                                 int finalNumberOfTickets,
                                                                                 String aNumberOfTickets) {
        assertEquals(String.valueOf(initialNumberOfTickets - finalNumberOfTickets), aNumberOfTickets);
    }

    public void addSelectedTicketsToCart() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(CART_SELECTOR_FOR_SUBMIT))).click();
    }

    public int getFirstTicketTypeQuantityInCart() {
        return Integer.parseInt(driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_FOR_TICKET_QUANTITY_FIRST_TYPE)))
                                          .getAttribute("value"));

    }

    public void assertTwoTypesOfTicketsInCart() {
        List<WebElement> tickets = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_CART_TICKETS)));
        assertEquals(2, tickets.size());
    }

    public void goOnCartPage() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(CART_LINK_NAME))).click();
    }

    public void assertTheCartIsEmpty() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(SELECTOR_CART_EMPTY),
                                                                                EXPECTED_MESSAGE_CART_IS_EMPTY)));
    }

    public void setNewTicketQuantityFromFirstTicketTypeFromCart(String newQuantity) {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_TICKET_QUANTITY_FIRST_TYPE)))
                  .clear();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_TICKET_QUANTITY_FIRST_TYPE)))
                  .sendKeys(newQuantity);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_UPDATE_TICKET_QUANTITY_FIRST_TYPE)))
                  .click();
    }

    public void assertNotEnoughTicketsAvalaibleErrorMessageIsDisplayed() {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.id("warningMessage"),
                                                                                EXPECTED_FAIL_MESSAGE_NOT_ENOUGH_TICKETS)));
    }

    public void emptyCart() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(EMPTY_CART_BUTTON))).click();
    }

    public void removeFirstTicketTypeFromCart() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_REMOVE_TICKETS_FIRST_TYPE)))
                  .click();
    }

    public void checkoutCart() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(CHECKOUT_CART_BUTTON))).click();
    }

    public void assertUserBoughtAllHisTickets() {
        List<WebElement> tickets = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("quantity")));
        assertEquals(2, tickets.size());
    }

}
