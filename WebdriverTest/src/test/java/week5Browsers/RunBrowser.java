package week5Browsers;

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
    public RunBrowser(String driverType) {
        this.driverType = driverType;
        if (driverType.equalsIgnoreCase("IE")) {
            //System.setProperty("webdriver.ie.driver", workingDir + "\\drivers\\IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", "C:\\Softwares\\IEDriverServer.exe");
            //Disable Internet Explorer security errors for IE 9-10
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
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void setEditField(By by, String valueToEnter){
        WebElement fieldToSet = driver.findElement(by);
        //Send keys is very slow in Internet Explorer
        if (driverType.equalsIgnoreCase("IE")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '" + valueToEnter + "'", fieldToSet);
        } else {
            fieldToSet.clear();
            fieldToSet.sendKeys(valueToEnter);
        }

    }

}

