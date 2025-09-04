package pages;

import Utilization.ActionsUtils;
import Utilization.AssertionUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private ActionsUtils actionsUtils;
    private AssertionUtils assertionUtils;

    // Locators
    private By Account_Btn = By.xpath("//span[@class='label'][contains(.,'Account')]");
    private By Login_Btn = By.xpath("//a[@title='Log In']");
    private By Register_Btn = By.xpath("//a[@title='Register']");
    private By Accessories_Menu = By.xpath("//a[@class='level0 has-children'][normalize-space()='Accessories']");
    private By Shoes_Tab = By.xpath("//a[contains(.,'Shoes')]");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.actionsUtils = new ActionsUtils(driver);
        this.assertionUtils = new AssertionUtils();
    }

    // Action Methods
    @Step("Click on 'Account' button")
    public void clickAccount() {
        driver.findElement(Account_Btn).click();
    }

    @Step("Click on 'Login' button")
    public void clickLogin() {
        driver.findElement(Login_Btn).click();
    }

    @Step("Click on 'Register' button")
    public void clickRegister() {
        driver.findElement(Register_Btn).click();
    }

    @Step("Hover over 'Accessories' menu")
    public void hoverOverAccessories() {
        actionsUtils.hoverOverElement(Accessories_Menu);
    }

    @Step("Validate that the 'Accessories' menu appear")
    public void isAccessoriesAppeared() {
        boolean actual = driver.findElement(Accessories_Menu).isDisplayed();
        assertionUtils.assertTrueHard(actual, "'Accessories' menu not appear");
    }

    @Step("Validate that the submenu appeared after hovering over 'Accessories'")
    public void isSubmenuAppeared() {
        boolean actual = driver.findElement(Shoes_Tab).isDisplayed();
        assertionUtils.assertTrueHard(actual, "Submenu not appear");
    }

    @Step("Click on 'Shoes' under Accessories")
    public void clickShoes() {
        driver.findElement(Shoes_Tab).click();
    }

    @Step("Goto 'Login' page")
    public void gotoLoginPage() {
        clickAccount();
        clickLogin();
    }
}
