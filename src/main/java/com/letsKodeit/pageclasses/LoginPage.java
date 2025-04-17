package com.letsKodeit.pageclasses;

import com.letsKodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public WebDriver driver;

    private String EMAIL_FIELD="id=>email";
    private String PASSWORD_FIELD="id=>login-password";
    private String LOGIN_BUTTON = "id=>login";

    public LoginPage(WebDriver driver)
    {   super(driver);
        this.driver= driver;
    }


    public NavigationPage signIN(String email,String password)
    {
        /*WebElement emailField = driver.findElement(By.id(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField= driver.findElement(By.id(PASSWORD_FIELD));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id(LOGIN_BUTTON));
        loginButton.click();
        */
        sendData(EMAIL_FIELD, email, "Email Field");
        sendData(PASSWORD_FIELD, password, "Password Field");
        elementClick(LOGIN_BUTTON, "Login Button");
        return new NavigationPage(driver);

    }





}
