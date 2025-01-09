package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductDetailsTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;
    private int selectedProduct = 0;
    private List<String> productAction;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        singInPage.goTo(singInPage.swagLabsMainURL);
    }

    @Test
    public void selectProductAndAddToCart(){
        productDetailsPage.printTestTitleToConsole("Select Product And Add To Cart");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        selectedProduct = 1;
        productDetailsPage.validateProductSelectedDetails(productsPage.selectProductToSeeDetails(selectedProduct));
        productAction = productDetailsPage.addToCartCurrentProduct();
        productDetailsPage.goBackToProducts();
        productsPage.validateProductStatusAddedAfterProductDetailsByProductNumber(selectedProduct, productAction, productDetailsPage.totalItemsInCart);
    }

    @Test
    public void selectAddedProductAndRemoveToCart(){
        productDetailsPage.printTestTitleToConsole("Select Added Product And Remove From Cart");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        selectedProduct = productsPage.addToCart(0);
        productDetailsPage.validateProductSelectedDetails(productsPage.selectProductToSeeDetails(selectedProduct));
        productAction = productDetailsPage.removeProductFromCart();
        productDetailsPage.goBackToProducts();
        productsPage.validateProductStatusRemovedAfterProductDetailsByProductNumber(selectedProduct, productAction, productDetailsPage.totalItemsInCart);
    }

    @Test
    public void validateMenuAllItemsActionFromProductsDetailsPage(){
        productDetailsPage.printTestTitleToConsole("Validate Menu All Items Action From Products Details Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        selectedProduct = productsPage.addToCart(0);
        productDetailsPage.validateProductSelectedDetails(productsPage.selectProductToSeeDetails(selectedProduct));
        productDetailsPage.menuPageOpenMenu();
        productDetailsPage.validateMenuPage();
        productDetailsPage.menuPageAllItems();
        productsPage.validateProductsPage();
    }

    @Test
    public void validateMenuLogoutActionFromProductsDetailsPage(){
        productDetailsPage.printTestTitleToConsole("Validate Menu Logout Action From Products Details Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        selectedProduct = productsPage.addToCart(0);
        productDetailsPage.validateProductSelectedDetails(productsPage.selectProductToSeeDetails(selectedProduct));
        productDetailsPage.menuPageOpenMenu();
        productDetailsPage.validateMenuPage();
        productDetailsPage.menuPageLogout();
        singInPage.validateSignInPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
