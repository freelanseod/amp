package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public static final String
            FOLDER_BY_NAME_TEMPLATE = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TEMPLATE = "//*[@text='{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder) {
        return FOLDER_BY_NAME_TEMPLATE.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathByTitle(String article_title) {
        return ARTICLE_BY_TITLE_TEMPLATE.replace("{TITLE}", article_title);
    }

    public void openFolderByName(String name_of_folder) {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(By.xpath(folder_name_xpath), "can't find and click folder by name " + name_of_folder, 10);
    }

    public void waitForArticleToAppear(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementPresent(By.xpath(article_xpath), "cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappear(String article_title) {
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(By.xpath(article_xpath), "saved article still present", 15);
    }

    public void swipeByArticleToDelete(String article_title) {
        this.waitForArticleToAppear(article_title);

        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath), "can't find saved article");

        this.waitForArticleToDisappear(article_xpath);
    }



}
