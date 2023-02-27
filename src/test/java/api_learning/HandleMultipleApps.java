package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login Screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            // Fill the form and login
            MobileElement inputEmail = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement inputPass = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            inputEmail.sendKeys("micheo@sth.com");
            inputPass.sendKeys("12345678");
            loginBtnElem.click();

//            // Put app under testing to background | Simulate pressing home button -> relaunch
//            appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            // Put app into background until we call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Switch into another app | Go to Settings app, toggle Wifi
            appiumDriver.activateApp(AppPackages.SETTINGS);

            // Navigate to Network & Internet
            MobileElement networkLabelElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Network\")"));
            networkLabelElem.click();

            // Navigate to Wi-Fi
            By wifiLabelSel = MobileBy.xpath("//*[@text='Wi-Fi']");
            appiumDriver.findElement(wifiLabelSel).click();

            // Toggle ON/OFF
            By wifiStatusSel = MobileBy.id("com.lge.wifisettings:id/switch_text");
            MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
            String wifiStatus = wifiStatusElem.getText().trim();
            if (wifiStatus.equalsIgnoreCase("on"))
                wifiStatusElem.click();

            // Back to previous and interact with elements
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            // DEBUG
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
