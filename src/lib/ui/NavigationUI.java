package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SAVED_LIST_BUTTON = "xpath://*[@content-desc='Saved']";

    public void clickMyLists() {
        this.waitForElementAndClick(SAVED_LIST_BUTTON, "can't click save lists button", 10);
    }

    public void closeKeyboard() {
        driver.hideKeyboard();
    }

    public void returnOnPreviousPage() {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }
}
