package pages;

import Utilization.AssertionUtils;
import Utilization.JavaScriptUtils;
import Utilization.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterationPage {

    private WebDriver driver;
    private WaitUtils waitUtils;
    private JavaScriptUtils javaScriptUtils;
    private AssertionUtils assertionUtils;

    // Locators
    private By FirstName_TextField = By.id("firstname");
    private By MiddleName_TextField = By.id("middlename");
    private By LastName_TextField = By.id("lastname");
    private By Email_TextField = By.id("email_address");
    private By Password_TextField = By.id("password");
    private By ConfirmPassword_TextField = By.id("confirmation");
    private By RememberMe_CheckBox = By.xpath("//input[starts-with(@id,'remember_')]");
    private By Register_Btn = By.xpath("//button[@title='Register']");
    private By Registeration_SuccessMessage = By.xpath("//li[@class='success-msg']//span");
    private By FirstName_Error = By.id("advice-required-entry-firstname");
    private By LastName_Error = By.id("advice-required-entry-lastname");
    private By Email_Error = By.id("advice-required-entry-email_address");
    private By Password_Error = By.id("advice-required-entry-password");
    private By ConfirmPassword_Error = By.id("advice-required-entry-confirmation");
    private By ConfirmPassword_Mismatch_Error = By.id("advice-validate-cpassword-confirmation");
    private By AlreadyRegisteredEmail_Error = By.xpath("//li[@class='error-msg']//span");

    // Constructor
    public RegisterationPage(WebDriver driver) {
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
        javaScriptUtils = new JavaScriptUtils(driver);
        assertionUtils = new AssertionUtils();
    }

    // Action Methods
    @Step("Enter First Name: {firstName}")
    public void enterFirstName(String firstName) {
        javaScriptUtils.scrollToElement(FirstName_TextField);
        driver.findElement(FirstName_TextField).clear();
        driver.findElement(FirstName_TextField).sendKeys(firstName);
    }

    @Step("Enter Middle Name: {middleName}")
    public void enterMiddleName(String middleName) {
        javaScriptUtils.scrollToElement(MiddleName_TextField);
        driver.findElement(MiddleName_TextField).clear();
        driver.findElement(MiddleName_TextField).sendKeys(middleName);
    }

    @Step("Enter Last Name: {lastName}")
    public void enterLastName(String lastName) {
        javaScriptUtils.scrollToElement(LastName_TextField);
        driver.findElement(LastName_TextField).clear();
        driver.findElement(LastName_TextField).sendKeys(lastName);
    }

    @Step("Enter Email: {email}")
    public void enterEmail(String email) {
        javaScriptUtils.scrollToElement(Email_TextField);
        driver.findElement(Email_TextField).clear();
        driver.findElement(Email_TextField).sendKeys(email);
    }

    @Step("Enter Password: {password}")
    public void enterPassword(String password) {
        javaScriptUtils.scrollToElement(Password_TextField);
        driver.findElement(Password_TextField).clear();
        driver.findElement(Password_TextField).sendKeys(password);
    }

    @Step("Enter Confirm Password: {confirmPassword}")
    public void enterConfirmPassword(String confirmPassword) {
        javaScriptUtils.scrollToElement(ConfirmPassword_TextField);
        driver.findElement(ConfirmPassword_TextField).clear();
        driver.findElement(ConfirmPassword_TextField).sendKeys(confirmPassword);
    }

    @Step("Click Remember Me checkbox")
    public void clickRememberMe() {
        javaScriptUtils.scrollToElement(RememberMe_CheckBox);
        waitUtils.waitForClickable(RememberMe_CheckBox, 10);
        driver.findElement(RememberMe_CheckBox).click();
    }

    @Step("Click Register button")
    public void clickRegister() {
        javaScriptUtils.scrollToElement(Register_Btn);
        driver.findElement(Register_Btn).click();
    }

    @Step("Get Registration Success Message")
    public String getSuccessMessage() {
        return driver.findElement(Registeration_SuccessMessage).getText();
    }

    @Step("Validate Registration Success Message is: {expectedText}")
    public void assertSuccessMessage(String expectedText, String message) {
        String actual = getSuccessMessage();
        assertionUtils.assertTrueHard(actual.contains(expectedText), message);
    }

    @Step("Get First Name error message")
    public String getFirstNameError() {
        return driver.findElement(FirstName_Error).getText();
    }

    @Step("Get Last Name error message")
    public String getLastNameError() {
        return driver.findElement(LastName_Error).getText();
    }

    @Step("Get Email error message")
    public String getEmailError() {
        return driver.findElement(Email_Error).getText();
    }

    @Step("Assert browser validation message for Email field is: {expectedText}")
    public void assertEmailValidationMessage(String expectedText) {
        String actual = javaScriptUtils.getValidationMessage(Email_TextField);
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected validation message: '" + expectedText + "', but got: '" + actual + "'");
    }

    @Step("Get Password error message")
    public String getPasswordError() {
        return driver.findElement(Password_Error).getText();
    }

    @Step("Get Confirm Password error message")
    public String getConfirmPasswordError() {
        return driver.findElement(ConfirmPassword_Error).getText();
    }

    @Step("Get Confirm Password misamtch error message")
    public String getConfirmPasswordMismacthError() {
        return driver.findElement(ConfirmPassword_Mismatch_Error).getText();
    }

    @Step("Get Already Registered Email error message")
    public String getAlreadyRegisteredEmailError() {
        return driver.findElement(AlreadyRegisteredEmail_Error).getText();
    }

    @Step("Validate Already Registered Email error message is: {expectedText}")
    public void assertAlreadyRegisteredEmailError(String expectedText) {
        javaScriptUtils.scrollToElement(AlreadyRegisteredEmail_Error);
        String actual = getAlreadyRegisteredEmailError();
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected error message: '" + expectedText + "', but got: '" + actual + "'");
    }

    @Step("Validate First Name error message is: {expectedText}")
    public void assertFirstNameError(String expectedText) {
        javaScriptUtils.scrollToElement(FirstName_Error);
        String actual = getFirstNameError();
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected error message: '" + expectedText + "', but got: '" + actual + "'");
    }

    @Step("Validate Last Name error message is: {expectedText}")
    public void assertLastNameError(String expectedText) {
        javaScriptUtils.scrollToElement(LastName_Error);
        String actual = getLastNameError();
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected error message: '" + expectedText + "', but got: '" + actual + "'");
    }

    @Step("Validate Email error message is: {expectedText}")
    public void assertEmailError(String expectedText) {
        javaScriptUtils.scrollToElement(Email_Error);
        String actual = getEmailError();
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected error message: '" + expectedText + "', but got: '" + actual + "'");
    }

    @Step("Validate Password error message is: {expectedText}")
    public void assertPasswordError(String expectedText) {
        javaScriptUtils.scrollToElement(Password_Error);
        String actual = getPasswordError();
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected error message: '" + expectedText + "', but got: '" + actual + "'");
    }

    @Step("Validate Confirm Password error message is: {expectedText}")
    public void assertConfirmPasswordError(String expectedText) {
        javaScriptUtils.scrollToElement(ConfirmPassword_Error);
        String actual = getConfirmPasswordError();
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected error message: '" + expectedText + "', but got: '" + actual + "'");
    }

    @Step("Validate Confirm Password mismatch error message is: {expectedText}")
    public void assertConfirmPasswordMismatchError(String expectedText) {
        javaScriptUtils.scrollToElement(ConfirmPassword_Mismatch_Error);
        String actual = getConfirmPasswordMismacthError();
        assertionUtils.assertTrueHard(actual.contains(expectedText),
                "Expected error message: '" + expectedText + "', but got: '" + actual + "'");
    }
}
