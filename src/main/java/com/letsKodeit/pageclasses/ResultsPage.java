package com.letsKodeit.pageclasses;

import com.letsKodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage extends BasePage {
    public WebDriver driver;
    private String COURSE_LIST = "xpath=>//div[@class='zen-course-list']";

public ResultsPage(WebDriver driver)
{   super(driver);
    this.driver=driver;
}

    public int coursesCount()
    {
        List<WebElement> courseList = getElementList(COURSE_LIST,"Courses List");
       // System.out.println("course list size is "+courseList.size());

        return courseList.size();

    }

    public boolean verifySearchResult()
    {
        boolean result=false;

        if(coursesCount()>0)
        {
            result=true;
        }
        return result;

    }

}
