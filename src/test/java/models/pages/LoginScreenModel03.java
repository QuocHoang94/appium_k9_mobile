package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class LoginScreenModel03 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private final By usernameSel = MobileBy.AccessibilityId("input-email");
    private final By passwordSel = MobileBy.AccessibilityId("input-password");
    private final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginScreenModel03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginScreenModel03 usernameElem(String usernameText) {
        if (!usernameText.isEmpty())
            appiumDriver.findElement(usernameSel).sendKeys(usernameText);
        return this;
    }

    public LoginScreenModel03 passwordElem(String passwordText) {
        if (!passwordText.isEmpty())
            appiumDriver.findElement(passwordSel).sendKeys(passwordText);
        return this;
    }

    public LoginScreenModel03 loginBtnElem() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }
}
