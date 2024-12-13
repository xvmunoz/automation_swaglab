package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class productsTest {
    private WebDriver driver;
    private singInPage snPage;
    private productsPage prdPage;

    @Before
    public void setUp(){
        snPage = new singInPage(driver);
        driver = snPage.safariDriverConnection();
        prdPage = new productsPage(driver);
        snPage.goTo("https://www.saucedemo.com");
    }

    @Test
    public void productTest(){
        snPage.signIn(1);
        prdPage.randomlyAddAllItemsToCart();
        System.out.println(prdPage.productsInCart);
        prdPage.removeAllItemsFromCart();
        System.out.println(prdPage.productsInCart);
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
