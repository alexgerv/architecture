package ca.ulaval.glo4003.web.views;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpTest {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final String SIGN_UP_LINK_NAME = "Sign Up";
    private static final String USER_NAME_INPUT_FIELD_ID = "usernameInput";
    private static final String A_USER_NAME = "olivier_dugas";
    private static final String PASSWORD_INPUT_FIELD_ID = "password";
    private static final String A_PASSWORD = "123456";
    private static final String SUBMIT_BUTTON_ID = "submit";
    private static final String SELECTOR_SINGUP_SUCCESS_MESSAGE = "div[class=\"alert alert-info\"]";
    private static final String EXPECTED_SUCCESS_MESSAGE = "Successfully created user";
    private static final String EXPECTED_FAIL_MESSAGE = "Username \"" + A_USER_NAME + "\" is already taken";
    private static final String PATH_TO_CREATED_USER_FILE = "./users/olivier_dugas.json";

    private WebDriver driver;
    WebDriverWait driverWait;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, 10);
        
        signUp();
    }
    
    private void signUp(){
        driver.get(BASE_URL);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(SIGN_UP_LINK_NAME))).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(USER_NAME_INPUT_FIELD_ID)))
                  .sendKeys(A_USER_NAME);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT_FIELD_ID)))
                  .sendKeys(A_PASSWORD);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(SUBMIT_BUTTON_ID))).click();
    }

    @Test
    public void canCreateAccountWhenNotLoggedIn() throws Exception {
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(SELECTOR_SINGUP_SUCCESS_MESSAGE), EXPECTED_SUCCESS_MESSAGE)));
    }
    
    @Test
    public void whenTringToCreateAnAccountWithAnExistingUserAnErrorMessageIsShown(){
        assertTrue(driverWait.until(ExpectedConditions.textToBePresentInElement(By.cssSelector(SELECTOR_SINGUP_SUCCESS_MESSAGE), EXPECTED_FAIL_MESSAGE)));      
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        deleteCreatedUser();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
    
    private void deleteCreatedUser(){
        File file = new File(PATH_TO_CREATED_USER_FILE);
        file.delete();
    }

}
