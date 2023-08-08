package com.cydeo.steps;

import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05_stepDefs {
    String expectedMostPopularGenre;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.runQuery("select bc.name\n" +
                "from book_borrow\n" +
                "         join books b on book_borrow.book_id = b.id\n" +
                "         join book_categories bc on b.book_category_id = bc.id\n" +
                "group by bc.name\n" +
                "order by count(*) desc\n" +
                "LIMIT 1");
        expectedMostPopularGenre = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedMostPopularGenre = " + expectedMostPopularGenre);

    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String genreName) {
        Assert.assertEquals(expectedMostPopularGenre,genreName);

    }
}
