package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        By by = this.getLocatorByString(locator);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();

        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);

        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = this.getLocatorByString(locator);

        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();

        return element;
    }

    public void assertElementHasText(String locator, String searchForText, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        Assert.assertEquals(error_message, searchForText, element.getAttribute("text"));
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(PointOption.point(x, start_y)).
                waitAction().
                moveTo(PointOption.point(x, end_y)).
                release().
                perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeUpToFindElement(String locator, String error_message, int max_swipes) {
        int already_swiped = 0;

        By by = this.getLocatorByString(locator);
        //swipe until there is no elements
        while (driver.findElements(by).size() == 0) { // find quantity of elements by findElements = driver.findElements(by).size()
            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "can't find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y)).
                waitAction(WaitOptions.waitOptions(Duration.ofMillis(300))).
                moveTo(PointOption.point(left_x, middle_y)).
                release().
                perform();
    }

    public int getAmountOfElements(String locator) {
        By by = this.getLocatorByString(locator);
        List elements =  driver.findElements(by);
        return elements.size();
    }

    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator + "' supposed not to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String byType = exploded_locator[0]; //first part of locator_with_type
        String locator = exploded_locator[1]; //second part of locator_with_type

        if (byType.equals("xpath")) {
            return By.xpath("locator");
        } else if (byType.equals("id")) {
            return By.id("locator");
        } else {
            throw new IllegalArgumentException("can not get type of locator. Locator: " + locator_with_type);
        }
    }

}
