package demoblaze.functional.step.definitions;

import demoblaze.functional.pages.CartPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class CartSteps extends BaseStep {

  private CartPage cartPage = new CartPage(getWebDriver(), getProperties().getHeadless());

  @And("^User clicks the Add to Cart button$")
  public void clickAddToCartButton() throws Throwable {
    cartPage.clickAddToCartButton();
  }

  @And("^User clicks the Place Order button$")
  public void clickPlaceOrderButton() throws Throwable {
    cartPage.clickPlaceOrderButton();
  }

  @Then("^User cliks on a mobile product$")
  public void clickOnMobileProduct() throws Throwable {
    cartPage.clickOnMobileProduct();
  }

  @Then("^User clicks on a laptop product$")
  public void clickOnLaptopProduct() throws Throwable {
    cartPage.clickOnLaptopProduct();
  }

  @Then("^User clicks on a monitor product$")
  public void clickOnMonitorProduct() throws Throwable {
    cartPage.clickOnMonitorProduct();
  }

  @And("^User verifies the mobile's product Header$")
  public void verifyMobileProductLanding() throws Throwable {
    cartPage.verifyMobileProductLanding();
  }

  @And("^User closes the product cart validation dialog$")
  public void closeProductCartValidationDialog() throws Throwable {
    cartPage.closeProductCartValidationDialog();
  }
}