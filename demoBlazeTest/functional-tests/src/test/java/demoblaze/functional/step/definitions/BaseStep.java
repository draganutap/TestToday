package demoblaze.functional.step.definitions;

import org.openqa.selenium.WebDriver;

import demoblaze.functional.base.PropertyLoader;
import demoblaze.functional.base.TestProperties;
import demoblaze.functional.base.WebDriverFactory;

import lombok.AccessLevel;
import lombok.Getter;

public class BaseStep {

    @Getter(value = AccessLevel.PROTECTED)
    private final TestProperties properties = PropertyLoader.getProperties();

    @Getter(value = AccessLevel.PROTECTED)
    private final WebDriver webDriver = WebDriverFactory.getInstance();
}