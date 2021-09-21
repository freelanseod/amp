package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            ARTICLE_TITLE_TEMPLATE = "//*[@content-desc='{SUBSTRING}']", ////*[@content-desc='Golongu'] //*[contains(@class, 'android.view.View')]//*[@text='{SUBSTRING}']
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            SAVE_ARTICLE_BUTTON = "org.wikipedia:id/article_menu_bookmark",
            SAVE_ARTICLE_TO_MY_LIST_BUTTON = "org.wikipedia:id/snackbar_action",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            MY_LIST_BUTTON = "org.wikipedia:id/snackbar_text";

    /* TEMPLATES METHODS */
    private static String getResultTitleElement(String substring) {
        return ARTICLE_TITLE_TEMPLATE.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public WebElement waitForTitleElement(String substring) {
        String title_result_xpath = getResultTitleElement(substring);
        return this.waitForElementPresent(By.xpath(title_result_xpath), "cannot find title article on page", 15);
    }

    public String getArticleTitle(String article) {
        WebElement title_element = waitForTitleElement(article);
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "can't find the end of article", 20);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(By.id(SAVE_ARTICLE_BUTTON), "can't find save article button", 5);
        this.waitForElementAndClick(By.id(SAVE_ARTICLE_TO_MY_LIST_BUTTON), "can't find save article to list button", 5);

        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT), "can't find list title", 5);
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT), name_of_folder, "can't put text into list title", 5);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "can't press OK button", 5);
        this.waitForElementNotPresent(By.id(MY_LIST_BUTTON), "view list button is still visible", 10);
    }

}
