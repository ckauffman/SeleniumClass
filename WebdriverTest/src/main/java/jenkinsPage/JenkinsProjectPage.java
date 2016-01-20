package jenkinsPage;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JenkinsProjectPage {
private WebDriver driver;
private String baseUrl;
private boolean acceptNextAlert = true;
private StringBuffer verificationErrors = new StringBuffer();


@FindBy(id = "name")
WebElement txtprojectName;

@FindBy(name = "name")
WebElement txtNameprojectName;

@FindBy(name = "mode")
WebElement modeWhatIsThis;

@FindBy(id = "ok-button")
WebElement okButton;

@FindBy(id = "yui-gen39-button")
WebElement saveButton;

@FindBy(linkText = "New Item")
WebElement linkNewItem;

@FindBy(id = "yui-gen1-button")
WebElement btnAreYouSureAboutRenamingProject;
                     //job-index-headline page-headline
@FindBy(xpath = ".//*[@id='main-panel']/h1")
public WebElement txtProjectPageTitle;




public JenkinsProjectPage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

// Add project to jenkins
public void addProject(String baseProjectName) throws Exception {
	linkNewItem.click();
	for (int second = 0;; second++) {
		if (second >= 60) Assert.fail("timeout");
		try { if (isElementPresent(txtprojectName/*By.id("name")*/)) break; } catch (Exception e) {}
		Thread.sleep(1000);
	}
	txtprojectName.clear();
	txtprojectName.sendKeys(baseProjectName);
	modeWhatIsThis.click();
	okButton.click();
	saveButton.click();
	Assert.assertEquals(driver.getTitle(), baseProjectName + " [Jenkins]");
}

// edit Project
public void editProject(String editProjectName){
	driver.manage().window().maximize(); 
	//.//*[@id='job_Project LnTSo']/td[3]/a
	//WebElement clickProject = driver.findElement(By.xpath(".//*[@id='job_" + editProjectName + "']/td[3]/a"));
	
	// Assume driver is a valid WebDriver instance that
	// has been properly instantiated elsewhere.
	//WebElement element = driver.findElement(By.id("gbqfd"));
	//JavascriptExecutor executor = (JavascriptExecutor)driver;
	//executor.executeScript("arguments[0].click();", clickProject);
	
	driver.findElement(By.linkText("Configure")).click();
	
	txtNameprojectName.sendKeys(editProjectName);
	System.out.println("editProjectName: " + editProjectName);
	//modeWhatIsThis.click();
	//okButton.click();
	saveButton.click();
	btnAreYouSureAboutRenamingProject.click();
	
	
	
	
	//driver.findElement(By.linkText(projectName)).click();
	//driver.findElement(By.linkText(projectName)).click();
	/*try{
		driver.findElement(By.linkText(projectName)).click();
	} catch (Exception e){
		Assert.fail("Unable to find project " + projectName + " full error details: " + e.toString());
	}*/
}

// delete project
public void deleteProject(String ProjectName, String editProjectName){
driver.findElement(By.linkText("Delete Project")).click();
	
	checkAlert();
	
}

// Check to see if Element is present
private boolean isElementPresent(WebElement txtprojectName2) {
	try {
		txtprojectName.isDisplayed();
		return true;
	} catch (NoSuchElementException e) {
		return false;
	}
}
public void checkAlert() {
    try {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    } catch (Exception e) {
        //exception handling
    }
}

}

