package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class SingInPage extends Base {

    //SingIn Page Locators
    By singInPageLoginContainerLocator = By.xpath("//div[@class = 'login_container']");
    By signInPageLoginLogoLocator = By.xpath("//div[@class = 'login_logo']");
    By singInPageLoginWrapperLocator = By.xpath("//div[@class = 'login_wrapper']");
    By singInPageLoginWrapperInnerLocator = By.xpath("//div[@class = 'login_wrapper']/div[@class = 'login_wrapper-inner']");
    By loginCredentialsLocator = By.xpath("//div[@id='login_credentials']");
    By loginPasswordsLocator = By.xpath("//div[@class='login_password']");
    By usersToSignInLocator = By.xpath("//div[@id='login_credentials']/text()");
    By passwordForAllUsersLocator = By.xpath("(//div[@class='login_password']/text())");
    By usernameInputLocator = By.xpath("//input[@id = 'user-name' and @name = 'user-name'] | //input[@placeholder = 'Username']");
    By passwordInputLocator = By.xpath("//input[@id = 'password' and @name = 'password'] | //input[@placeholder = 'Password']");
    By loginButtonLocator = By.xpath("//input[@type = 'submit' and @id = 'login-button' and @name = 'login-button']");
    By mainPageLogoLocator = By.xpath("//div[@class = 'primary_header']/div[@class = 'header_label']/div[@class = 'app_logo']");

    //Random object to get random user and password from available user and password list
    Random rmd;

    //SignIn header message
    public final String signInHeaderMessage = "|From Sign In Page:";

    public SingInPage(WebDriver driver) {
        super(driver);
    }

    public void validateSignInPage(){
        //Validate Sign In Page Elements Are Displayed
        if(validateElementIsVisible_Time(singInPageLoginContainerLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(signInPageLoginLogoLocator,time_out_limit_milliseconds)
                && validateElementIsVisible_Time(singInPageLoginWrapperLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(singInPageLoginWrapperInnerLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(loginCredentialsLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(loginPasswordsLocator, time_out_limit_seconds)){
                printToConsoleWithHeader(signInHeaderMessage,"Sing In Page Elements, Are Present.");
        }else {
            throw new NoSuchElementException(String.format("%s Sign In Page Elements Are Not Present.",signInHeaderMessage));
        }
    }

    public List<WebElement> getAvailableUsers(){
        //Get users that are available to singIn
        return getElements(usersToSignInLocator);
    }

    public List<WebElement> getAvailablePasswords(){
        return getElements(passwordForAllUsersLocator);
    }

    public String getRandomAvailableUser(){
        rmd = new Random();
        int rmdUserIndexValue = rmd.nextInt(getAvailableUsers().size());
        return getAvailableUsers().get(rmdUserIndexValue).getText();
    }

    public String getRandomAvailablePassword(){
        rmd = new Random();
        int rmdPasswordIndexValue = rmd.nextInt(getAvailablePasswords().size());
        return getAvailablePasswords().get(rmdPasswordIndexValue).getText();
    }

    public String getStandardUser(){
        final String[] standarUserCredential = {""};
        getAvailableUsers().forEach((usr) ->{
            if(usr.getText().contains("standard")){
                standarUserCredential[0] = usr.getText();
            }
        });
        return standarUserCredential[0];
    }

    public void signIn(int userType){
        /*
        *User types:
        * 1 = standard_user
        */

        //Initialize user variables
        String validLoginUser = "";

        //Wait for credentials to be displayed
        waitForElementIsVisible_Seconds(loginCredentialsLocator);
        waitForElementIsVisible_Seconds(loginPasswordsLocator);

        if(userType > 0){
            validLoginUser = userType == 1 ? getStandardUser() : validLoginUser;
        }else{
            validLoginUser = getRandomAvailableUser();
        }

        //Get user that's not locked
        validLoginUser = validLoginUser.contains("locked") ? getRandomAvailableUser() : validLoginUser;
        printToConsoleWithHeader(signInHeaderMessage,String.format("Login with %s",validLoginUser));

        //Get random username and password to LogIn
        setText(usernameInputLocator,validLoginUser);
        setText(passwordInputLocator,getRandomAvailablePassword());
        click(loginButtonLocator);
        waitForElementIsVisible_Seconds(mainPageLogoLocator);
    }
}
