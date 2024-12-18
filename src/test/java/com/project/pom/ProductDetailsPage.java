package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductDetailsPage extends Base{

    //Product Details Page Locators
    By productDetailsContainerLocator = By.xpath("//div[@id = 'inventory_item_container']");
    By productDetailsNameLocator = By.xpath("//div[contains(@class,'inventory_details_name')]");
    By productDetailsDescriptionLocator = By.xpath("//div[@data-test = 'inventory-item-desc']");
    By productDetailsPriceLocator = By.xpath("//div[@data-test = 'inventory-item-price']");
    By productDetailsButtonLocator = By.xpath("//div[@class = 'inventory_details_container']//descendant::button");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateProductSelectedDetails(List<String> productDetails) {
        //Validate if product details content is displayed
        if (elementIsVisible(productDetailsContainerLocator)) {
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

}
