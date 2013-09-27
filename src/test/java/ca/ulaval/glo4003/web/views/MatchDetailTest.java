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
    private static final String BASE_URL = "http://localhost:8080/matchList";

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @Test
    public void whenSeeingDetailsOfAGivenMatchTheNumberOfTicketsAvailableIsDisplayedForEachSection() throws Exception {
        driver.get(BASE_URL);
        driver.findElement(By.xpath("//table[@id='matchList']/tbody/tr/td[7]/a")).click();
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
}