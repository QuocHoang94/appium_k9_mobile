package tests.parallel;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.models.LoginCred;
import test_flows.authentication.LoginFlow;
import tests.BaseTest;

public class Login extends BaseTest {

    @Description("Login Test with data driven")
    @Test(dataProvider = "loginCredData", description = "Login Test")
    @TmsLink("TEST-123")
    public void testLogin(LoginCred loginCred) {
        System.out.println("--> Session ID: " + getDriver().getSessionId());
        LoginFlow loginFlow = new LoginFlow(getDriver(), loginCred.getEmail(), loginCred.getPassword());
        loginFlow.gotoLoginScreen();
        loginFlow.login();
        loginFlow.verifyLogin();
    }

    @DataProvider
    public LoginCred[] loginCredData() {
        String filePath = "/src/test/java/test_data/authen/LoginCreds.json";
        return DataObjectBuilder.buildDataObject(filePath, LoginCred[].class);
    }

}