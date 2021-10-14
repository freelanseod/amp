package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        String first_article = "Object-oriented programming language";
        String second_article = "Java (programming language)";
        String ios_article = "Java (programming language)\nObject-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult(first_article);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = articlePageObject.getArticleTitle(second_article);

        if (Platform.getInstance().isAndroid()) {
            assertEquals("We see unexpected title", second_article, article_title);
        } else {
            assertEquals("We see unexpected title", ios_article, article_title);
        }
    }

    @Test
    public void testSwipeArticle() {
        String article_name = "Java (programming language)";
        String ios_article = "Java (programming language)\nObject-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(article_name);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.waitForTitleElement(article_name);
        } else {
            articlePageObject.waitForTitleElement(ios_article);
        }

        articlePageObject.swipeToFooter();
    }

}
