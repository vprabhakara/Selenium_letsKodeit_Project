package com.letsKodeit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.observer.entity.MediaEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;

public class TestListeners extends BaseTest implements ITestListener {

    private static ExtentReports extentReports = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private static final Logger log = LogManager.getLogger(TestListeners.class.getName());

    public void onStart(ITestContext context)
    {
        log.info("onStart---> Test Tag Name:"+context.getName());
        ITestNGMethod methods[] = context.getAllTestMethods();
        log.info("These methods will be executed ");
        for(ITestNGMethod method:methods)
        {
            log.info(method.getMethodName());
        }
    }

    public void onFinish(ITestContext context)
    {
        log.info("onFinish Test Tag Name ->" + context.getName());
        extentReports.flush();
    }

    public void onTestStart(ITestResult result)
    {
        ExtentTest test = extentReports.createTest(result.getInstanceName() + "::" +
                result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result)
    {
        log.info("onTestSuccess ->Test Method Name "+result.getName());
        String methodName = result.getMethod().getMethodName();
        String logText =  "<b>" + "Test Method " + methodName + " Successful" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS,m);
    }

    public void onTestFailure(ITestResult result)
    {
        log.info("onTestFailure ->Test Method Name "+result.getName());
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details>" + "<summary>" + "<b>" + "<font color=red>" +
                "Exception Occurred: Click to see details: " + "</font>" + "</b>" + "</summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

        String browser = WebDriverFactory.getIntance().getBrowser();
        WebDriver driver = WebDriverFactory.getIntance().getDriver(browser);
        CustomDriver cd= new CustomDriver(driver);
        String path = cd.takeScreenshot(result.getName(),browser);
        try
        {
            extentTest.get().fail("<b>" + "<font color=red>" +
                            "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        }catch(Exception e)
        {
            extentTest.get().fail("Test Method failed, cannot attach screenshot");

        }


        String logText = "<b>" + "Test Method " + methodName + " Failed" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
    }
    public void onTestSkipped(ITestResult result) {
        log.info("onTestSkipped -> Test Method Name: " + result.getName());
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "Test Method " + methodName + " Skipped" + "</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.PASS, m);
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Ignore this
    }


}
