package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;

abstract public class SearchPageObject extends MainPageObject {
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_SUBSTRING_TEMPLATE,
            SEARCH_CANCEL_BUTTON,
            SEARCH_CLEAR_FIELD_BUTTON,
            SEARCH_RESULT_LOCATOR,
            SEARCH_EMPTY_RESULT_ELEMENT;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TEMPLATE.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "cannot find and click search init element", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "cannot find search result with substring " + substring, 5);
    }

    public WebElement waitForSearchResultAndSave(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        return this.waitForElementPresent(search_result_xpath, "cannot find search result with substring " + substring, 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "cannot find and click search result with substring " + substring, 15);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "cannot find X to cancel search", 10);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "search cancel button is still present", 5);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "cannot find and click search cancel button", 5);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(SEARCH_RESULT_LOCATOR, "can't find anything by the request " + SEARCH_RESULT_LOCATOR, 15);
        return this.getAmountOfElements(SEARCH_RESULT_LOCATOR);
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "can't find 'no results by the request'", 15);
    }

    public void assertThereIsNoResultSearch() {
        this.assertElementNotPresent(SEARCH_RESULT_LOCATOR, "we have found some results by request");
    }

    public void waitForArticleToDisappear(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementNotPresent(search_result_xpath, "article is still visible with substring " + substring, 5);
    }

    public void assertArticleTitleHasText(String substring, String lookingFor) {
        String search_result_xpath = getResultSearchElement(substring);
        this.assertElementHasText(search_result_xpath, lookingFor, "cannot find in article word " + lookingFor, 5);
    }

    public void assertSearchInputText(String search_input) {
        this.assertElementHasText(SEARCH_INIT_ELEMENT, search_input, "text doesn't match with search input", 5);
    }

}
