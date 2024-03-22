package demoblaze.functional.base;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);

    private static final TestProperties properties = PropertyLoader.getProperties();

    private static String separator = File.separator;

    private static String propertyFileDownloadPath = System.getProperty("user.dir") + separator + "src" + separator
            + "test" + separator + "resources"
            + separator + "downloadFiles";

    private static WebDriver webDriver = initWebDriver();

    private static WebDriver initWebDriver() {
        return WebDriverFactory.getDriver(properties.getBrowser());
    }

    public static void reinitWebDriver() {
        webDriver = initWebDriver();
    }

    public static WebDriver getInstance() {
        return webDriver;
    }

    private static WebDriver getDriver(Browser browser) {
        LOGGER.info("Requested test browser: '{}', headless: {}", browser, properties.getHeadless());
        return switch (browser) {
            case FIREFOX -> getFirefoxDriver();
            case CHROME, EDGE -> getChromeDriver();
        };
    }

    public static WebDriver getFirefoxDriver() {
        LOGGER.info("Using Firefox browser driver at {}", properties.getFirefoxDriver());
        System.setProperty("webdriver.gecko.driver", properties.getFirefoxDriver());
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", propertyFileDownloadPath);
        profile.setPreference("browser.download.folderList",2);
        profile.setPreference("browser.download.useDownloadDir", true);
        FirefoxOptions options = new FirefoxOptions();
        if (properties.getHeadless()) {
            options.addArguments("--headless");
        }
        options.setProfile(profile);
        WebDriver driver = new FirefoxDriver(options);
        maximizeWebWindowIfNotHeadless(driver);
        return driver;
    }

    public static WebDriver getChromeDriver() {
        LOGGER.info("Using Chrome browser driver at {}", properties.getChromeDriver());
        System.setProperty("webdriver.chrome.driver", properties.getChromeDriver());

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", propertyFileDownloadPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        setHeadlessArguments(options);
        WebDriver driver = new ChromeDriver(options);
        maximizeWebWindowIfNotHeadless(driver);
        return driver;
    }

    public static WebDriver getEdgeDriver() {
        LOGGER.info("Using Edge browser driver at {}", properties.getEdgeDriver());
        System.setProperty("webdriver.edge.driver", properties.getEdgeDriver());

        EdgeOptions options = new EdgeOptions();
        setHeadlessArguments(options);
        WebDriver driver = new EdgeDriver(options);
        maximizeWebWindowIfNotHeadless(driver);
        return driver;
    }

    private static <T extends ChromiumOptions<?>> void setHeadlessArguments(T options) {
        if (properties.getHeadless()) {
            options.addArguments("--headless", "--disable-dev-shm-usage", "--no-sandbox", "--disable-gpu",
                    "--window-size=1920,1080");
        }
    }

    private static void maximizeWebWindowIfNotHeadless(WebDriver driver) {
        if (!properties.getHeadless()) {
            driver.manage().window().maximize();
        }
    }
}