package pages;

import Utilization.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookiesPopup {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //Locators
    By OptIn_RadioButton = By.id("privacy_pref_optin");
    By OptOut_RadioButton = By.id("privacy_pref_optout");
    By Close_Icon = By.xpath("//div[@class='close_btn_thick']");
    By Submit_Btn = By.id("consent_prompt_submit");

    //Constructor
    public CookiesPopup(WebDriver driver) {
        this.driver = driver;
        waitUtils = new WaitUtils(driver);
    }

    //Action Methods
    @Step("Click on 'Opt In' radiobutton in the cookies popup")
    public void clickOptIn() {
        waitUtils.waitForClickable(OptIn_RadioButton,10);
        driver.findElement(OptIn_RadioButton).click();
    }

    @Step("Click on 'Opt Out' radiobutton in the cookies popup")
    public void clickOptOut() {
        waitUtils.waitForClickable(OptOut_RadioButton,10);
        driver.findElement(OptOut_RadioButton).click();
    }

    @Step("Click on the 'Close' icon in the cookies popup")
    public void clickCloseIcon() {
        waitUtils.waitForClickable(Close_Icon,10);
        driver.findElement(Close_Icon).click();
    }

    @Step("Click on 'Submit' button in the cookies popup")
    public void clickSubmitButton() {
        waitUtils.waitForClickable(Submit_Btn,10);
        driver.findElement(Submit_Btn).click();
    }
}
