package api_learning;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import platform.Platform;

import java.time.Duration;

public class HandleMultipleApps {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            // Find Login form elements
            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            // Fill the form and login
            emailInputElem.sendKeys("teo@sth.com");
            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

            // Put the app under test to background in a certain time | simulate pressing home button > relaunch
            // appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            // Put the app under test to background till we call it back
            appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

            // Switch into another app | Go to Settings toggle wifi
            appiumDriver.activateApp(AppPackages.SETTINGS);

            // Navigate to network list
            By wifiLabelSel = MobileBy.xpath("//*[@text='Wi-Fi']");
            appiumDriver.findElement(wifiLabelSel).click();

            // Toggle ON/OFF
            By wifiStatusSel = MobileBy.id("com.android.settings:id/switch_text");
            MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
            String wifiStatusStr = wifiStatusElem.getText().trim();
            boolean isWifiOn = wifiStatusStr.equalsIgnoreCase("on");
            if(isWifiOn) wifiStatusElem.click();

            // Come back to the app > interact with other elements
            appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

}
