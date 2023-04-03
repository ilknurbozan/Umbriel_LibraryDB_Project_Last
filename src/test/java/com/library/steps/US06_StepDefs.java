package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class US06_StepDefs {
    BookPage bookPage = new BookPage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {

        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {

        bookPage.bookName.sendKeys(bookName);
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String ISBN) {
        bookPage.isbn.sendKeys(ISBN);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        bookPage.author.sendKeys(author);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
        Select dropDown = new Select(bookPage.categoryDropdown);
        dropDown.selectByVisibleText(category);
    }

    @When("the librarian click to save changes")

    public void the_librarian_click_to_save_changes() {
        BrowserUtil.waitFor(2);
        bookPage.saveChanges.click();
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String message) {
        BrowserUtil.waitForVisibility(bookPage.toastMessage,5);
        Assert.assertEquals(message, bookPage.toastMessage.getText());
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String bookName) {

        DB_Util.runQuery("select name from books where name=" + "'" + bookName + "'");
        System.out.println("DB_Util.getFirstRowFirstColumn() = " + DB_Util.getFirstRowFirstColumn());
        Assert.assertEquals(bookName, DB_Util.getFirstRowFirstColumn());


    }
}
