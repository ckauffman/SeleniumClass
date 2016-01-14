package jenkinsPage;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JenkinsProjectPage {
private WebDriver driver;
private String baseUrl;
private boolean acceptNextAlert = true;
private StringBuffer verificationErrors = new StringBuffer();
public String projectName = "Project " + RandomStringUtils.randomAlphabetic(5);

@FindBy(id = "name")
WebElement txtprojectName;

@FindBy(name = "mode")
WebElement modeWhatIsThis;

@FindBy(id = "ok-button")
WebElement okButton;

@FindBy(id = "yui-gen39-button")
WebElement saveButton;

@FindBy(linkText = "New Item")
WebElement linkNewItem;


public JenkinsProjectPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}


public void addProject(String baseProjectName) throws Exception {
	//driver.get("http://localhost:8080/");
	//driver.findElement(By.linkText("New Item")).click();
	linkNewItem.click();
	//driver.findElement(By.linkText("New Item")).click();
	for (int second = 0;; second++) {
		if (second >= 60) Assert.fail("timeout");
		try { if (isElementPresent(txtprojectName/*By.id("name")*/)) break; } catch (Exception e) {}
		Thread.sleep(1000);
	}
	
	//driver.findElement(By.id("name")).clear();
	txtprojectName.clear();
	//driver.findElement(By.id("name")).sendKeys(projectName);
	txtprojectName.sendKeys(projectName);
	//driver.findElement(By.name("mode")).click();
	modeWhatIsThis.click();
	//driver.findElement(By.id("ok-button")).click();
	okButton.click();
	//driver.findElement(By.id("yui-gen39-button")).click();
	saveButton.click();
	Assert.assertEquals(driver.getTitle(), projectName + " [Jenkins]");
}

public void editProject(String projectName){
	try{
		driver.findElement(By.linkText(projectName)).click();
	} catch (Exception e){
		Assert.fail("Unable to find project " + projectName + " full error details: " + e.toString());
	}
}

public void deleteProject(String projectName){
	
}


private boolean isElementPresent(WebElement txtprojectName2) {
	try {
		txtprojectName.isDisplayed();
		return true;
	} catch (NoSuchElementException e) {
		return false;
	}
}

}

