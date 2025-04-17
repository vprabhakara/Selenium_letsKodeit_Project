package com.letsKodeit.pageclasses;

import com.letsKodeit.Utilities.Utility;
import com.letsKodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NavigationPage extends BasePage {


    public  WebDriver driver;
    private JavascriptExecutor js;
    private String URL = "https://www.letskodeit.com/courses";
    private String ALL_COURSES = "xpath=>//a[text()='ALL COURSES']";
    private String MY_COURSES = "xpath=>//a[text()='MY COURSES']";
    private String ACCT_LINK = "xpath=>//img[@class='zl-navbar-rhs-img ']";
    private String LOGOUT_LINK = "xpath=>//a[@href='/logout']";
    private String SIGNIN="xpath=>//a[text()='Sign In']";
    private By LOGIN =By.xpath("//a[text()='Sign In']");
    private String ALL_COURSES_HEADER = "xpath=>//h1[text()='All Courses']";


    public NavigationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public void allCourses() {
       // WebElement allCoursesLink = driver.findElement(By.xpath(ALL_COURSES));
        // allCoursesLink.click();
        elementClick(ALL_COURSES,"All Courses Link");

        //Thread.sleep(2000);


    }

    public void myCourses() throws InterruptedException {
       // WebElement myCoursesLink = driver.findElement(By.xpath(MY_COURSES));
        //myCoursesLink.click(MY_COURSES,"My Courses link");
        elementClick(MY_COURSES,"My Courses link");
        //Thread.sleep(2000);

    }

    public boolean isOpen() {
        return URL.equalsIgnoreCase(driver.getCurrentUrl());
    }
    public LoginPage login()
    {
        //WebElement signIn = driver.findElement(By.xpath(SIGNIN));
       clickWhenReady(LOGIN,10);

        return new LoginPage(driver);
    }

    public boolean isUserLoggedIn() {
        try {
           WebElement acct_Image = getElement(ACCT_LINK,"Account Link");
           if (acct_Image.isDisplayed())
           {
               return true;
           }
           else
           {
               return false;
           }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;


    }

   public boolean verifyHeader()
    {
        String actText =  getText(ALL_COURSES,"All courses");
        return Utility.verifyTextContains(actText,"All Courses");



    }


public void logout()
{
   elementClick(ACCT_LINK,"Account Click");
    // driver.findElement(By.xpath(ACCT_LINK)).click();
    WebElement logoutlink = waitForElement(LOGOUT_LINK,Duration.ofSeconds(10));
    elementClick(logoutlink,"Logout link");

}


}
