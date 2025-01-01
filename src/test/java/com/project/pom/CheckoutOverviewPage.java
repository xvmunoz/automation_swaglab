package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
            throw new IllegalArgumentException(String.format("%s Checkout - Overview Page Elements, Are Not Present."
                    ,checkoutYourInformationHeaderMessage));
        }
    }
}
