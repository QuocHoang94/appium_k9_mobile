package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod01;
import platform.Platform;

public class LoginWithPOMMod01 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            LoginScreenMod01 loginScreen = new LoginScreenMod01(appiumDriver);
            loginScreen.usernameElem().sendKeys("teo@sth.com");
            loginScreen.passwordElem().sendKeys("12345678");
            loginScreen.loginBtnElem().click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
