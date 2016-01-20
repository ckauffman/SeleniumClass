package gmailGuruXp;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JUnitDataDriven {
	WebDriver driver;
	String baseUrl;
	private String testScenario;
	private String discardOldBuilds;
	private String daysToKeepBuilds;
	private String maxNumOfBuildsToKeep;


	///////////////////////
	// continue with DD video 2.
	////////////////////////


	/*@Parameters
public static Collection testData() {
	return Arrays.asList(
			new Object [][] {
					{"Keep all builds", "Uncheck", "", ""},
					{"Keep Days and Num of Builds", "Check", "5", "15"},
					{"Keep Days Only", "Check", "6", ""},
					{"Keep Builds Only", "Check", "", "10"}
			}
			);
	}        */

	//Constructor to populate data
	public JUnitDataDriven(String testScenario, String discardOldBuilds, String daysToKeepBuilds, String maxNumOfBuildsToKeep) {
		this.testScenario = testScenario;
		this.discardOldBuilds = discardOldBuilds;
		this.daysToKeepBuilds = daysToKeepBuilds;
		this.maxNumOfBuildsToKeep = maxNumOfBuildsToKeep;
	}
			

	@BeforeMethod
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		//baseUrl = "http://localhost:8080/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testDiscardOldBuilds() throws Exception {
		// Set the initial state
		driver.get("http://localhost:8080");
		driver.findElement(By.id("NewItem")).click();
		driver.findElement(By.id("name")).clear();
		String projectName = testScenario + " "
				+ RandomStringUtils.randomAlphabetic(5);
		driver.findElement(By.id("name")).sendKeys(projectName);
		driver.findElement(By.name("mode")).click();
		driver.findElement(By.id("ok-button")).click();
		if (discardOldBuilds.equalsIgnoreCase("check")) {
			driver.findElement(By.id("cb6")).click();
			driver.findElement(By.name("_.daysToKeepStr")).clear();
			driver.findElement(By.name("_.daysToKeepStr")).sendKeys(
					daysToKeepBuilds);
			driver.findElement(By.name("_.numToKeepStr")).clear();
			driver.findElement(By.name("_.daysToKeepStr")).sendKeys(
					maxNumOfBuildsToKeep);
		}
		driver.findElement(By.id("yui-gen30-button")).click();

		Assert.assertEquals(projectName + " [Jenkins]", driver.getTitle());
	}

	/*@AfterClass
	public static void tearDown() throws Exception {

	}*/
}
