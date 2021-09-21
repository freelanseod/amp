package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();

        String search_line = "Golang";
        searchPageObject.typeSearchLine(search_line);
        String article_name = "Golongu";
        searchPageObject.clickByArticleWithSubstring(article_name);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = articlePageObject.getArticleTitle(article_name);

        this.rotateScreenLandscape();

        String title_after_rotation = articlePageObject.getArticleTitle(article_name);
        assertEquals("article title have been changed after screen rotation", title_before_rotation, title_after_rotation);

        this.rotateScreenPortrait();

        String title_after_portrait_rotation = articlePageObject.getArticleTitle(article_name);
        assertEquals("article title have been changed after screen rotation (portrait)", title_before_rotation, title_after_portrait_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();

        String search_line = "Golang";
        searchPageObject.typeSearchLine(search_line);
        String article_name = "Golongu";
        searchPageObject.waitForSearchResult(article_name);

        this.backgroundApp(2);
        searchPageObject.waitForSearchResult(article_name);
    }

}
