package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginScreenMod01 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel = MobileBy.AccessibilityId("input-email");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginScreenMod01(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public MobileElement usernameElem(){
        return appiumDriver.findElement(usernameSel);
    }

    public MobileElement passwordElem(){
        return appiumDriver.findElement(passwordSel);
    }

    public MobileElement loginBtnElem(){
        return appiumDriver.findElement(loginBtnSel);
    }

}