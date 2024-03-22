package demoblaze.functional.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtilities {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtilities.class);

    public static void sleep(int miliSeconds) {
        try {
            Thread.sleep(miliSeconds);
        } catch (InterruptedException e) {
            LOGGER.error("Exception caught", e);
        }
    }

    public static Object executeJavaScript(WebDriver webDriver, String script) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        return executor.executeScript(script);
    }

    public static Object executeJavaScriptOnElement(WebDriver webDriver, WebElement element, String script) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        return executor.executeScript(script, element);
    }
}
