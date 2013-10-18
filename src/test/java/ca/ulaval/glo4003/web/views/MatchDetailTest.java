package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MatchDetailTest {

    private static final String MATCH_DETAILS_PAGE_TITLE = "Match Details";
    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    private static final String BASE_URL = "http://localhost:8080/";
    private static final String A_PARTICULAR_SPORT = "Football";

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
    public void whenSeeingDetailsOfAGivenMatchTheNumberOfTicketsAvailableIsDisplayedForEachSection() throws Exception {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='" + A_PARTICULAR_SPORT + "']"))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@id='matchList']/tbody/tr/td[8]/a"))).click();
        
        assertTrue(driverWait.until(ExpectedConditions.titleIs(MATCH_DETAILS_PAGE_TITLE)));
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