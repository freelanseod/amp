package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://*[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://*[@name='Search Wikipedia']"; //@name="Search Wikipedia"
        SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_CANCEL_BUTTON = "xpath://*[@name='Cancel']";
        SEARCH_CLEAR_FIELD_BUTTON = "id:Clear text";
        SEARCH_RESULT_LOCATOR = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@name='No results found']";
    }

    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
