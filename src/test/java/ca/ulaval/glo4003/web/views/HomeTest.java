package ca.ulaval.glo4003.web.views;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.SeleneseTestCase;

public class HomeTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private final String MATCH_LIST_MENU_LINK_TEXT = "Matches";
  private final String MATCH_LIST_HOME_LINK_TEXT = "View the match list »";
  private final String MATCH_LIST_PAGE_TITLE = "Match List";
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
  }

  @Test
  public void whenOnHomePageclickingOnViewMatchListButtonNavigatesToMatchListPage() throws Exception {
    driver.get("http://localhost:8080");
    driver.findElement(By.linkText(MATCH_LIST_HOME_LINK_TEXT)).click();
    assertEquals(MATCH_LIST_PAGE_TITLE, driver.getTitle());
  }
  
  @Test
  public void whenOnHomePageclickingOnMatchesButtonNavigatesToMatchListPage() throws Exception {
    driver.get("http://localhost:8080");
    driver.findElement(By.linkText(MATCH_LIST_MENU_LINK_TEXT)).click();
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

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
