package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUi extends NavigationUI {

    static {
            SAVED_LIST_BUTTON = "xpath://*[@content-desc='Saved']";
            BACK_BUTTON = "xpath://*[contains(@class, 'android.widget.ImageButton')]";
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
    }

    public AndroidNavigationUi(AppiumDriver driver) {
        super(driver);
    }

}
