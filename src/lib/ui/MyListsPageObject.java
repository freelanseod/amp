package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.SearchPageObjectFactory;

abstract public class MyListsPageObject extends MainPageObject {

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    protected static String
            FOLDER_BY_NAME_TEMPLATE,
            ARTICLE_BY_TITLE_TEMPLATE,
            CLOSE_SYNC_SAVED_ARTICLES_BUTTON;

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TEMPLATE.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TEMPLATE.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(folder_name_xpath, "can't find and click folder by name " + name_of_folder, 10);
    }

    public void waitForArticleToAppear(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(article_xpath, "cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappear(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(article_xpath, "saved article still present", 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppear(article_title);

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(article_xpath, "can't find saved article");

        if (Platform.getInstance().isIos()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "can not find saved article to delete");
        }

        this.waitForArticleToDisappear(article_xpath);
    }

    public void swipeByArticleToDeleteMoreThanOneArticle(String article_title, int expected_count) {
        this.waitForArticleToAppear(article_title);

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(article_xpath, "can't find saved article");

        if (Platform.getInstance().isIos()) {
           //жду ответа в чате this.clickElementToTheRightUpperCorner(article_xpath, "can not find saved article to delete");
        }

        checkCountOffArticles("can not check count of articles for " + article_title, expected_count);
    }

    public void checkCountOffArticles(String error_message, int expected_count) {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        int actual_count = searchPageObject.getAmountOfFoundArticles();
        if (actual_count != expected_count) {
            new Exception(error_message + actual_count);
        }
    }

    public void closePopUpSyncSavedArticles() {
        this.waitForElementAndClick(CLOSE_SYNC_SAVED_ARTICLES_BUTTON, "can not find X button", 10);
    }

}
