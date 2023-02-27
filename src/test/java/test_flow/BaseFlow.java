package test_flow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;

public class BaseFlow {

    protected final AppiumDriver<MobileElement> appiumDriver;

    public BaseFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void goToLoginScreen() {
        new BottomNavComponent(appiumDriver).clickOnLoginIconNav();
    }

    public void goToFormsScreen() {
        new BottomNavComponent(appiumDriver).clickOnFormsIconNav();
    }
}
