package com.cydeo.steps;

import com.cydeo.pages.BooksModulePage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class US04_stepDefs {

    BooksModulePage booksModulePage = new BooksModulePage();
    String bookName;

    @When("the user searches for {string} book")
    public void the_user_searches_for_clean_code_book(String bookName) {
        BrowserUtil.waitFor(1);
        booksModulePage.searchInput.sendKeys(bookName + Keys.ENTER);
        this.bookName = bookName;
    }

    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
        BrowserUtil.waitFor(1);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(booksModulePage.editFirstBookBtn));
        booksModulePage.editFirstBookBtn.click();
    }

    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
        DB_Util.runQuery("select * from books\n" +
                "where name = '"+bookName+"'\n" +
                "order by isbn desc\n" +
                "LIMIT 1");
        String expectedBookName = "";
        String expectedISBN = "";
        String expectedYear = "";
        String expectedAuthor = "";
        String expectedCategoryId = "";
        String expectedDescription = "";
        List<Map<String, String>> bookInformationList = DB_Util.getAllRowAsListOfMap();
        for (Map<String, String> eachMap : bookInformationList) {
            expectedBookName = eachMap.get("name");
            expectedISBN = eachMap.get("isbn");
            expectedYear = eachMap.get("year");
            expectedAuthor = eachMap.get("author");
            expectedCategoryId = eachMap.get("book_category_id");
            expectedDescription = eachMap.get("description");
        }
        BrowserUtil.waitFor(1);

        Assert.assertEquals(expectedBookName, booksModulePage.bookName.getAttribute("value"));
        Assert.assertEquals(expectedISBN, booksModulePage.bookIsbn.getAttribute("value"));
        Assert.assertEquals(expectedYear, booksModulePage.bookYear.getAttribute("value"));
        Assert.assertEquals(expectedAuthor, booksModulePage.bookAuthor.getAttribute("value"));
        Assert.assertEquals(expectedCategoryId, booksModulePage.addEditCategorySelectDropdown.getAttribute("value"));
        Assert.assertEquals(expectedDescription, booksModulePage.bookDescription.getAttribute("value"));


    }


}
