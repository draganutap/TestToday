package demoblaze.functional.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import demoblaze.functional.enums.DeviceCategories;
import demoblaze.functional.enums.HeaderMenu;

public class NavigationPage extends BasePage {

    @FindBy(xpath = "//div[contains(concat(' ', @class, ' '), ' list-group')]")
    private WebElement CATEGORIES_COLUMN;

    @FindBys(@FindBy(xpath = "//a[contains(concat(' ', @class, ' '), ' list-group-item')]"))
    private List<WebElement> CATEGORIES_ITEMS;

    @FindBys(@FindBy(xpath = "//a[contains(text(),'Home')]"))
    private WebElement homeHeaderButton;

    @FindBys(@FindBy(xpath = "//a[contains(text(),'Contact')]"))
    private WebElement contactHeaderButton;

    @FindBys(@FindBy(xpath = "//a[contains(text(),'About Us')]"))
    private WebElement aboutUsHeaderButton;

    @FindBys(@FindBy(xpath = "//a[contains(text(),'Cart')]"))
    private WebElement cartHeaderButton;

    @FindBys(@FindBy(xpath = "//a[contains(text(),'Log in')]"))
    private WebElement logInHeaderButton;

    @FindBys(@FindBy(xpath = "//a[contains(text(),'Sign up')]"))
    private WebElement signUpHeaderButton;

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    public void navigateCategoriesItems(DeviceCategories deviceCategories) throws Exception {
        switch (deviceCategories) {
            case PHONES:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                CATEGORIES_ITEMS.get(1).click();
                break;
            case LAPTOPS:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                CATEGORIES_ITEMS.get(2).click();
                break;
            case MONITORS:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                CATEGORIES_ITEMS.get(3).click();
                break;
        }
    }

    public void navigateHeaderMenuItems(HeaderMenu headerMenu) throws Exception {
        switch (headerMenu) {
            case HOME:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                clickButton(homeHeaderButton);
                break;
            case CONTACT:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                clickButton(contactHeaderButton);
                break;
            case ABOUT_US:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                clickButton(aboutUsHeaderButton);
                break;
            case CART:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                clickButton(cartHeaderButton);
                break;
            case LOG_IN:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                clickButton(logInHeaderButton);
                break;
            case SIGN_UP:
                getWaitHelper().waitForPageLoad();
                getWaitHelper().waitForElementVisibility(CATEGORIES_COLUMN);
                clickButton(signUpHeaderButton);
                break;
        }
    }
}