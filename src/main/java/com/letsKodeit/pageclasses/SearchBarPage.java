package com.letsKodeit.pageclasses;

import com.letsKodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBarPage extends BasePage {

    public WebDriver driver;
    private String SEARCH_COURSE_FIELD = "name=>course";
    private String SEARCH_COURSE_BUTTON = "xpath=>//button[@type='submit']";

    public SearchBarPage(WebDriver driver)
    {  super(driver);
        this.driver = driver;

    }

    public ResultsPage course(String searchCourse)
    {
        sendData(SEARCH_COURSE_FIELD,searchCourse,"Enter Course");
       elementClick(SEARCH_COURSE_BUTTON,"Click Search Course Button");
               return new ResultsPage(driver);
    }
}
