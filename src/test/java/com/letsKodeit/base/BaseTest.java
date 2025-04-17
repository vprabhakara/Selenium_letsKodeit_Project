package com.letsKodeit.base;

import com.letsKodeit.Utilities.Constants;
import com.letsKodeit.pageclasses.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected String baseURL;
    protected LoginPage login;
    protected NavigationPage nav;
    protected SearchBarPage search;
    protected ResultsPage result;
    protected CategoryFilterPage category;

    @BeforeClass
    @Parameters({"browser"})
    public void Commonsetup(String browser) throws InterruptedException {
        driver = WebDriverFactory.getIntance().getDriver(browser);
        baseURL = Constants.BASE_URL;
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURL);
        nav = new NavigationPage(driver);
        login = nav.login();

        // Thread.sleep(2000);
    }

   @BeforeMethod
   @Parameters({"browser"})
    public void setupMethod(String browser)
   {    driver = WebDriverFactory.getIntance().getDriver(browser);
        Checkpoint.clearHashMap();
    }
    @AfterTest
    public void commonTearDown()
    {
        driver.quit();
    }
}
