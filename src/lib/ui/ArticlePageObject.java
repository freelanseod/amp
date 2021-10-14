package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

public abstract class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            ARTICLE_TITLE_TEMPLATE,
            FOOTER_ELEMENT,
            SAVE_ARTICLE_BUTTON,
            SAVE_ARTICLE_TO_MY_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_LIST_BUTTON;

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
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "can't find the end of article", 100);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "can't find the end of article", 40);
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(SAVE_ARTICLE_BUTTON, "can't find save article button", 5);
        this.waitForElementAndClick(SAVE_ARTICLE_TO_MY_LIST_BUTTON, "can't find save article to list button", 5);

        this.waitForElementAndClear(MY_LIST_NAME_INPUT, "can't find list title", 5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT, name_of_folder, "can't put text into list title", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON, "can't press OK button", 5);
        this.waitForElementNotPresent(MY_LIST_BUTTON, "view list button is still visible", 10);
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(SAVE_ARTICLE_TO_MY_LIST_BUTTON, "can not find option to add article to reading list", 5);
    }

}
