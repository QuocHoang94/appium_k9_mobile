package tests.form;

import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_flows.form.FormFlow;
import tests.BaseTest;

public class FormTest extends BaseTest {

    @Test
    @Issue("JIRA-321")
    public void testFormInput() {
        FormFlow formFlow = new FormFlow(getDriver());
        formFlow.gotoFormScreen();
        formFlow.fillTheForm();
        formFlow.verifyFormDisplay();
    }
}
