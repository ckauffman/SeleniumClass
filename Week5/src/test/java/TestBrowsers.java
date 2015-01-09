import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class TestBrowsers {
    WebDriver driver;
    public void startBrowser(String driverType){
        if(driverType.equalsIgnoreCase("IE")){
            System.setProperty("webdriver.ie.driver", "C:\\SeleniumProjects\\SeleniumClass\\Week5\\drivers\\IEDriverServer.exe");
            //Disable Internet Explorer security errors
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            driver = new InternetExplorerDriver(desiredCapabilities);
        } else if(driverType.equalsIgnoreCase("Chrome")){
            System.setProperty("webdriver.chrome.driver", "C:\\SeleniumProjects\\SeleniumClass\\Week5\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://localhost:8080");
    }

    @Test
    public void testBrowsers(){
        startBrowser("IE");
        driver.findElement(By.linkText("First Project")).click();
        driver.findElement(By.cssSelector("b")).click();
        driver.quit();

//        startBrowser("Chrome");
//        driver.findElement(By.linkText("First Project")).click();
//        driver.findElement(By.cssSelector("b")).click();
//        driver.quit();
    }
}
