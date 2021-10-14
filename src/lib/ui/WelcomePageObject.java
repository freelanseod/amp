package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "xpath://*[@name='Learn more about Wikipedia']",
            STEP_NEXT_BUTTON = "xpath://*[@name='Next']",
            STEP_NEW_WAYS_TO_EXPLORE_LINK = "xpath://*[@name='New ways to explore']",
            STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK = "xpath://*[@name='Add or edit preferred languages']",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://*[@name='Learn more about data collected']",
            GET_STARTED_BUTTON = "xpath://*[@name='Get started']",
            SKIP = "xpath://XCUIElementTypeButton[@name='Skip']",
            SKIP_ANDROID = "id:org.wikipedia:id/view_announcement_action_negative";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "can not find 'Learn more about Wikipedia' link " + STEP_LEARN_MORE_LINK, 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(STEP_NEXT_BUTTON, "can not find 'Next' button", 10);
    }

    public void waitForNewWaysToExploreText() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_LINK, "can not find 'New ways to explore' title", 10);
    }

    public void waitForAddOrEditPreferredLangText() {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANGUAGES_LINK, "can not find 'Add or edit preferred languages' button", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText() {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "can not find 'Learn more about data collected' button", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "can not find 'Get started' button", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "can not find and click skip button", 10);
    }

    public void clickSkipAndroid() {
        (new TouchAction(driver)).tap(PointOption.point(135, 1695)).perform();
        this.waitForElementAndClick(SKIP_ANDROID, "can not find and click skip button", 10);
    }

}
