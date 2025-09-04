import Base.DriverManager;
import Utilization.ConfigManager;
import Utilization.FakerUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.CookiesPopup;
import pages.HomePage;
import pages.RegisterationPage;

@Epic("Tealium E-Commerce")
@Feature("User Registration")
public class RegistrationTests {

    private WebDriver driver;
    private HomePage homePage;
    private RegisterationPage registerationPage;
    private CookiesPopup cookiesPopup;

    private String alreadyRegisteredEmail;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        registerationPage = new RegisterationPage(driver);
        cookiesPopup = new CookiesPopup(driver);
        ConfigManager.init("src/test/resources/configs.properties");
        alreadyRegisteredEmail = ConfigManager.getInstance().getProperty("alreadyRegisteredEmail");
        cookiesPopup.clickOptIn();
        cookiesPopup.clickSubmitButton();
        homePage.clickAccount();
        homePage.clickRegister();
    }

    @Test(description = "Validate that a user can successfully register with valid credentials",priority = 1)
    @Story("As a new user, I want to register successfully so that I can log in later")
    public void testSuccessfulRegistration() {

        String firstName = FakerUtils.getRandomFirstName();
        String middleName = FakerUtils.getRandomMiddleName();
        String lastName = FakerUtils.getRandomLastName();
        String email = FakerUtils.getRandomEmail();
        String password = FakerUtils.getRandomPassword();

        ConfigManager.getInstance().setProperty("name", firstName + " " + middleName + " " + lastName);
        ConfigManager.getInstance().setProperty("email", email);
        ConfigManager.getInstance().setProperty("password", password);

        registerationPage.enterFirstName(firstName);
        registerationPage.enterMiddleName(middleName);
        registerationPage.enterLastName(lastName);
        registerationPage.enterEmail(email);
        registerationPage.enterPassword(password);
        registerationPage.enterConfirmPassword(password);
        registerationPage.clickRememberMe();
        registerationPage.clickRegister();

        registerationPage.assertSuccessMessage("Thank you for registering", "Registration failed!");
    }

    @Test(description = "Validate registration fails when required fields are empty")
    @Story("As a new user, I should see an error when required fields are missing")
    public void testRegistrationMissingRequiredFields() {

        registerationPage.clickRememberMe();
        registerationPage.clickRegister();

        registerationPage.assertFirstNameError("This is a required field.");
        registerationPage.assertLastNameError("This is a required field.");
        registerationPage.assertEmailError("This is a required field.");
        registerationPage.assertPasswordError("This is a required field.");
        registerationPage.assertConfirmPasswordError("This is a required field.");
    }

    @Test(description = "Validate registration fails for invalid email format")
    @Story("As a new user, I should see an error when entering invalid email format")
    public void testRegistrationInvalidEmail() {

        registerationPage.enterFirstName(FakerUtils.getRandomFirstName());
        registerationPage.enterMiddleName(FakerUtils.getRandomMiddleName());
        registerationPage.enterLastName(FakerUtils.getRandomLastName());
        registerationPage.enterEmail("invalidemail");
        String password = FakerUtils.getRandomPassword();
        registerationPage.enterPassword(password);
        registerationPage.enterConfirmPassword(password);
        registerationPage.clickRememberMe();
        registerationPage.clickRegister();

        registerationPage.assertEmailValidationMessage("Please include an '@' in the email address.");
    }

    @Test(description = "Validate registration fails if password and confirm password do not match")
    @Story("As a new user, I should see an error if password and confirm password do not match")
    public void testRegistrationPasswordMismatch() {

        registerationPage.enterFirstName(FakerUtils.getRandomFirstName());
        registerationPage.enterMiddleName(FakerUtils.getRandomMiddleName());
        registerationPage.enterLastName(FakerUtils.getRandomLastName());
        registerationPage.enterEmail(FakerUtils.getRandomEmail());
        registerationPage.enterPassword("Password123!");
        registerationPage.enterConfirmPassword("Password1234!");
        registerationPage.clickRememberMe();// mismatch
        registerationPage.clickRegister();

        registerationPage.assertConfirmPasswordMismatchError("Please make sure your passwords match.");
    }

    @Test(description = "Validate registration fails when using an already registered email")
    @Story("As a new user, I should see an error if I try to register with an email that already exists")
    public void testRegistrationAlreadyRegisteredEmail() {

        registerationPage.enterFirstName(FakerUtils.getRandomFirstName());
        registerationPage.enterMiddleName(FakerUtils.getRandomMiddleName());
        registerationPage.enterLastName(FakerUtils.getRandomLastName());
        registerationPage.enterEmail(alreadyRegisteredEmail); // email already used
        String password = FakerUtils.getRandomPassword();
        registerationPage.enterPassword(password);
        registerationPage.enterConfirmPassword(password);
        registerationPage.clickRememberMe();
        registerationPage.clickRegister();


        registerationPage.assertAlreadyRegisteredEmailError("There is already an account with this email address.");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
