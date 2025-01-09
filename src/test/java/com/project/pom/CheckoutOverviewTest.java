package com.project.pom;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

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
        checkoutOverviewPage.printTestTitleToConsole("Validate Checkout Overview Page Elements Are Present");
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

    @Test
    public void validateCheckoutOverviewPageElementsInfo(){
        checkoutOverviewPage.printTestTitleToConsole("Validate Checkout Overview Page Elements Info");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        List<List> shoppingCartItemListDetails = shoppingCartPage.getShoppingCartPageItemsDetailsList();
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
        checkoutOverviewPage.validateItemsOnShoppingCartAreDisplayedOnCheckoutOverviewPageCartItemsList(shoppingCartItemListDetails);
    }

    @Test
    public void validateCheckoutOverviewPageSummaryValuesInfoMatches(){
        checkoutOverviewPage.printTestTitleToConsole("Validate Checkout Overview Page Summary Values Info Matches");
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
        checkoutOverviewPage.validateCheckoutOverviewPageSummaryValuesMatch();
    }

    @Test
    public void validateCheckoutOverviewPageAndCancel(){
        checkoutOverviewPage.printTestTitleToConsole("Validate Checkout Overview Page And Cancel");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        List<List> shoppingCartItemListDetails = shoppingCartPage.getShoppingCartPageItemsDetailsList();
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
        checkoutOverviewPage.validateItemsOnShoppingCartAreDisplayedOnCheckoutOverviewPageCartItemsList(shoppingCartItemListDetails);
        checkoutOverviewPage.validateCheckoutOverviewPageSummaryValuesMatch();
        checkoutOverviewPage.checkoutOverviewPageCancelCheckout();
    }

    @Test
    public void validateCheckoutOverviewPageAndFinish(){
        checkoutOverviewPage.printTestTitleToConsole("Validate Checkout Overview Page And Finish");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        List<List> shoppingCartItemListDetails = shoppingCartPage.getShoppingCartPageItemsDetailsList();
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
        checkoutOverviewPage.validateItemsOnShoppingCartAreDisplayedOnCheckoutOverviewPageCartItemsList(shoppingCartItemListDetails);
        checkoutOverviewPage.validateCheckoutOverviewPageSummaryValuesMatch();
        checkoutOverviewPage.checkoutOverviewPageFinishCheckout();
    }

    @Test
    public void validateMenuAllItemsActionFromCheckoutOverviewPage(){
        checkoutOverviewPage.printTestTitleToConsole("Validate Menu All Items Action From Checkout Overview Page");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        List<List> shoppingCartItemListDetails = shoppingCartPage.getShoppingCartPageItemsDetailsList();
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
        checkoutOverviewPage.menuPageOpenMenu();
        checkoutOverviewPage.validateMenuPage();
        checkoutOverviewPage.menuPageAllItems();
        productsPage.validateProductsPage();
    }

    @Test
    public void validateMenuLogoutActionFromCheckoutOverviewPage(){
        checkoutOverviewPage.printTestTitleToConsole("Validate Menu Logout Action From Checkout Overview Page");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        List<List> shoppingCartItemListDetails = shoppingCartPage.getShoppingCartPageItemsDetailsList();
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
        checkoutOverviewPage.menuPageOpenMenu();
        checkoutOverviewPage.validateMenuPage();
        checkoutOverviewPage.menuPageLogout();
        singInPage.validateSignInPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
