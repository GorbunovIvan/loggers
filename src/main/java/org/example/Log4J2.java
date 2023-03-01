package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4J2 {
    // file with settings - /resources/log4j2.xml
    private static final Logger logger = LoggerFactory.getLogger(Log4J2.class);

    public static void main(String[] args) {
        logger.trace("all");
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

        // works faster than concatenation, because will concat only if this logging is enabled
        logger.info("log in class '{}'", Log4J2.class.getName());
    }
}
