package com.jpetstore.pages;

import com.jpetstore.utils.PetCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.jpetstore.utils.Helper.getAppUrl;

/**
 * BasePage class extends abstract Page class.
 */
public class BasePage extends Page {

    private WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static final String ENTER_THE_STORE_LINK_XPATH = "//a[@href='actions/Catalog.action']";

    private static final String SIGN_IN_LINK_XPATH = "//*[@id='MenuContent']/a[contains(text(),'Sign In')]";

    private static final String LOGO_LINK_CSS = "div[id='LogoContent'] a[href$='.action']";

    private static final String SIGN_OUT_LINK = "//*[@id='MenuContent']/a[contains(text(),'Sign Out')]";

    private static final String MY_ACCT_LINK = "//*[@id='MenuContent']/a[contains(text(),'My Account')]";

    private static final String HELP_LINK = "//*[@id='MenuContent']/a[contains(text(),'?')]";

    private static final String SHOPPING_CART_LINK = "div[id='MenuContent'] a[href$='?viewCart=']";

    private static final String SEARCH_INPUT = "//input[@name='keyword']";

    private static final String SEARCH_BUTTON = "//input[@value='Search']";

    // Product Quick Links
    private static final String FISH_LINK = "div[id='QuickLinks'] a[href$='FISH']";

    private static final String DOGS_LINK = "div[id='QuickLinks'] a[href$='DOGS']";

    private static final String REPTILES_LINK = "div[id='QuickLinks'] a[href$='REPTILES']";

    private static final String CATS_LINK = "div[id='QuickLinks'] a[href$='CATS']";

    private static final String BIRDS_LINK = "div[id='QuickLinks'] a[href$='BIRDS']";

    private static final String BANNER_IMG = "//*[@id='Banner']/img";

    @FindBy(xpath = ENTER_THE_STORE_LINK_XPATH)
    private WebElement enterTheStoreLink;

    @FindBy(xpath = SIGN_IN_LINK_XPATH)
    private WebElement signInLink;


    /**
     * Navigate to application.
     */
    public void navigateToApp() {

        try {
            driver.navigate()
                  .to(new URL(getAppUrl()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to navigate to sign in page.
     *
     * @return LoginPage
     */
    public LoginPage navigateToSignOnPage() {
        isTextPresentOnPage("Welcome to JPetStore 6");

        clickLinkOrBtn(enterTheStoreLink);
        clickLinkOrBtn(signInLink);
        return new LoginPage(driver);
    }

    /**
     * Clicks on Sign in link.
     *
     * @return LoginPage
     */
    public LoginPage clickSignInLink() {
        clickLinkOrBtn(By.xpath(SIGN_IN_LINK_XPATH));
        return new LoginPage(driver);
    }

    /**
     * Method to navigate to DashBoard.
     *
     * @return DashboardPage
     */
    public DashboardPage navigateToDashBoard() {
        clickLinkOrBtn(By.cssSelector(LOGO_LINK_CSS));
        return new DashboardPage(driver);
    }

    /**
     * Navigate to shopping cart.
     *
     * @return ProductsPage
     */
    public ProductsPage navigateToShoppingCart() {
        clickLinkOrBtn(By.cssSelector(SHOPPING_CART_LINK));
        return new ProductsPage(driver);
    }

    /**
     * Navigate to help page.
     *
     * @return HelpPage
     */
    public HelpPage navigateToHelpPage() {
        clickLinkOrBtn(By.xpath(HELP_LINK));
        return new HelpPage(driver);
    }

    /**
     * Method to search for a product.
     *
     * @param searchValue search text entered into search field
     * @return ProductsPage
     */
    public ProductsPage searchForProduct(String searchValue) {
        enterTextIntoTextBox(By.xpath(SEARCH_INPUT), searchValue);
        clickLinkOrBtn(By.xpath(SEARCH_BUTTON));
        return new ProductsPage(driver);
    }

    /**
     * Method to sign out.
     *
     * @return DashboardPage
     */
    public DashboardPage signOut() {
        clickLinkOrBtn(By.xpath(SIGN_OUT_LINK));
        return new DashboardPage(driver);
    }

    /**
     * Navigate to accounts page.
     *
     * @return AccountsPage
     */
    public AccountsPage navigateToAccountPage() {
        clickLinkOrBtn(By.xpath(MY_ACCT_LINK));
        return new AccountsPage(driver);
    }

    /**
     * Method to select a product category from the header.
     *
     * @param productCategory which stands for PetCategories. Available PetCategories: FISH, DOGS, REPTILES, CATS, BIRDS
     * @return ProductsPage if correct PetCategory provided,
     * null if not defined pet category provided
     */
    public ProductsPage navigateToProductCategory(PetCategories productCategory) {

        switch (productCategory) {

            case FISH:
                clickLinkOrBtn(By.cssSelector(FISH_LINK));
                return new ProductsPage(driver);
            case DOGS:
                clickLinkOrBtn(By.cssSelector(DOGS_LINK));
                return new ProductsPage(driver);

            case REPTILES:
                clickLinkOrBtn(By.cssSelector(REPTILES_LINK));
                return new ProductsPage(driver);

            case CATS:
                clickLinkOrBtn(By.cssSelector(CATS_LINK));
                return new ProductsPage(driver);

            case BIRDS:
                clickLinkOrBtn(By.cssSelector(BIRDS_LINK));
                return new ProductsPage(driver);

            default:
                break;
        }
        return null;
    }
}
