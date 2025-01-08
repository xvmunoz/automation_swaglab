package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class MenuPage extends SingInPage{

    //Menu Page Locators
    By menuPageBurgerMenuButtonLocator = By.xpath("//div[@class = 'primary_header']/div[@id = 'menu_button_container']//descendant::div[@class = 'bm-burger-button']/button[contains(@id,'burger-menu')]");
    By menuPageBurgerMenuWrapLocator = By.xpath("//div[@class = 'bm-menu-wrap']");
    By menuPageBurgerMenuCrossButtonLocator = By.xpath("//div[@class = 'primary_header']/div[@id = 'menu_button_container']//descendant::div[@class = 'bm-cross-button']/button[contains(@id,'burger-cross')]");
    String menuPageBurgerMenuItemListLocator = "//div[@class = 'bm-menu-wrap']/div[@class = 'bm-menu']/nav[@class = 'bm-item-list']";
    By menuPageBurgerMenuAllItemsLinkLocator = By.xpath("//div[@class = 'bm-menu-wrap']/div[@class = 'bm-menu']/nav[@class = 'bm-item-list']/a[@id = 'inventory_sidebar_link' and contains(text(),'All Items')]");
    By menuPageBurgerMenuAboutLinkLocator = By.xpath("//div[@class = 'bm-menu-wrap']/div[@class = 'bm-menu']/nav[@class = 'bm-item-list']/a[@id = 'about_sidebar_link' and contains(text(),'About')]");
    By menuPageBurgerMenuLogoutLinkLocator = By.xpath("//div[@class = 'bm-menu-wrap']/div[@class = 'bm-menu']/nav[@class = 'bm-item-list']/a[@id = 'logout_sidebar_link' and contains(text(),'Logout')]");
    By menuPageBurgerMenuResetLinkLocator = By.xpath("//div[@class = 'bm-menu-wrap']/div[@class = 'bm-menu']/nav[@class = 'bm-item-list']/a[@id = 'reset_sidebar_link' and contains(text(),'Reset')]");

    //Menu Page header message
    public final String menuPageHeaderMessage = "|From Menu Page:";

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void validateMenuPage(){
        //Validate Menu Page Options Are Displayed
        if(validateElementIsVisible_Time(menuPageBurgerMenuWrapLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(menuPageBurgerMenuCrossButtonLocator,time_out_limit_milliseconds)
                && validateElementIsVisible_Time(By.xpath(menuPageBurgerMenuItemListLocator),time_out_limit_seconds)
                && validateElementIsVisible_Time(menuPageBurgerMenuAllItemsLinkLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(menuPageBurgerMenuAboutLinkLocator,time_out_limit_seconds)
                && validateElementIsVisible_Time(menuPageBurgerMenuLogoutLinkLocator, time_out_limit_seconds)
                && validateElementIsVisible_Time(menuPageBurgerMenuResetLinkLocator,time_out_limit_seconds)){
            printToConsoleWithHeader(menuPageHeaderMessage,"Menu Page Elements, Are Present.");
        }else{
            throw new NoSuchElementException(String.format("%s Menu Page Elements Are Not Present."
                    ,menuPageHeaderMessage));
        }
    }

    public void menuPageOpenMenu(){
        //Click On Burger Menu Page Button
        click(menuPageBurgerMenuButtonLocator);
    }

    public void menuPageLogout(){
        //Validate If Menu Is Open and Logout Link Locator Is Displayed
        if(validateElementIsVisible_Time(menuPageBurgerMenuLogoutLinkLocator,time_out_limit_seconds)){
            click(menuPageBurgerMenuLogoutLinkLocator);
            printToConsoleWithHeader(menuPageHeaderMessage,"Logout action performed.");
        }else {
            throw new NoSuchElementException(String.format("%s Menu Page Logout Link Locator (%s) Element Is Not Present."
                    ,menuPageHeaderMessage,menuPageBurgerMenuLogoutLinkLocator));
        }
    }
}
