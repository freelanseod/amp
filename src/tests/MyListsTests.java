package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        String article_name = "Java (programming language)";
        String name_of_folder = "Learning Java";
        String article_name_ios = "Java (programming language)\nObject-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(article_name);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement(article_name);

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        //go to search results page
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.returnOnPreviousPage();
        searchPageObject.waitForCancelButtonToAppear();

        //go to main page
        if (Platform.getInstance().isAndroid()) {
            navigationUI.returnOnPreviousPage();
        } else {
            navigationUI.clickOnCancelSearchResultsButton();
        }
        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
            myListsPageObject.swipeByArticleToDelete(article_name);
        } else {
            myListsPageObject.closePopUpSyncSavedArticles();
            myListsPageObject.swipeByArticleToDelete(article_name_ios);
        }

    }

    @Test
    public void testDeleteOnlyOneArticle() {
        String article_name = "Java (programming language)";
        String article_name_ios = "Java (programming language)\nObject-oriented programming language";

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring(article_name);

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement(article_name);

        articlePageObject.addArticlesToMySaved();

        //go to search results page
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.returnOnPreviousPage();

        //open another one article
        searchPageObject.clickByArticleWithSubstring("Java version history");
        articlePageObject.waitForTitleElement("Java version history");
        articlePageObject.addArticlesToMySaved();

        navigationUI.returnOnPreviousPage();
        searchPageObject.waitForCancelButtonToAppear();
        navigationUI.clickOnCancelSearchResultsButton();

        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        myListsPageObject.closePopUpSyncSavedArticles();
        myListsPageObject.swipeByArticleToDeleteMoreThanOneArticle(article_name_ios, 1);
    }

}
