package com.vytrack.step_definitions;

import com.vytrack.pages.DashboardPage;
import com.vytrack.pages.LoginPage;
import com.vytrack.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

}
