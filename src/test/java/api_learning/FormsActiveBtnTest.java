package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

public class FormsActiveBtnTest {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Forms Screen
            MobileElement navFormsScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
            navFormsScreenBtn.click();

            // Click on Active button
            MobileElement activeBtn = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
            activeBtn.click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
