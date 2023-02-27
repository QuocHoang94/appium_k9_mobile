package tests;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_flow.FormFlow;

public class FormTest extends BaseTest {

    @Test
    @Issue("JIRA-321")
    public void testFormsScreen() {
        FormFlow formFlow = new FormFlow(getDriver());
        formFlow.goToFormsScreen();
        formFlow.checkSwitchBtn();
    }
}
