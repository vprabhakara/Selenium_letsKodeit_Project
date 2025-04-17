package com.letsKodeit.testClasses;

import com.letsKodeit.Utilities.Constants;
import com.letsKodeit.Utilities.ExcelUtility;
import com.letsKodeit.base.BaseTest;
import com.letsKodeit.base.Checkpoint;
import com.letsKodeit.pageclasses.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AllCoursesTests extends BaseTest {

    @BeforeClass
    public void setup() throws IOException {

         nav = login.signIN(Constants.DEFAULT_USERNAME,Constants.DEFAULT_PASSWORD);
         ExcelUtility.setExcelFile(Constants.EXCEL_FILE,"AllCoursesTests");
        // System.out.println("Excel file accessed successfully");
    }
    @DataProvider(name="verifyCourseSearch")
    public Object[][] getVerifyCourseSearch()
    {
        Object[][] testData = ExcelUtility.getTestData("verify_search_course");
        return testData;

    }
@Test(dataProvider = "verifyCourseSearch")
    public void verifySearchTest(String CourseName) throws InterruptedException {

        nav.allCourses();
        search =  new SearchBarPage(driver);
        result = search.course(CourseName);
       boolean searchResult  = result.verifySearchResult();
    Checkpoint.markFinal("test-02", searchResult, "search course verification");


}

    @Test(enabled = false)
    public void verifyCategorySearch() throws InterruptedException {
        driver.get(baseURL);
         nav.allCourses();
        category = new CategoryFilterPage(driver);
        String filterValue = "Test Automation";

         category.select(filterValue);
         String actualCategoryFilterText = category.getCourseResultsPageTitle(filterValue);
          String partialFilterText = "Category : ";
        String expectedCategoryFilterText = partialFilterText+filterValue;
       // Checkpoint.markFinal("test-03", filterResult, "filter by category verification");
        // Assert.assertEquals(actualCategoryFilterText,expectedCategoryFilterText);

    }


}
