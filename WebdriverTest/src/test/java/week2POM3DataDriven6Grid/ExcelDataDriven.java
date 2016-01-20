package week2POM3DataDriven6Grid;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import util.ReadSpreadsheetData;

@RunWith(value = Parameterized.class)
public class ExcelDataDriven {
	public static WebDriver driver;
	private String runSkip;
	private String testScenario;
	private String discardOldBuilds;
	private String daysToKeepBuilds;
	private String maxNumOfBuildsToKeep;
	private String sourceCodeManagement;

	// @SuppressWarnings("rawtypes")
	@Parameters
	public static Collection<?> testData() throws Exception {
		InputStream spreadsheet = new FileInputStream("Week3-DataDriven.xlsx");
		return new ReadSpreadsheetData(spreadsheet).getData();
	}

	// Constructor to populate data
	public ExcelDataDriven(String runSkip, String testScenario,
			String discardOldBuilds, String daysToKeepBuilds,
			String maxNumOfBuildsToKeep, String sourceCodeManagement) {
		this.testScenario = testScenario;
		this.discardOldBuilds = discardOldBuilds;
		this.daysToKeepBuilds = daysToKeepBuilds;
		this.maxNumOfBuildsToKeep = maxNumOfBuildsToKeep;
		this.runSkip = runSkip;
		this.sourceCodeManagement = sourceCodeManagement;
	}

	// Setup prerequisites for test by initializing firefox driver
	@BeforeClass
	public static void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}


	/*
	 * clicking on checkboxes, radio buttons specified by use of data driving 
	 */
	@Test
	public void testDiscardOldBuilds() throws Exception {
		if (runSkip.equalsIgnoreCase("x")) {
			// Set the initial state
			driver.get("http://localhost:8080");
			driver.findElement(By.linkText("New Item")).click();
			driver.findElement(By.name("name")).clear();
			String projectName = testScenario + " "
					+ RandomStringUtils.randomAlphabetic(5);
			System.out.println("projectName: " + projectName);
			driver.findElement(By.name("name")).sendKeys(projectName);
			driver.findElement(By.name("mode")).click();
			driver.findElement(By.id("ok-button")).click();

			setCheckbox("cb7", discardOldBuilds);
			if (discardOldBuilds.equalsIgnoreCase("check")) {
				if (!daysToKeepBuilds.equals("")) {
					driver.findElement(By.name("_.daysToKeepStr")).clear();
					driver.findElement(By.name("_.daysToKeepStr")).sendKeys(
							daysToKeepBuilds);
				}
				if (!maxNumOfBuildsToKeep.equals("")) {
					driver.findElement(By.name("_.numToKeepStr")).clear();
					driver.findElement(By.name("_.daysToKeepStr")).sendKeys(
							maxNumOfBuildsToKeep);
				}
			}
			setSourceCodeManagement(sourceCodeManagement);
			driver.findElement(By.id("yui-gen39-button")).click();

			Assert.assertEquals(projectName + " [Jenkins]", driver.getTitle());
		}
	}

	// Close browser
	 @AfterClass public static void tearDown() throws Exception {
		driver.quit();
	
	}

	
	 /**
	  * Click checkbox depending on specified in data driven excel sheet
	  * @param checkboxName
	  * @param valueToSet
	  */
	private void setCheckbox(String checkboxName, String valueToSet) {
		if (!valueToSet.equalsIgnoreCase("")) {

			WebElement checkbox = driver.findElement(By.id(checkboxName));
			boolean checkboxChecked = false;
			// Determine the current state of the checkbox
			if (checkbox.getAttribute("checked") == null) {
				checkboxChecked = false;
			} else if (checkbox.getAttribute("checked")
					.equalsIgnoreCase("true")) {
				checkboxChecked = true;
			} else {
				checkboxChecked = false;
			}

			if (checkboxChecked && valueToSet.equalsIgnoreCase("uncheck")) {
				// It should be unchecked, uncheck it
				checkbox.click();
			} else if (!checkboxChecked && valueToSet.equalsIgnoreCase("check")) {
				// value is unchecked, if it should be checked, click it
				checkbox.click();
			}
		}
	}

	/**
	 * Check radio button depending on specified in data driven excel sheet
	 * @param sourceCodeManagement
	 */
	private void setSourceCodeManagement(String sourceCodeManagement) {
		if (sourceCodeManagement.equalsIgnoreCase("None")) {
			driver.findElement(By.id("radio-block-0")).click();
		} else if (sourceCodeManagement.equalsIgnoreCase("CVS")) {
			driver.findElement(By.id("radio-block-1")).click();
		} else if (sourceCodeManagement.equalsIgnoreCase("")) {
			// do nothing
		} else {
			Assert.fail("UNknown naming strategy: " + sourceCodeManagement);
		}
	}
	
}