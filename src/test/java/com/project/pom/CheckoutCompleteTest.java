package com.project.pom;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CheckoutCompleteTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutYourInformationPage checkoutYourInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;
    //Instance Of Faker Class To Generate Dynamic Customer Data
    private Faker javaFaker;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        javaFaker = new Faker();
        checkoutCompletePage.goTo(checkoutYourInformationPage.swagLabsMainURL);
    }

    @Test
    public void validateCheckoutCompletePageElementsArePresent(){
        checkoutCompletePage.printTestTitleToConsole("Validate Checkout Complete Page Elements Are Present");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
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
        checkoutOverviewPage.validateCheckoutOverviewPage();
        checkoutOverviewPage.checkoutOverviewPageFinishCheckout();
        checkoutCompletePage.validateCheckoutCompletePage();
    }

    @Test
    public void validateMenuAllItemsActionFromCheckoutCompletePage(){
        checkoutCompletePage.printTestTitleToConsole("Validate Menu All Items Action From Checkout Complete Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
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
        checkoutOverviewPage.validateCheckoutOverviewPage();
        checkoutOverviewPage.checkoutOverviewPageFinishCheckout();
        checkoutCompletePage.validateCheckoutCompletePage();
        checkoutCompletePage.menuPageOpenMenu();
        checkoutCompletePage.validateMenuPage();
        checkoutCompletePage.menuPageAllItems();
        productsPage.validateProductsPage();
    }

    @Test
    public void validateMenuLogoutActionFromCheckoutCompletePage(){
        checkoutCompletePage.printTestTitleToConsole("Validate Menu Logout Action From Checkout Complete Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
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
        checkoutOverviewPage.validateCheckoutOverviewPage();
        checkoutOverviewPage.checkoutOverviewPageFinishCheckout();
        checkoutCompletePage.validateCheckoutCompletePage();
        checkoutCompletePage.menuPageOpenMenu();
        checkoutCompletePage.validateMenuPage();
        checkoutCompletePage.menuPageLogout();
        singInPage.validateSignInPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
