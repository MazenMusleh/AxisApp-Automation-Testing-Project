package Utilization;

import io.qameta.allure.Allure;

public class AllureUtils {

    public static void attachScreenshot(byte[] screenshotBytes) {
        try {
            Allure.getLifecycle().addAttachment(
                    "Screenshot",   // name
                    "image/png",            // type
                    "png",                  // file extension
                    screenshotBytes         // content
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to attach screenshot to Allure report", e);
        }
    }
}

