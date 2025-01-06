package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends CheckoutOverviewPage{
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    //Checkout Complete Page Locators
    By checkoutCompletePageShoppingCartLocator = By.xpath("//div[@id = 'shopping_cart_container']/a[@class = 'shopping_cart_link']");
    By checkoutCompletePageTitleLocator = By.xpath("//div[@class = 'header_secondary_container']/span[contains(text(),'Complete')]");
    By checkoutCompletePageContainerLocator = By.xpath("//div[@id = 'checkout_complete_container']");
    By checkoutCompletePageBackHomeButtonLocator = By.xpath("//button[contains(@id,'back') and contains(text(),'Back Home')]");

    //Checkout Overview Page header message
    public final String checkoutCompletePageHeaderMessage = "|From Checkout - Complete Page:";

    public void validateCheckoutCompletePage(){
        //Validate Elements Checkout Complete Page Elements Are Present
        if(validateElementIsVisible_Time(checkoutCompletePageTitleLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(checkoutCompletePageContainerLocator,time_out_limit_milliseconds)
                && validateElementIsVisible_Time(checkoutCompletePageBackHomeButtonLocator,time_out_limit_seconds)){
            printToConsoleWithHeader(checkoutCompletePageHeaderMessage,"Checkout - Complete Page Elements, Are Present.");
        }else {
            throw new NoSuchElementException(String.format("%s Checkout Complete Page Elements Are Not Present."
                    ,checkoutCompletePageHeaderMessage));
        }
    }
}
