package com.letsKodeit.testClasses;

import com.letsKodeit.Utilities.Constants;
import com.letsKodeit.Utilities.ExcelUtility;
import com.letsKodeit.base.BaseTest;
import com.letsKodeit.base.Checkpoint;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestsDataDriven extends BaseTest {
    @BeforeClass
    public void setUp() throws IOException {
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE,"LoginTests");

    }

    @DataProvider(name="verifyLogin")
    public Object[][] getVerifyLoginDetails()
    {
        Object[][] testData = ExcelUtility.getTestData("verify_valid_Login");
        return testData;

    }

    @AfterMethod
    public void afterMethod()
    {   System.out.println("****** After Method ******");

        if(nav.isUserLoggedIn())
        {
            nav.logout();
            nav.login();
        }
    }

    @Test(dataProvider = "verifyLogin")
    public void testValidlogin(String username,String password) throws InterruptedException {
        nav = login.signIN(username,password);

        //  boolean result =nav.isUserLoggedIn();

        boolean verifyHeader=nav.verifyHeader();
        Checkpoint.mark("test-01",verifyHeader,"Verify Header");
        Thread.sleep(3000);
        boolean result = nav.isUserLoggedIn();
        Checkpoint.markFinal("test-01",result,"Login verification");
        //Assert.assertTrue(result);

    }
    @Test(enabled = false)
    public void testInvalidLogin()
    {    nav = login.signIN("test@email",Constants.DEFAULT_PASSWORD);
        boolean result=nav.isUserLoggedIn();
        Assert.assertFalse(result);

    }


}
