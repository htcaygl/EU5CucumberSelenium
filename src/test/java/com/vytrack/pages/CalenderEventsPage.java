package com.vytrack.pages;

import com.vytrack.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalenderEventsPage extends BasePage {

    //Actually this constructor is not needed. It calls the superclasses constructor.
   public CalenderEventsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEvent;

   @FindBy(xpath = "//div[contains(text(),'Options')]")
   public WebElement optionsLink;

   @FindBy(xpath = "//input[@type='number']")
   public WebElement pageNumber;

   @FindBy(xpath = "//button[@class='btn dropdown-toggle ']")
   public WebElement viewPerPage;

   @FindBy(xpath = "//label[contains(text(), 'Total of')]")
    public WebElement totalOfRecord;

   @FindBy(xpath = "//div[@class='btn-group dropdown']//input")
    public WebElement allCheckbox;

    @FindBy(xpath = "//tbody[@class='grid-body']//input")
    public List<WebElement> allCheckBoxes;

    @FindBy(xpath = "//td[contains(text(),'Testers meeting')]")
    public WebElement testerMeeting;

//   public List<WebElement> totalRow(){
//
//       List<WebElement> t1= Driver.get().findElements(By.xpath("//table/tbody/tr"));
//     //  return t1.get()
//
//   }
}