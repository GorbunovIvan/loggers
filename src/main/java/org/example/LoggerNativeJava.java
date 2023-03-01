package org.example;

import java.io.IOException;
import java.util.logging.*;

public class LoggerNativeJava {
    private static final Logger loggerGlobal = Logger.getGlobal();
    private static final Logger logger = Logger.getLogger(LoggerNativeJava.class.getName()); // name in 'getLogger()' can be any

    public static void main(String[] args) throws IOException {
        loggerGlobal.info("log global");
        logger.info("my log\n");

        logger.log(Level.INFO, "================== DIFFERENT LEVELS: ==================\n");

        // Not all levels will be output to console because of the settings
        // in file 'C:\Users\gorbu\.jdks\openjdk-18.0.2.1\conf\logging.properties'

        logger.log(Level.FINEST, "finest"); // (300) specialized developer information
        logger.log(Level.FINER, "finer"); // (400) detailed developer information
        logger.log(Level.FINE, "fine"); // (500) general developer information
        logger.log(Level.CONFIG, "config"); // (700) configuration information
        logger.log(Level.INFO, "info"); // (800) general information
        logger.log(Level.WARNING, "warning"); // (900) potential problem
        logger.log(Level.SEVERE, "severe"); // (1000) represents serious failure
        // Some special Log Levels:
        logger.log(Level.OFF, "off"); // (max int) turns off the logging
        logger.log(Level.ALL, "all"); // (min int) captures everything

//        // the same as above;
//        logger.finest( "finest");
//        logger.finer("finer");
//        logger.fine("fine");
//        logger.config("config");
//        logger.info("info");
//        logger.warning("warning");
//        logger.severe("severe");

        logger.log(Level.INFO, "================== LOGGING IN FILE: ==================\n");

        FileHandler fileHandler = new FileHandler();
//        logger.setUseParentHandlers(false); // will not log in console (will log only in file)
        logger.addHandler(fileHandler);
        logger.info("this log must be in file 'C:\\Users\\gorbu\\java0.log'");

        // removing logging in file
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);
        logger.removeHandler(fileHandler);

        logger.log(Level.INFO, "================== FILTERING LOGGING: ==================\n");

        // 'logger.setFilter()' works too
        consoleHandler.setFilter(record -> !record.getMessage().endsWith("(not for outputting)"));
        logger.info("this message will not be logged (not for outputting)");
        logger.info("this message will be logged");

        logger.log(Level.INFO, "================== FORMATTING LOGS: ==================\n");

        consoleHandler.setFormatter(new MyFormatter());
        logger.info("formatted log"); // works not very good
    }
}

class MyFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getLevel() + " - " + record.getMessage();
    }
}