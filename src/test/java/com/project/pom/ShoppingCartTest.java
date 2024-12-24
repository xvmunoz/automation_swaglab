package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCartTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;
    private ShoppingCartPage shoppingCartPage;
    private int selectedProduct = 0;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.goTo(shoppingCartPage.swagLabsMainURL);
    }

    @Test
    public void addProductsToCartAndValidateOnShoppingCart(){
        shoppingCartPage.printTestTitleToConsole("Add Products To Cart And Validate On Shopping Cart");
        singInPage.signIn(1);
        productsPage.addToCart(1);
        productsPage.addToCart(2);
        System.out.println(productsPage.shoppingCartItemsDetailsByItem);
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
    }

    @Test
    public void addProductAndRemoveItFromShoppingCartList(){
        shoppingCartPage.printTestTitleToConsole("Add Product And Remove It From Shopping Cart List");
        singInPage.signIn(1);
        selectedProduct = productsPage.addToCart(0);
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.removeItemFromShoppingCartList(1);
        shoppingCartPage.goToContinueShopping();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
