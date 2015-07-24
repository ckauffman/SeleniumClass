package testsByFeature.jenkinsProject;


import java.util.concurrent.TimeUnit;

import org.junit.*;
import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjectModel.JenkinsProject;

public class JenkinsProjectTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void testProjectBuildSucceeds() throws Exception {
        driver.get("http://localhost:8080/");
        JenkinsProject jenkinsProject = new JenkinsProject(driver);
        jenkinsProject.addProject("Project 1");
        jenkinsProject.buildProject(jenkinsProject.projectName);
    }

    @Test
    public void testProjectBuildFail() throws Exception {
        boolean failOccur = false;
        driver.get("http://localhost:8080/");

        JenkinsProject jenkinsProject = new JenkinsProject(driver);
        jenkinsProject.addProject("Project Fail");

        try {
            jenkinsProject.buildProject("Build Fail");
        } catch (AssertionError e){
            //Expected an error
            failOccur = true;
        }
        if(failOccur == false){
            fail("Did not cause the fail of the project");
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();

        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }



    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
