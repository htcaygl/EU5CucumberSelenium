package com.vytrack.step_definitions;

import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @Before
    public void setup() {
        System.out.println("\t this is coming from BEFORE");
    }

    @After
    public  void tearDown(Scenario scenario){

        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName()+"_screenshot");
        }

        Driver.closeDriver();

    }


    @Before("@db")
    public void setupDb() {
        System.out.println("\t connecting database..");
    }

    @After("@db")
    public  void closeDb(){
        System.out.println("\t disconnecting database..");
    }
}
