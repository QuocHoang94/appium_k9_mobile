package test_flow;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormFlow extends BaseFlow {

    public FormFlow(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public void checkSwitchBtn() {
        // Wait until on Forms screen
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (MobileBy.xpath("//android.widget.TextView[contains(@text, \"Form components\")]")));

        // Toggle ON/OFF
        MobileElement switchButtonSel = appiumDriver.findElement(MobileBy.AccessibilityId("switch"));
        MobileElement switchStatusSel = appiumDriver.findElement(MobileBy.AccessibilityId("switch-text"));
        String switchStatusText = switchStatusSel.getText().trim();
        if (switchStatusText.contains("ON"))
            switchButtonSel.click();
        System.out.println(switchStatusSel.getText());
    }
}
