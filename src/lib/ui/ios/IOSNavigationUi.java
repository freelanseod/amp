package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class IOSNavigationUi extends NavigationUI {
    static {
        SAVED_LIST_BUTTON = "id:Saved";
        BACK_BUTTON = "id:Back";
        CANCEL_SEARCH_RESULTS_BUTTON = "xpath://*[@name='Cancel']";
        SEARCH_INIT_ELEMENT = "xpath://*[@name='Search Wikipedia']";
    }

    public IOSNavigationUi(AppiumDriver driver) {
        super(driver);
    }


}
