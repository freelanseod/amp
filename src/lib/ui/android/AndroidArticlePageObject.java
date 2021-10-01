package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                ARTICLE_TITLE_TEMPLATE = "xpath://*[contains(@class, 'android.view.View')]//*[@text='{SUBSTRING}']"; //"xpath://*[contains(@class, 'android.view.View')]//*[@text='{SUBSTRING}']"      xpath://*[@content-desc='{SUBSTRING}']
                FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
                SAVE_ARTICLE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark";
                SAVE_ARTICLE_TO_MY_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
                MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
                MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
                MY_LIST_BUTTON = "id:org.wikipedia:id/snackbar_text";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

}
