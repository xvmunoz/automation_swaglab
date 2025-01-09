package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MenuTest {
    private WebDriver driver;
    private SingInPage singInPage;
    private MenuPage menuPage;
    private ProductsPage productsPage;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        menuPage = new MenuPage(driver);
        productsPage = new ProductsPage(driver);
        menuPage.goTo("https://www.saucedemo.com");
    }

    @Test
    public void validateMenuElements() {
        menuPage.printTestTitleToConsole("Validate Menu Elements");
        singInPage.signIn(1);
        menuPage.menuPageOpenMenu();
        menuPage.validateMenuPage();
    }

    @Test
    public void validateAllItemsActionFromMenu(){
        menuPage.printTestTitleToConsole("Validate All Items Action From Menu");
        singInPage.signIn(1);
        menuPage.menuPageOpenMenu();
        menuPage.validateMenuPage();
        menuPage.menuPageAllItems();
        productsPage.validateProductsPage();
    }

    @Test
    public void validateLogoutActionFromMenu(){
        menuPage.printTestTitleToConsole("Validate Logout Action From Menu");
        singInPage.signIn(1);
        productsPage.validateProductsPage();
        menuPage.menuPageOpenMenu();
        menuPage.validateMenuPage();
        menuPage.menuPageLogout();
        singInPage.validateSignInPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
