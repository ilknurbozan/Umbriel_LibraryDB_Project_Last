package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US07_Fatima {
    BookPage bookPage;
    String bookName;






    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        bookPage.borrowBook(bookName).click();
        BrowserUtil.waitFor(2);
    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String module) {
        BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
        new DashBoardPage().navigateModule(module);

        Assert.assertTrue(BrowserUtil.getElementsText(borrowedBooksPage.allBorrowedBooksName).contains(bookName));
    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {
        String query = "select name from books b\n" +
                "join book_borrow bb on b.id = bb.book_id\n" +
                "join users u on bb.user_id = u.id\n" +
                "where name = '"+bookName+"' and full_name = 'Test Student 5';";



        DB_Util.runQuery(query);
        List<String> actualList = DB_Util.getColumnDataAsList(1);
        Assert.assertTrue(actualList.contains(bookName));
    }


}
