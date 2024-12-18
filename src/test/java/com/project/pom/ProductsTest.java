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
        singInPage.signIn(1);
        int selectedProduct = productsPage.addToCart(0);
        productsPage.selectProductToSeeDetails(selectedProduct);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
