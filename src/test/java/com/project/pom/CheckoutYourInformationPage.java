package com.project.pom;

import com.github.javafaker.Faker;
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

    //Instance Of Faker Class To Generate Dynamic Customer Data
    public Faker faker = new Faker();

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

    public void setCustomerInformationDetails(String firstName, String lastName, int postalCode){

    }

    public boolean validateYourInformationErrorMessageIsShown(){
        //Validate That The 'error-message-container' With Text Error Message Pops Up
        return validateElementIsVisible_Time(checkoutYourInformationPageErrorContainerLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(checkoutYourInformationPageErrorMessageLocator,time_out_limit_seconds);
    }

    public boolean validateYourInformationCustomerInfoIsEmpty(){
        return getText(checkoutYourInformationPageFirstNameInputLocator).isEmpty()
                || getText(checkoutYourInformationPageLastNameInputLocator).isEmpty()
                || getText(checkoutYourInformationPagePostalCodeInputLocator).isEmpty();
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
