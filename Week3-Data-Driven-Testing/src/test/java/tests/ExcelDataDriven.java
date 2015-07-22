package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.*;

import java.lang.String;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(value = Parameterized.class)
public class ExcelDataDriven {
    private static WebDriver driver;
    private static StringBuffer verificationErrors = new StringBuffer();

    private String runSkip;
    private String testScenario;
    private String discardOldBuilds;
    private String daysToKeepBuilds;
    private String maxNumOfBuildsToKeep;
    private String sourceCodeManagement;

    @Parameters
    public static Collection testData() throws Exception {
        InputStream spreadsheet = new FileInputStream("C:\\Users\\cdorsey\\Documents\\workspace\\seleniumclass\\Week3-Data-Driven-Testing\\Week3-DataDriven.xlsx");
        return new ReadSpreadsheetData(spreadsheet).getData();
    }

    //Constructor to populate data
    public ExcelDataDriven(String runSkip, String testScenario, String discardOldBuilds, String daysToKeepBuilds, String maxNumOfBuildsToKeep, String sourceCodeManagement) {
        this.testScenario = testScenario;
        this.discardOldBuilds = discardOldBuilds;
        this.daysToKeepBuilds = daysToKeepBuilds;
        this.maxNumOfBuildsToKeep = maxNumOfBuildsToKeep;
        this.runSkip = runSkip;
        this.sourceCodeManagement = sourceCodeManagement;
    }

    //Run this once
    @BeforeClass
    public static void setUp() throws Exception {
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }


    private void setCheckbox(String checkboxName, String valueToSet) {
        if (!valueToSet.equalsIgnoreCase("")) {
            //driver.findElement(By.id("cb7")).click();

            WebElement checkbox = driver.findElement(By.id(checkboxName));
            boolean checkboxChecked = false;

            //Determine the current state of the checkbox
            if (checkbox.getAttribute("checked") == null) {
                checkboxChecked = false;
            } else if (checkbox.getAttribute("checked").equalsIgnoreCase("true")) {
                checkboxChecked = true;
            } else {
                checkboxChecked = false;
            }

            if (checkboxChecked && valueToSet.equalsIgnoreCase("uncheck")) {
                //It should be unchecked, uncheck it
                checkbox.click();
            } else if (!checkboxChecked && valueToSet.equalsIgnoreCase("check")) {
                //value is unchecked, if it should be checked, click it
                checkbox.click();
            }
        }
    }


    private void setSourceCodeManagement(String sourceCodeManagement) {
        if (sourceCodeManagement.equalsIgnoreCase("None")) {
            driver.findElement(By.id("radio-block-0")).click();
        } else if (sourceCodeManagement.equalsIgnoreCase("CVS")) {
            driver.findElement(By.id("radio-block-1")).click();
        } else if (sourceCodeManagement.equalsIgnoreCase("")) {
            //do nothing
        } else {
            fail("Unknown Source Code Management: " + sourceCodeManagement);
        }
    }

    @Test
    public void testDiscardOldBuilds() throws Exception {
        if (runSkip.equalsIgnoreCase("x")) {

            //Set the initial state
            driver.get("http://localhost:8080");
            driver.findElement(By.linkText("New Item")).click();
            driver.findElement(By.id("name")).clear();
            String projectName = testScenario + " " + RandomStringUtils.randomAlphabetic(5);
            driver.findElement(By.id("name")).sendKeys(projectName);
            driver.findElement(By.name("mode")).click();
            driver.findElement(By.id("ok-button")).click();

            setCheckbox("cb6", discardOldBuilds); // Jenkins version discrepancy. use cb6 instead of cb7

            if (discardOldBuilds.equalsIgnoreCase("check")) {
                if (!daysToKeepBuilds.equals("")) {
                    driver.findElement(By.name("_.daysToKeepStr")).clear();
                    driver.findElement(By.name("_.daysToKeepStr")).sendKeys(daysToKeepBuilds);
                }
                if (!maxNumOfBuildsToKeep.equals("")) {
                    driver.findElement(By.name("_.numToKeepStr")).clear();
                    driver.findElement(By.name("_.numToKeepStr")).sendKeys(maxNumOfBuildsToKeep);
                }
            }

            setSourceCodeManagement(sourceCodeManagement);

            driver.findElement(By.id("radio-block-0")).click();
            driver.findElement(By.id("yui-gen30-button")).click(); // Jenkins version discrepancy. use yui-gen30-button instead of yui-gen38-button

            assertEquals(projectName + " [Jenkins]", driver.getTitle());
        }

    }

    //Clean up at the end
    @AfterClass
    public static void tearDown() throws Exception {
        //Close the browser
        driver.quit();
    }
}
