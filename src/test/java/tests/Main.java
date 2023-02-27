package tests;

import com.google.common.reflect.ClassPath;
import driver.MobileCapabilityTypeEx;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import platform.Platform;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class Main implements MobileCapabilityTypeEx {

    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws IOException {

        // Get all test classes
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();

        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            String classInfoName = info.getName();
            boolean startWithTestDot = classInfoName.startsWith("tests.");
            boolean isBaseTestClass = classInfoName.startsWith("tests.BaseTest");
            boolean isMainClass = classInfoName.startsWith("tests.Main");

            if(startWithTestDot && !isBaseTestClass && !isMainClass){
                testClasses.add(info.load());
            }
        }

        // Get platform
        String platformName = System.getProperty("platform");
        if(platformName == null){
            throw new IllegalArgumentException("[ERR] Please provide platform via -Dplatform");
        }
        try {
            Platform.valueOf(platformName);
        } catch (Exception e){
            throw new IllegalArgumentException("[ERR] We don't support platform " + platformName + ", supported platforms: " + Arrays.toString(Platform.values()));
        }

        // Devices under test
        List<String> iPhoneDeviceList = Arrays.asList("iPhone 12", "iPhone 13");
        List<String> androidDeviceList = Arrays.asList("G8S1LV05133401MM", "emulator-5554");
        List<String> deviceList = platformName.equalsIgnoreCase("ios") ? iPhoneDeviceList : androidDeviceList;

        // Assign test classes into devices
        final int testNumEachDevice = testClasses.size() / deviceList.size();
        Map<String, List<Class<?>>> desiredCaps = new HashMap<>();

        for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
            int startIndex = deviceIndex * testNumEachDevice;
            boolean isTheLastDevice = deviceIndex == deviceList.size() -1;
            int endIndex = isTheLastDevice ? testClasses.size() : (startIndex + testNumEachDevice);
            List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
            desiredCaps.put(deviceList.get(deviceIndex), subTestList);
        }

        // Build dynamic test suite
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        List<XmlTest> allTests = new ArrayList<>();
        for (String deviceName : desiredCaps.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(deviceName);
            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> dedicatedClasses = desiredCaps.get(deviceName);
            for (Class<?> dedicatedClass : dedicatedClasses) {
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }

            test.setXmlClasses(xmlClasses);
            test.addParameter(UDID, deviceName);
            test.addParameter(PLATFORM_NAME, platformName);
            test.addParameter(PLATFORM_VERSION, "15.0");
            test.addParameter(SYSTEM_PORT, String.valueOf(new SecureRandom().nextInt(1000) + 8300));
            allTests.add(test);
        }

        suite.setTests(allTests);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(10);

        System.out.println(suite.toXml());

    }
}