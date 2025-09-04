package pages;

import Utilization.AssertionUtils;
import Utilization.JavaScriptUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ItemDetailsPage {

    private WebDriver driver;
    private AssertionUtils assertionUtils;
    private JavaScriptUtils javaScriptUtils;

    //Locators
    private By item_Title = By.xpath("//div[@class='product-name']//span");
    private By color_Options = By.xpath("//ul[@id='configurable_swatch_shoe_size']/li");
    private By selectedColor_Label = By.id("select_label_shoe_size");
    private By size_Options = By.xpath("//ul[@id='configurable_swatch_color']/li");
    private By selectedSize_Label = By.id("select_label_color");
    private By addToCart_Btn = By.xpath("//button[@onclick='productAddToCartForm.submit(this)']");

    //Constructor
    public ItemDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.assertionUtils = new AssertionUtils();
        javaScriptUtils = new JavaScriptUtils(driver);
    }

    //Action Methods
    @Step("Get item title text")
    public String getitem_TitleText() {
        return driver.findElement(item_Title).getText().trim();
    }

    @Step("Validate item title is: {expectedText}")
    public void assertItemTitle(String expectedText, String message) {
        String actual = getitem_TitleText();
        assertionUtils.assertTrueHard(actual.contains(expectedText.toUpperCase()), message);
    }

    /**
     * Selects a random option from a list (size, color) and verifies that:
     * 1. The chosen option has the 'selected' class.
     * 2. The label element shows the correct selected value.
     *
     * @param ListLocator       Locator of the <li> options
     * @param labelLocator      Locator of the label/span that displays the selected value
     */
    public void selectRandomOptionAndAssert(By ListLocator, By labelLocator) {
        javaScriptUtils.scrollToElement(labelLocator);

        List<WebElement> options = driver.findElements(ListLocator);

        Random rand = new Random();
        WebElement chosenOption = options.get(rand.nextInt(options.size()));
        String chosenValue = chosenOption.getAttribute("title").trim();

        chosenOption.click();

        String classAttr = chosenOption.getDomAttribute("class");
        assertionUtils.assertTrueHard(classAttr.contains("selected"), "Option Not selected");

        String labelText = driver.findElement(labelLocator).getText().trim();
        assertionUtils.assertTrueHard(labelText.contains(chosenValue), "Selected label not shown correctly");
    }

    @Step("Select a random shoe size and verify selection")
    public void selectRandomShoeSizeAndAssert() {
        selectRandomOptionAndAssert(size_Options, selectedSize_Label);
    }

    @Step("Select a random color and verify selection")
    public void selectRandomColorAndAssert() {
        selectRandomOptionAndAssert(color_Options, selectedColor_Label);
    }

    @Step("Click 'Add to Cart' button")
    public void clickAddToCart() {
        driver.findElement(addToCart_Btn).click();
    }
}
