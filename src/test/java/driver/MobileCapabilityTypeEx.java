package driver;

import io.appium.java_client.remote.MobileCapabilityType;

public interface MobileCapabilityTypeEx extends MobileCapabilityType {

    String PLATFORM_NAME = "platformName";
    String APP_PACKAGE = "appPackage";
    String APP_ACTIVITY = "appActivity";
    String SYSTEM_PORT = "systemPort";
    String BUNDLE_ID = "bundleId";
    String WDA_LOCAL_PORT = "wdaLocalPort";
}
