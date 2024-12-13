package com.project.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Base {
    private WebDriver driver;

    public Base(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver safariDriverConnection(){
        driver = new SafariDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void goTo(String URL){
        driver.get(URL);
    }

    public void setText(By locator,String text){
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator){
        return driver.findElement(locator).getText();
    }

    public List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public boolean elementIsVisible(By locator){
        return driver.findElement(locator).isDisplayed();
    }

    public boolean validateElementIsVisible_Time(By locator, Duration time){
        try{
            WebDriverWait elementVisible = new WebDriverWait(driver, time);
            return elementVisible.until((d) -> d.findElement(locator).isDisplayed());
        }catch (TimeoutException e){
            return false;
        }
    }

    public void waitForElementIsVisible_Seconds(By locator,int Seconds){
        WebDriverWait elementVisible = new WebDriverWait(driver, Duration.ofSeconds(Seconds));
        elementVisible.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
