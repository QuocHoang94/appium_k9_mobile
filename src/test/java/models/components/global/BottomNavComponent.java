package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final By loginIconSel = MobileBy.AccessibilityId("Login");
    private final By formsIconSel = MobileBy.AccessibilityId("Forms");

    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void clickOnLoginIconNav() {
        appiumDriver.findElement(loginIconSel).click();
    }

    public void clickOnFormsIconNav() {
        appiumDriver.findElement(formsIconSel).click();
    }
}
