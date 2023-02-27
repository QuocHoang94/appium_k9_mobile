package tests.testng;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_flow.LoginFlow;

public class TestNGLoginTestDataProvider {

    @Test(dataProvider = "loginCredsData")
    public void testLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getUsername(), loginCred.getPassword());
            loginFlow.goToLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();

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

        @Override
        public String toString() {
            return "LoginCred{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @DataProvider
    public LoginCred[] loginCredsData() {
        LoginCred loginCred01 = new LoginCred("micheo@", "12345678");
        LoginCred loginCred02 = new LoginCred("micheo@sth.com", "123456");
        LoginCred loginCred03 = new LoginCred("micheo@sth.com", "12345678");
        return new LoginCred[]{loginCred01, loginCred02, loginCred03};
    }
}
