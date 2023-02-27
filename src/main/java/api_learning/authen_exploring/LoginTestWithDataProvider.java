package api_learning.authen_exploring;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_flows.authentication.LoginFlow;

public class LoginTestWithDataProvider {

    @Test(dataProvider = "loginCredData")
    public void testLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
            loginFlow.gotoLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
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

        @Override
        public String toString() {
            return "LoginCred{" +
                    "email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    @DataProvider
    public LoginCred[] loginCredData() {
        LoginCred loginCred01 = new LoginCred("teo@", "12345678");
        LoginCred loginCred02 = new LoginCred("teo@sth.com", "1234567");
        LoginCred loginCred03 = new LoginCred("teo@sth.com", "12345678");
        return new LoginCred[]{loginCred01, loginCred02, loginCred03};
    }

}
