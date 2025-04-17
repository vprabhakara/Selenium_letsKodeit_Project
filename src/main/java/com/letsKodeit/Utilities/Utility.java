package com.letsKodeit.Utilities;

import com.google.common.collect.Ordering;
import com.letsKodeit.base.CustomDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class Utility {
    private static final Logger log = LogManager.getLogger(Utility.class.getName());
    public static void sleep(Long msec,String info)
    {
        if (info!=null)
        {
            log.info("Wait is--->"+(msec*0.001)+"seconds"+info);
        }
        try {
            Thread.sleep(msec);
        }catch(InterruptedException e)
        {
            e.printStackTrace();

        }
    }

    public static void sleep(Long msec)
    {
        sleep(msec,null);
    }

    public static String getReportName()
    {
        String localDateTime = getCurrentDateTime();
        StringBuilder name  = new StringBuilder()
                               .append("AutomationReport")
                .append(localDateTime).append(".html");

        return name.toString();
    }
    public static String getScreenshotName(String methodName, String browserName) {
        String localDateTime = getCurrentDateTime();
        StringBuilder name = new StringBuilder().append(browserName)
                .append("_")
                .append(methodName)
                .append("_")
                .append(localDateTime)
                .append(".png");
        return name.toString();
    }



    public static int getRandomNumber(int min,int max)
    { int diff = max-min;
        int randomNum = (int)(min+Math.random()*diff);
        log.info("Random number within range "+ min+ " "+ max);
        return randomNum;


    }

    public static int getRandomNumber(int number) {
        return getRandomNumber(1, number);
    }

    public static String getRandomString(int length)
    {
        StringBuilder sbuilder  = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i=0;i<length;i++)
        {  int index = (int)(Math.random()*chars.length());
            sbuilder.append(chars.charAt(index));

        }
        String randomString=sbuilder.toString();
        log.info("Random String with length "+ randomString);
        return randomString;
    }

    public static String getRandomString()
    {
        return getRandomString(10);

    }
    public static String getSimpleDateFormat(String format)
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formattedDate = formatter.format(date);
        log.info("Formatted date for the given date "+ formattedDate);
        return formattedDate;
    }

    public static String getCurrentDateTime()
    {
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "MM/dd/yyyy HH:mm:ss");
        String date = formatter.format(currentDate.getTime()).replace("/", "_");
        date = date.replace(":", "_");
        log.info("Date and Time :: " + date);
        return date;
    }

    public static boolean verifyTextContains(String actualText,String expectedText)
    {
        if(actualText.toLowerCase().contains(expectedText.toLowerCase()))
        {
            log.info("Actual Text From Web Application UI   --> : "+ actualText);
            log.info("Expected Text From Web Application UI --> : "+ expectedText);
            log.info("### Verification Contains !!!");
            return true;
        }else
        {
            log.info("Actual Text From Web Application UI   --> : "+ actualText);
            log.info("Expected Text From Web Application UI --> : "+ expectedText);
            log.info("### Verification DOES NOT Contains !!!");
            return false;

        }

    }

    public static boolean verifyTextMatch(String actualText,String expectedText)
    {
        if(actualText.equalsIgnoreCase(expectedText))
        {
            log.info("Actual Text From Web Application UI   --> : "+ actualText);
            log.info("Expected Text From Web Application UI --> : "+ expectedText);
            log.info("### Verification MATCHED !!!");
            return true;
        }else{
            log.info("Actual Text From Web Application UI   --> : "+ actualText);
            log.info("Expected Text From Web Application UI --> : "+ expectedText);
            log.info("### Verification DOES NOT MATCH !!!");
            return false;
        }


    }

    public static boolean verifyListContains(List<String> actList, List<String> expList)
    {
        int expListSize = expList.size();
        for (int i=0;i<expListSize;i++)
         {
             if(!actList.contains(expList.get(i)))
             {
                 return false;
             }

        }
        log.info("Actual List Contains Expected List !!!");
        return true;

    }

    public static boolean verifyListMatch(List<String> actList,List<String> expList)
    {
        boolean found = false;
        int actListSize  = actList.size();
        int expListSize = expList.size();

        if(actListSize!=expListSize)
            return false;
        for (int i = 0; i < actListSize; i++) {
            found = false;
            for (int j = 0; j < expListSize; j++) {
                if (verifyTextMatch(actList.get(i), expList.get(j))) {
                    found = true;
                    break;
                }
            }
        }
        if (found) {
            log.info("Actual List Matches Expected List !!!");
            return true;
        }
        else {
            log.info("Actual List DOES NOT Match Expected List !!!");
            return false;
        }


    }

    public static boolean verifyItemPresentInList(List<String> actList, String item)
    {
        int actListsize = actList.size();
        for (int i =0;i<actListsize;i++)
        {
            if(!actList.contains(item))
            {
                log.info("Item is NOT present in List !!!");
                return false;

            }


        }
        log.info("Item is present in List !!!");
        return true;

    }


    public static boolean isListAscendingOrder(List<Long> list)
    {
        boolean sorted = Ordering.natural().isOrdered(list);
        return sorted;
    }

}
