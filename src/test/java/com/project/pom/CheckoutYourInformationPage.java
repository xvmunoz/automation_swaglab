package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

public class CheckoutYourInformationPage extends ShoppingCartPage{

    //Checkout Your Information Page Locators
    By checkoutYourInformationPageTitleLocator = By.xpath("//div[@class = 'header_secondary_container']/span[contains(text(),'Your Information')]");
    By checkoutYourInformationPageInfoContainerLocator = By.xpath("//div[@id = 'checkout_info_container']");
    String checkoutYourInformationPageInputsLocator = "//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]";
    By checkoutYourInformationPageFirstNameInputLocator = By.xpath("//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]/input[contains(@name, 'firstName')]");
    By checkoutYourInformationPageLastNameInputLocator = By.xpath("//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]/input[contains(@name, 'lastName')]");
    By checkoutYourInformationPagePostalCodeInputLocator = By.xpath("//div[@class = 'checkout_info']//descendant::div[contains(@class,'form_group')]/input[contains(@name, 'postalCode')]");
    By checkoutYourInformationPageErrorContainerLocator = By.xpath("//div[contains(@class,'error-message-container')]");
    By checkoutYourInformationPageErrorMessageLocator = By.xpath("//div[contains(@class,'error-message-container')]//descendant::h3[contains(text(),'Error')]");
    By checkoutYourInformationPageErrorButtonLocator = By.xpath("//div[contains(@class,'error-message-container')]//descendant::button[contains(@class,'error')]");
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
            printToConsoleWithHeader(checkoutYourInformationHeaderMessage,"Your Information Page Elements Are Present.");
            return true;
        }else {
            throw new IllegalArgumentException(String.format("%s Your Information Page Title And Customer Information Container Are Not Present."
                    ,checkoutYourInformationHeaderMessage));
        }
    }

    public void setCustomerInformationDetails(String firstName, String lastName, String postalCode){
        //Set Customer Information
        //Set Customer First Name And Validate Info Is Set
        setText(checkoutYourInformationPageFirstNameInputLocator,firstName);
        if(getValueFromInput(checkoutYourInformationPageFirstNameInputLocator).isEmpty()){
            throw new IllegalArgumentException(String.format("%s Fist Name has not been set."
                    ,checkoutYourInformationHeaderMessage));
        }
        //Set Customer Last Name And Validate Info Is Set
        setText(checkoutYourInformationPageLastNameInputLocator,lastName);
        if(getValueFromInput(checkoutYourInformationPageLastNameInputLocator).isEmpty()){
            throw new IllegalArgumentException(String.format("%s Last Name has not been set."
                    ,checkoutYourInformationHeaderMessage));
        }
        //Set Customer Postal Code And Validate Info Is Set
        setText(checkoutYourInformationPagePostalCodeInputLocator,postalCode);
        if(getValueFromInput(checkoutYourInformationPagePostalCodeInputLocator).isEmpty()){
            throw new IllegalArgumentException(String.format("%s Postal Code has not been set."
                    ,checkoutYourInformationHeaderMessage));
        }
    }

    public boolean validateYourInformationErrorMessageIsShown(){
        //Validate That The 'error-message-container' With Text Error Message Pops Up
        return validateElementIsVisible_Time(checkoutYourInformationPageErrorContainerLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(checkoutYourInformationPageErrorMessageLocator,time_out_limit_seconds);
    }

    public boolean validateYourInformationCustomerInfoIsEmpty(){
        return getValueFromInput(checkoutYourInformationPageFirstNameInputLocator).isEmpty()
                || getValueFromInput(checkoutYourInformationPageLastNameInputLocator).isEmpty()
                || getValueFromInput(checkoutYourInformationPagePostalCodeInputLocator).isEmpty();
    }

    public boolean validateIfCustomerInfoIsNotSetShowErrorMessage(){
        if(validateYourInformationCustomerInfoIsEmpty()){
            click(checkoutYourInformationPageContinueButtonLocator);
            if(validateYourInformationErrorMessageIsShown()){
                return true;
            }else {
                throw new NoSuchElementException(String.format("%s Error Message Is Not Displayed."
                        ,checkoutYourInformationHeaderMessage));
            }
        }else {
            throw new IllegalArgumentException(String.format("Customer Information Is Not Empty."
                    ,checkoutYourInformationHeaderMessage));
        }
    }
}
