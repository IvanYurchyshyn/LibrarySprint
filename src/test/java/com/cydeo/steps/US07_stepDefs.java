package com.cydeo.steps;

import com.cydeo.pages.BooksModulePage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class US07_stepDefs {
    String bookTitle;
    BooksModulePage booksModulePage = new BooksModulePage();

    @Given("the user searches book name called {string}")
    public void the_user_searches_book_name_called(String bookTitle) {
        booksModulePage.searchInput.sendKeys(bookTitle + Keys.ENTER);
        this.bookTitle = bookTitle;
    }

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        BrowserUtil.waitFor(1);
        booksModulePage.borrowBookBtn.click();
    }

    @Then("verify that book is shown in \"Borrowing Books” page")
    public void verify_that_book_is_shown_in_borrowing_books_page() {

        booksModulePage.borrowingBooks.click();

        List<String> allBoxesOfTable = new ArrayList<>();
        for (WebElement eachBox : booksModulePage.tableBorrowedBooks) {
            allBoxesOfTable.add(eachBox.getText());
        }
        Assert.assertTrue(allBoxesOfTable.contains(bookTitle));
    }

    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {

        DB_Util.runQuery("select email,b.name,bb.borrowed_date from users u\n" +
                "inner join book_borrow bb on u.id = bb.user_id\n" +
                "inner join books b on bb.book_id = b.id\n" +
                "where email= '" + ConfigurationReader.getProperty("student_username") + "' and name= '" + bookTitle + "'\n" +
                "order by 3 desc");

        String expectedBookTitle = DB_Util.getCellValue(1, "name");
        Assert.assertEquals(expectedBookTitle,bookTitle);

        booksModulePage.firstNotReturnedBookBtn.click();
    }
}
