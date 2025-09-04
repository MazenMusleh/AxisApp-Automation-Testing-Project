package pages;

import Utilization.AssertionUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;
    private AssertionUtils assertionUtils;

    // Locators
    private By AddedToCart_SuccessMessage = By.xpath("//li[@class='success-msg']//span");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        assertionUtils = new AssertionUtils();
    }

    // Action Methods
    @Step("Get Added To Cart Success Message")
    public String getSuccessMessage() {
        return driver.findElement(AddedToCart_SuccessMessage).getText();
    }

    @Step("Validate Added To Cart Success Message is: {expectedText}")
    public void assertSuccessMessage(String expectedText, String message) {
        String actual = getSuccessMessage();
        assertionUtils.assertTrueHard(actual.contains(expectedText), message);
    }
}
