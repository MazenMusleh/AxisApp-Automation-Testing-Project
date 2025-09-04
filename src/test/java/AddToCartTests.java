import Base.DriverManager;
import Utilization.ConfigManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.*;

@Epic("Tealium E-Commerce")
@Feature("Add Item to Cart")
public class AddToCartTests {

    private WebDriver driver;
    private ItemsPage itemsPage;
    private ItemDetailsPage itemDetailsPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private CookiesPopup cookiesPopup;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        itemsPage = new ItemsPage(driver);
        itemDetailsPage = new ItemDetailsPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        cookiesPopup = new CookiesPopup(driver);
        cartPage = new CartPage(driver);
        ConfigManager.init("src/test/resources/configs.properties");
        cookiesPopup.clickOptIn();
        cookiesPopup.clickSubmitButton();
        String email = ConfigManager.getInstance().getProperty("email");
        String password = ConfigManager.getInstance().getProperty("password");
        homePage.gotoLoginPage();
        loginPage.login(email, password);
    }

    @Test(description = "Verify user can select item options, add it to cart successfully")
    @Story("As a customer, I want to select item options and add the item to the cart so that I can purchase it")
    public void testAddItemToCart() {
        homePage.isAccessoriesAppeared();
        homePage.hoverOverAccessories();
        homePage.isSubmenuAppeared();
        homePage.clickShoes();

        itemsPage.selectSortBy("Price");

        itemsPage.assertItemsSortedByPriceAscending();


        String productName = "Dorian Perforated Oxford";
        itemsPage.clickViewDetailsByItemName(productName);

        itemDetailsPage.assertItemTitle(productName, "Item title does not match expected item name");

        itemDetailsPage.selectRandomColorAndAssert();
        itemDetailsPage.selectRandomShoeSizeAndAssert();

        itemDetailsPage.clickAddToCart();

        cartPage.assertSuccessMessage(productName + " was added to your shopping cart" , "item was not added to cart");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
