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

public class LoginTests extends BaseTest {

    @BeforeClass
    public void setUp() {

    }



    @AfterMethod
    public void afterMethod() throws InterruptedException {

        System.out.println("****** After Method ******");

        if(nav.isUserLoggedIn())
        {
            nav.logout();
           // Thread.sleep(2000);
            //nav.login();
        }
    }

    @Test
    public void testValidlogin() throws InterruptedException {
        nav = login.signIN(Constants.DEFAULT_USERNAME,Constants.DEFAULT_PASSWORD);
      //  boolean result =nav.isUserLoggedIn();

        boolean verifyHeader=nav.verifyHeader();
        Checkpoint.mark("test-01",verifyHeader," Header Verification");
        Thread.sleep(3000);
       boolean result = nav.isUserLoggedIn();
        //Checkpoint.markFinal("test-01",result,"Login verification");
        Assert.assertTrue(result);

    }
    @Test(enabled = false)
    public void testInvalidLogin()
    {    nav = login.signIN("test@email",Constants.DEFAULT_PASSWORD);
        boolean result=nav.isUserLoggedIn();
        Assert.assertFalse(result);

    }

}
