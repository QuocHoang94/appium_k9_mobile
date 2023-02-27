package tests.parallel;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.DataObjectBuilder;
import test_data.LoginCred;
import test_flow.LoginFlow;
import tests.BaseTest;

public class LoginDevice01 extends BaseTest {

    @Description("Login Test with data driven")
    @Test(dataProvider = "loginCredData", description = "Login Test")
    @TmsLink("JIRA-123")
    public void testLogin(LoginCred loginCred) {
        System.out.println("--> Session ID: " + getDriver().getSessionId());
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
