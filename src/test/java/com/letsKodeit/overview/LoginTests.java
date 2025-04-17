package com.letsKodeit.overview;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {
    WebDriver driver;
    String baseURL;

         @BeforeClass
        public void setupClass()
    {
        driver = new ChromeDriver();
        baseURL= "https://sso.teachable.com/secure/42299/identity/login/password?force=true";
        driver.get(baseURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));


    }
    @Test
    public void verify_Login()
    {
      driver.findElement(By.id("email")).sendKeys("vprabhakara@gmail.com");

      WebElement password =  driver.findElement(By.id("password"));

      password.sendKeys("abcabc");

      driver.findElement(By.name("commit")).click();
      WebElement acct_Image = null;

      try
      { acct_Image = driver.findElement(By.className("gravatar"));

      }catch (Exception e) {
          e.printStackTrace();
      }
        Assert.assertNotNull(acct_Image);



    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
    }
}

