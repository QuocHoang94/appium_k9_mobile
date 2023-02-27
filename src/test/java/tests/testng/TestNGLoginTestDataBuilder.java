package tests.testng;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.Platform;
import test_data.DataObjectBuilder;
import test_data.LoginCred;
import test_flow.LoginFlow;

public class TestNGLoginTestDataBuilder {

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

    @DataProvider
    public LoginCred[] loginCredsData() {
        String filePath = "/src/test/java/test_data/LoginCreds.json";
        return DataObjectBuilder.buildDataObject(filePath, LoginCred[].class);
    }
}
