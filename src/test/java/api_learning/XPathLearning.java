package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

import java.util.List;

public class XPathLearning {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login Screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            // Find all matching elements
            List<MobileElement> credFieldsElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            credFieldsElem.get(USERNAME_INDEX).sendKeys("mije@sth.com");
            credFieldsElem.get(PASSWORD_INDEX).sendKeys("88888888");

            // Find login  text by UISelector
            MobileElement loginInstructionElem =
                    appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInstructionElem.getText());

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
