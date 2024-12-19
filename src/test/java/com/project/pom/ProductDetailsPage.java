package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductDetailsPage extends ProductsPage{

    //Product Details Page Locators
    By productDetailsContainerLocator = By.xpath("//div[@id = 'inventory_item_container']");
    By productDetailsNameLocator = By.xpath("//div[contains(@class,'inventory_details_name')]");
    By productDetailsDescriptionLocator = By.xpath("//div[@data-test = 'inventory-item-desc']");
    By productDetailsPriceLocator = By.xpath("//div[@data-test = 'inventory-item-price']");
    By productDetailsButtonLocator = By.xpath("//div[@class = 'inventory_details_container']//descendant::button");
    By productDetailsShoppingCartLocator = By.xpath("//div[contains(@id,'shopping_cart')]");
    By productDetailsShoppingCartBadgeLocator = By.xpath("//div[contains(@id,'shopping_cart')]//descendant::span[contains(@class,'shopping_cart_badge')]");

    //Count of current total of items in cart
    public int totalItemsInCart = 0;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateProductSelectedDetails(List<String> productDetails) {
        //Validate if product details content is displayed
        if (elementIsVisible(productDetailsContainerLocator)) {
            //Get current total of items in cart
            getTotalItemsInCart();
            //Validate product name,price and button status are same as the one selected. (if index > 0 then string exists inside product details list values)
            if (getText(productDetailsNameLocator).equals(productDetails.getFirst())
                    && productDetails.indexOf(getText(productDetailsPriceLocator)) > 0
                    && getText(productDetailsButtonLocator).equals(productDetails.getLast())) {
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public int getTotalItemsInCart(){
        //Validate if shopping cart badge is present so shopping cart contains products
        if(validateElementIsVisible_Time(productDetailsShoppingCartBadgeLocator,time_out_limit_seconds)){
            return totalItemsInCart = Integer.parseInt(getText(productDetailsShoppingCartBadgeLocator));
        }else {
            return 0;
        }
    }

    public void addToCartCurrentProduct(){
        if(getText(productDetailsButtonLocator).contains("Add to cart")){
            click(productDetailsButtonLocator);
            if(getText(productDetailsButtonLocator).equals("Remove")){
                totalItemsInCart ++;
                System.out.println("+ Product has been added to cart.");
            }else {
                System.out.println(String.format("Unknown status from ->%s, current status as: '%s'.",productDetailsButtonLocator,
                        getText(productDetailsButtonLocator)));
            }
        }else if (getText(productDetailsButtonLocator).equals("Remove")){
            System.out.println(String.format("Product cannot be added to cart, product status as: '%s'.",getText(productDetailsButtonLocator)));
        }
        System.out.println(totalItemsInCart);
    }

    public void removeProductFromCurrentCart(){
        if(getText(productDetailsButtonLocator).contains("Remove")){
            click(productDetailsButtonLocator);
            if(getText(productDetailsButtonLocator).equals("Add to cart")){
                //Validate if total items in cart has value
                if(totalItemsInCart >= 1){
                    totalItemsInCart --;
                }
                System.out.println("- Product has been removed from cart.");
            }else {
                System.out.println(String.format("Unknown status from ->%s, current status as: '%s'.",productDetailsButtonLocator,
                        getText(productDetailsButtonLocator)));
            }
        }else if (getText(productDetailsButtonLocator).equals("Add to cart")){
            System.out.println(String.format("Product cannot be removed from cart, product status as: '%s'.",getText(productDetailsButtonLocator)));
        }
        System.out.println(totalItemsInCart);
    }

}
