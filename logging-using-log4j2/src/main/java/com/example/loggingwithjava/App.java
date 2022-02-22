package com.example.loggingwithjava;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
        System.out.println("Let's write some logs...");
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
    }
}
