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
    public void addProductsToCartAndValidateOnShoppingCart(){
        shoppingCartPage.printTestTitleToConsole("Add Products To Cart And Validate On Shopping Cart");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);

    }

    @Test
    public void addProductAndRemoveItFromShoppingCartList(){
        shoppingCartPage.printTestTitleToConsole("Add Product And Remove It From Shopping Cart List");
        singInPage.signIn(1);
        productsPage.addToCart(0);
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage
                .validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        List<String> itemRemovedFromShoppingCartPage = shoppingCartPage.removeItemFromShoppingCartList(1);
        List<String> itemRemovedFromProductsPage = productsPage.removeProductDetailsFromCart(itemRemovedFromShoppingCartPage.getLast());
        shoppingCartPage.goToContinueShopping();
        //***NEED A METHOD TO REMOVE ITEM RANDOMLY PICKED FROM SHOPPING CART PAGE LIST***//
    }

    @Test
    public void addProductAndValidateOnShoppingCartList(){
        shoppingCartPage.printTestTitleToConsole("Add Product And Remove It From Shopping Cart List");
        singInPage.signIn(1);
        while (productsPage.shoppingCartItemsDetailsByItem.size() < productsPage.getProducts().size()) {
            if(productsPage.shoppingCartItemsDetailsByItem.size() == productsPage.getProducts().size()){
                break;
            }else {
                productsPage.addToCart(0);
                productsPage.goToShoppingCart();
                shoppingCartPage.validateShoppingCartPage();
                shoppingCartPage
                        .validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
                shoppingCartPage.goToContinueShopping();
                System.out.println("\n******------******\n");
            }
        }
        productsPage.goToShoppingCart();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
