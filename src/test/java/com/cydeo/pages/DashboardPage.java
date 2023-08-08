package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    public DashboardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "borrowed_books")
    public WebElement borrowedBooks;

    @FindBy(xpath = "//a[@href='#books']")
    public WebElement booksModule;
    @FindBy(xpath = "//a[@href='#users']")
    public WebElement usersModule;

    @FindBy(xpath = "//a[@href='#dashboard']")
    public WebElement dashboardModule;

    public void goToModule(String moduleName) {
        moduleName = moduleName.toLowerCase();
        if(moduleName.equals("books")){
            booksModule.click();
        }else if(moduleName.equals("users")){
            usersModule.click();
        }else if(moduleName.equals("dashboard")){
            dashboardModule.click();
        }else {
            System.out.println("No such MODULE on this page " + moduleName);
        }
    }


}
