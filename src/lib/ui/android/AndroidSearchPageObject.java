package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
                SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_INPUT = "id:org.wikipedia:id/search_src_text";
                SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "xpath://*[contains(@class, 'android.view.ViewGroup')]//*[@text='{SUBSTRING}']";//android.view.View  ViewGroup
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_LOCATOR = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
    }

    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
