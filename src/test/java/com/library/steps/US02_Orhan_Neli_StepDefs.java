package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_Orhan_Neli_StepDefs {

    DashBoardPage dashBoardPage;

    String actualBorrowedBookNumber;

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {

        //BrowserUtil.waitForVisibility(dashBoardPage.usersNumber,3);
        BrowserUtil.waitFor(2);
        dashBoardPage = new DashBoardPage();
        //OPT 1
        actualBorrowedBookNumber = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumber = " + actualBorrowedBookNumber);

        //OPT 2
        //System.out.println("dashBoardPage.getModuleCount(\"Borrowed Books\") = " + dashBoardPage.getModuleCount("Borrowed Books"));
    }

    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        BrowserUtil.waitFor(2);
        String query="select count(*) from book_borrow where is_returned=0";

        DB_Util.runQuery(query);

        String expectedBorrowedBookNumber = DB_Util.getCellValue(1, 1);
        System.out.println("expectedBorrowedBookNumber = " + expectedBorrowedBookNumber);

        Assert.assertEquals(expectedBorrowedBookNumber, actualBorrowedBookNumber);

    }

}
