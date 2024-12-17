package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ProductDetailsPage extends Base{

    //Product Details Page Locators
    By productDetailsContainerLocator = By.xpath("//div[@id = 'inventory_item_container']");
    By productDetailsNameLocator = By.xpath("//div[@data-test = 'inventory_details_name']");
    By productDetailsDescriptionLocator = By.xpath("//div[@data-test = 'inventory-item-desc']");
    By productDetailsPriceLocator = By.xpath("//div[@data-test = 'inventory-item-price']");
    By productDetailsButtonLocator = By.xpath("//div[@class = 'inventory_details_container']//descendant::button");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateIfProductSelectedDetails(){
        return elementIsVisible(productDetailsContainerLocator);
    }


}
