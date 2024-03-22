package demoblaze.functional.utilities;

import static demoblaze.functional.utilities.CommonUtilities.executeJavaScript;
import static demoblaze.functional.utilities.CommonUtilities.sleep;

import java.time.Duration;
import java.util.List;
import java.util.function.Supplier;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ElementWaitHelper {

    private static final int SHORT_TIMEOUT = 5;
    private static final int MEDIUM_TIMEOUT = 10;
    private static final int LONG_TIMEOUT = 15;
    private static final int SHORT_SLEEP = 2000;
    private static final int LONG_SLEEP = 4000;

    private final WebDriver driver;

    public void waitForPageLoad() {
        wait(SHORT_TIMEOUT, this::isDocumentReady);
    }

    public void shortSleep() {
        sleep(SHORT_SLEEP);
    }

    public void longSleep() {
        sleep(LONG_SLEEP);
    }

    public void waitForElementVisibility(WebElement element) {
        wait(SHORT_TIMEOUT, ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementsVisibility(List<WebElement> elements) {
        wait(SHORT_TIMEOUT, ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForElementIsDisplayed(WebElement element) {
        incrementalWait(element::isDisplayed, 2);
    }

    public boolean isElementDisplayed(WebElement element) {
        if (element == null)
            return false;
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException | NullPointerException e) {
            return false;
        }
    }

    public void waitForClickability(WebElement element) {
        waitForElementIsDisplayed(element);
        wait(SHORT_TIMEOUT, ExpectedConditions.elementToBeClickable(element));
    }

    private <T> void wait(int timeout, ExpectedCondition<T> condition) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(condition);
    }

    private boolean isDocumentReady(WebDriver webDriver) {
        return executeJavaScript(webDriver, "return document.readyState").toString().equals("complete");
    }

    private void incrementalWait(Supplier<Boolean> condition, int retries) {
        int counter = 0;
        while (counter <= retries) {
            if (condition.get()) {
                return;
            }
            sleep(SHORT_SLEEP);
            counter++;
        }
        throw new TimeoutException("Element not visible after " + retries + " retries.");
    }
}