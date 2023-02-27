package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginScreenModel02 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final static By usernameSel = MobileBy.AccessibilityId("input-email");
    private final static By passwordSel = MobileBy.AccessibilityId("input-password");
    private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginScreenModel02(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void usernameElem(String usernameText) {
        if (!usernameText.isEmpty())
            appiumDriver.findElement(usernameSel).sendKeys(usernameText);
    }

    public void passwordElem(String passwordText) {
        if (!passwordText.isEmpty())
            appiumDriver.findElement(passwordSel).sendKeys(passwordText);
    }

    public void loginBtnElem() {
        appiumDriver.findElement(loginBtnSel).click();
    }
}
