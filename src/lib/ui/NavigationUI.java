package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            SAVED_LIST_BUTTON,
            BACK_BUTTON,
            CANCEL_SEARCH_RESULTS_BUTTON,
            SEARCH_INIT_ELEMENT;

    public void clickMyLists() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "can not find search field", 10);
        this.waitForElementAndClick(SAVED_LIST_BUTTON, "can't click save lists button", 10);
    }

    public void closeKeyboard() {
        driver.hideKeyboard();
    }

    public void returnOnPreviousPage() {
       this.waitForElementAndClick(BACK_BUTTON, "can not find back button at search results", 10);
    }

    public void clickOnCancelSearchResultsButton() {
        this.waitForElementAndClick(CANCEL_SEARCH_RESULTS_BUTTON, "can not find cancel button", 10);
    }

}
