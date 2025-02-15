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
        productsPage.validateProductsPage();
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);

    }

    @Test
    public void addProductAndRemoveItFromShoppingCartList(){
        shoppingCartPage.printTestTitleToConsole("Add Product And Remove It From Shopping Cart List");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        productsPage.addToCart(0);
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage
                .validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        List<String> itemRemovedFromShoppingCartPage = shoppingCartPage.removeItemFromShoppingCartList(0);
        List<String> itemRemovedFromProductsPage = productsPage.removeProductDetailsFromCart(itemRemovedFromShoppingCartPage.getLast());
        shoppingCartPage.goToContinueShopping();
    }

    @Test
    public void addProductAndValidateOnShoppingCartList(){
        shoppingCartPage.printTestTitleToConsole("Add Product And Remove It From Shopping Cart List");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
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
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
    }

    @Test
    public void addAllProductsAndRemoveEachOneOnShoppingCartPageList(){
        shoppingCartPage.printTestTitleToConsole("Add All Products And Remove Each One On Shopping Cart Page List");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.validateShoppingCartPage();
        shoppingCartPage.validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(productsPage.shoppingCartItemsDetailsByItem);
        while (!shoppingCartPage.getItemsInShoppingCartList().isEmpty()) {
            if(shoppingCartPage.getItemsInShoppingCartList().isEmpty()){
                break;
            }else {
                List<String> itemRemovedFromShoppingCartPage = shoppingCartPage.removeItemFromShoppingCartList(0);
                List<String> itemRemovedFromProductsPage = productsPage.removeProductDetailsFromCart(itemRemovedFromShoppingCartPage.getLast());
                System.out.println("\n******------******\n");
            }
        }
        shoppingCartPage.goToContinueShopping();
    }

    @Test
    public void validateMenuAllItemsActionFromShoppingCartPage(){
        shoppingCartPage.printTestTitleToConsole("Validate Menu All Items Action From Shopping Cart Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.menuPageOpenMenu();
        shoppingCartPage.validateMenuPage();
        shoppingCartPage.menuPageAllItems();
        productsPage.validateProductsPage();
    }

    @Test
    public void validateMenuLogoutActionFromShoppingCartPage(){
        shoppingCartPage.printTestTitleToConsole("Validate Menu Logout Action From Shopping Cart Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        productsPage.randomlyAddAllItemsToCart();
        productsPage.goToShoppingCart();
        shoppingCartPage.menuPageOpenMenu();
        shoppingCartPage.validateMenuPage();
        shoppingCartPage.menuPageLogout();
        singInPage.validateSignInPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
