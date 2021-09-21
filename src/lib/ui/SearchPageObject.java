package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "org.wikipedia:id/search_plate",
            SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "//*[contains(@class, 'android.view.ViewGroup')]//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_LOCATOR = "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TEMPLATE.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line, "cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "cannot find search result with substring " + substring, 5);
    }

    public WebElement waitForSearchResultAndSave(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        return this.waitForElementPresent(By.xpath(search_result_xpath), "cannot find search result with substring " + substring, 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "cannot find and click search result with substring " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "cannot find X to cancel search", 10);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "search cancel button is still present", 5);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_LOCATOR), "can't find anything by the request " + SEARCH_RESULT_LOCATOR, 15);

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_LOCATOR));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "can't find 'no results by the request'", 15);
    }

    public void assertThereIsNoResultSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_LOCATOR), "we have found some results by request");
    }

    public void waitForArticleToDisappear(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(By.xpath(search_result_xpath), "article is still visible with substring " + substring, 5);
    }

    public void assertArticleTitleHasText(String substring, String lookingFor) {
        String search_result_xpath = getResultSearchElement(substring);
        this.assertElementHasText(By.xpath(search_result_xpath), lookingFor, "cannot find in article word " + lookingFor, 5);
    }

    public void assertSearchInputText(String search_input) {
        this.assertElementHasText(By.xpath(SEARCH_INIT_ELEMENT), search_input, "text doesn't match with search input", 5);
    }

}
