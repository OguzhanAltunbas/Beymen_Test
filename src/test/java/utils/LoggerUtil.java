package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class LoggerUtil {
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    public static void info(String message) {

        logger.info(message);
    }

    public static void assertfailWithLogging(String message) {
        logger.error(message);
        Assert.fail();
    }

    public static void assertEquals(String actual, String expected) {
        info("Expected text: " + expected + "\nActual text: " + actual);
        Assert.assertEquals(actual, expected);
    }
}
