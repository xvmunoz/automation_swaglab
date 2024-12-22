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
        int selectedProduct = productsPage.addToCart(0);
        productsPage.selectProductToSeeDetails(selectedProduct);
    }

    @Test
    public void addProductsRandomly(){
        productsPage.printTestTitleToConsole("Add Products Randomly");
        singInPage.signIn(1);
        productsPage.randomlyAddAllItemsToCart();
        productsPage.shoppingCartItemsDetailsByItem.forEach(item -> {
            System.out.println(String.format("Product Name: %s | Product Price: %s | Product Actual Status: %s"
                    ,item.getFirst()
                    ,item.get(1)
                    ,item.getLast()));
        });
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
