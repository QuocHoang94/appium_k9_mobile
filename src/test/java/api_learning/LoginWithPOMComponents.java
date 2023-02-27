package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreen;
import platform.Platform;

public class LoginWithPOMComponents {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            LoginScreen loginScreen = new LoginScreen(appiumDriver);
            loginScreen.bottomNavComp().clickOnLoginIconNav();
            loginScreen.loginFormComp().usernameElem("micheo@sth.com");
            loginScreen.loginFormComp().passwordElem("12345678");
            loginScreen.loginFormComp().loginBtnElem();

            // DEGUB
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
