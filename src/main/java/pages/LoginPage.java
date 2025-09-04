package pages;

import Utilization.AssertionUtils;
import Utilization.JavaScriptUtils;
import Utilization.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private JavaScriptUtils javaScriptUtils;
    private AssertionUtils assertionUtils;

    // Locators
    private By Email_TextField = By.id("email");
    private By Password_TextField = By.id("pass");
    private By RememberMe_CheckBox = By.xpath("//input[starts-with(@id,'remember_')]");
    private By Login_Btn = By.id("send2");
    private By Hello_Msg = By.xpath("//p[@class='hello']//strong");
    private By Welcome_Msg = By.xpath("//p[@class='welcome-msg']");
    private By EmptyEmail_Error = By.id("advice-required-entry-email");
    private By EmptyPassword_Error = By.id("advice-required-entry-pass");
    private By InvalidCredentials_Error = By.xpath("//li[@class='error-msg']//span");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
        javaScriptUtils = new JavaScriptUtils(driver);
        assertionUtils = new AssertionUtils();
    }

    // Action Methods
    @Step("Enter email: {email}")
    public void enterEmail(String email) {
        javaScriptUtils.scrollToElement(Email_TextField);
        driver.findElement(Email_TextField).clear();
        driver.findElement(Email_TextField).sendKeys(email);
    }

    @Step("Enter password: {password}")
    public void enterPassword(String password) {
        javaScriptUtils.scrollToElement(Password_TextField);
        driver.findElement(Password_TextField).clear();
        driver.findElement(Password_TextField).sendKeys(password);
    }

    @Step("Click Remember Me checkbox")
    public void clickRememberMe() {
        javaScriptUtils.scrollToElement(RememberMe_CheckBox);
        waitUtils.waitForClickable(RememberMe_CheckBox, 10);
        driver.findElement(RememberMe_CheckBox).click();
    }

    @Step("Click 'Login' button")
    public void clickLogin() {
        javaScriptUtils.scrollToElement(Login_Btn);
        driver.findElement(Login_Btn).click();
    }

    @Step("Get Hello message text")
    public String getHelloMessage() {
        return driver.findElement(Hello_Msg).getText();
    }

    @Step("Validate Hello message contains: {expectedText}")
    public void assertHelloMessageContains(String expectedText, String message) {
        String actual = getHelloMessage();
        assertionUtils.assertTrueHard(actual.contains(expectedText), message);
    }

    @Step("Get Welcome message text")
    public String getWelcomeMessage() {
        return driver.findElement(Welcome_Msg).getText();
    }

    @Step("Validate Welcome message contains: {expectedText}")
    public void assertWelcomeMessageContains(String expectedText, String message) {
        String actual = getWelcomeMessage();
        assertionUtils.assertTrueHard(actual.contains(expectedText.toUpperCase()), message);
    }

    @Step("Login with email: {email} and password: {password}")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickRememberMe();
        clickLogin();
    }

    @Step("Get error message for empty email")
    public String getEmptyEmailErrorText() {
        return driver.findElement(EmptyEmail_Error).getText().trim();
    }

    @Step("Get error message for empty password")
    public String getEmptyPasswordErrorText() {
        return driver.findElement(EmptyPassword_Error).getText().trim();
    }

    @Step("Get error message for invalid credentials")
    public String getInvalidCredentialsErrorText() {
        return driver.findElement(InvalidCredentials_Error).getText().trim();
    }

    @Step("Validate error message for empty email is displayed")
    public void assertEmptyEmailError(String expectedMessage) {
        String actual = getEmptyEmailErrorText();
        assertionUtils.assertTrueHard(actual.contains(expectedMessage),
                "Expected empty email error message: '" + expectedMessage + "', but got: '" + actual + "'");
    }

    @Step("Validate error message for empty password is displayed")
    public void assertEmptyPasswordError(String expectedMessage) {
        String actual = getEmptyPasswordErrorText();
        assertionUtils.assertTrueHard(actual.contains(expectedMessage),
                "Expected empty password error message: '" + expectedMessage + "', but got: '" + actual + "'");
    }

    @Step("Validate error message for invalid credentials is displayed")
    public void assertInvalidCredentialsError(String expectedMessage) {
        String actual = getInvalidCredentialsErrorText();
        assertionUtils.assertTrueHard(actual.contains(expectedMessage),
                "Expected invalid credentials error message: '" + expectedMessage + "', but got: '" + actual + "'");
    }
}
