package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.LoginCred;
import test_flow.LoginFlow;

public class LoginTestWithBaseTest extends BaseTest {

    @Test(dataProvider = "loginCredData")
    public void testLogin(LoginCred loginCred) {
        LoginFlow loginFlow = new LoginFlow(getDriver(), loginCred.getUsername(), loginCred.getPassword());
        loginFlow.goToLoginScreen();
        loginFlow.login();
        loginFlow.verifyLogin();
    }

    @DataProvider
    public LoginCred[] loginCredData() {
        String filePath = "/src/test/java/test_data/LoginCreds.json";
        return DataObjectBuilder.buildDataObject(filePath, LoginCred[].class);
    }
}
