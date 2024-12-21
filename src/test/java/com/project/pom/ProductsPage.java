package com.project.pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ProductsPage extends Base{

    //Products Page Locators
    By inventoryProductsListLocator = By.xpath("//div[@class = 'inventory_list']");
    By inventoryProductsLocator = By.xpath("//div[@class = 'inventory_item']");
    By addToCartProductLocator = By.xpath("//button[text() = 'Add to cart']");
    String inventoryProductNameLocator = "(//div[contains(@class, 'inventory_item_name')])";
    String inventoryProductPriceLocator = "(//div[@class = 'inventory_item_price'])";
    String addOrRemoveToCartButtonLocatorForDynamic = "(//div[@class = 'inventory_item']//descendant::button)";
    By shoppingCartLocator = By.xpath("//span[@class = 'shopping_cart_badge']");
    By getShoppingCartCountLocator = By.xpath("//div[contains(@id,'shopping_cart')]//descendant::span[contains(@class,'shopping_cart_badge')]");

    //Products added to cart
    public int totalProductsInCart = 0;
    public List<Integer> productsInCart = new ArrayList<>();
    public List<String> productDetails = new ArrayList<>();

    //Products header message
    public final String productsHeaderMessage = "|From products page:";

    public ProductsPage(WebDriver driver) {
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

    public By getProductNameLocatorByProductNumber(int productNumber){
        //Create dynamic XPath locator to get product details
        //String Of Dynamic Product Name Locator
        String stringDynamicProductNameLocator = String.format(inventoryProductNameLocator + "[%s%s",productNumber,"]");
        return By.xpath(stringDynamicProductNameLocator);
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

    public int getTotalItemsInCart(){
        //Validate if shopping cart badge is present so shopping cart contains products
        if(validateElementIsVisible_Time(getShoppingCartCountLocator,time_out_limit_seconds)){
            return totalProductsInCart = Integer.parseInt(getText(getShoppingCartCountLocator));
        }else {
            return 0;
        }
    }

    public boolean validateTotalProductsInCart(int totalOfProducts){
        //Validate Shopping Cart Count Is Visible
        waitForElementIsVisible_Seconds(getShoppingCartCountLocator);
        return true;
        //int totalInCart = Integer.parseInt(getText(getShoppingCartCountLocator));
        //System.out.println(String.format("totalInCart xpath = %s and totalCountByAction = %s",totalInCart,totalOfProducts));
        //return totalOfProducts == totalInCart;
    }

    public int addToCart(int productNumber){
        //If product is equals to 0 a random product is going to be chosen from products length
        if(productNumber == 0){
            productNumber = chooseRandomProduct();
        }
        //Validate if there's available products to add
            if (productsInCart.size() != getProducts().size()) {
                if (!productsInCart.contains(productNumber) &&
                        !getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber)).contains("Remove")) {
                        click(getProductAddOrRemoveToCartLocationByProductNumber(productNumber));
                        productsInCart.add(productNumber);
                        totalProductsInCart++;
                        System.out.println(String.format("+ %s %s.", getProductNameByProductNumber(productNumber), getProductPriceByProductNumber(productNumber)));
                        Assert.assertTrue("Products in cart are not matching", validateTotalProductsInCart(totalProductsInCart));
                        return productNumber;
                } else {
                    System.out.println(String.format("--%s already added--", getProductNameByProductNumber(productNumber)));
                    return 0;
                }
            } else {
                System.out.println("--No more products to add--");
                return 0;
            }
    }

    public int removeProductFromCart(int productNumber){
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
                return productNumber;
            } else {
                System.out.println(String.format("Product [%s] (%s) cannot be removed from shopping cart, Product is on '%s' status."
                        , productNumber, getProductNameByProductNumber(productNumber)
                        , getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber))));
                return 0;
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

    public List<String> addProductDetailsByProductNumber(int productNumber){
        List<String> currentProductDetails = productDetails;
        if(!productDetails.isEmpty()){
            productDetails.removeAll(currentProductDetails);
        }
        productDetails.add(getProductNameByProductNumber(productNumber));
        productDetails.add(getProductPriceByProductNumber(productNumber));
        productDetails.add(getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber)));
        return productDetails;
    }

    public List<String> selectProductToSeeDetails(int productNumber){
        List<String> productDetails = addProductDetailsByProductNumber(productNumber);
        click(getProductNameLocatorByProductNumber(productNumber));
        System.out.println(String.format("%s Selected product: %s",productsHeaderMessage,productDetails));
        return productDetails;
    }

    public void validateProductStatusAddedAfterProductDetailsByProductNumber(int productNumber, List<String> productDetailsPageStatus, int productDetailsProductsInCart){
        //Check if product was added or removed
        if(productDetailsPageStatus.getFirst().equals("Added") || productDetailsPageStatus.getFirst().equals("Removed")){
            //Validate product "inventory item" button status and cart items in product page are same as product details page
            if(productDetailsPageStatus.getFirst().equals("Added") && getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber)).equals("Remove")
                    && validateSameItemsInCart(productDetailsProductsInCart)){
                System.out.println(String.format("%s Product added successfully",productsHeaderMessage));
            }else if (productDetailsPageStatus.getFirst().equals("Removed")){
                throw new IllegalArgumentException(String.format("Product was not added successfully, product #%s in '%s' status."
                        ,productNumber,getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber))));
            }else {
                throw new IllegalArgumentException(String.format("Unexpected status '%s', 'Added' or 'Removed' expected status.",productDetailsPageStatus.getFirst()));
            }
        }else {
            throw new IllegalArgumentException(String.format("Unexpected status '%s', 'Added' or 'Removed' expected status.",productDetailsPageStatus.getFirst()));
        }
    }

    public void validateProductStatusRemovedAfterProductDetailsByProductNumber(int productNumber, List<String> productDetailsPageStatus, int productDetailsProductsInCart){
        //Check if product was added or removed
        if(productDetailsPageStatus.getFirst().equals("Added") || productDetailsPageStatus.getFirst().equals("Removed")){
            //Validate product "inventory item" button status and cart items in product page are same as product details page
            if(productDetailsPageStatus.getFirst().equals("Removed") && getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber)).equals("Add to cart")
                    && validateSameItemsInCart(productDetailsProductsInCart)){
                System.out.println(String.format("%s Product removed successfully",productsHeaderMessage));
            }else if (productDetailsPageStatus.getFirst().equals("Added")){
                throw new IllegalArgumentException(String.format("Product was not removed successfully, product #%s in '%s' status."
                        ,productNumber,getText(getProductAddOrRemoveToCartLocationByProductNumber(productNumber))));
            }else {
                throw new IllegalArgumentException(String.format("Unexpected status '%s', 'Added' or 'Removed' expected status.",productDetailsPageStatus.getFirst()));
            }
        }else {
            throw new IllegalArgumentException(String.format("Unexpected status '%s', 'Added' or 'Removed' expected status.",productDetailsPageStatus.getFirst()));
        }
    }

    public boolean validateSameItemsInCart(int itemsFromCart){
        return getTotalItemsInCart() == itemsFromCart;
    }

    public void goToShoppingCart(int numberOfItemsInCart){
        //Validate if shopping cart element is visible
        if(validateElementIsVisible_Time(shoppingCartLocator,time_out_limit_seconds)){
            click(shoppingCartLocator);
        }else {
            throw new NoSuchElementException(String.format("Element -> '%s' is not visible."));
        }
    }
}
