package api_learning;

import context.Contexts;
import context.WaitMoreThanOneContext;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HybridContext {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            MobileElement navWebviewScreenBtn = appiumDriver.findElement(MobileBy.AccessibilityId("Webview"));
            navWebviewScreenBtn.click();

            // Wait more than one context
            WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            for (String contextHandle : appiumDriver.getContextHandles()) {
                System.out.println(contextHandle);
            }

            // Switch to Webview
            appiumDriver.context(Contexts.WEB_VIEW);

            // Interact with Webview elements
            WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToggleBtnElem.click();
            List<MobileElement> menuItemsElem = appiumDriver.findElementsByCssSelector(".menu__list li a");
            Map<String, String> menuItemsDataMap = new HashMap<>();
            List<MenuItemData> menuItemDataList = new ArrayList<>();

            if (menuItemsElem.isEmpty())
                throw new RuntimeException("[ERR] There is no item in list!");
            for (MobileElement menuItemElem : menuItemsElem) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");
                if (itemHref.contains("github"))
//                    menuItemsDataMap.put("GitHub", itemHref);
                    menuItemDataList.add(new MenuItemData("GitHub", itemHref));
                else if (itemHref.contains("twitter"))
                    menuItemDataList.add(new MenuItemData("Twitter", itemHref));
                else
//                    menuItemsDataMap.put(itemText, itemHref);
                    menuItemDataList.add(new MenuItemData(itemText, itemHref));
            }

            // Verification
//            for (String itemText: menuItemsDataMap.keySet()) {
//                System.out.println("Item text is: " + itemText);
//                System.out.println("Item href is: " + menuItemsDataMap.get(itemText));
//            }

            for (MenuItemData menuItemData : menuItemDataList) {
                System.out.println(menuItemData);
            }

            // Switch back to Native context
            appiumDriver.context(Contexts.NATIVE);

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

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
