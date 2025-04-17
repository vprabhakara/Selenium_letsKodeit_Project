package com.letsKodeit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.letsKodeit.Utilities.Constants;
import com.letsKodeit.Utilities.Utility;


import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance()
    {
        if(extent ==null)
        {
            createInstance();
        }
        return extent;
    }

    public static synchronized ExtentReports createInstance()
    {
        String fileName = Utility.getReportName();
        String reportsDirectory = Constants.REPORTS_DIRECTORY;
        new File(reportsDirectory).mkdir();
        String path = reportsDirectory+fileName;

        ExtentSparkReporter  sparkReporter = new ExtentSparkReporter(path);

        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Run");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(fileName);
        sparkReporter.config().setTimelineEnabled(true);

        extent = new ExtentReports();
        extent.setSystemInfo("Organization","R1RCM");
        extent.setSystemInfo("Automation Framework","Selenium WebDriver");
        extent.attachReporter(sparkReporter);
        return extent;

    }
}
