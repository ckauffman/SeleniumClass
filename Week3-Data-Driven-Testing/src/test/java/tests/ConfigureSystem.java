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
    private String numOfExecutors;
    private String restrictProjectNaming;
    private String namingStrategy;
    private String mavenDefaultSettings;
    private String mavenFilePath;

    @Parameters
    public static Collection testData() throws Exception {
        InputStream spreadsheet = new FileInputStream("C:\\SeleniumProjects\\SeleniumClass\\Week3-Data-Driven-Testing\\Assignment3.xlsx");
        return new ReadSpreadsheetData(spreadsheet).getData();
    }

    //Modify constructor
    public ConfigureSystem(String runSkip, String testScenario, String numOfExecutors, String restrictProjectNaming, String namingStrategy, String mavenDefaultSettings, String mavenFilePath) {
        this.runSkip = runSkip;
        this.testScenario = testScenario;
        this.numOfExecutors = numOfExecutors;
        this.restrictProjectNaming = restrictProjectNaming;
        this.namingStrategy = namingStrategy;
        this.mavenDefaultSettings = mavenDefaultSettings;
        this.mavenFilePath = mavenFilePath;
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
            driver.findElement(By.id("radio-block-0")).click();

        } else if(namingStrategy.equalsIgnoreCase("pattern")){
            driver.findElement(By.id("radio-block-1")).click();

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

            driver.findElement(By.linkText("Manage Jenkins")).click();
            driver.findElement(By.linkText("Configure System")).click();
            if(!numOfExecutors.equals("")) {
                driver.findElement(By.name("_.numExecutors")).clear();
                driver.findElement(By.name("_.numExecutors")).sendKeys(numOfExecutors);
            }
            setCheckbox("cb4", restrictProjectNaming);
            //driver.findElement(By.id("cb4")).click();
            setNamingStrategy(namingStrategy);

            new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText(mavenDefaultSettings);

            if(! mavenFilePath.equalsIgnoreCase("")) {
                driver.findElement(By.name("_.path")).clear();
                driver.findElement(By.name("_.path")).sendKeys(mavenFilePath);
            }
            driver.findElement(By.id("yui-gen10-button")).click();

        }

    }

    //Clean up at the end
    @AfterClass
    public static void tearDown() throws Exception {
        //Close the browser
        driver.quit();

    }
}
