package com.project.pom;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformationTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutYourInformationPage checkoutYourInformationPage;
    //Instance Of Faker Class To Generate Dynamic Customer Data
    private Faker javaFaker;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        javaFaker = new Faker();
        checkoutYourInformationPage.goTo(checkoutYourInformationPage.swagLabsMainURL);
    }

    @Test
    public void addProductsToCartValidateItemsAddedOnShoppingCartThenStartCheckoutProcess(){
        checkoutYourInformationPage.printTestTitleToConsole("Add Products To Cart Validate Items Added On Shopping Cart Then Start Checkout Process");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        shoppingCartPage.goToCheckOut();
        checkoutYourInformationPage.validateCheckOutYourInformationPage();
        System.out.println(checkoutYourInformationPage.validateYourInformationCustomerInfoIsEmpty());
        System.out.println(checkoutYourInformationPage.validateYourInformationErrorMessageIsShown());
    }

    @Test
    public void validateIfCustomerInfoIsNotSetThenErrorMessagePopsUp(){
        checkoutYourInformationPage.printTestTitleToConsole("Validate If Customer Info Is Not Set, The Error Message Pops Up");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        shoppingCartPage.goToCheckOut();
        checkoutYourInformationPage.validateCheckOutYourInformationPage();
        checkoutYourInformationPage.validateIfCustomerInfoIsNotSetShowErrorMessage();
    }

    @Test
    public void setCustomerInformationIfInfoIsSetGoToCheckoutOverviewPage(){
        checkoutYourInformationPage.printTestTitleToConsole("Set Customer Information, If Info Is Set, Go To Checkout Overview Page");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        shoppingCartPage.goToCheckOut();
        checkoutYourInformationPage.validateCheckOutYourInformationPage();
        checkoutYourInformationPage.setCustomerInformationDetails(javaFaker.name().firstName()
                ,javaFaker.name().lastName()
                ,javaFaker.address().zipCode());
        if(checkoutYourInformationPage.validateYourInformationCustomerInfoIsEmpty())
            throw new IllegalArgumentException(String.format("%s Customer Info Were Not Set."
                    ,checkoutYourInformationPage.checkoutYourInformationHeaderMessage));
        checkoutYourInformationPage.goToCheckoutOverviewPage();
    }

    @Test
    public void validateMenuAllItemsActionFromCheckoutYourInformationPage(){
        checkoutYourInformationPage.printTestTitleToConsole("Validate Menu All Items Action From Checkout Your Information Page");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        shoppingCartPage.goToCheckOut();
        checkoutYourInformationPage.validateCheckOutYourInformationPage();
        checkoutYourInformationPage.menuPageOpenMenu();
        checkoutYourInformationPage.validateMenuPage();
        checkoutYourInformationPage.menuPageAllItems();
        productsPage.validateProductsPage();
    }

    @Test
    public void validateMenuLogoutActionFromCheckoutYourInformationPage(){
        checkoutYourInformationPage.printTestTitleToConsole("Validate Menu Logout Action From Checkout Your Information Page");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        shoppingCartPage.goToCheckOut();
        checkoutYourInformationPage.validateCheckOutYourInformationPage();
        checkoutYourInformationPage.menuPageOpenMenu();
        checkoutYourInformationPage.validateMenuPage();
        checkoutYourInformationPage.menuPageLogout();
        singInPage.validateSignInPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
