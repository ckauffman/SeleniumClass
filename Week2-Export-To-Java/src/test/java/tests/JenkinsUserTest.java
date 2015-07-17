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
 * Created by Magnito on 11/30/2014.
 */
public class JenkinsUserTest {
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
        driver.get(baseUrl + "/");
        //add in code to create the JenkinsUser object

        JenkinsUser user = new JenkinsUser(driver);

        //add in code to Add a user
        user.addUser("baseuser");

        //add in code to Edit a user
        user.editUser(user.fullUsername + "/******", "foo");

        //add in code to Delete a user
        user.deleteUser(user.fullUsername + "/******");
    }

    @After
   /* public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }*/

    public void Ending() {
        try{
            driver.close();
            Thread.sleep(3000);
        }
        catch(Exception b){
            b.getMessage();
        }
    }
}
