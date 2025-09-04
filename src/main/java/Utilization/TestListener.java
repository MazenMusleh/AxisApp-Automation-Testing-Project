package Utilization;

import Base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {

    private String currentClassName = "";

    // ================= SUITE LEVEL =================
    @Override
    public void onStart(ISuite suite) {
        LogUtils.info("=== SUITE STARTED: " + suite.getName() + " ===");
    }

    @Override
    public void onFinish(ISuite suite) {
        LogUtils.info("=== SUITE FINISHED: " + suite.getName() + " ===");

        try {
            LogUtils.info("Generating Allure Report...");
            Runtime.getRuntime().exec("cmd /c allure generate allure-results -o allure-report --clean").waitFor();

            LogUtils.info("Opening Allure Report...");
            Runtime.getRuntime().exec("cmd /c start allure open allure-report");
            LogUtils.info("Allure Report opened successfully\n");
        } catch (Exception e) {
            LogUtils.error("Failed to open Allure report");
        }
    }

    // ================= METHOD LEVEL =================
    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();

        if (!className.equals(currentClassName)) {
            if (!currentClassName.isEmpty()) {
                LogUtils.info("=== TEST CLASS FINISHED: " + currentClassName + " ===");
            }
            currentClassName = className;
            LogUtils.info("=== TEST CLASS STARTED: " + className + " ===");
        }

        LogUtils.info(">>> STARTING Test Method: " + className + "." + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info(">>> PASSED Test Method: " +
                result.getTestClass().getName() + "." + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error(">>> FAILED Test Method: " +
                result.getTestClass().getName() + "." + result.getMethod().getMethodName());
        try {
            WebDriver driver = DriverManager.getDriver();
            if (driver != null) {
                byte[] screenshot = new ScreenshotsUtils(driver).captureScreenshot(result.getMethod().getMethodName());
                AllureUtils.attachScreenshot(screenshot);
            }
        } catch (Exception e) {
            LogUtils.error("Error while capturing screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn(">>> SKIPPED Test Method: " +
                result.getTestClass().getName() + "." + result.getMethod().getMethodName());
    }
}
