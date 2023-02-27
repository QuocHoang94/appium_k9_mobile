package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenModel02;
import platform.Platform;

public class LoginWithPOMModel02 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            LoginScreenModel02 loginScreen = new LoginScreenModel02(appiumDriver);
            loginScreen.usernameElem("micheo@sth.com");
            loginScreen.passwordElem("12345678");
            loginScreen.loginBtnElem();

            // DEBUG
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
