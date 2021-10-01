package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TEMPLATE = "xpath://*[@name='{TITLE}']";
        CLOSE_SYNC_SAVED_ARTICLES_BUTTON = "xpath://*[@name='Close']";
    }

    public IOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
