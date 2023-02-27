package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NarrowDownSearchingScope {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            // Get Mobile window size
            Dimension windowSize = appiumDriver.manage().window().getSize();
            int screenHeight = windowSize.getHeight();
            int screenWidth = windowSize.getWidth();

            // Calculate touch point
            int xStartPoint = 50 * screenWidth / 100;
            int xEndPoint = 50 * screenWidth / 100;

            int yStartPoint = 0;
            int yEndPoint = 50 * screenHeight / 100;

            // Convert coordinate to PointOption
            PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
            PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

            // Using TouchAction to swipe
            TouchAction touchAction = new TouchAction(appiumDriver);

            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            List<MobileElement> notificationElems =
                    appiumDriver.findElements(MobileBy.id("android:id/notification_main_column"));

            Map<String, String> notificationContents = new HashMap<>();

            for (MobileElement notificationElem : notificationElems) {
                MobileElement titleElem = notificationElem.findElement(MobileBy.id("android:id/title"));
                MobileElement contentElem = notificationElem.findElement(MobileBy.id("android:id/text"));
                notificationContents.put(titleElem.getText().trim(), contentElem.getText().trim());
            }

            // Verification
            // App bug > always green | False negative
            if (notificationContents.keySet().isEmpty())
                throw new RuntimeException("No notification");

            for (String title : notificationContents.keySet()) {
                System.out.println("Title: " + title);
                System.out.println("Content: " + notificationContents.get(title));
            }

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
