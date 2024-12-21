package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends ProductsPage{

    //Shopping Cart Elements Locators
    By shoppingCartTitleLocator = By.xpath("//span[contains(text(),'Your Cart')]");
    By shoppingCartListLocator = By.xpath("//div[@class = 'cart_list']");
    By shoppingCartItemsLocator = By.xpath("(//div[@class = 'cart_list']//descendant::div[@class = 'cart_item'])");
    By shoppingCartRemoveItemButtonLocator = By.xpath("(//div[@class = 'cart_list']//descendant::button[contains(@id,'remove')])");
    By shoppingCartContinueShoppingButtonLocator = By.xpath("//button[@id = 'continue-shopping']");
    By shoppingCartCheckoutButtonLocator = By.xpath("//button[@id = 'checkout']");

    //Shopping cart header message
    public final String shoppingCartHeaderMessage = "|From shopping cart page:";

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void validateShoppingCartPage(){
        //Validate if Shopping Cart Title and Item List are visible and Validate Shopping Cart Total Items Are Same Total Items Displayed On Shopping Cart List
        if(validateElementIsVisible_Time(shoppingCartTitleLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(shoppingCartListLocator,time_out_limit_seconds)
                && validateItemsInShoppingCartSameAsItemsOnShoppingCartList(Integer.parseInt(getText(super.getShoppingCartCountLocator))
                ,getItemsInShoppingCartList().size())){
            printToConsoleWithHeader(shoppingCartHeaderMessage,String.format("We are on shopping cart, total in cart: %s, total in shopping list: %s."
                    , getText(super.getShoppingCartCountLocator),getItemsInShoppingCartList().size()));
        }else {
            throw new NoSuchElementException(String.format("%s Shopping Cart List is not visible, Element -> '%s' is not present."
                    ,shoppingCartHeaderMessage,shoppingCartListLocator));
        }
    }

    public void goToContinueShopping(){
        //Validate Continue Shopping Button Is Visible
        if(validateElementIsVisible_Time(shoppingCartContinueShoppingButtonLocator,time_out_limit_seconds)){
            click(shoppingCartContinueShoppingButtonLocator);
        }else {
            throw new NoSuchElementException(String.format("%s Continue Shopping Button is not visible, Element -> '%s' is not present."
                    ,shoppingCartHeaderMessage,shoppingCartContinueShoppingButtonLocator));
        }
    }

    public void goToCheckOut(){
        //Validate Checkout Button Is Visible
        if(validateElementIsVisible_Time(shoppingCartCheckoutButtonLocator,time_out_limit_seconds)){
            click(shoppingCartCheckoutButtonLocator);
        }else {
            throw new NoSuchElementException(String.format("%s Checkout Button is not visible, Element -> '%s' is not present."
                    ,shoppingCartHeaderMessage,shoppingCartCheckoutButtonLocator));
        }
    }

    public boolean validateItemsInShoppingCartSameAsItemsOnShoppingCartList(int shoppingCartItems, int shoppingCartItemsList){
        return shoppingCartItems == shoppingCartItemsList;
    }

    public void removeItemFromShoppingCartList(int itemNumberOnList){
        //Validate Item Number Is On List And Has Remove Status

    }

    public String getItemLocatorOnListByItemNumber(int itemNumberOnList){
        return String.format("%s[%s]",shoppingCartItemsLocator,itemNumberOnList);
    }

    public List<WebElement> getItemsInShoppingCartList(){
        return getElements(shoppingCartItemsLocator);
    }
}
