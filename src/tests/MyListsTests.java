package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        String article_name = "Golongu";
        String name_of_folder = "Learning Iran";

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Golang");
        searchPageObject.clickByArticleWithSubstring(article_name);

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement(article_name);
        articlePageObject.addArticleToMyList(name_of_folder);

        //go to search results page
        NavigationUI navigationUI = new NavigationUI(driver);
        navigationUI.returnOnPreviousPage();
        searchPageObject.waitForCancelButtonToAppear();

        navigationUI.closeKeyboard();

        //go to main page
        navigationUI.returnOnPreviousPage();

        navigationUI.clickMyLists();

        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_name);
    }

}
