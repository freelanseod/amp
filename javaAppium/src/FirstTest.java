import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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

        //skip intro
        (new TouchAction(driver)).tap(135, 1695).perform();
        WebElement skipCustomization = driver.findElementById("org.wikipedia:id/view_announcement_action_negative");
        skipCustomization.click();
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

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
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

}
