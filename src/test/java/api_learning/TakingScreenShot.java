package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.io.File;

public class TakingScreenShot {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login Screen
            MobileElement navLoginScreenElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenElem.click();

            // Wait until Login screen appear
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
            wait.until(ExpectedConditions.
                    visibilityOfElementLocated(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Login / Sign up Form\")")));

            // Whole screen
            File base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginScreen.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            // An area
            MobileElement loginFormElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            base64ScreenshotData = loginFormElem.getScreenshotAs(OutputType.FILE);
            fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginForm.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            // Login Button Only
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            base64ScreenshotData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginButton.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            // DEBUG
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
