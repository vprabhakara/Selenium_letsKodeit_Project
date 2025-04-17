package com.letsKodeit.overview;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingDemo {
    private static final Logger log = LogManager.getLogger(LoggingDemo.class.getName());

    public static void main(String[] args)
    {
        log.trace("Trace message logged");
        log.debug("Debug message logged");
        log.info("INFO message logged");
        log.error("ERROR message logged");
        log.fatal("FATAL message logged");
    }
}
