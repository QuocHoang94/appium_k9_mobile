package tests.testng;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import platform.Platform;
import test_flow.LoginFlow;

import java.util.ArrayList;
import java.util.List;

public class TestNGLoginTest {

    @Test
    public void testLogin() {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        List<LoginCred> loginCreds = new ArrayList<>();
        loginCreds.add(new LoginCred("micheo@", "12345678"));
        loginCreds.add(new LoginCred("micheo@sth.com", "123456"));
        loginCreds.add(new LoginCred("micheo@sth.com", "12345678"));

        try {
            for (LoginCred loginCred : loginCreds) {
                LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getUsername(), loginCred.getPassword());
                loginFlow.goToLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();
            }

            // DEBUG
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class LoginCred {
        private String username;
        private String password;

        public LoginCred(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
