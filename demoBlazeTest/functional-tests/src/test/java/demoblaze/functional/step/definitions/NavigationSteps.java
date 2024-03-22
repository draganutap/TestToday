package demoblaze.functional.step.definitions;

import demoblaze.functional.enums.DeviceCategories;
import demoblaze.functional.enums.HeaderMenu;
import demoblaze.functional.pages.NavigationPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class NavigationSteps extends BaseStep {

  private NavigationPage navigationPage = new NavigationPage(getWebDriver());

  @Given("^User navigates to the public site$")
  public void userNavigatesToPublicSite() throws Throwable {
    navigationPage.open(getProperties().getDemoblazeUrl());
  }

  @When("^User navigates to \"([^\"]*)\"$")
  public void navigateCategoriesItems(DeviceCategories deviceCategories) throws Exception {
    navigationPage.navigateCategoriesItems(deviceCategories);
  }

  @And("^User navigates to \"([^\"]*)\" within the Header Menu$")
  public void navigateHeaderMenuItems(HeaderMenu headerMenu) throws Exception {
    navigationPage.navigateHeaderMenuItems(headerMenu);
  }
}