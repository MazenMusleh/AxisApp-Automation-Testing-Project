package Utilization;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptUtils(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // Scrolls the page until the specified element is visible
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Clicks on an element using JavaScript
    public void clickWithJS(By locator) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].click();", element);
    }

    // Sets a value into an input field using JavaScript
    public void setValue(By locator, String value) {
        WebElement element = driver.findElement(locator);
        js.executeScript("arguments[0].value='" + value + "';", element);
    }

    // Retrieves the visible text of an element using JavaScript
    public String getText(By locator) {
        WebElement element = driver.findElement(locator);
        return (String) js.executeScript("return arguments[0].innerText;", element);
    }

    // Scrolls to the bottom of the page
    public void scrollToBottom() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scrolls to the top of the page
    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    // Gets the built-in validation message of an element
    public String getValidationMessage(By locator) {
        WebElement element = driver.findElement(locator);
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }
}
