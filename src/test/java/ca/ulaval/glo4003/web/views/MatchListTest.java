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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MatchListTest {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    private static final String A_PARTICULAR_SPORT = "Football";
    private static final String SELECTOR_FOR_A_PARTICULAR_SPORT = "input[name='" + A_PARTICULAR_SPORT + "']";
    private static final String XPATH_FOR_MATCH_LIST_TICKETS_NUMBER = "//*[@id='matchList']//tr//td[8]//strong";
    private static final String XPATH_FOR_MATCH_LIST_LINK = "//*[@id='matchList']//tr//td[8]//a";
    private static final String CLASS_NAME_FOR_TICKET_BY_SECTION = "ticketsBySection";

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
    public void whenOpenningMatchListPageTheNumberOfAvalaibleTicketsIsDisplayedForAParticularSport() throws Exception {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_A_PARTICULAR_SPORT)))
                  .click();
        String totalNumberOfTicketsText = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_FOR_MATCH_LIST_TICKETS_NUMBER)))
                                                    .getText();
        
        int totalNumberOfTickets = Integer.parseInt(totalNumberOfTicketsText);
        
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH_FOR_MATCH_LIST_LINK))).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className(CLASS_NAME_FOR_TICKET_BY_SECTION)));
        
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

}
