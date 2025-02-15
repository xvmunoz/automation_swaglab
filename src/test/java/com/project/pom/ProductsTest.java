package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ProductsTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        singInPage.goTo(singInPage.swagLabsMainURL);
    }

    @Test
    public void productTest(){
        productsPage.printTestTitleToConsole("Product Test");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        int selectedProduct = productsPage.addToCart(0);
        productsPage.selectProductToSeeDetails(selectedProduct);
    }

    @Test
    public void addProductsRandomly(){
        productsPage.printTestTitleToConsole("Add Products Randomly");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        productsPage.randomlyAddAllItemsToCart();
    }

    @Test
    public void validateMenuAllItemsActionFromProductsPage(){
        productsPage.printTestTitleToConsole("Validate Menu All Items Action From Products Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        productsPage.menuPageOpenMenu();
        productsPage.validateMenuPage();
        productsPage.menuPageAllItems();
        productsPage.validateProductsPage();
    }

    @Test
    public void validateMenuLogoutActionFromProductsPage(){
        productsPage.printTestTitleToConsole("Validate Menu Logout Action From Products Page");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        productsPage.menuPageOpenMenu();
        productsPage.validateMenuPage();
        productsPage.menuPageLogout();
        singInPage.validateSignInPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
