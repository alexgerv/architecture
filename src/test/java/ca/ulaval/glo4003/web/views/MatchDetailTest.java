package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MatchDetailTest {

    private static final String MATCH_DETAILS_PAGE_TITLE = "Match Details";
    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    private static final String BASE_URL = "http://localhost:8080/";
    private static final String A_PARTICULAR_SPORT = "Football";

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void whenSeeingDetailsOfAGivenMatchTheNumberOfTicketsAvailableIsDisplayedForEachSection() throws Exception {
        driver.findElement(By.linkText(MATCH_LIST_HOME_LINK_TEXT)).click();
        waitForPage();
        driver.findElement(By.cssSelector("input[name='" + A_PARTICULAR_SPORT + "']")).click();
        waitForPage();
        driver.findElement(By.xpath("//table[@id='matchList']/tbody/tr/td[8]/a")).click();
        waitForPage();
        
        assertEquals(MATCH_DETAILS_PAGE_TITLE, driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    
    private void waitForPage() throws InterruptedException{
        synchronized (driver) {
            driver.wait(1000);
        }
    }
}