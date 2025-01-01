package com.project.pom;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutYourInformationPage checkoutYourInformationPage;
    private CheckoutOverviewPage checkoutOverviewPage;
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
        javaFaker = new Faker();
        checkoutOverviewPage.goTo(checkoutYourInformationPage.swagLabsMainURL);
    }

    @Test
    public void validateCheckoutOverviewPageElementsArePresent(){
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
        checkoutOverviewPage.validateCheckoutOverviewPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
