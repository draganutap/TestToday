package demoblaze.functional.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.stream.Stream;

import lombok.Synchronized;

public class PropertyLoader {

    private Properties properties = new Properties();

    private static TestProperties testProperties;

    private String separator = File.separator;

    private String propertyFilePath = (System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "resources" + separator + "config.properties");

    private PropertyLoader() {
        
        try (InputStream stream = new FileInputStream(propertyFilePath)) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Synchronized
    public static TestProperties getProperties() {
        if (Objects.isNull(testProperties)) {
            PropertyLoader propertyLoader = new PropertyLoader();
            testProperties = new TestProperties();
            testProperties.setBrowser(propertyLoader.getBrowser());
            testProperties.setChromeDriver(propertyLoader.getChromeDriverPath());
            testProperties.setFirefoxDriver(propertyLoader.getFirefoxDriverPath());
            testProperties.setDemoblazeUrl(propertyLoader.getUrl());
            testProperties.setHeadless(propertyLoader.getHeadless());
        }
        return testProperties;
    }

    public Browser getBrowser() {
        return Browser.valueOf(getFirstNonNull("Invalid browser.",
                () -> System.getProperty("browser"),
                () -> properties.getProperty("browser")).toUpperCase());
    }

    public String getUrl() {
        return getFirstNonNull("Invalid URL.",
                () -> System.getProperty("demoBlazeUrl"),
                () -> properties.getProperty("demoBlazeUrl"));
    }

    public String getChromeDriverPath() {
        return getFirstNonNull("Chrome driver not found.",
                () -> System.getProperty("chrome.driver.path"),
                () -> properties.getProperty("chrome.driver.path"));
    }

    public String getFirefoxDriverPath() {
        return getFirstNonNull("Firefox driver not found.",
                () -> System.getProperty("firefox.driver.path"),
                () -> properties.getProperty("firefox.driver.path"));
    }

    public Boolean getHeadless() {
        return getFirstNonNull("Headless option not found.",
                () -> System.getProperty("headless"),
                () -> properties.getProperty("headless")).equals("true");
    }

    @SafeVarargs
    private String getFirstNonNull(String errorMessage, Supplier<String> ... propertySources) {
        return Stream.of(propertySources)
            .map(Supplier::get)
            .filter(Objects::nonNull)
            .findFirst()
            .orElseThrow(() -> new ConfigurationException(errorMessage));
    }
}