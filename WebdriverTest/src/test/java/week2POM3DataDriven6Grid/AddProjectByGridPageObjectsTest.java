package week2POM3DataDriven6Grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import jenkinsPage.JenkinsProjectPage;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
@Test
public void creatProjectTest() throws Exception {
	jenkinsProject = new JenkinsProjectPage(driver);
	System.out.println("projectName: " + projectName);
	jenkinsProject.addProject(projectName);
	Assert.assertEquals(driver.getTitle(), projectName + " [Jenkins]");
}

@Test(dependsOnMethods = { "creatProjectTest" })
private void editProjectTest() {
	jenkinsProject.editProject(editProjectName/*jenkinsProject.projectName*/);
	Assert.assertEquals(jenkinsProject.txtProjectPageTitle.getText(), newProjectName);
}

@Test(dependsOnMethods = { "editProjectTest" })
private void deleteProjectTest() {
	jenkinsProject.deleteProject(projectName, editProjectName);
	Assert.assertTrue(driver.findElements(By.linkText("myLinkText")).size() < 1, "assert that project has been deleted.");
}

@AfterClass
public void tearDown() throws Exception {
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
		Assert.fail(verificationErrorString);
	}
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
