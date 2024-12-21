package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends Base{

    //Shopping Cart Elements Locators
    By shoppingCartTitleLocator = By.xpath("//span[contains(text(),'Your Cart')]");
    By shoppingCartListLocator = By.xpath("//div[@class = 'cart_list']");
    By shoppingCartItemsLocator = By.xpath("(//div[@class = 'cart_list']//descendant::div[@class = 'cart_item'])");
    By shoppingCartContinueShoppingButtonLocator = By.xpath("//button[@id = 'continue-shopping']");
    By shoppingCartCheckoutButtonLocator = By.xpath("//button[@id = 'checkout']");

    //Shopping cart header message
    public final String shoppingCartHeaderMessage = "|From shopping cart page:";

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void validateShoppingCartPage(){
        //Validate if Shopping Cart Title and Item List are visible
        if(validateElementIsVisible_Time(shoppingCartTitleLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(shoppingCartListLocator,time_out_limit_seconds)){
            System.out.println("We are on shopping cart");
        }else {
            throw new NoSuchElementException(String.format("%s Shopping Cart List is not visible, Element -> '%s' is not present.",shoppingCartHeaderMessage,shoppingCartListLocator));
        }
    }
}
