package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutYourInformationPage extends ShoppingCartPage{

    //Checkout Your Information Page Locators
    By checkoutYourInformationPageTitleLocator = By.xpath("//div[@class = 'header_secondary_container']/span[contains(text(),'Your Information')]");
    By checkoutYourInformationPageInfoContainerLocator = By.xpath("//div[@id = 'checkout_info_container']");
    String checkoutYourInformationPageInputsLocator = "//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]";
    By checkoutYourInformationPageFirstNameInputLocator = By.xpath("//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]/input[contains(@name, 'firstName')]");
    By checkoutYourInformationPageLastNameInputLocator = By.xpath("//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]/input[contains(@name, 'lastName')]");
    By checkoutYourInformationPagePostalCodeInputLocator = By.xpath("//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]/input[contains(@name, 'postalCode')]");
    By checkoutYourInformationPageCancelButtonLocator = By.xpath("//div[@class = 'checkout_buttons']/button[@id = 'cancel']");
    By checkoutYourInformationPageContinueButtonLocator = By.xpath("//div[@class = 'checkout_buttons']/input[@id = 'continue']");

    //Your Information Page header message
    public final String checkoutYourInformationHeaderMessage = "|From Your Information Page:";

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    public boolean validateCheckOutYourInformationPage(){
        //Validate If Your Information Page Title Is Visible And Customer Information Container Is Present
        if(validateElementIsVisible_Time(checkoutYourInformationPageTitleLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(checkoutYourInformationPageInfoContainerLocator,time_out_limit_seconds)){
            printToConsoleWithHeader(checkoutYourInformationHeaderMessage,"We Are On Your Information Page.");
            return true;
        }else {
            throw new IllegalArgumentException(String.format("%s Your Information Page Title And Customer Information Container Are Not Present."
                    ,checkoutYourInformationHeaderMessage));
        }
    }
}
