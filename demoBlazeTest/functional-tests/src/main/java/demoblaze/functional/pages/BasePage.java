package demoblaze.functional.pages;

import static demoblaze.functional.utilities.CommonUtilities.executeJavaScript;
import static demoblaze.functional.utilities.CommonUtilities.executeJavaScriptOnElement;
import static demoblaze.functional.utilities.CommonUtilities.sleep;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import demoblaze.functional.utilities.ElementWaitHelper;
import lombok.AccessLevel;
import lombok.Getter;

public class BasePage {

    public final WebDriver webDriver;

    @Getter(value = AccessLevel.PROTECTED)
    private final ElementWaitHelper waitHelper;

    private String separator = File.separator;
    private String path = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator
            + "resources"
            + separator + "screenCaptures";

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.waitHelper = new ElementWaitHelper(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public void open(String pageUrl) {
        webDriver.get(pageUrl);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void clearText(WebElement element) {
        element.click();
        element.clear();
    }

    public void enterText(WebElement element, String text) {
        clearText(element);
        element.sendKeys(text);
    }

    public boolean isElementPresent(WebElement element) {
        return Optional.ofNullable(element)
                .map(e -> isConditionOnElement(e::isDisplayed))
                .orElse(false);
    }

    public boolean isElementClickable(WebElement element) {
        if (Objects.isNull(element)) {
            return false;
        }
        try {
            waitHelper.waitForClickability(element);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean isConditionOnElement(Supplier<Boolean> condition) {
        try {
            return condition.get();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitAndClickElement(WebElement element) {
        waitHelper.waitForClickability(element);
        clickElement(element);
    }

    public void selectVisibleTextFromDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
        sleep(500);
    }

    // Operations that rely on JS
    public void waitAndClickByJSElement(WebElement element) {
        waitHelper.waitForClickability(element);
        clickByJS(element);
    }

    public void sleepAndClickByJSElement(WebElement element, int sleepTime) {
        sleep(sleepTime);
        clickByJS(element);
    }

    public boolean clickByJS(WebElement element) {
        try {
            executeJavaScriptOnElement(webDriver, element, "arguments[0].click();");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToElement(WebElement element) {
        executeJavaScriptOnElement(webDriver, element, "arguments[0].scrollIntoView(true);;window.scrollBy(0, -120)");
        waitHelper.waitForElementVisibility(element);
    }

    public void enterTextJSClick(WebElement element, String text) {
        element.clear();
        clickByJS(element);
        sleep(500);
        element.sendKeys(text);
    }

    public void enterTextAfterClearingTextUsingJS(WebElement element, String text) {
        clickByJS(element);
        element.clear();
        String value = element.getAttribute("innerHTML");
        if (StringUtils.isEmpty(value)) {
            return;
        }
        clearElementJS(element);
        value = element.getAttribute("innerHTML");
        if (StringUtils.isEmpty(value)) {
            return;
        }
        clearElementKeyboard(element, text);
    }

    public void clearElementKeyboard(WebElement element, String text) {
        clearElementKeyboard(element);
        element.sendKeys(text);
    }

    public void clearElementKeyboard(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
    }

    public void clearElementJS(WebElement element) {
        executeJavaScriptOnElement(webDriver, element, "arguments[0].setAttribute('innerHTML' ,'');");
    }

    public void scrollToPageTop() {
        executeJavaScript(webDriver, "window.scrollTo(0, -document.body.scrollHeight)");
    }

    public void scrollToPageBottom() {
        executeJavaScript(webDriver, "window.scrollBy(0,1000)");
    }

    // Window related operations
    public void switchWindow(String parentWindow) {
        Set<String> handles = webDriver.getWindowHandles();
        if (handles.size() > 1) {
            for (String windowHandle : handles) {
                if (!windowHandle.equals(parentWindow)) {
                    webDriver.switchTo().window(windowHandle);
                }
            }
        }
    }

    public void closeWindowAndSwitchtoParent(String ParentWindow) {
        Set<String> handles = webDriver.getWindowHandles();
        if (handles.size() > 1) {
            webDriver.close();
            webDriver.switchTo().window(ParentWindow);
        }
    }

    public void navigateTo(String url) {
        webDriver.navigate().to(url);
        waitHelper.waitForPageLoad();
    }

    public void enterTextInInputBox(WebElement element, String text) {
        int limit = 0;
        while (limit <= 2) {
            enterText(element, text);
            if (element.getAttribute("value").equalsIgnoreCase(text)) {
                break;
            }
            limit++;
        }
    }

    public void mouseHoverAndClick(WebElement element1, WebElement element2) {
        Actions hover = new Actions(webDriver);
        hover.moveToElement(element1).build().perform();
        hover.moveToElement(element2).build().perform();
        hover.click(element2).build().perform();
    }

    public void selectByValueFromDropdown(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByValue(text);
    }

    public void sendKeysToXpath(String xpath, String key) {
        webDriver.findElement(By.xpath(xpath)).sendKeys(key);
    }

    public WebElement findByXpath(String element) {
        return webDriver.findElement(By.xpath(element));
    }

    public String getFilePath(Path filePath, String string) {
        Assert.assertFalse(string.isEmpty(), "FileName can not be empty.");
        return Paths.get(filePath.toString(), string).toString();
    }

    public void browseForWindowsPopUp(String stringFilePath) throws Exception {
        Robot robot = new Robot();
        StringSelection stringSelection = new StringSelection(stringFilePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        getWaitHelper().shortSleep();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        getWaitHelper().shortSleep();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        getWaitHelper().shortSleep();
    }

    public void closePrintDialog() throws Exception {
        Robot robot = new Robot();
        getWaitHelper().shortSleep();
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
    }

    public void captureFullScreen() {
        Shutterbug.shootPage(webDriver, Capture.FULL, true).save(path);
    }

    public void clickButton(WebElement element) {
        getWaitHelper().waitForElementVisibility(element);
        Assert.assertTrue(isElementPresent(element));
        scrollToElement(element);
        clickByJS(element);
    }

    public void enterTextkByJS(WebElement element, String text) {
        clearElementJS(element);
        executeJavaScriptOnElement(webDriver, element, "arguments[0].setAttribute('innerHTML' ,'');");
        element.sendKeys(text);
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                return true;
            }
        }
        return false;
    }

    public boolean delFileIfFileExists(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                // File has been found, it can now be deleted:
                dirContents[i].delete();
                return true;
            }
        }
        return false;
    }

    public void switchToOpenedWindow() {
        webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[1].toString());
    }

    public void switchToMainWindow() {
        webDriver.switchTo().window(webDriver.getWindowHandles().toArray()[0].toString());
    }

    public void executeJS(String script) {
        executeJavaScript(webDriver, script);
    }
}