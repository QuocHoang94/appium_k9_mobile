package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod03;
import platform.Platform;

public class LoginWithPOMMod03 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtnElem.click();

            LoginScreenMod03 loginScreen = new LoginScreenMod03(appiumDriver);
            loginScreen.inputUsername("teo@sth.com").inputPassword("12345678").clickOnLoginBtn();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}