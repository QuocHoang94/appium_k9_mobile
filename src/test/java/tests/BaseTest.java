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

    protected AppiumDriver<MobileElement> getDriver(){
        return driverThread.get().getDriver(Platform.ANDROID, udid, systemPort);
    }

    @BeforeTest
    @Parameters({"udid", "systemPort"})
    public void initAppiumSession(String udid, String systemPort){
        this.udid = udid;
        this.systemPort = systemPort;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverThread = new DriverFactory();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession(){
        driverThread.get().quitAppiumDriver();
    }

    @AfterMethod(description = "Capture screenshot if test is failed")
    public void captureScreenshot(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            // 1. Get the test method name
            String testMethodName = result.getName();

            // 2. Get taken time | y-m-d-hr-mm-s
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int mm = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String takenTime = y + "-" + m + "-" + d + "-" + hr + "-" + mm + "-" + sec;

            // 3. File location to save
            String fileName = testMethodName + "-" + takenTime + ".png";
            String fileLocation = System.getProperty("user.dir") + "/screenshots/" + fileName;

            // 4. Save then attach to Allure report
            File screenshotBase64Data = getDriver().getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotBase64Data, new File(fileLocation));
                Path screenshotContentPath = Paths.get(fileLocation);
                InputStream inputStream = Files.newInputStream(screenshotContentPath);
                Allure.addAttachment(testMethodName, inputStream);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}