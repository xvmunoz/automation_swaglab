package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.NoSuchElementException;

public class CheckoutYourInformationTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutYourInformationPage checkoutYourInformationPage;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
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

    @After
    public void tearDown(){
        //driver.close();
    }
}
