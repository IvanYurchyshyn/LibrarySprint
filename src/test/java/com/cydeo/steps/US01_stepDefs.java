package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class US01_stepDefs {

    List<String> actualNamesOfColumns = new ArrayList<>();

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        DB_Util.createConnection();
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        DB_Util.runQuery("select id from users");
    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        List<String> allId =  DB_Util.getColumnDataAsList(1);
        DB_Util.runQuery("select distinct id from users");
        List<String> distinctId = DB_Util.getColumnDataAsList(1);

        Assert.assertEquals(allId.size(),distinctId.size());
    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {
        DB_Util.runQuery("select * from users");
       actualNamesOfColumns =  DB_Util.getAllColumnNamesAsList();
    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedNamesOfColumns) {

        Assert.assertEquals(expectedNamesOfColumns,actualNamesOfColumns);

    }

}
