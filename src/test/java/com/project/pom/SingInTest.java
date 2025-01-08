package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SingInTest {
    private WebDriver driver;
    SingInPage singInPage;


    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        singInPage.goTo("https://www.saucedemo.com");
    }

    @Test
    public void signInTest() {
        singInPage.printTestTitleToConsole("SignIn Test");
        singInPage.validateSignInPage();
        singInPage.signIn(1);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
