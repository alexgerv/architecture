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

public class DynamicSearchTest {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String MATCH_LIST_MENU_LINK_TEXT = "Matches";
    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    private static final String MATCH_LIST_PAGE_TITLE = "Match List";
    private static final String SELECTOR_FOR_MATCH_A_PARTICULAR_SPORT = "input[name='Soccer']";
    private static final String SELECTOR_FOR_MATCH_A_PARTICULAR_VENUE = "input[name='Stade Telus']";
    private static final String XPATH_FOR_MATCH_SPORT = "//*[@id='matchList']//tr//td[4]";

    private WebDriver driver;
    WebDriverWait driverWait;
    
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, 10);
        driver.get(BASE_URL);
    }
    
    @Test
    public void testRemovingCriteraFromSearchExpandsDisplayedResults(){
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click(); 
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_PARTICULAR_SPORT))).click();
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_PARTICULAR_VENUE))).click();
        List<WebElement> firstSearchResults = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_MATCH_SPORT))); 
        
        driverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SELECTOR_FOR_MATCH_A_PARTICULAR_VENUE))).click();
        List<WebElement> secondSearchResults = driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(XPATH_FOR_MATCH_SPORT)));
        
        assertTrue(firstSearchResults.size() <= secondSearchResults.size());
    }
    
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
