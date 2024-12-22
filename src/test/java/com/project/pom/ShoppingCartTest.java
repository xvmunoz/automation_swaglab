package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

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
    public void addProductToCartAndValidateOnShoppingCart(){
        shoppingCartPage.printTestTitleToConsole("Add Product To Cart And Validate On Shopping Cart");
        singInPage.signIn(1);
        selectedProduct = productsPage.addToCart(0);
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.removeItemFromShoppingCartList(1);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
