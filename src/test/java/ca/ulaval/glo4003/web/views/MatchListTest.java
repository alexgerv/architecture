package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MatchListTest {

    private static final String NUMBER_OF_AVALAIBLE_TICKETS_IN_FIRST_MATCH = "10";
    private static final String NUMBER_OF_AVALAIBLE_TICKETS_IN_SECOND_MATCH = "20";
    private static final String BASE_URL = "http://localhost:8080/";

    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @Test
    public void whenOpenningMatchListPageTheNumberOfAvalaibleTicketsIsDisplayedForEachMatch() throws Exception {
        assertEquals(NUMBER_OF_AVALAIBLE_TICKETS_IN_FIRST_MATCH,
                     driver.findElement(By.xpath("//*[@id='matchList']//tr[1]//td[7]//strong")).getText());
        assertEquals(NUMBER_OF_AVALAIBLE_TICKETS_IN_SECOND_MATCH,
                     driver.findElement(By.xpath("//*[@id='matchList']//tr[2]//td[7]//strong")).getText());
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
