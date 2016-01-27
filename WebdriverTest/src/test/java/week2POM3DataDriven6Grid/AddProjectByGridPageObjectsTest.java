package week2POM3DataDriven6Grid;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jenkinsPage.JenkinsProjectPage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddProjectByGridPageObjectsTest {
private WebDriver driver;
private String baseUrl;
private boolean acceptNextAlert = true;
private StringBuffer verificationErrors = new StringBuffer();
JenkinsProjectPage jenkinsProject;

public String projectName = "Project " + RandomStringUtils.randomAlphabetic(5);
public String editProjectName = RandomStringUtils.randomAlphabetic(5);
public String newProjectName = "Project " + projectName + editProjectName;

@BeforeClass
public void setUp() throws Exception {
	DesiredCapabilities capability = DesiredCapabilities.firefox();
	try {
	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
	} catch (MalformedURLException e) {
		Assert.fail("Error building URL to Hub");
	}
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	driver.get("http://localhost:8080/");
}

// Add project test through grid using firefox browser 
@Test(enabled = false)
public void creatProjectTest() throws Exception {
	jenkinsProject = new JenkinsProjectPage(driver);
	System.out.println("projectName: " + projectName);
	jenkinsProject.addProject(projectName);
	Assert.assertEquals(driver.getTitle(), projectName + " [Jenkins]");
}

@Test(dependsOnMethods = { "creatProjectTest" }, enabled = false)
private void editProjectTest() {
	jenkinsProject.editProject(editProjectName/*jenkinsProject.projectName*/);
	Assert.assertEquals(jenkinsProject.txtProjectPageTitle.getText(), newProjectName);
}

@Test(dependsOnMethods = { "editProjectTest" }, enabled = false)
private void deleteProjectTest() throws Exception {
	jenkinsProject.deleteProject(projectName, editProjectName);
	Assert.assertTrue(driver.findElements(By.linkText("myLinkText")).size() < 1, "assert that project has been deleted.");
	 //Call take screenshot function
    this.takeSnapShot(driver, "C://Users//ckauffman//Documents//GuruPix//test.png") ;
}

@Test
public void creatProjectTestToSearchOnHomePage() throws Exception {
	jenkinsProject = new JenkinsProjectPage(driver);
	System.out.println("projectName: " + projectName);
	jenkinsProject.addProject(projectName);
	Assert.assertEquals(driver.getTitle(), projectName + " [Jenkins]");
}


//Validate that the newly created project is in the list.
@Test(dependsOnMethods = { "creatProjectTestToSearchOnHomePage" }, enabled = true)
public void searchHomePageForNewlyCreatedProjectTestingTheVariousWaysToUseXpathInTables() throws Exception {
	jenkinsProject.linkBackToDashboard.click();
	  this.takeSnapShot(driver, "C://Users//ckauffman//Documents//GuruPix//test.png") ;
	//td[contains(text(),'Nash')]/following-sibling::td/
	
	WebElement newProjectName = driver.findElement(By.xpath(".//*[@id='job_" + projectName + "']/td/a[contains(text(),'" + projectName + "')]"));
	                                                        // "//ul/li[contains(text(),'Apple Mobiles')]/following-sibling::li"
	                                                                                                            //*[@id='job__Run Selenium']/td[4]
	                                                                                                       //span[contains(text(), 'ABZ')]/../following-sibling::section/span[@name='edit']
	String text = newProjectName.getText().toString();
	System.out.println("text: " + text);
	
	
/*	// Grab the table
	WebElement table = driver.findElement(By.id("projectstatus"));
	// Now get all the TR elements from the table
	List<WebElement> allRows = table.findElements(By.tagName("tr"));
	// And iterate over them, getting the cells
	for (WebElement row : allRows) {
	 List<WebElement> cells = row.findElements(By.xpath("./*"));
	 for (WebElement cell : cells) {
		 System.out.println("cells: " + cell.getText());
	 }
	}*/
}


//Validate that the newly created project is in the list.
@Test(dependsOnMethods = { "creatProjectTestToSearchOnHomePage" }, enabled = false)
public void searchHomePageForNewlyCreatedProject() throws Exception {
	jenkinsProject.linkBackToDashboard.click();
	//WebElement newProjectName = driver.findElement(By.xpath(".//*[@id='job_" + projectName + "']/td[3]/a"));  WORKS
	// WebElement newProjectName = driver.findElement(By.xpath(".//*[@id='job_" + projectName + "']/td/a[contains(text(),'" + projectName + "')]")); Best Way so far
	WebElement newProjectName = driver.findElement(By.xpath("//td/a[.='" + projectName + "']")); // This worked as well.
	
	String text = newProjectName.getText().toString();
	System.out.println("text: " + text);
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", newProjectName);
	Assert.assertEquals(driver.getTitle(), projectName + " [Jenkins]");
}




/*@AfterClass
public void tearDown() throws Exception {
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
		Assert.fail(verificationErrorString);
	}
}*/


public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
	 
    //Convert web driver object to TakeScreenshot

    TakesScreenshot scrShot =((TakesScreenshot)webdriver);

    //Call getScreenshotAs method to create image file

            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

            File DestFile=new File(fileWithPath);

            //Copy file at destination

            FileUtils.copyFile(SrcFile, DestFile);

}


private boolean isElementPresent(By by) {
	try {
		driver.findElement(by);
		return true;
	} catch (NoSuchElementException e) {
		return false;
	}
}

}
