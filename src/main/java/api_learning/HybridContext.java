package api_learning;

import context.Contexts;
import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridContext {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

        try {
            By webviewNavBtnSel = MobileBy.AccessibilityId("Webview");
            MobileElement webviewNavBtnElem = appiumDriver.findElement(webviewNavBtnSel);
            webviewNavBtnElem.click();

            // Wait until we have one more than context
            WebDriverWait wait = new WebDriverWait(appiumDriver, 15L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            // Print all contexts
            for (String contextHandle : appiumDriver.getContextHandles()) {
                System.out.println(contextHandle);
            }

            // Switch to Webview
            appiumDriver.context(Contexts.WEB_VIEW);

            // Interact with Webview elements
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();
            List<MobileElement> menuItemsElem = appiumDriver.findElementsByCssSelector(".menu__list li a");
            Map<String, String> menuItemDataMap = new HashMap<>();
            List<MenuItemData> menuItemDataList = new ArrayList<>();

            if (menuItemsElem.isEmpty())
                throw new RuntimeException("[ERR] There is no list items!");
            for (MobileElement menuItemElem : menuItemsElem) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");
                if (itemText.isEmpty()) {
                    menuItemDataMap.put("GitHub", itemHref);
                    menuItemDataList.add(new MenuItemData("GitHub", itemHref));
                } else {
                    menuItemDataMap.put(itemText, itemHref);
                    menuItemDataList.add(new MenuItemData(itemText, itemHref));
                }
            }

            // Verification
//            for (String itemText : menuItemDataMap.keySet()) {
//                System.out.println("itemText: " + itemText);
//                System.out.println("itemHref:" + menuItemDataMap.get(itemText));
//            }

            for (MenuItemData menuItemData : menuItemDataList) {
                System.out.println(menuItemData);
            }

            // Switch back to Native context
            appiumDriver.context(Contexts.NATIVE);

            // DEBUG PURPOSE ONLY
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class MenuItemData {
        private String name;
        private String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "MenuItemData{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
}
