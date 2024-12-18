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

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        productsPage = new ProductsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        singInPage.goTo("https://www.saucedemo.com");
    }

    @Test
    public void productTest(){
        singInPage.signIn(1);
        int selectedProduct = productsPage.addToCart(0);
        System.out.println(productDetailsPage.validateProductSelectedDetails(productsPage.selectProductToSeeDetails(selectedProduct)));;
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
