package Base;


import Utilization.ConfigManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;


public class DriverFactory {

    /**
     * Creates and initializes a new {@link WebDriver} instance based on the browser type
     * specified in the configuration file (`configs.properties`).
     * Supported browsers: CHROME - FIREFOX - EDGE
     * The method will:
     *   Read the "Browser" property from the configuration file
     *   Instantiate the appropriate WebDriver
     *   Maximize the browser window
     *   Set an implicit wait of 10 seconds
     *   Navigate to the base URL from the configuration file
     *
     * @return a fully initialized {@link WebDriver} instance
     * @throws RuntimeException if the specified browser type is not supported
     */
    public static WebDriver createInstance() {
        ConfigManager.init("src/test/resources/configs.properties");
        String browserType = ConfigManager.getInstance().getProperty("Browser").toUpperCase();
        WebDriver driver;

        switch (browserType) {
            case "CHROME":
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browserType);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigManager.getInstance().getProperty("baseUrl"));
        return driver;
    }
}
