package utils;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static builders.DriverBuilds.getDriver;

public class ScreenShotUtils implements MethodRule {

    public static void TakeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
        try {
            FileUtils.copyFile(srcFile, new File("./Screenshots/" + testName + "_" + TimeStamp + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    TakeScreenshot(frameworkMethod.getName());
                    throw t;
                }
            }
        };
    }
}
