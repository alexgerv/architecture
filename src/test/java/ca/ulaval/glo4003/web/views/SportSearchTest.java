package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SportSearchTest {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    private static final String A_PARTICULAR_SPORT = "Football";
    private static final String SELECTOR_FOR_MATCH_A_PARTICULAR_SPORT = "input[name='" + A_PARTICULAR_SPORT + "']";
    private static final String A_SPORT_WITHOUT_MATCHES = "Cheerleading";
    private static final String SELECTOR_FOR_MATCH_A_SPORT_WITHOUT_MATCHES = "input[name='" + A_SPORT_WITHOUT_MATCHES + "']";
    private static final String XPATH_FOR_MATCH_SPORT = "//*[@id='matchList']//tr//td[4]";

    private static final String ID_MESSAGE_WITH_NO_MATCH = "searchMessage";
    private static final String EXPECTED_NO_MATCH_MESSAGE = "Your search produced no results.";

    private WebDriver driver;
    WebDriverWait driverWait;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, 10);
        driver.get(BASE_URL);
    }

    @Test
    public void whenChoosingASportTheMatchesForThisSportAreDisplayed() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_PARTICULAR_SPORT))).click();

        List<WebElement> sports = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_MATCH_SPORT)));
        for (WebElement sport : sports) {
            assertEquals(A_PARTICULAR_SPORT, sport.getText());
        }
    }

    @Test
    public void whenChoosingASportWithoutMatchesAMessageIsDisplayed() {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_SPORT_WITHOUT_MATCHES)))
                  .click();

        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.id(ID_MESSAGE_WITH_NO_MATCH), EXPECTED_NO_MATCH_MESSAGE)));
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