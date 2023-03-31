package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

public class US04_Mila {

    LoginPage loginPage= new LoginPage();

    BookPage bookPage = new BookPage();

    String  bookName;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String userType) {

        loginPage.login(userType);

    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {

        bookPage.navigateModule(moduleName);

    }
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String BookName) {

        bookPage.search.sendKeys(BookName);
        this.bookName=BookName;
    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {

        bookPage.editBook(bookName).click();

    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {

        Select select = new Select(bookPage.categoryDropdown);

        String query1 = "select * from books where name = 'Clean Code JDBS'";

        DB_Util.runQuery(query1);
        Map<String, String> rowMap = DB_Util.getRowMap(1);

        String expectedBookName = rowMap.get("name");
        String expectedAuthorName = rowMap.get("author");
        String expectedISBN = rowMap.get("isbn");
        String expectedYear = rowMap.get("year");
        String expectedDesc = rowMap.get("description");
        String categoryID = rowMap.get("book_category_id");

        String query2 = "select name from book_categories where id ="+categoryID+"";

        System.out.println("hi");

        DB_Util.runQuery(query2);
        String expectedBookCategory = DB_Util.getCellValue(1, 1);


        Assert.assertEquals(bookPage.bookName.getAttribute("value"), expectedBookName);
        Assert.assertEquals(bookPage.author.getAttribute("value"), expectedAuthorName);
        Assert.assertEquals(bookPage.isbn.getAttribute("value"), expectedISBN);
        Assert.assertEquals(bookPage.year.getAttribute("value"), expectedYear);
        Assert.assertEquals(bookPage.description.getAttribute("value"), expectedDesc);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), expectedBookCategory);


    }

}
