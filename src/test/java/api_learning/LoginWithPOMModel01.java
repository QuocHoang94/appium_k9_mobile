package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenModel01;
import platform.Platform;

public class LoginWithPOMModel01 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            LoginScreenModel01 loginScreen = new LoginScreenModel01(appiumDriver);
            loginScreen.usernameElem().sendKeys("micheo@sth.com");
            loginScreen.passwordElem().sendKeys("12345678");
            loginScreen.loginBtnElem().click();

            // DEBUG
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
