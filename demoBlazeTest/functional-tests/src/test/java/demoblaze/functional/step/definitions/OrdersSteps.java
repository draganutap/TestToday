package demoblaze.functional.step.definitions;

import demoblaze.functional.base.TestDataProvider;
import demoblaze.functional.pages.OrdersPage;
import io.cucumber.java.en.And;

public class OrdersSteps extends BaseStep {

  private OrdersPage ordersPage = new OrdersPage(getWebDriver());

  @And("^User verifies the place order's header$")
  public void verifyPlaceOrderLanding() throws Throwable {
    ordersPage.verifyPlaceOrderLanding();
  }

  @And("^User verifies the purchase confirmation's header$")
  public void verifyPurchaseConfirmationLanding() throws Throwable {
    ordersPage.verifyPurchaseConfirmationLanding();
  }

  @And("^User inputs \"([^\"]*)\" as Country, \"([^\"]*)\" as City, \"([^\"]*)\" as Credit Card, \"([^\"]*)\" as Month and \"([^\"]*)\" as Year$")
  public void inputDataIntoPlaceOrderFields(String countryKey, String cityKey, String creditCardKey, String monthKey,
      String yearKey) throws Throwable {
    ordersPage.inputDataIntoPlaceOrderFields(TestDataProvider.getProperty(countryKey),
        TestDataProvider.getProperty(cityKey), TestDataProvider.getProperty(creditCardKey),
        TestDataProvider.getProperty(monthKey), TestDataProvider.getProperty(yearKey));
  }
}