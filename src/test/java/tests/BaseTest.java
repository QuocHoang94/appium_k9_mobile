package tests;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import platform.Platform;

public class BaseTest {

    protected static AppiumDriver<MobileElement> appiumDriver;

    @BeforeTest
    public void initAppiumSession(){
        appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession(){
        appiumDriver.quit();
    }
}