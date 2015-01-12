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
        //Add in code from AddProject here

        //Close the browser
        runBrowser.driver.quit();
    }

    @Test
    public void testAddProject() {
        addProjectByBrowser("IE");
        addProjectByBrowser("Chrome");

    }
}
