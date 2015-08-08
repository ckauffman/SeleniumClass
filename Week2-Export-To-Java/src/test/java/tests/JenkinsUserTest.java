package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjectModel.JenkinsUser;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by thanley on 8/5/2015.
 */
public class JenkinsUserTest {
    public String username;
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUser() throws Exception {
        driver.get(baseUrl + '/');
        JenkinsUser jenkinsUser = new JenkinsUser(driver);
        jenkinsUser.addUser("Test User");
        jenkinsUser.editUser(jenkinsUser.username + "/******", "foo");
        jenkinsUser.deleteUser(jenkinsUser.username + "/****** (update)");

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
