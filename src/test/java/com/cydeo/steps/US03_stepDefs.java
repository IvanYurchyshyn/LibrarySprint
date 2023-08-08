package com.cydeo.steps;

import com.cydeo.pages.BooksModulePage;
import com.cydeo.pages.DashboardPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class US03_stepDefs {
    DashboardPage dashboardPage = new DashboardPage();
    BooksModulePage booksModulePage = new BooksModulePage();

    List<String> actualCategories;
    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        dashboardPage.goToModule(moduleName);
    }
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
      Select select = new Select(booksModulePage.categoriesDropdownSelect);

        List<WebElement> listOfCategories = select.getOptions();
        actualCategories = new ArrayList<>();

        for (WebElement each : listOfCategories) {
           actualCategories.add(each.getText());
        }
        actualCategories.remove(0);

    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        DB_Util.runQuery("select name from book_categories");
        List<String> expectedCategories = DB_Util.getColumnDataAsList(1);
        Assert.assertEquals(expectedCategories,actualCategories);
    }
}
