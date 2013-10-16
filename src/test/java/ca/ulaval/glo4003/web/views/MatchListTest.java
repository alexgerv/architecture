package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MatchListTest {

    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
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
        driver.findElement(By.linkText(MATCH_LIST_HOME_LINK_TEXT)).click();
        int totalNumberOfTickets = Integer.parseInt(driver.findElement(By.xpath("//*[@id='matchList']//tr//td[7]//strong")).getText());
        waitForPage();
        
        driver.findElement(By.xpath("//*[@id='matchList']//tr//td[7]//a")).click();
        waitForPage();
        
        List<WebElement> list = driver.findElements(By.className("ticketsBySection"));
        int totalAvailableTickets = 0;
        for (WebElement element : list) {
            totalAvailableTickets += Integer.parseInt(element.getText());
        }
        
        assertEquals(totalAvailableTickets, totalNumberOfTickets);
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
