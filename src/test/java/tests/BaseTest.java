package tests;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import platform.Platform;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;

    private String udid;
    private String systemPort;
    private String platform;
    private String platformVersion;

    protected AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getDriver(Platform.valueOf(platform), udid, systemPort, platformVersion);
    }

    @BeforeTest
    @Parameters({"udid", "systemPort", "platform", "platformVersion"})
    public void initAppiumSession(String udid, String systemPort, String platform, @Optional("platformVersion") String platformVersion) {
        this.udid = udid;
        this.systemPort = systemPort;
        this.platform = platform;
        this.platformVersion = platformVersion;
        driverThread = ThreadLocal.withInitial( () -> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest (alwaysRun = true)
    public void quitAppiumSession() {
        driverThread.get().quitAppiumDriver();
    }

    @AfterMethod (description = "Capture screen if test is failed")
    public void captureScreen(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get the test method name
            String testMethodName = result.getName();

            // 2. Get taken time | y-m-d-hr-min-s
            Calendar calendar = new GregorianCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DATE);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            String takenTime = year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second;

            // 3. File location to save
            String filename = testMethodName + "-" + takenTime + ".png";
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + filename;

            // 4. Sava & attach to Allure report
            File screenshotBase64Data = getDriver().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotBase64Data, new File(fileLocation));
                Path screenshotContentPath = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(screenshotContentPath);
                Allure.addAttachment(testMethodName, inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
