package com.cydeo.pages;

import com.cydeo.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BooksModulePage {
    public BooksModulePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "book_categories")
    public WebElement categoriesDropdownSelect;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement searchInput;

    @FindBy(xpath = "//a[@href='javascript:void(0)']")
    public WebElement editFirstBookBtn;

    @FindBy(name = "name")
    public WebElement bookName;
    @FindBy(name = "isbn")
    public WebElement bookIsbn;
    @FindBy(name = "year")
    public WebElement bookYear;
    @FindBy(xpath = "//input[@name = 'author']")
    public WebElement bookAuthor;
    @FindBy(id = "description")
    public WebElement bookDescription;
    @FindBy(id = "book_group_id")
    public WebElement addEditCategorySelectDropdown;

    @FindBy(xpath = "//a[@href= 'tpl/add-book.html']")
    public WebElement addBookBtn;

    @FindBy(xpath = "//button[.= 'Save changes']")
    public WebElement saveChangesBtn;


    @FindBy(xpath = "//div[@class = 'toast-message']")
    public WebElement popUpBook;

    @FindBy(xpath = "//a[@href='javascript:void(0)']")
    public WebElement borrowBookBtn;

    @FindBy(xpath = "//a[@href='#borrowing-books']")
    public WebElement borrowingBooks;

    @FindBy(xpath = "//td")
    public List<WebElement> tableBorrowedBooks;

    @FindBy(xpath = "//td[.='NOT RETURNED ']/..//a")
    public WebElement firstNotReturnedBookBtn;


}
