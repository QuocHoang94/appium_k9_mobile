package test_flow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.login.LoginFormComponent;
import org.apache.commons.validator.routines.EmailValidator;
import org.testng.Assert;

public class LoginFlow extends BaseFlow {

    private String username;
    private String password;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
        super(appiumDriver);
        this.username = username;
        this.password = password;
    }

    public void login() {
        LoginFormComponent loginComps = new LoginFormComponent(appiumDriver);
        if (!username.isEmpty())
            loginComps.usernameElem(username);
        if (!password.isEmpty())
            loginComps.passwordElem(password);
        loginComps.loginBtnElem();
    }

    public void verifyLogin() {
        LoginFormComponent loginFormComp = new LoginFormComponent(appiumDriver);
        boolean isValidEmail = EmailValidator.getInstance().isValid(username);
        boolean isValidPassword = password.length() >= 8;

        if (isValidEmail && isValidPassword)
            verifyCorrectLoginCreds(loginFormComp);

        if (!isValidEmail)
            verifyIncorrectEmail(loginFormComp);

        if (!isValidPassword)
            verifyIncorrectPassword(loginFormComp);
    }

    // TODO: Homework
    @Step("Verify login with correct creds")
    private void verifyCorrectLoginCreds(LoginFormComponent loginFormComp) {
        String actual = loginFormComp.getLoginSuccessfullyTextSel();
        String expected = "Success";
        Assert.assertEquals(actual, expected, "[ERR] Invalid login success str: not match");
    }

    @Step("Verify login with incorrect email")
    private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
        String actual = loginFormComp.getIncorrectEmailTextSel();
        String expected = "Please enter a valid email address";

//        // For failed test Allure report purpose
//        String expected = "Please enter a valid email";
        Assert.assertEquals(actual, expected, "[ERR] Invalid email str: not match");
    }

    @Step("Verify login with incorrect password")
    private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
        String actual = loginFormComp.getIncorrectPasswordTextSel();
        String expected = "Please enter at least 8 characters";
        Assert.assertEquals(actual, expected, "[ERR] Invalid password str: not match");
    }
}
