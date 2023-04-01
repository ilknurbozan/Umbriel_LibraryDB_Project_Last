package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US05_StepDefs {
    String actualGenre;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
        DB_Util.runQuery("select bc.name,count(*) as total_book_count from book_borrow bb\n" +
                "                       inner join books b on bb.book_id = b.id\n" +
                "                        inner join book_categories bc on b.book_category_id = bc.id\n" +
                "                group by bc.name\n" +
                "                order by total_book_count desc;");
    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedGenre) {

        actualGenre=DB_Util.getFirstRowFirstColumn();

        System.out.println("expectedGenre = " + expectedGenre);
        System.out.println("actualGenre = " + actualGenre);

        Assert.assertEquals(expectedGenre,actualGenre);


    }
}
