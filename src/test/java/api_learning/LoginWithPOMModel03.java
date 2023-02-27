package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenModel03;
import platform.Platform;

public class LoginWithPOMModel03 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            LoginScreenModel03 loginScreen = new LoginScreenModel03(appiumDriver);
            loginScreen.usernameElem("micheo@sth.com")
                       .passwordElem("12345678")
                       .loginBtnElem();

            // DEBUG
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
