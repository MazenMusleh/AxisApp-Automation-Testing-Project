package pages;

import Utilization.AssertionUtils;
import Utilization.JavaScriptUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ItemsPage {

    private WebDriver driver;
    private AssertionUtils assertionUtils;
    private JavaScriptUtils javaScriptUtils;

    //Locators
    private By SortBy_DDL = By.xpath("//select[@title='Sort By']");
    private By page_Title = By.xpath("//div[@class='page-title category-title']//h1");
    private By viewDetails_Btn = null;
    private List<WebElement> itemPrices_List = null;

    //Constructor
    public ItemsPage(WebDriver driver) {
        this.driver = driver;
        assertionUtils = new AssertionUtils();
        javaScriptUtils = new JavaScriptUtils(driver);
    }

    //Action Methods
    @Step("Select '{Text}' from Sort By dropdown")
    public void selectSortBy(String Text) {
        Select dropdown = new Select(driver.findElement(SortBy_DDL));
        dropdown.selectByVisibleText(Text);
    }

    @Step("Get page title text")
    public String getPageTitleText() {
        return driver.findElement(page_Title).getText().trim();
    }

    @Step("Validate page title is: {expectedText}")
    public void assertPageTitle(String expectedText, String message) {
        String actual = getPageTitleText();
        assertionUtils.assertTrueHard(actual.contains(expectedText), message);
    }

    /**
     * Checks if the items on the page are sorted in ascending order by price.
     * @return true if sorted ascending, false otherwise
     */
    @Step("Validate items are sorted by price ascending")
    public void assertItemsSortedByPriceAscending() {
        itemPrices_List = driver.findElements(By.xpath("//div[@class='price-box']//span[@class='price']"));
        double previousPrice = 0;
        boolean sorted = true;

        for (WebElement item : itemPrices_List) {
            String priceText = item.getText().replace("$", "").replace(",", "").trim();
            double currentPrice = Double.parseDouble(priceText);

            if (currentPrice < previousPrice) {
                sorted = false;
                break;
            }
            previousPrice = currentPrice;
        }

        assertionUtils.assertTrueHard(sorted, "item prices are not sorted ascending");
    }

    /**
     * Clicks the "View Details" button for a given item name.
     * This method searches for the item by a partial or full name and clicks the "View Details" link inside its item container.
     *
     * @param itemName The name or partial name of the item as displayed on the page
     */
    @Step("Click 'View Details' for item: {itemName}")
    public void clickViewDetailsByItemName(String itemName) {
        try {
            viewDetails_Btn = By.xpath("//h2[@class='product-name']/a[contains(text(),'" + itemName + "')]/ancestor::div[@class='product-info']//a[@title='View Details']");
            javaScriptUtils.scrollToElement(viewDetails_Btn);
            driver.findElement(viewDetails_Btn).click();
        } catch (NoSuchElementException e) {
            System.out.println("Item not found");
        }
    }

}
