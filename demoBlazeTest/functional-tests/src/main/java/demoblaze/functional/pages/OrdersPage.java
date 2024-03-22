package demoblaze.functional.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import demoblaze.functional.utilities.RandomGenerator;

public class OrdersPage extends BasePage {

    @FindBy(xpath = "//h5[contains(text(),'Place order')]")
    private WebElement placeOrderHeader;

    @FindBy(id = "name")
    private static WebElement nameInputField;

    @FindBy(id = "country")
    private static WebElement countryInputField;

    @FindBy(id = "city")
    private static WebElement cityInputField;

    @FindBy(id = "card")
    private static WebElement cardInputField;

    @FindBy(id = "month")
    private static WebElement monthInputField;

    @FindBy(id = "year")
    private static WebElement yearInputField;

    @FindBy(xpath = "//button[contains(text(),'Purchase')]")
    private static WebElement purchaseButton;

    @FindBy(xpath = "//h2[contains(text(),'Thank you for your purchase')]")
    private static WebElement purchaseConfirmationHeader;

    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    public void verifyPlaceOrderLanding() throws Throwable {
        getWaitHelper().shortSleep();
        Assert.assertEquals(placeOrderHeader.getAttribute("innerHTML"), "Place order");
    }

    public void verifyPurchaseConfirmationLanding() throws Throwable {
        getWaitHelper().shortSleep();
        Assert.assertEquals(purchaseConfirmationHeader.getAttribute("innerHTML"), "Thank you for your purchase");
    }

    public void inputDataIntoPlaceOrderFields(String country, String city, String creditCard, String month, String year)
            throws Throwable {
        String nameText = RandomGenerator.randomString();

        getWaitHelper().waitForElementVisibility(placeOrderHeader);

        enterTextInInputBox(nameInputField, nameText);
        enterText(countryInputField, country);
        enterText(cardInputField, creditCard);
        enterText(monthInputField, month);
        enterText(yearInputField, year);
    }
}