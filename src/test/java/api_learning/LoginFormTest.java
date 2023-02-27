package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

public class LoginFormTest {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Navigate to Login screen
            MobileElement navLoginScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginScreenBtn.click();

            // Find login form element
            MobileElement inputEmailElement = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement inputPassElement = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElement = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            // Fill the form and login
            inputEmailElement.sendKeys("mije@sth.com");
            inputPassElement.sendKeys("88888888");
            loginBtnElement.click();

            // Verification | Login dialog
            // ID | android:id/alertTitle
            WebDriverWait waitTime = new WebDriverWait(appiumDriver, 5);
            waitTime.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));
            MobileElement loginDialogTitleElem = appiumDriver.findElement(MobileBy.id("android:id/alertTitle"));
            System.out.println(loginDialogTitleElem.getText());

            // DEBUG PURPOSE ONLY
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
