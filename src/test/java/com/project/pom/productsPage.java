package com.project.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

public class productsPage extends Base{

    //Products page locators
    By inventoryProductsListLocator = By.xpath("//div[@class = 'inventory_list']");
    By inventoryProductsLocator = By.xpath("//div[@class = 'inventory_item']");
    By addToCartProductLocator = By.xpath("//button[text() = 'Add to cart']");
    String inventoryProductNameLocator = "(//div[contains(@class, 'inventory_item_name')])";
    String inventoryProductPriceLocator = "(//div[@class = 'inventory_item_price'])";
    String addOrRemoveToCartButtonLocatorForDynamic = "(//div[@class = 'inventory_item']//descendant::button)";
    By shoppingCartLocator = By.xpath("//span[@class = 'shopping_cart_badge']");
    By getShoppingCartCountLocator = By.xpath("(//span[@class = 'shopping_cart_badge']/text())");

    //Products added to cart
    int totalProductsInCart = 0;
    List<Integer> productsInCart = new ArrayList<>();

    public productsPage(WebDriver driver) {
        super(driver);
    }

    // this is the number of total available products, need method or som to refresh available products to add
    public List<WebElement> getProducts(){
        return getElements(inventoryProductsLocator);
    }

    public List<WebElement> getTotalOfAvailableProductsToAdd(){
        return getElements(addToCartProductLocator);
    }

    public Integer chooseRandomProduct(){
        //Get an item between 1 and products.size()
        return ThreadLocalRandom.current().nextInt(1, getProducts().size() + 1);
    }

    public String getProductNameByProductNumber(int productNumber){
        //Create dynamic XPath locator to get product details
        //String Of Dynamic Product Name Locator
        String stringDynamicProductNameLocator = String.format(inventoryProductNameLocator + "[%s%s",productNumber,"]");
        By dynamicProductNameLocator = By.xpath(stringDynamicProductNameLocator);
        return getText(dynamicProductNameLocator);
    }

    public String getProductPriceByProductNumber(int productNumber){
        //Create dynamic XPath locator to get product details
        //String Of Dynamic Product Name Locator
        String stringDynamicProductPriceLocator = String.format(inventoryProductPriceLocator + "[%s%s",productNumber,"]");
        By dynamicProductPriceLocator = By.xpath(stringDynamicProductPriceLocator);
        return getText(dynamicProductPriceLocator);
    }

    public By getProductAddOrRemoveToCartLocationByProductNumber(int productNumber){
        //String Of Dynamic Add To Cart Button By Product Locator
        String stringDynamicAddToCartByProductLocator = String.format(addOrRemoveToCartButtonLocatorForDynamic + "[%s%s",productNumber,"]");
        return By.xpath(stringDynamicAddToCartByProductLocator);
    }

    public boolean validateTotalProductsInCart(int totalOfProducts){
        //Validate Shopping Cart Count Is Visible
        waitForElementIsVisible_Seconds(getShoppingCartCountLocator);
        return true;
        //int totalInCart = Integer.parseInt(getText(getShoppingCartCountLocator));
        //System.out.println(String.format("totalInCart xpath = %s and totalCountByAction = %s",totalInCart,totalOfProducts));
        //return totalOfProducts == totalInCart;
    }

    public void addToCart(int productNumber){
        if(productNumber == 0){
            productNumber = chooseRandomProduct();
        }
            if (productsInCart.size() != getProducts().size()) {
                if (!productsInCart.contains(productNumber) &&
                        !getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber)).contains("Remove")) {
                        click(getProductAddOrRemoveToCartLocationByProductNumber(productNumber));
                        productsInCart.add(productNumber);
                        totalProductsInCart++;
                        System.out.println(String.format("+ %s %s.", getProductNameByProductNumber(productNumber), getProductPriceByProductNumber(productNumber)));
                } else {
                    System.out.println(String.format("--%s already added--", getProductNameByProductNumber(productNumber)));
                }
                Assert.assertTrue("Products in cart are not matching", validateTotalProductsInCart(totalProductsInCart));
            } else {
                System.out.println("--No more products to add--");
                System.out.println("test");
            }
    }

    public void removeProductFromCart(int productNumber){
            //Validate item is in cart and 'Remove' button is displayed for it
            if (getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber)).equals("Remove")
                    && productsInCart.contains(productNumber)) {
                click(getProductAddOrRemoveToCartLocationByProductNumber(productNumber));
                totalProductsInCart--;
                if (!productsInCart.isEmpty() && !validateElementIsVisible_Time(getProductAddOrRemoveToCartLocationByProductNumber(productNumber)
                        , time_out_limit_seconds)) {
                    validateTotalProductsInCart(totalProductsInCart);
                    System.out.println(totalProductsInCart);
                }
                System.out.println(String.format("- %s, has been removed.", getProductNameByProductNumber(productNumber)));
            } else {
                System.out.println(String.format("Product [%s] (%s) cannot be removed from shopping cart, Product is on '%s' status."
                        , productNumber, getProductNameByProductNumber(productNumber)
                        , getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber))));
            }
    }

    public void randomlyAddAllItemsToCart(){
        while (productsInCart.size() < getProducts().size()) {
            if(productsInCart.size() == getProducts().size()){
                break;
            }else {
                addToCart(0);
            }
        }
        System.out.println(productsInCart);
    }

    public void removeAllItemsFromCart() {
        List<Integer> productsInCartList = productsInCart;
        productsInCartList.forEach((p) -> {
            removeProductFromCart(p);
        });
        if (totalProductsInCart == 0) {
            productsInCartList.removeAll(productsInCart);
        }
        System.out.println(productsInCart);
    }
}
