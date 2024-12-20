package com.project.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.DocFlavor;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;

public class Base {
    private WebDriver driver;

    final String swagLabsMainURL = "https://www.saucedemo.com";
    final Duration time_out_limit_seconds = Duration.ofSeconds(1);
    final Duration time_out_limit_milliseconds = Duration.ofMillis(5000);

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
        if(validateElementIsVisible_Time(locator,time_out_limit_seconds)){
            driver.findElement(locator).sendKeys(text);
        }else {
            throw new NoSuchElementException(String.format("Cannot set text to -> '%s', element is not found.",locator));
        }
    }

    public String getText(By locator){
        if(validateElementIsVisible_Time(locator,time_out_limit_seconds)){
            return driver.findElement(locator).getText();
        }else {
            throw new NoSuchElementException(String.format("Cannot get text from -> '%s', element is not found.",locator));
        }
    }

    public List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public void click(By locator){
        if(validateElementIsVisible_Time(locator,time_out_limit_seconds)){
            driver.findElement(locator).click();
        }else {
            throw new NoSuchElementException(String.format("Element -> '%s', cannot be clickable, element is not found.",locator));
        }
    }

    public boolean elementIsVisible(By locator){
        if(validateElementIsVisible_Time(locator,time_out_limit_seconds)){
            return true;
        }else {
            throw new NoSuchElementException(String.format("Element -> '%s', is not found.",locator));
        }
    }

    public boolean validateElementIsVisible_Time(By locator, Duration time){
        try{
            WebDriverWait elementVisible = new WebDriverWait(driver, time);
            return elementVisible.until((d) -> d.findElement(locator).isDisplayed());
        }catch (TimeoutException e){
            return false;
        }
    }

    public void waitForElementIsVisible_Seconds(By locator){
        WebDriverWait elementVisible = new WebDriverWait(driver, time_out_limit_seconds);
        elementVisible.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
