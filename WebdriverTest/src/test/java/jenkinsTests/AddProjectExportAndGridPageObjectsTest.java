package jenkinsTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import jenkinsPage.JenkinsProjectPage;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddProjectExportAndGridPageObjectsTest {
private WebDriver driver;
private String baseUrl;
private boolean acceptNextAlert = true;
private StringBuffer verificationErrors = new StringBuffer();

@BeforeMethod
public void setUp() throws Exception {
	//driver = new FirefoxDriver();
	baseUrl = "http://localhost:8080/";
	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

@Test
public void testExportToJava() throws Exception {
	
	DesiredCapabilities capability = DesiredCapabilities.firefox();
	try {
	//WebDriver driver = new RemoteWebDriver(capability);
	driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
	} catch (MalformedURLException e) {
		Assert.fail("Error building URL to Hub");
	}
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	driver.get("http://localhost:8080/");
	JenkinsProjectPage jenkinsProject = new JenkinsProjectPage(driver);
	jenkinsProject.addProject("Project 1");
	jenkinsProject.editProject(jenkinsProject.projectName);
	Assert.assertEquals(driver.getTitle(), jenkinsProject.projectName + " [Jenkins]");
}



@AfterMethod
public void tearDown() throws Exception {
/*	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
		Assert.fail(verificationErrorString);
	}*/
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
