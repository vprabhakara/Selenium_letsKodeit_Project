package com.letsKodeit.base;

import org.apache.commons.io.FileUtils;
import com.letsKodeit.Utilities.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CustomDriver {
    private static final Logger log = LogManager.getLogger(CustomDriver.class.getName());
    public WebDriver driver;
    private JavascriptExecutor js;

    public CustomDriver() {

    }



    public CustomDriver(WebDriver driver)
    {
        this.driver = driver;
    }
    public void refresh()
    { driver.navigate().refresh();
        log.info("The current url is refreshed");

    }
    public String takeScreenshot(String methodName, String browserName) {
        String fileName = Utility.getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println("Screen Shot Was Stored at: "+ path);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public String getTitle()
    {
       String title =  driver.getTitle();
       log.info(" The title of the page ------->"+ title);
       return title;
    }

    public By getByType(String locator)
    {
        By by =null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];

        try {
            if (locatorType.contains("id")) {
                by = By.id(locator);

            }else
            if (locatorType.contains("xpath")) {
                by = By.xpath(locator);
            }else
                if (locatorType.contains("class")) {
                by = By.className(locator);

            }else
            if (locatorType.contains("tag")) {
                by = By.tagName(locator);

            }else
            if (locatorType.contains("css")) {
                by = By.cssSelector(locator);

            }else
            if (locatorType.contains("link")) {
                by = By.linkText(locator);

            }else
            if (locatorType.contains("partial")) {
                by = By.partialLinkText(locator);
            }
            else if (locatorType.contains("name")) {
                by = By.name(locator);

            }else
            {
                log.info("By Locator type not Supported ");
            }

        }catch (Exception e)
        {
            log.error("Locator NOT found" + locatorType);
            e.printStackTrace();

        }
        return by;
    }

    public WebElement getElement(String locator, String info)
    {
        WebElement element = null;
        By bytype = getByType(locator);

        try
        {
            element = driver.findElement(bytype);
            log.info("Element found with :"+locator);

        }catch (Exception e)
        {
            log.error("Element Not found with"+locator);
            e.printStackTrace();
        }

        return element;
    }

    public String getText(WebElement element , String info)
    {
        String text = null;
        text = element.getText();
        if(text.length()==0)
        {   text=element.getAttribute("innerText");
                    }
        if (!text.isEmpty()){
            log.info("Text is found--->"+text);

        }else
        {
            log.info("Text NOT found");
        }
        return text.trim();
    }

    public String getText(String locator,String info)
    {
        WebElement element = this.getElement(locator,info);
          return this.getText(element,info);
    }

    public Boolean isElementEnabled(WebElement element, String info)
    {
       Boolean enabled =element.isEnabled();
        if(enabled)
        {
            log.info("Element is enabled"+info+ "Is enabled");

        }else
        {
            log.info("Element not enabled"+info +"Is Disabled");
        }
        return enabled;
    }

    public boolean IsElementEnabled(String locator,String info)
    {
        WebElement element = this.getElement(locator,info);
        return this.isElementEnabled(element,info);
    }

    public void javascriptClick(String locator,String info) {
        WebElement element = this.getElement(locator, info);
        try {
            js.executeScript("arguments[0].click()", element);
            log.info("Element is clickable " + info);
        } catch (Exception e) {
            log.info("Element is not clickable" + info);
            e.printStackTrace();
        }
    }

    /***
     * Click element using JavaScript
     * @param element - WebElement to perform Click operation
     * @param info - Information about element
     */
    public void javascriptClick(WebElement element, String info) {
        try {
            js.executeScript("arguments[0].click();", element);
            log.info("Clicked on :: " + info);
        } catch (Exception e) {
            log.info("Cannot click on :: " + info);
        }
    }

    public void elementClick(WebElement element, String info,long timeToWait)
    {
        try {
            element.click();
            if (timeToWait == 0) {
                log.info("Element clicked " + info);
            } else
                Utility.sleep(timeToWait, "Clicked on ::" + info);
        }catch(Exception e)
        {
            log.error("Element is not clicked"+info);
        }


    }
    /***
     *
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element, usually text on element
     * @return
     */
    public List<WebElement> getElementList(String locator, String info) {
        List<WebElement> elementList = new ArrayList<WebElement>();
        By byType = getByType(locator);
        try {
            elementList = driver.findElements(byType);
            log.info("Element List found with: " + locator);
        } catch (Exception e) {
            log.error("Element List not found with: " + locator);
            e.printStackTrace();
        }
        return elementList;
    }





    public void elementClick(WebElement element,String info)
    {
        this.elementClick(element,info,0);
    }
    public void elementClick(String locator, String info, long timeToWait) {
        WebElement element = this.getElement(locator, info);
        elementClick(element, info, timeToWait);
    }

    /**
     * Click element with locator and no time to wait
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param info - Information about element
     * @return
     */
    public void elementClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        elementClick(element, info, 0);
    }

    public void clickWhenReady(By locator,int timeout)
    {
        try
        { driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            WebElement element=null;
            log.info("Waiting for max"+timeout+"secs to be clickable");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        }catch(Exception e)
        {
            log.error("Element is not clickable");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
    }
    public void sendData(WebElement element, String data, String info, Boolean clear) {
        try {
            if (clear) {
                element.clear();
            }
            //Util.sleep(1000, "Waiting Before Entering Data");
            element.sendKeys(data);
            log.info("Send Keys on element :: "
                    + info + " with data :: " + data);
        } catch (Exception e) {
            log.error("Cannot send keys on element :: "
                    + info + " with data :: " + data);
        }
    }

    /***
     * Send Keys to element with locator
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     * @param clear - True if you want to clear the field before sending data
     */
    public void sendData(String locator, String data, String info, Boolean clear) {
        WebElement element = this.getElement(locator, info);
        sendData(element, data, info, clear);
    }

    /***
     * Send Keys to element with clear
     * @param element - WebElement to send data
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(WebElement element, String data, String info) {
        sendData(element, data, info, true);
    }

    /***
     * Send Keys to element with locator and clear
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param data - Data to send
     * @param info - Information about element
     */
    public void sendData(String locator, String data, String info) {
        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);
    }

    public Boolean isDisplayed(WebElement element, String info) {
        Boolean displayed = false;
        if (element != null) {
            displayed = element.isDisplayed();
            if (displayed)
                log.info("Element :: " + info + " is displayed");
            else
                log.info("Element :: " + info + " is NOT displayed");
        }
        return displayed;
    }

    /***
     * Check if element is displayed with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
    public Boolean isSelected(WebElement element, String info) {
        Boolean selected = false;
        if (element != null) {
            selected = element.isSelected();
            if (selected)
                log.info("Element :: " + info + " is selected");
            else
                log.info("Element :: " + info + " is already selected");
        }
        return selected;
    }

    /**
     * @param locator
     * @param info
     * @return
     */
    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    /**
     * Selects a check box irrespective of its state
     *
     * @param element
     * @param info
     */
    public void Check(WebElement element, String info) {
        if (!isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is checked");
        } else
            log.info("Element :: " + info + " is already checked");
    }

    /**
     * Selects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    /**
     * UnSelects a check box irrespective of its state
     *
     * @param element
     * @param info
     * @return
     */
    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is unchecked");
        } else
            log.info("Element :: " + info + " is already unchecked");
    }

    /**
     * UnSelects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            log.info("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }

    /**
     * @param locator
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    /**
     * @param element
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * @param locator
     * @param timeout
     * @return
     */
    public WebElement waitForElement(String locator, Duration timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            log.info("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver,timeout);
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            log.info("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return element;
    }

    /***
     * Wait for element to be clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
    public WebElement waitForElementToBeClickable(String locator, Duration timeout) {
        By byType = getByType(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(byType));
            log.info("Element is clickable on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return element;
    }

    /**
     *
     */
    public boolean waitForLoading(String locator, Duration timeout) {
        By byType = getByType(locator);
        boolean elementInvisible = false;
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            log.info("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            elementInvisible = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(byType));
            log.info("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        }
        return elementInvisible;
    }

    /**
     * Mouse Hovers to an element
     *
     * @param locator
     */
    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    /**
     * @param element
     * @param optionToSelect
     */
    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        log.info("Selected option : " + optionToSelect);
    }

    /**
     * Selects a given option in list box
     *
     * @param locator
     * @param optionToSelect
     */
    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    /**
     * get Selected drop down value
     *
     * @param element
     * @return
     */
    public String getSelectDropDownValue(WebElement element) {
        Select sel = new Select(element);
        return sel.getFirstSelectedOption().getText();
    }

    /**
     * @param element
     * @param optionToVerify
     */
    public boolean isOptionExists(WebElement element, String optionToVerify) {
        Select sel = new Select(element);
        boolean exists = false;
        List<WebElement> optList = sel.getOptions();
        for (int i = 0; i < optList.size(); i++) {
            String text = getText(optList.get(i), "Option Text");
            if (text.matches(optionToVerify)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            log.info("Selected Option : " + optionToVerify + " exist");
        } else {
            log.info("Selected Option : " + optionToVerify + " does not exist");
        }
        return exists;
    }


    /*public String takeScreenshot(String methodName, String browserName) {
        String fileName = Utility.getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            System.out.println("Screen Shot Was Stored at: "+ path);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return path;
    }
*/
    public void DoubleClick(WebElement element, String info) {
        Actions action = new Actions(driver);
        action.doubleClick(element);
        System.out.println("Double Clicked on :: " + info);
        action.perform();
    }

    /**
     * Right Click a WebElement
     *
     * @param locator
     */
    public void rightClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        log.info("Double Clicked on :: " + info);
    }

    /**
     * Right click a WebElement and select the option
     *
     * @param elementLocator
     * @param itemLocator
     */
    public void selectItemRightClick(String elementLocator, String itemLocator) {
        WebElement element = getElement(elementLocator, "info");
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        WebElement itemElement = getElement(itemLocator, "info");
        elementClick(itemElement, "Selected Item");
    }

    /**
     * @param key
     */
    public void keyPress(Keys key, String info) {
        Actions action = new Actions(driver);
        action.keyDown(key).build().perform();
        System.out.println("Key Pressed :: " + info);
    }


}
