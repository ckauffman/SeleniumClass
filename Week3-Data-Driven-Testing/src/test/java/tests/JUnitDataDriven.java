package tests;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@RunWith(value = Parameterized.class)
public class JUnitDataDriven {

    private static WebDriver driver;
    private static StringBuffer verificationErrors = new StringBuffer();

    private String testScenario;
    private String discardOldBuilds;
    private String daysToKeepBuilds;
    private String maxNumOfBuildsToKeep;


    @Parameters
    public static Collection testData() {
        return Arrays.asList(
                new Object[][] {
                        {"Keep all builds", "Uncheck", "", ""},
                        {"Keep Days and Num of Builds", "Check", "5", "15"},
                        {"Keep Days Only", "Check", "6", ""},
                        {"Keep Builds Only", "Check", "", "10"}
                }
        );
    }

    //Constructor to populate data
    public JUnitDataDriven(String testScenario, String discardOldBuilds, String daysToKeepBuilds, String maxNumOfBuildsToKeep) {
        this.testScenario = testScenario;
        this.discardOldBuilds = discardOldBuilds;
        this.daysToKeepBuilds = daysToKeepBuilds;
        this.maxNumOfBuildsToKeep = maxNumOfBuildsToKeep;
    }

    //Run this once
    @BeforeClass
    public static void setUp() throws Exception {
        // Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test
    public void testDiscardOldBuilds() throws Exception {
        //Set the initial state
        driver.get("http://localhost:8080");
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.id("name")).clear();
        String projectName = testScenario  + " " + RandomStringUtils.randomAlphabetic(5);
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.name("mode")).click();
        driver.findElement(By.id("ok-button")).click();
        if(discardOldBuilds.equalsIgnoreCase("check")) {
            driver.findElement(By.id("cb6")).click();
            driver.findElement(By.name("_.daysToKeepStr")).clear();
            driver.findElement(By.name("_.daysToKeepStr")).sendKeys(daysToKeepBuilds);
            driver.findElement(By.name("_.numToKeepStr")).clear();
            driver.findElement(By.name("_.numToKeepStr")).sendKeys(maxNumOfBuildsToKeep);
        }
        driver.findElement(By.id("yui-gen30-button")).click();

        assertEquals(projectName + " [Jenkins]", driver.getTitle());

    }

    //Clean up at the end
    @AfterClass
    public static void tearDown() throws Exception {
        //Close the browser
        driver.quit();

    }
}
