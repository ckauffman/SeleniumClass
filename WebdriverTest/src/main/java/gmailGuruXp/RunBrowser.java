package gmailGuruXp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class RunBrowser {
    public WebDriver driver;
    private String driverType = "";
    String workingDir = System.getProperty("user.dir");
    
    
    /**
     * Constructor setting local driverType variable to browser to run test on.
     * @param driverType
     */
    public RunBrowser(String driverType) {
        this.driverType = driverType;
        
        if (driverType.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver", workingDir + "\\drivers\\IEDriverServer.exe");
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            driver = new InternetExplorerDriver(desiredCapabilities);
     
        } else if (driverType.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Softwares\\chromedriver.exe");
           // System.setProperty("webdriver.chrome.driver", workingDir + "\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            
        } else {
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
}

