package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeTest {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String MATCH_LIST_MENU_LINK_TEXT = "Matches";
    private static final String MATCH_LIST_HOME_LINK_TEXT = "View the match list";
    private static final String MATCH_LIST_PAGE_TITLE = "Match List";

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
    public void whenOnHomePageclickingOnViewMatchListButtonNavigatesToMatchListPage() throws Exception {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_HOME_LINK_TEXT))).click();

        driverWait.until(ExpectedConditions.titleIs(MATCH_LIST_PAGE_TITLE));
        assertEquals(MATCH_LIST_PAGE_TITLE, driver.getTitle());
    }

    @Test
    public void whenOnHomePageclickingOnMatchesButtonNavigatesToMatchListPage() throws Exception {
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(MATCH_LIST_MENU_LINK_TEXT))).click();
        
        driverWait.until(ExpectedConditions.titleIs(MATCH_LIST_PAGE_TITLE));
        assertEquals(MATCH_LIST_PAGE_TITLE, driver.getTitle());
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
