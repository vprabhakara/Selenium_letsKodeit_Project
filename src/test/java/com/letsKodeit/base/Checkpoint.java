package com.letsKodeit.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

public class Checkpoint {
    public static HashMap<String,String> resultMap = new HashMap<String,String>();
    private static String PASS="PASS";
    private static String FAIL= "FAIL";
    private static final Logger log = LogManager.getLogger(Checkpoint.class.getName());
    public static void clearHashMap() {
        System.out.println("Clears Hashmap");
        resultMap.clear();
    }

    /*Set the status of ResultMap*/
    private static void setStatus(String mapKey,String status)
    {
        resultMap.put(mapKey,status);
        log.info(mapKey + " :-> " + resultMap.get(mapKey));
    }

    public static void mark(String testName,boolean result, String resultMessage)
    {
        testName = testName.toLowerCase();
        String mapKey = testName+"."+resultMessage;
                try
        {
            if(result)
                setStatus(mapKey,PASS);
            else
                setStatus(mapKey,FAIL);

        }catch (Exception e)
                {
                    System.out.println("Exception Occured");
                    setStatus(mapKey,FAIL);
                    e.printStackTrace();
                }
    }
    public static void markFinal(String testName,boolean result, String resultMessage)
    {
        testName = testName.toLowerCase();
        String mapKey = testName+"."+resultMessage;
        try
        {
            if(result)
                setStatus(mapKey,PASS);
            else
                setStatus(mapKey,FAIL);

        }catch (Exception e)
        {
            log.error("Exception Occured");
            setStatus(mapKey,FAIL);
            e.printStackTrace();
        }
        ArrayList<String> resultlist = new ArrayList<String>();
        for( String key: resultMap.keySet())
        {
            resultlist.add(resultMap.get(key));
        }
        for(int i=0;i<resultlist.size();i++) {
            if (resultlist.contains(FAIL)) {
                log.info("Test Method Failed");
                Assert.assertTrue(false);
            } else {
                log.info("Test Method succeeded");
                Assert.assertTrue(true);

            }
        }




    }

}
