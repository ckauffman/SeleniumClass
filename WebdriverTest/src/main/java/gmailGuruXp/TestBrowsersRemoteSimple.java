package gmailGuruXp;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.Thread.sleep;
import static org.junit.Assert.fail;

public class TestBrowsersRemoteSimple {

    @Test
    public void testProject() {

        //WebDriver driver = new FirefoxDriver();
        WebDriver driver =null;
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);

        } catch (MalformedURLException e) {
            fail("Error building URL to Hub");
        }

        driver.get("http://localhost:8080");
        driver.findElement(By.linkText("New Item")).click();
        try {
            sleep(5000);
        } catch (Exception e){

        }
        driver.findElement(By.cssSelector("b")).click();
        driver.findElement(By.linkText("Configure")).click();
        driver.findElement(By.name("description")).clear();
        driver.findElement(By.name("description")).sendKeys("New Description");

        driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        //driver.findElement(By.id("yui-gen30-button")).click();
        driver.quit();


    }

}
