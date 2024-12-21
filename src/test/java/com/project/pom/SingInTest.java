package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class SingInTest {
    private WebDriver driver;
    SingInPage singInPage;

    //Random object to get random value form user and password list
    private Random ram;

    @Before
    public void setUp(){
        singInPage = new SingInPage(driver);
        driver = singInPage.safariDriverConnection();
        singInPage.goTo("https://www.saucedemo.com");
    }

    @Test
    public void signInTest() {
        singInPage.printTestTitleToConsole("SignIn Test");
        singInPage.signIn(1);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
