package com.project.pom;

import com.google.common.util.concurrent.AtomicDouble;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CheckoutOverviewPage extends CheckoutYourInformationPage{

    //Checkout Overview Page locators
    By checkoutOverviewPageShoppingCartTotalItemsLocator = super.shoppingCartLocator;
    By checkoutOverviewPageTitleLocator = By.xpath("//div[@class = 'header_secondary_container']/span[contains(text(),'Overview')]");
    By checkoutOverviewPageCartListContainerLocator = By.xpath("//div[@class = 'cart_list']");
    String checkoutOverviewPageCartItemsLocator = "(//div[@class = 'cart_item'])";
    By checkoutOverviewPageSummaryInfoContainerLocator = By.xpath("//div[@class = 'summary_info']");
    By checkoutOverviewPageSummarySubtotalLabelLocator = By.xpath("//div[@class = 'summary_subtotal_label']");
    By checkoutOverviewPageSummaryTaxLabelLocator = By.xpath("//div[@class = 'summary_tax_label']");
    By checkoutOverviewPageSummaryTotalLabelLocator = By.xpath("//div[@class = 'summary_total_label']");
    By checkoutOverviewPageCartFooterLocator = By.xpath("//div[@class = 'cart_footer']");
    By checkoutOverviewPageCancelButtonLocator = By.xpath("//div[@class = 'cart_footer']/button[@id = 'cancel']");
    By checkoutOverviewPageFinishButtonLocator = By.xpath("//div[@class = 'cart_footer']/button[@id = 'finish']");

    //Checkout Overview Page header message
    public final String checkoutOverviewPageHeaderMessage = "|From Checkout - Overview Page:";

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void validateCheckoutOverviewPage(){
        //Validate If Checkout Overview Page Title, Cart List Container, Summary Info Container And Cart Footer Container Elements Are Displayed
        if(validateElementIsVisible_Time(checkoutOverviewPageTitleLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(checkoutOverviewPageCartListContainerLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(checkoutOverviewPageSummaryInfoContainerLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(checkoutOverviewPageCartFooterLocator,time_out_limit_seconds)){
            printToConsoleWithHeader(checkoutOverviewPageHeaderMessage,"Checkout - Overview Page Elements, Are Present.");
        }else {
            throw new NoSuchElementException(String.format("%s Checkout - Overview Page Elements, Are Not Present."
                    ,checkoutYourInformationHeaderMessage));
        }
    }

    public List<WebElement> getCheckoutOverviewPageCartItemsList(){
        //Get ALL Items Wrapper
        return getElements(By.xpath(checkoutOverviewPageCartItemsLocator));
    }

    public String getCheckoutOverviewPageCartItemNameByItemNumber(int itemNumber){
        //Get Item Name If Item Is Located In DOM
        return getText(By.xpath(String.format("%s[%s]/div[@class = 'cart_item_label']//descendant::div[@class = 'inventory_item_name']"
                ,checkoutOverviewPageCartItemsLocator,itemNumber)));
    }

    public String getCheckoutOverviewPageCartItemPriceByItemNumber(int itemNumber){
        //Get Item Price If Item Is Located In DOM
        return getText(By.xpath(String.format("%s[%s]/div[@class = 'cart_item_label']//descendant::div[@class = 'inventory_item_price']"
                ,checkoutOverviewPageCartItemsLocator,itemNumber)));
    }

    public List<String> getCheckoutOverviewPageCartItemDetailsByItemNumber(int itemInListNumber){

        //List That Collects All Item Details
        List<String> checkoutOverviewPageCartItemListDetails = new ArrayList<>();

            //Add Item Number
            checkoutOverviewPageCartItemListDetails.add(String.format("%s",itemInListNumber));
            //Add Item Name
            checkoutOverviewPageCartItemListDetails
                    .add(getCheckoutOverviewPageCartItemNameByItemNumber(itemInListNumber));
            //Add Item Price
            checkoutOverviewPageCartItemListDetails
                    .add(getCheckoutOverviewPageCartItemPriceByItemNumber(itemInListNumber));

        //Return List With Item Details
        return checkoutOverviewPageCartItemListDetails;
    }

    public List<List> getCheckoutOverviewPageCartItemsDetailsList(){

        //List That Collects Details By Each Item On The Checkout Overview Page Cart Items List In DOM
        List<List> checkoutOverviewPageCartItemsListDetails = new ArrayList<>();

        //Iterator For Each Item
        AtomicInteger checkoutOverviewPageCartItemsListIterator = new AtomicInteger(0);

        //Iterate And Get Details By Each Item Listed On The Checkout Overview Page Cart Items List In DOM
        getCheckoutOverviewPageCartItemsList().forEach(item ->{
            //Increment Item Number In List
            checkoutOverviewPageCartItemsListIterator.getAndIncrement();
            //Add Item Details By Item Number In List
            checkoutOverviewPageCartItemsListDetails
                    .add(getCheckoutOverviewPageCartItemDetailsByItemNumber(Integer
                            .parseInt(checkoutOverviewPageCartItemsListIterator.toString())));
        });

        //Return List With All Items Details Collected On The Checkout Overview Page Cart Items List In DOM
        return checkoutOverviewPageCartItemsListDetails;
    }

    public Double getCheckoutOverviewPageCartItemsPriceSubTotal(){
        //Declare Variable That Will Return Items Price Sub Total
        AtomicDouble priceTotal = new AtomicDouble(0);
        //Iterate The Items To Get The Price And Add It To Current Sub Total Value
        getCheckoutOverviewPageCartItemsDetailsList().forEach(item -> {
            //Split To Get Actual Item Price Value
            String[] price = item.getLast().toString().split("[$]");
            priceTotal.getAndSet(priceTotal.get() + Double.parseDouble(price[1]));
        });
        //Return Items Price Sub Total
        return Double.parseDouble(priceTotal.toString());
    }

    public Double getCheckoutOverviewPageCurrentTaxValue(){
        //Get Tax Label Value And Split To Get Current Tax Value
        String[] currentTaxValue = getText(checkoutOverviewPageSummaryTaxLabelLocator).split("[$]");
        //Return Current Tax Value
        return Double.parseDouble(currentTaxValue[1]);
    }

    public Double getCheckoutOverviewPageCartItemsPriceSummaryTotal(){
        //Create An Addition Between Items Price Sub Total And Current Tax Value To Get Price Summary Total
        //Return Price Summary Total
        return getCheckoutOverviewPageCartItemsPriceSubTotal() + getCheckoutOverviewPageCurrentTaxValue();
    }
}
