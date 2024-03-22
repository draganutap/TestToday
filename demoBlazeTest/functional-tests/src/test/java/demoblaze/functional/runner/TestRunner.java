package demoblaze.functional.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import demoblaze.functional.base.FunctionalTestBase;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberPropertiesProvider;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@CucumberOptions(features = "src/test/resources/features/OrderPlacement.feature", glue = {
        "demoblaze.functional.step.definitions" }, tags = "@OrderTest", plugin = { "pretty",
                "html:target/cucumber-reports/index.html",
                "json:target/cucumber-reports/cucumber.json", "rerun:target/cucumber-reports/rerun.txt" })

public class TestRunner extends FunctionalTestBase {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass(ITestContext context) {
        XmlTest currentXmlTest = context.getCurrentXmlTest();
        CucumberPropertiesProvider properties = currentXmlTest::getParameter;
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass(), properties);
    }

    @SuppressWarnings("unused")
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }
        return testNGCucumberRunner.provideScenarios();
    }

    @SuppressWarnings("unchecked")
    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        System.out.println("TestEnd");
        testNGCucumberRunner.finish();
        File reportOutputDirectory = new File("target/cucumber-reports");
        List jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber-reports/cucumber.json");

        String projectName = "Functional tests for Drone Management";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Browser", "Chrome");
        configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}