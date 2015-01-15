import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class TestAddProjects {
    private String baseUrl = "http://localhost:8080/";

    public void addProjectByBrowser(String browserVersion){
        //Create a random project name
        String projectName = "project-" + RandomStringUtils.randomAlphabetic(5);;

        //Get the correct browser based on what is passed in 
        RunBrowser runBrowser = new RunBrowser(browserVersion);
        runBrowser.driver.get(baseUrl + "/");
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("Failed to wait: " + e.toString());
        }
        runBrowser.driver.findElement(By.linkText("New Item")).click();
        runBrowser.driver.findElement(By.id("name")).clear();
        runBrowser.driver.findElement(By.id("name")).sendKeys(projectName);
        runBrowser.driver.findElement(By.name("mode")).click();
        runBrowser.driver.findElement(By.id("ok-button")).click();
        // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
        new Select(runBrowser.driver.findElement(By.name("_.compressionLevel"))).selectByVisibleText("System Default");
        runBrowser.driver.findElement(By.xpath("//text()[normalize-space(.)='CVS Projectset']/../input")).click();
        runBrowser.driver.findElement(By.xpath("//text()[normalize-space(.)='This Connection Requires A Password']/../../input")).click();
        runBrowser.driver.findElement(By.name("_.password")).clear();
        runBrowser.driver.findElement(By.name("_.password")).sendKeys("test");
        runBrowser.driver.findElement(By.xpath("//text()[normalize-space(.)='None']/../input")).click();
        runBrowser.driver.findElement(By.xpath("//*[@name='Apply']")).click();
        runBrowser.driver.findElement(By.xpath("//*[@name='Submit']")).click();
        assertEquals(projectName, runBrowser.driver.findElement(By.cssSelector("h1.job-index-headline.page-headline")).getText());

        //Close the browser
        runBrowser.driver.quit();
    }

    @Test
    public void testAddProject() {
        //addProjectByBrowser("IE");
        addProjectByBrowser("Chrome");

    }
}
