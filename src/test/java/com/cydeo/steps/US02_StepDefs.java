package com.cydeo.steps;

import com.cydeo.pages.DashboardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import com.cydeo.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class US02_StepDefs {
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    String actualBorrowedBooks;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {
        loginPage.login(userType);
    }

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.borrowedBooks));
        BrowserUtil.waitFor(1);
        actualBorrowedBooks = dashboardPage.borrowedBooks.getText();
        System.out.println("actualBorrowedBooks = " + actualBorrowedBooks);
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
        DB_Util.runQuery("select count(*) from book_borrow\n" +
                "where is_returned = 0");
        String expectedBorrowedBooks = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedBorrowedBooks, actualBorrowedBooks);

    }
}
