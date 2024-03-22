package demoblaze.functional.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CartPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[contains(text(),'Place Order')]")
    private WebElement placeOrderButton;

    @FindBy(xpath = "//a[contains(text(),'Samsung galaxy s6')]")
    private WebElement mobileProduct;

    @FindBy(xpath = "//a[contains(text(),'Sony vaio i5')]")
    private WebElement laptopProduct;

    @FindBy(xpath = "//a[contains(text(),'Apple monitor 24')]")
    private WebElement monitorProduct;

    @FindBy(xpath = "//h2[contains(text(),'Samsung galaxy s6')]")
    private WebElement mobileProductHeader;

    private boolean headless;

    public CartPage(WebDriver driver, boolean isHeadless) {
        super(driver);
        this.headless = isHeadless;
    }

    public void clickAddToCartButton() throws Throwable {
        getWaitHelper().shortSleep();
        clickElement(addToCartButton);
    }

    public void clickPlaceOrderButton() throws Throwable {
        getWaitHelper().shortSleep();
        clickElement(placeOrderButton);
    }

    public void clickOnMobileProduct() throws Throwable {
        getWaitHelper().shortSleep();
        clickElement(mobileProduct);
    }

    public void clickOnLaptopProduct() throws Throwable {
        getWaitHelper().shortSleep();
        clickElement(laptopProduct);
    }

    public void clickOnMonitorProduct() throws Throwable {
        getWaitHelper().shortSleep();
        clickElement(monitorProduct);
    }

    public void verifyMobileProductLanding() throws Throwable {
        getWaitHelper().shortSleep();
        Assert.assertEquals(mobileProductHeader.getAttribute("innerHTML"), "Samsung galaxy s6");
    }

    public void closeProductCartValidationDialog() throws Exception {
        if (headless) {
        } else {
            switchToOpenedWindow();
            executeJS(
                    "document.querySelector('body > print-preview-app').shadowRoot.querySelector('#sidebar').shadowRoot.querySelector('print-preview-button-strip').shadowRoot.querySelector('div > cr-button.cancel-button').click();");
            switchToMainWindow();
        }
    }
}