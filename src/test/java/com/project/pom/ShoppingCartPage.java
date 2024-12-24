package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.EOFException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCartPage extends ProductsPage{

    //Shopping Cart Elements Locators
    By shoppingCartTitleLocator = By.xpath("//span[contains(text(),'Your Cart')]");
    By shoppingCartListLocator = By.xpath("//div[@class = 'cart_list']");
    String shoppingCartItemsLocator = "(//div[@class = 'cart_list']//descendant::div[@class = 'cart_item'])";
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
        if(validateElementIsVisible_Time(By.xpath(getItemLocatorOnListByItemNumber(itemNumberOnList)),time_out_limit_seconds)
                && getItemButtonStatusByItemNumber(itemNumberOnList).equals("Remove")){
            click(By.xpath(getItemButtonStatusLocatorByItemNumber(itemNumberOnList)));
            //Validate Item On List Has Been Removed
            if(!validateElementIsVisible_Time(By.xpath(getItemLocatorOnListByItemNumber(itemNumberOnList)),time_out_limit_seconds)){
                printToConsoleWithHeader(shoppingCartHeaderMessage,String.format("Product from list #%s, has been removed.",itemNumberOnList));
            }else {
                throw new IllegalArgumentException(String.format("%s Remove action could not be completed, Item still on list."));
            }
        }else {
            throw new NoSuchElementException(String.format("%s Item is not visible, Element -> '%s' is not present."
                    ,shoppingCartHeaderMessage,By.xpath(getItemLocatorOnListByItemNumber(itemNumberOnList))));
        }

    }

    public String getItemLocatorOnListByItemNumber(int itemNumberOnList){
        return String.format("%s[%s]",shoppingCartItemsLocator,itemNumberOnList);
    }

    public List<WebElement> getItemsInShoppingCartList(){
        return getElements(By.xpath(shoppingCartItemsLocator));
    }

    public String getItemNameByItemNumber(int itemNumberOnList){
        return getText(By.xpath(String.format("%s//descendant::div[@class = 'inventory_item_name']"
                ,getItemLocatorOnListByItemNumber(itemNumberOnList))));
    }

    public String getItemButtonStatusLocatorByItemNumber(int itemNumberOnList){
        return String.format("%s//descendant::button",getItemLocatorOnListByItemNumber(itemNumberOnList));
    }

    public String getItemButtonStatusByItemNumber(int itemNumberOnList){
        return getText(By.xpath(String.format("%s//descendant::button",getItemLocatorOnListByItemNumber(itemNumberOnList))));
    }

    //Validate If Shopping Cart List Has Items Added In Product Page
    public void validateItemsAddedFromProductPageAreDisplayedOnShoppingCartList(List<List> productsPageList){
        //Lists Iterators
        AtomicInteger incrementProductsPageList = new AtomicInteger(0);
        AtomicInteger incrementShoppingCartList = new AtomicInteger(0);
        //Final Result Flag
        AtomicBoolean result = new AtomicBoolean(false);
        //Iterate Inside Shopping Cart Item List To Validate That Items Added In Product Page Are Listed
        getItemsInShoppingCartList().forEach(item->{
            //Increment Shopping Cart List So It Search Item Number Inside DOM
            incrementShoppingCartList.getAndIncrement();
            //Validate Item Listed In Shopping Cart Page List Is Inside Items Added In Product Page
            System.out.println(String.format("......Checking Item '%s'......"
                    ,getItemNameByItemNumber(Integer.parseInt(incrementShoppingCartList.toString()))));
            if(getItemNameByItemNumber(Integer.parseInt(incrementShoppingCartList.toString()))
                    .equals(productsPageList.get(Integer.parseInt(incrementProductsPageList.toString())).getFirst())){
                //Validate Item Price Is Same As Item Added In Product Page
                System.out.println("Checking Price......");
                if(getProductPriceByProductNumber(Integer.parseInt(incrementShoppingCartList.toString()))
                        .equals(productsPageList.get(Integer.parseInt(incrementProductsPageList.toString())).get(1))){
                    //Validate Item Button Status Is Same As Item Added In Product Page
                    System.out.println("Checking Button Status......");
                    if(getItemButtonStatusByItemNumber(Integer.parseInt(incrementShoppingCartList.toString()))
                            .equals(productsPageList.get(Integer.parseInt(incrementProductsPageList.toString())).getLast())){
                        System.out.println("Product Is On List.\n");
                        result.set(true);
                    }else {
                        throw new IllegalArgumentException(String.format("%s Item Added In Products Page 'Button Status' Is Not Same As Item In Shopping Cart Page List."
                                ,shoppingCartHeaderMessage));
                    }
                }else{
                    throw new IllegalArgumentException(String.format("%s Item Added In Products Page 'Price' Is Not Same As Item In Shopping Cart Page List."
                            ,shoppingCartHeaderMessage));
                }
            }else {
                throw new NoSuchElementException(String.format("%s Item Added In Products Page Is Not In Shopping Cart Page List."
                        ,shoppingCartHeaderMessage));
            }
            //Increment Product Page List So It Search Inside List's Arrays
            incrementProductsPageList.getAndIncrement();
        });
        if(result.get()){
            printToConsoleWithHeader(shoppingCartHeaderMessage,"Items Added In Product Page Are Same As Items Listed In Shopping Cart Page List.");
        }
    }
}
