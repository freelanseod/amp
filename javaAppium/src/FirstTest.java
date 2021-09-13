import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/user/projects/courses/mobile/javaAppium/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);

        //skip intro
        (new TouchAction(driver)).tap(135, 1695).perform();
        waitForElementAndClick(By.id("org.wikipedia:id/view_announcement_action_negative"), "can't click on skip info button", 10);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        //start searching
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);

        //page with results
        WebElement element_to_enter_search_line = driver.findElementById("org.wikipedia:id/search_plate");
        element_to_enter_search_line.sendKeys("Java");

        waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Object-oriented programming language']"),
                "Can't find 'Object-oriented programming language' topic searching by 'Java'",
                5);

        WebElement title_element = waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Java (programming language)']"),
                "can't find article title ",
                15);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals("We see unexpected title", "Java (programming language)", article_title);
    }

    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "can not 'Search wikipedia' input", 5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), "Java", "can not find search input", 5);

        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"), "can't find search field", 5);

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "can not find X to cancel search", 5);

        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "X is still present on page", 5);
    }

    @Test
    public void testComparePublicArticle() {
        //start searching
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), "Java", "can not find search input", 5);

        waitForElementAndClick(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Object-oriented programming language']"),
                "can't find text element with text 'Object-oriented programming language'",
                15);
    }

    @Test
    public void checkTextFromSearchingInput() {
        assertElementHasText(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Search Wikipedia", "", 5);
    }

    @Test
    public void cancelSearching() {
        //start searching
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);

        //page with results
        WebElement element_to_enter_search_line = driver.findElementById("org.wikipedia:id/search_plate");
        element_to_enter_search_line.sendKeys("Golang");

        //find articles
        WebElement title_element_first = waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Go (programming language)']"),
                "can't find article title ",
                15);
        String article_title_first = title_element_first.getAttribute("text");

        WebElement title_element_second = waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"),
                "can't find article title ",
                15);
        String article_title_second = title_element_second.getAttribute("text");

        Assert.assertEquals("We see unexpected title", "Go (programming language)", article_title_first);
        Assert.assertEquals("We see unexpected title", "Golongu", article_title_second);

        //cancel search
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "can not find X to cancel search", 5);
        waitForElementNotPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Go (programming language)']"), "Article 'Go (programming language)' is still here", 5);
        waitForElementNotPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"), "Article 'Golongu' is still here", 5);
    }

    @Test
    public void findInSearchCertainWord() {
        //start searching
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), "HolidayInn", "can not find search input", 5);

        //find articles with word
        assertElementHasText(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Holiday Inn']"), "Holiday Inn", "can't find holiday inn in first article", 5);
        assertElementHasText(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Holiday Inn (film)']"), "Holiday Inn (film)", "can't find holiday inn in second article", 5);
        assertElementHasText(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Holiday Inn (musical)']"), "Holiday Inn (musical)", "can't find holiday inn in third article", 5);
    }

    @Test
    public void testSwipeArticle() {
        //start searching
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), "Golang", "can not find search input", 5);

        waitForElementAndClick(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"),
                "can't find text element with text 'Golongu'",
                15);

        waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.View')]//*[@text='Golongu']"),
                "can't find article title ",
                15);

        swipeUpToFindElement(By.xpath("//*[@text='View article in browser']"), "can't find the end of article", 20);
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), "Golang", "can not find search input", 5);
        waitForElementAndClick(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"),
                "can't find text element with text 'Golongu'",
                15);

        waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"),
                "can't find article title ",
                15);

        waitForElementAndClick(By.id("org.wikipedia:id/article_menu_bookmark"), "can't find save article button", 5);

        waitForElementAndClick(By.id("org.wikipedia:id/snackbar_action"), "can't find save article to list button", 5);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "can't find list title", 5);

        String name_of_folder = "Learning Iran";

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), name_of_folder, "can't put text into list title", 5);
        waitForElementAndClick(By.xpath("//*[@text='OK']"), "can't press OK button", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/snackbar_text"), "view list button is still visible", 10);

        //go to search results page
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
        waitForElementPresent(By.id("org.wikipedia:id/search_close_btn"), "can not find X to cancel search", 5);
        driver.hideKeyboard();

        //go to main page
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);

        waitForElementAndClick(By.xpath("//*[@content-desc='Saved']"), "can't click save lists button", 10);
        waitForElementAndClick(By.xpath("//*[@text='" + name_of_folder + "']"), "can't press Learning Iran list", 5);
        swipeElementToLeft(By.xpath("//*[@text='" + name_of_folder + "']"), "can't find saved article");
        waitForElementNotPresent(By.xpath("//*[@text='" + name_of_folder + "']"), "can't delete saved article", 5);
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);

        String search_line = "hyperprolactinemia saha syndrome";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), search_line, "can not find search input", 5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(By.xpath(search_result_locator), "can't find anything by the request " + search_result_locator, 15);

        int amount_of_search_results = getAmountOfElements(By.xpath(search_result_locator));

        Assert.assertTrue("We found too many results", amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);

        String search_line = "jhjhjhhjhjhjhj";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), search_line, "can not find search input", 5);

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";

        waitForElementPresent(By.xpath(empty_result_label), "can't find 'no results by the request '" + search_line, 15);
        assertElementNotPresent(By.xpath(search_result_locator), "We have found some results by request " + search_line);
    }

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);

        String search_line = "Golang";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), search_line, "can not find search input", 5);
        waitForElementAndClick(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"),
                "can't find text element with text 'Golongu'",
                15);

        String title_before_rotation = waitForElementAndGetAttribute(By.xpath("//*[contains(@class, 'android.view.View')]//*[@text='Golongu']"), "text",
                "can't find title of article", 15);

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(By.xpath("//*[contains(@class, 'android.view.View')]//*[@text='Golongu']"), "text",
                "can't find title of article", 15);

        Assert.assertEquals("article title have been changed after screen rotation", title_before_rotation, title_after_rotation);

        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_portrait_rotation = waitForElementAndGetAttribute(By.xpath("//*[contains(@class, 'android.view.View')]//*[@text='Golongu']"), "text",
                "can't find title of article", 15);

        Assert.assertEquals("article title have been changed after screen rotation (portrait)", title_before_rotation, title_after_portrait_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), "Golang", "can not find search input", 5);
        waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"),
                "can't find text element with text 'Golongu'",
                5);

        driver.runAppInBackground(2);

        waitForElementPresent(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='Golongu']"),
                "can't find article after returning from background",
                5);
    }

    @Test
    public void testFindTitle() {
        String first_article_name = "Golongu";

        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "can not find element to init search", 5);
        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_plate"), "Golang", "can not find search input", 5);
        waitForElementAndClick(By.xpath("//*[contains(@class, 'android.view.ViewGroup')]//*[@text='" + first_article_name + "']"),
                "can't find text element with text '" + first_article_name + "'",
                15);

        assertElementPresent(By.id("pcs-edit-section-title-description"), "there is no title element");
    }

    private void assertElementPresent(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 0);
        Assert.assertTrue(element.isEnabled());
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();

        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);

        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();

        return element;
    }

    private void assertElementHasText(By by, String searchForText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        Assert.assertEquals(error_message, searchForText, element.getAttribute("text"));
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).
                waitAction().
                moveTo(x, end_y).
                release().
                perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;

        //swipe until there is no elements
        while (driver.findElements(by).size() == 0) { // find quantity of elements by findElements = driver.findElements(by).size()
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "can't find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).
                waitAction(300).
                moveTo(left_x, middle_y).
                release().
                perform();
    }

    private int getAmountOfElements(By by) {
        List elements =  driver.findElements(by);
        return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed not to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

}
