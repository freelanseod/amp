package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        String first_article = "Object-oriented programming language";
        String second_article = "Java (programming language)";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult(first_article);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        String article_title = articlePageObject.getArticleTitle(second_article);

        assertEquals("We see unexpected title", second_article, article_title);
    }

    @Test
    public void testSwipeArticle() {
        String article_name = "Golongu";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Golang");
        searchPageObject.clickByArticleWithSubstring(article_name);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement(article_name);
        articlePageObject.swipeToFooter();
    }

}
