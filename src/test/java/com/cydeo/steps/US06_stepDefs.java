package com.cydeo.steps;

import com.cydeo.pages.BooksModulePage;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class US06_stepDefs {

    BooksModulePage booksModulePage = new BooksModulePage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        booksModulePage.addBookBtn.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String title) {
        booksModulePage.bookName.sendKeys(title);
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        booksModulePage.bookIsbn.sendKeys(isbn);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        booksModulePage.bookYear.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        booksModulePage.bookAuthor.sendKeys(author);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
        Select select = new Select(booksModulePage.addEditCategorySelectDropdown);
        select.selectByVisibleText(category);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        booksModulePage.saveChangesBtn.click();
    }

    @Then("verify “The book has been created\" message is displayed")
    public void verify_the_book_has_been_created_message_is_displayed() {
        String actualPopUp = booksModulePage.popUpBook.getText();
        String expectedPopUp = "The book has been created.";
        Assert.assertEquals(expectedPopUp,actualPopUp);
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String title) {

        DB_Util.runQuery("select name from books\n" +
                "where name = '"+title+"'");

        String expectedBookTitle = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBookTitle,title);


    }

}
