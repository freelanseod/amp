package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearchButton();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();

        String search_line = "hyperprolactinemia saha syndrome";
        searchPageObject.typeSearchLine(search_line);

        assertTrue("We found too many results", searchPageObject.getAmountOfFoundArticles() > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();

        String search_line = "jhjhjhhjhjhjhj";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultSearch();
    }

    @Test
    public void testCheckTextFromSearchingInput() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.assertSearchInputText("Search Wikipedia");
    }

    @Test
    public void testCancelSearching() {
        //start searching
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();

        //page with results
        String search_line = "Golang";
        searchPageObject.typeSearchLine(search_line);

        //find articles
        String first_article = "Go (programming language)";
        String second_article = "Golongu";

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title_first = articlePageObject.getArticleTitle(first_article);
        String article_title_second = articlePageObject.getArticleTitle(second_article);

        assertEquals("We see unexpected title", "Go (programming language)", article_title_first);
        assertEquals("We see unexpected title", "Golongu", article_title_second);

        //cancel search
        searchPageObject.clickCancelSearchButton();
        searchPageObject.waitForCancelButtonToDisappear();
        searchPageObject.waitForArticleToDisappear(article_title_first);
        searchPageObject.waitForArticleToDisappear(article_title_second);
    }

    @Test
    public void testFindInSearchCertainWord() {
        //start searching
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();

        String search_line = "Holiday Inn";
        searchPageObject.typeSearchLine(search_line);

        //find articles with word
        String lookForWordSecondArticle = "Holiday Inn (film)";
        String lookForWordThirdArticle = "Holiday Inn (musical)";
        searchPageObject.assertArticleTitleHasText("Holiday Inn", search_line);
        searchPageObject.assertArticleTitleHasText("Holiday Inn (film)", lookForWordSecondArticle);
        searchPageObject.assertArticleTitleHasText("Holiday Inn (musical)", lookForWordThirdArticle);
    }

}
