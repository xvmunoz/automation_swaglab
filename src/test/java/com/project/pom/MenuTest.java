package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MenuTest {
    private WebDriver driver;
    SingInPage singInPage;
    MenuPage menuPage;
    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        menuPage = new MenuPage(driver);
        menuPage.goTo("https://www.saucedemo.com");
    }

    @Test
    public void validateMenuElements() {
        singInPage.printTestTitleToConsole("Validate Menu Elements");
        singInPage.signIn(1);
        menuPage.validateMenuPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
