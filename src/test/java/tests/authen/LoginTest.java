package tests.authen;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import platform.Platform;
import test_flows.authentication.LoginFlow;

import java.util.*;

public class LoginTest {

    @Test
    public void testLogin() {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        List<LoginCred> loginCreds = new ArrayList<>();
        loginCreds.add(new LoginCred("teo@", "12345678"));
        loginCreds.add(new LoginCred("teo@sth.com", "1234567"));
        loginCreds.add(new LoginCred("teo@sth.com", "12345678"));

        try {
            for (LoginCred loginCred : loginCreds) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
                loginFlow.gotoLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class LoginCred {
        private String email;
        private String password;

        public LoginCred(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

}