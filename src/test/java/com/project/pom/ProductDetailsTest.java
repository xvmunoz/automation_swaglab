package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ProductDetailsTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;
    private int selectedProduct = 0;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        singInPage.goTo(singInPage.swagLabsMainURL);
    }

    @Test
    public void productTest(){
        singInPage.signIn(1);
        //selectedProduct = productsPage.addToCart(0);
        selectedProduct = 1;
        productDetailsPage.validateProductSelectedDetails(productsPage.selectProductToSeeDetails(selectedProduct));
        productDetailsPage.addToCartCurrentProduct();
        productDetailsPage.removeProductFromCurrentCart();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
