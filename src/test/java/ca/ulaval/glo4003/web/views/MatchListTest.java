package ca.ulaval.glo4003.web.views;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class MatchListTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private final String NUMBER_OF_AVALAIBLE_TICKETS_IN_FIRST_MATCH = "10";
  private final String NUMBER_OF_AVALAIBLE_TICKETS_IN_SECOND_MATCH = "20";

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
  }

  @Test
  public void whenOpenningMatchListPageTheNumberOfAvalaibleTicketsIsDisplayedForEachMatch() throws Exception {
    driver.get("http://localhost:8080/matchList");
    assertEquals(NUMBER_OF_AVALAIBLE_TICKETS_IN_FIRST_MATCH, driver.findElement(By.xpath("//*[@id='matchList']//tr[1]//td[7]//strong")).getText());
    assertEquals(NUMBER_OF_AVALAIBLE_TICKETS_IN_SECOND_MATCH, driver.findElement(By.xpath("//*[@id='matchList']//tr[2]//td[7]//strong")).getText());
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
