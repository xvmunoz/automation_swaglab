package com.project.pom;

import com.google.common.util.concurrent.AtomicDouble;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
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

    public void validateItemsOnShoppingCartAreDisplayedOnCheckoutOverviewPageCartItemsList(List<List> shoppingCartItemList){
        //Create Flag That Will Change To True If Checkout Overview Page Cart Item Is On Shopping Cart Items List
        AtomicBoolean checkoutOverviewPageCartItemConfirmation = new AtomicBoolean(false);

        //Iterate Inside Checkout Overview Page Cart Items List And Validate Items Displayed Were On Shopping Cart Items List
        getCheckoutOverviewPageCartItemsDetailsList().forEach(checkoutOverviewPageCartItem -> {
            checkoutOverviewPageCartItemConfirmation.getAndSet(false);
            //Iterate Inside Shopping Cart Items List
            shoppingCartItemList.forEach(shoppingCartItem -> {
                //Validate Checkout Overview Page Cart Item Is On Shopping Cart Items List
                if(checkoutOverviewPageCartItem.get(1).equals(shoppingCartItem.get(1))){
                    //Turn checkoutOverviewPageCartItemConfirmation Flag To True Since Item Exist On Shopping Cart Items List
                    checkoutOverviewPageCartItemConfirmation.getAndSet(true);
                    //Validate Item Name
                    printToConsoleWithHeader(productsHeaderMessage,"Checking Item Name...");
                    if(!checkoutOverviewPageCartItem.get(1).equals(shoppingCartItem.get(1))) throw new IllegalArgumentException(String
                            .format("%s Checkout Overview Page Cart Item ('%s') Name Is Not Same As Shopping Cart Item ('%s') Name."
                            ,checkoutOverviewPageHeaderMessage,checkoutOverviewPageCartItem.get(1),shoppingCartItem.get(1)));
                    //Validate Item Price
                    printToConsoleWithHeader(productsHeaderMessage,"Checking Item Price...");
                    if(!checkoutOverviewPageCartItem.getLast().equals(shoppingCartItem.getLast())) throw new IllegalArgumentException(String
                            .format("%s Checkout Overview Page Cart Item ('%s') Price ['%s'] Is Not Same As Shopping Cart Item ('%s') Price ['%s']."
                            ,checkoutOverviewPageHeaderMessage,checkoutOverviewPageCartItem.get(1),checkoutOverviewPageCartItem.getLast()
                                    ,shoppingCartItem.get(1),shoppingCartItem.getLast()));
                }
            });
            //Validate If checkoutOverviewPageCartItemConfirmation Flag Is False Throw An Error Since Checkout Overview Page Cart Item Is Not On Shopping Cart Items List
            if(!checkoutOverviewPageCartItemConfirmation.get()) throw new IllegalArgumentException(String.format("%s Checkout Overview Page Cart Item ('%s') Is Not On Shopping Cart Items List."
                    ,checkoutOverviewPageHeaderMessage,checkoutOverviewPageCartItem.get(1)));
            //If Items Match Print Success Info Validation Message
            printToConsoleWithHeader(productsHeaderMessage,"Item Confirmed On Shopping Cart Is On Checkout Overview Page Cart Items List.");
        });

    }

    public void validateCheckoutOverviewPageSummaryValuesMatch(){
        //Split Checkout Overview Page Summary Subtotal Label Value To Get Price Value Only
        String[] checkoutOverviewPageSummarySubtotalLabelValue = getText(checkoutOverviewPageSummarySubtotalLabelLocator)
                .split("[$]");
        //Split Checkout Overview Page Summary Tax Label Value To Get Price Value Only
        String[] checkoutOverviewPageSummaryTaxLabelValue = getText(checkoutOverviewPageSummaryTaxLabelLocator)
                .split("[$]");
        //Split Checkout Overview Page Summary Total Label Value To Get Price Value Only
        String[] checkoutOverviewPageSummaryTotalLabelValue = getText(checkoutOverviewPageSummaryTotalLabelLocator)
                .split("[$]");

        //Validate Checkout Overview Page Summary Values Are Expected
        //Check Subtotal Value (Parse To Double To Compare Expected Value)
        Double finalSubtotalValue = Double.parseDouble(checkoutOverviewPageSummarySubtotalLabelValue[1]);
        if(!finalSubtotalValue.equals(getCheckoutOverviewPageCartItemsPriceSubTotal()))
            throw new IllegalArgumentException(String.format("%s Checkout Overview Page Summary Subtotal '(Current: %s)' Value Is Not Expected '(Expected: %s)'."
                    ,checkoutOverviewPageHeaderMessage
                    ,finalSubtotalValue
                    ,getCheckoutOverviewPageCartItemsPriceSubTotal()));
        //Check Tax Value (Parse To Double To Compare Expected Value)
        Double finalTaxValue = Double.parseDouble(checkoutOverviewPageSummaryTaxLabelValue[1]);
        if(!finalTaxValue.equals(getCheckoutOverviewPageCurrentTaxValue()))
            throw new IllegalArgumentException(String.format("%s Checkout Overview Page Tax '(Current: %s)' Value Is Not Expected '(Expected: %s)'."
                    ,checkoutOverviewPageHeaderMessage
                    ,finalTaxValue
                    ,getCheckoutOverviewPageCurrentTaxValue()));
        //Check Total Value (Parse To Double To Compare Expected Value)
        Double finalTotalValue = Double.parseDouble(checkoutOverviewPageSummaryTotalLabelValue[1]);
        if(!finalTotalValue.equals(getCheckoutOverviewPageCartItemsPriceSummaryTotal()))
            throw new IllegalArgumentException(String.format("%s Checkout Overview Page Total '(Current: %s)' Value Is Not Expected '(Expected: %s)'."
                    ,checkoutOverviewPageHeaderMessage
                    ,finalTotalValue
                    ,getCheckoutOverviewPageCartItemsPriceSummaryTotal()));

        //Print Success Message
        printToConsoleWithHeader(checkoutOverviewPageHeaderMessage,"Summary Values Info, Match.");
    }
}
