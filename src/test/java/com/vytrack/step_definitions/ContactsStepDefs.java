package com.vytrack.step_definitions;

import com.vytrack.pages.ContactInfoPage;
import com.vytrack.pages.ContactsPage;
import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import com.vytrack.utilities.DBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactsStepDefs {

    @Then("the user should see following options")
    public void the_user_should_see_following_options(List<String> menuOptions) {

        BrowserUtils.waitFor(2);
        List<String> actualOptions= BrowserUtils.getElementsText(new DashboardPage().menuOptions);

        Assert.assertEquals(menuOptions,actualOptions);
        System.out.println("menuOptions = " + menuOptions);
        System.out.println("actualOptions = " + actualOptions);

    }


    @When("the user logs in using following credentials")
    public void the_user_logs_in_using_following_credentials(Map<String,String> userInfo) {

        System.out.println(userInfo);

        //use map info to login and also verify firstname and lastname
        new LoginPage().login(userInfo.get("username"), userInfo.get("password"));

        String expectedUserInfo= userInfo.get("firstname") + " " + userInfo.get("lastname");
        String actualUserInfo= new DashboardPage().getUserName();

        Assert.assertEquals(expectedUserInfo,actualUserInfo);

    }

    @When("the user clicks the {string} from contacts")
    public void theUserClicksTheFromContacts(String email) {

        BrowserUtils.waitFor(3);
        BrowserUtils.waitForClickablility(new ContactsPage().getContactEmail(email),5).click();
        //new ContactsPage().getContactEmail(email).click();

    }

    @Then("the information should be same with database")
    public void theInformationShouldBeSameWithDatabase() {

        BrowserUtils.waitFor(5);

        //get information from UI
        String actualFullName = BrowserUtils.waitForVisibility(new ContactInfoPage().contactFullName,5).getText();
        String actualEmail = new ContactInfoPage().email.getText();
        String actualPhone = new ContactInfoPage().phone.getText();

        System.out.println("actualFullName = " + actualFullName);
        System.out.println("actualEmail = " + actualEmail);
        System.out.println("actualPhone = " + actualPhone);


        //get information from Database

        String query="SELECT concat (C.FIRST_NAME, ' ', C.LAST_NAME) as \"full_name\", E.EMAIL, PHONE\n" +
                "FROM OROCRM_CONTACT C JOIN OROCRM_CONTACT_EMAIL E\n" +
                "ON C.ID=E.OWNER_ID JOIN OROCRM_CONTACT_PHONE P\n" +
                "ON C.ID=P.OWNER_ID\n" +
                "WHERE E.EMAIL='mbrackstone9@example.com'";

        // get info and save in the map
        Map<String, Object> rowMap= DBUtils.getRowMap(query);

        String expectedFullname= (String) rowMap.get("full_name");
        String expectedEmail= (String) rowMap.get("email");
        String expectedPhone= (String) rowMap.get("phone");

        System.out.println("expectedFullname = " + expectedFullname);
        System.out.println("expectedEmail = " + expectedEmail);
        System.out.println("expectedPhone = " + expectedPhone);

        //Assertion

        Assert.assertEquals(expectedFullname,actualFullName);
        Assert.assertEquals(expectedEmail,actualEmail);
        Assert.assertEquals(expectedPhone,actualPhone);

    }

    @Then("the information for {string} should be same with database")
    public void theInformationForShouldBeSameWithDatabase(String email) {

        BrowserUtils.waitFor(3);

        //get information from UI
        String actualFullName = new ContactInfoPage().contactFullName.getText();
        String actualEmail = new ContactInfoPage().email.getText();
        String actualPhone = new ContactInfoPage().phone.getText();

        System.out.println("actualFullName = " + actualFullName);
        System.out.println("actualEmail = " + actualEmail);
        System.out.println("actualPhone = " + actualPhone);


        //get information from Database

        String query="SELECT concat (C.FIRST_NAME, ' ', C.LAST_NAME) as \"full_name\", E.EMAIL, PHONE\n" +
                "FROM OROCRM_CONTACT C JOIN OROCRM_CONTACT_EMAIL E\n" +
                "ON C.ID=E.OWNER_ID JOIN OROCRM_CONTACT_PHONE P\n" +
                "ON C.ID=P.OWNER_ID\n" +
                "WHERE E.EMAIL='"+email+"'";

        // get info and save in the map
        Map<String, Object> rowMap= DBUtils.getRowMap(query);

        String expectedFullname= (String) rowMap.get("full_name");
        String expectedEmail= (String) rowMap.get("email");
        String expectedPhone= (String) rowMap.get("phone");

        System.out.println("expectedFullname = " + expectedFullname);
        System.out.println("expectedEmail = " + expectedEmail);
        System.out.println("expectedPhone = " + expectedPhone);

        //Assertion

        Assert.assertEquals(expectedFullname,actualFullName);
        Assert.assertEquals(expectedEmail,actualEmail);
        Assert.assertEquals(expectedPhone,actualPhone);


    }
}
