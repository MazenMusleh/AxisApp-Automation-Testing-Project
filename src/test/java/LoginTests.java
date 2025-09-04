import Base.DriverManager;
import Utilization.ConfigManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CookiesPopup;
import pages.HomePage;
import pages.LoginPage;

@Epic("Tealium E-Commerce")
@Feature("User Login")
public class LoginTests {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private CookiesPopup cookiesPopup;

    private String name;
    private String email;
    private String password;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        cookiesPopup = new CookiesPopup(driver);
        ConfigManager.init("src/test/resources/configs.properties");
        cookiesPopup.clickOptIn();
        cookiesPopup.clickSubmitButton();
        name = ConfigManager.getInstance().getProperty("name");
        email = ConfigManager.getInstance().getProperty("email");
        password = ConfigManager.getInstance().getProperty("password");
    }

    @Test(description = "Validate that a registered user can log in successfully")
    @Story("As a registered user, I want to log in with my saved credentials")
    public void testLoginWithRegisteredUser() {

        homePage.clickAccount();
        homePage.clickLogin();

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickRememberMe();
        loginPage.clickLogin();

        loginPage.assertWelcomeMessageContains(name, "Login failed!");
        loginPage.assertHelloMessageContains(name, "Login failed!");
    }

    @Test(description = "Verify error message for wrong email and correct password")
    @Story("As a user, I want to see an error when I enter a wrong email")
    public void testLoginWrongEmail() {
        homePage.clickAccount();
        homePage.clickLogin();

        loginPage.enterEmail("wrongemail@example.com");
        loginPage.enterPassword(password);
        loginPage.clickRememberMe();
        loginPage.clickLogin();

        loginPage.assertInvalidCredentialsError("Invalid login or password.");
    }

    @Test(description = "Verify error message for correct email and wrong password")
    @Story("As a user, I want to see an error when I enter a wrong password")
    public void testLoginWrongPassword() {
        homePage.clickAccount();
        homePage.clickLogin();

        loginPage.enterEmail(email);
        loginPage.enterPassword("WrongPassword123");
        loginPage.clickRememberMe();
        loginPage.clickLogin();

        loginPage.assertInvalidCredentialsError("Invalid login or password.");
    }

    @Test(description = "Verify error message for empty email field")
    @Story("As a user, I want to see an error when I leave the email field empty")
    public void testLoginEmptyEmail() {
        homePage.clickAccount();
        homePage.clickLogin();

        loginPage.enterEmail("");
        loginPage.enterPassword(password);
        loginPage.clickRememberMe();
        loginPage.clickLogin();

        loginPage.assertEmptyEmailError("This is a required field.");
    }

    @Test(description = "Verify error message for empty password field")
    @Story("As a user, I want to see an error when I leave the password field empty")
    public void testLoginEmptyPassword() {
        homePage.clickAccount();
        homePage.clickLogin();

        loginPage.enterEmail(email);
        loginPage.enterPassword("");
        loginPage.clickRememberMe();
        loginPage.clickLogin();

        loginPage.assertEmptyPasswordError("This is a required field.");
    }

    @Test(description = "Verify error message for empty email and password fields")
    @Story("As a user, I want to see errors when I leave both fields empty")
    public void testLoginEmptyEmailAndPassword() {
        homePage.clickAccount();
        homePage.clickLogin();

        loginPage.clickRememberMe();
        loginPage.clickLogin();

        loginPage.assertEmptyEmailError("This is a required field.");
        loginPage.assertEmptyPasswordError("This is a required field.");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
