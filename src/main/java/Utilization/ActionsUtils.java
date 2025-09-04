package Utilization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsUtils {

    private WebDriver driver;
    private Actions actions;

    // Constructor
    public ActionsUtils(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    // Hover over element
    public void hoverOverElement(By locator) {
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
    }

    // Right click (context click) on element
    public void rightClick(By locator) {
        WebElement element = driver.findElement(locator);
        actions.contextClick(element).perform();
    }

    // Double click on element
    public void doubleClick(By locator) {
        WebElement element = driver.findElement(locator);
        actions.doubleClick(element).perform();
    }

    // Drag and Drop from source to target
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = driver.findElement(sourceLocator);
        WebElement target = driver.findElement(targetLocator);
        actions.dragAndDrop(source, target).perform();
    }

    // Drag and Drop by offset (x, y)
    public void dragAndDropByOffset(By sourceLocator, int xOffset, int yOffset) {
        WebElement source = driver.findElement(sourceLocator);
        actions.dragAndDropBy(source, xOffset, yOffset).perform();
    }

    // Click and hold (useful for slider or custom drag)
    public void clickAndHold(By locator) {
        WebElement element = driver.findElement(locator);
        actions.clickAndHold(element).perform();
    }

    // Release mouse button (after clickAndHold)
    public void release() {
        actions.release().perform();
    }

    // Move to element and click
    public void moveToElementAndClick(By locator) {
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).click().perform();
    }

}
