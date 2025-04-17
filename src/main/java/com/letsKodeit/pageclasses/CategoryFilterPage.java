package com.letsKodeit.pageclasses;

import com.letsKodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CategoryFilterPage extends BasePage {
    public WebDriver driver ;
    private String CATEGORY_DROPDOWN="name=>categories";
    private String CATEGORY_OPTION = "Category : ";

    public CategoryFilterPage (WebDriver driver)
    {    super(driver);
        this.driver = driver;
    }

    public void select(String filteroption)
    {
       //  driver.findElement(By.name(CATEGORY_DROPDOWN));
        WebElement category = getElement(CATEGORY_DROPDOWN,"Category dropdown");
        Select categoryOption = new Select(category);
        categoryOption.selectByVisibleText(filteroption);


        //return new ResultsPage(driver);
    }
public String getCourseResultsPageTitle(String filteroption) throws InterruptedException {
    String result ;
    String filterText = CATEGORY_OPTION+filteroption;
    //System.out.println(filterText);
    Thread.sleep(3000);
   result = driver.findElement(By.xpath("//h1[contains(text(),filterText)]")).getText();

   //System.out.println("Result from getCourseResultsPageTitle   --------->" + result);
    return result;
}

}
