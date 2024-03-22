package demoblaze.functional.base;

import org.testng.annotations.AfterSuite;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.TestNGCucumberRunner;

public class FunctionalTestBase extends AbstractTestNGCucumberTests{

    private TestNGCucumberRunner testNGCucumberRunner;

    @AfterSuite
    public void tearDownDriver() {
        WebDriverFactory.getInstance().quit();
    }

    public TestNGCucumberRunner getTestNGCucumberRunner() {
        return testNGCucumberRunner;
    }
}