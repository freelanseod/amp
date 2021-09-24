package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            ARTICLE_TITLE_TEMPLATE = "xpath://*[@content-desc='{SUBSTRING}']",
            FOOTER_ELEMENT = "xpath://*[@text='View article in browser']",
            SAVE_ARTICLE_BUTTON = "id:org.wikipedia:id/article_menu_bookmark",
            SAVE_ARTICLE_TO_MY_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            MY_LIST_BUTTON = "id:org.wikipedia:id/snackbar_text";

    /* TEMPLATES METHODS */
    private static String getResultTitleElement(String substring) {
        return ARTICLE_TITLE_TEMPLATE.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement(String substring) {
        String title_result_xpath = getResultTitleElement(substring);
        return this.waitForElementPresent(title_result_xpath, "cannot find title article on page", 15);
    }

    public String getArticleTitle(String article) {
        WebElement title_element = waitForTitleElement(article);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "can't find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "can't find save article button", 5);
        this.waitForElementAndClick(SAVE_ARTICLE_TO_MY_LIST_BUTTON, "can't find save article to list button", 5);

        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "can't find list title", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "can't put text into list title", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "can't press OK button", 5);
        this.waitForElementNotPresent(MY_LIST_BUTTON, "view list button is still visible", 10);
    }

}
