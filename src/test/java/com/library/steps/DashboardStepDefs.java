package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DashboardStepDefs {

    LoginPage loginPage;
    DashBoardPage dashBoardPage;

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
        loginPage=new LoginPage();
        loginPage.login(user);
        BrowserUtil.waitFor(3);
    }

    String actualBorrowedBookNumber;

    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
        dashBoardPage=new DashBoardPage();

        // OPT 1
        actualBorrowedBookNumber = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println(actualBorrowedBookNumber);

        // OPT 2
        System.out.println("dashBoardPage.getModuleCount(\"Borrowed Books\") = " + dashBoardPage.getModuleCount("Borrowed Books"));


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

          String query="select count(*) from book_borrow where is_returned=0";

          DB_Util.runQuery(query);

        String expectedBorrowedBookNumber = DB_Util.getCellValue(1, 1);
        System.out.println("expectedBorrowedBookNumber = " + expectedBorrowedBookNumber);

        //Assertions
        Assert.assertEquals(expectedBorrowedBookNumber,actualBorrowedBookNumber);



    }
}
