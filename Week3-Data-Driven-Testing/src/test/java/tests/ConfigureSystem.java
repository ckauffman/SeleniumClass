package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

@RunWith(value = Parameterized.class)
public class ConfigureSystem {
    private static WebDriver driver;
    private static StringBuffer verificationErrors = new StringBuffer();

    //Replace variables here
    private String runSkip;
    private String testScenario;
    private String discardOldBuilds;
    private String daysToKeepBuilds;
    private String maxNumOfBuildsToKeep;
    private String variable4;
    private String variable5;

    @Parameters
    public static Collection testData() throws Exception {
        InputStream spreadsheet = new FileInputStream("C:\\SeleniumProjects\\Week3-Data-Driven-Testing\\Week3-DataDriven.xlsx");
        return new ReadSpreadsheetData(spreadsheet).getData();
    }

    //Modify constructor
    public ConfigureSystem(String runSkip, String testScenario, String discardOldBuilds, String daysToKeepBuilds, String maxNumOfBuildsToKeep, String variable4, String variable5) {
        this.runSkip = runSkip;
        this.testScenario = testScenario;
        this.discardOldBuilds = discardOldBuilds;
        this.daysToKeepBuilds = daysToKeepBuilds;
        this.maxNumOfBuildsToKeep = maxNumOfBuildsToKeep;
        this.variable4 = variable4;
        this.variable5 = variable5;
    }

    //Run this once
    @BeforeClass
    public static void setUp() throws Exception {
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    private void setCheckbox(String checkboxName, String valueToSet){
        if(!valueToSet.equalsIgnoreCase("")) {
            WebElement checkbox = driver.findElement(By.id(checkboxName));
            if (checkbox.getAttribute("checked").equalsIgnoreCase("true")) {
                //value is already checked, if it should be unchecked, click it
                if (valueToSet.equalsIgnoreCase("uncheck")) {
                    checkbox.click();
                }
            } else {
                //value is unchecked, if it should be checked, click it
                if (valueToSet.equalsIgnoreCase("check")) {
                    checkbox.click();
                }
            }
        }
    }

    private void setNamingStrategy(String namingStrategy){
        if(namingStrategy.equalsIgnoreCase("default")){
            //Enter the radio button for clicking default

        } else if(namingStrategy.equalsIgnoreCase("pattern")){
            //Enter the radio button for clicking pattern

        } else if(namingStrategy.equalsIgnoreCase("")){
            //do nothing
        } else {
            fail("Unknown naming strategy: " + namingStrategy);
        }
    }


    @Test
    public void testConfigureSystem() throws Exception {
        if(runSkip.equalsIgnoreCase("x")){
            //Set the initial state
            driver.get("http://localhost:8080");

            //Copy in code here

        }

    }

    //Clean up at the end
    @AfterClass
    public static void tearDown() throws Exception {
        //Close the browser
        driver.quit();

    }
}
