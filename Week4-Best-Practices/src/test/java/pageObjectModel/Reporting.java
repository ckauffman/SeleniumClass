package pageObjectModel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

import static org.junit.Assert.fail;

/**
 * Created by Owner on 12/15/2014.
 */
public class Reporting {
    public String takeScreenshot(WebDriver driver){
        String errorFileName = "c:\\SeleniumProjects\\" + RandomStringUtils.randomAlphabetic(5) + ".png";
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(errorFileName));
        } catch (Exception e){
            fail(e.toString());

        }
        return errorFileName;
    }
}
