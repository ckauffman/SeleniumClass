package pageObjectModel;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Owner on 11/29/2014.
 */
public class JenkinsProject {
    private WebDriver driver;
    public String projectName;
    private String baseUrl = "http://localhost:8080/";

    public JenkinsProject(WebDriver driver){
        this.driver = driver;
    }

    public void addProject(String baseProjectName) throws InterruptedException{
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("New Item")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.id("name"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.id("name")).clear();
        projectName = baseProjectName + " " + RandomStringUtils.randomAlphanumeric(5);
        driver.findElement(By.id("name")).sendKeys(projectName);
        driver.findElement(By.name("mode")).click();
        driver.findElement(By.id("ok-button")).click();
        driver.findElement(By.id("yui-gen30-button")).click();
        assertEquals(projectName + " [Jenkins]", driver.getTitle());
    }

    public void addProjectComplex(String projectName, String initialSetting1, String initialSetting2, String initialSetting3) throws InterruptedException {

    }

    public void editProject(){
        driver.findElement(By.linkText("Jenkins")).click();

        try {
            driver.findElement(By.linkText(projectName)).click();
        } catch (Exception e){
            Reporting reporting = new Reporting();
            String errorFile = reporting.takeScreenshot(driver);
            fail("Unable to find project " + projectName + ", screenshot at " + errorFile + " full error details: " + e.toString());
        }

    }

    public void buildProject(String projectName){
        //Add code from BuildNow here
        driver.get(baseUrl + "/");
        editProject();
        driver.findElement(By.linkText("Build Now")).click();
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Build History")).click();
        try{
            driver.findElement(By.linkText(projectName)).click();
        } catch (Exception e){
            Reporting reporting = new Reporting();
            String errorFile = reporting.takeScreenshot(driver);
            fail("Unable to find build in build history " + projectName + ", screenshot at " + errorFile + " full error details: " + e.toString());
        }
        assertEquals("Project "+projectName, driver.findElement(By.cssSelector("h1.job-index-headline.page-headline")).getText());

    }

    public void deleteProject(String projectName){

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
