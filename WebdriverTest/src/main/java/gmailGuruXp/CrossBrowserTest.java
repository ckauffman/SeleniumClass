package gmailGuruXp;

	import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
	//import org.openqa.selenium.WebrunBrowser.driver;
	//import org.openqa.selenium.firefox.FirefoxrunBrowser.driver;
	import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

	public class CrossBrowserTest {
	    private String baseUrl = "http://localhost:8080";

	    public void addProjectByBrowser(String browserVersion){
	        //Create a random project name
	        String projectName = "project-" + RandomStringUtils.randomAlphabetic(5);;

	        //Get the correct browser based on what is passed in 
	        RunBrowser runBrowser = new RunBrowser(browserVersion);
	        //Add in code from AddProject here
	        runBrowser.driver.get(baseUrl + "/");
	        runBrowser.driver.findElement(By.linkText("New Item")).click();
	        runBrowser.driver.findElement(By.id("name")).clear();
	        runBrowser.driver.findElement(By.id("name")).sendKeys(projectName);
	        runBrowser.driver.findElement(By.name("mode")).click();
	        runBrowser.driver.findElement(By.id("ok-button")).click();
	        // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
	        new Select(runBrowser.driver.findElement(By.name("_.compressionLevel"))).selectByVisibleText("System Default");
	        System.out.println("Almost done running.  Why isn't Jenkins showing up in the browser?????");
	        //Select CVS Projectset radio button
	        runBrowser.driver.findElement(By.xpath("//label[contains(text(), 'CVS Projectset')]/input[@name='scm']")).click();
/*	        runBrowser.driver.findElement(By.id("cb17")).click();
	        // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
	        //Select None radio button
	        runBrowser.driver.findElement(By.xpath("//label [contains(text(), 'None')]/input[@name='scm']")).click();
	        //Click the apply button
	        runBrowser.driver.findElement(By.xpath("//button[contains(text(), 'Apply')]")).click();
	        //Click the save button
	        runBrowser.driver.findElement(By.xpath("//button[contains(text(), 'Save')]")).click();
	        assertEquals("Project " + projectName, runBrowser.driver.findElement(By.cssSelector("h1.job-index-headline.page-headline")).getText());
	        //Close the browser
	        runBrowser.driver.quit();*/
	    }

	    @Test
	    public void testAddProject() {
	        addProjectByBrowser("IE");
	        addProjectByBrowser("Chrome");

	    }
	}
