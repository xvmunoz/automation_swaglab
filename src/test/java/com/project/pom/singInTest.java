package com.project.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.Random;

public class singInTest {
    private WebDriver driver;
    singInPage singinp;

    //Random object to get random value form user and password list
    private Random ram;

    @Before
    public void setUp(){
        singinp = new singInPage(driver);
        driver = singinp.safariDriverConnection();
        singinp.goTo("https://www.saucedemo.com");
    }

    @Test
    public void signInTest() {
       singinp.signIn(1);
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
