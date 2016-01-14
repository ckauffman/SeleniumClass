package gmailGuruTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginGmailMultipleSessions {
    @Test   
    public void executSessionOne(){
            //First session of WebDriver
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            //Goto guru99 site
            driver.get("https://www.gmail.com");
            //find user name text box and fill it
            driver.findElement(By.id("Email")).sendKeys("TRcomission@gmail.com");
        }
         
    @Test   
        public void executeSessionTwo(){
            //Second session of WebDriver
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        WebDriver driver = new ChromeDriver();
            //Goto guru99 site
        driver.get("https://www.gmail.com");
        //find user name text box and fill it
        driver.findElement(By.id("Email")).sendKeys("TR");
        }
         
    @Test   
        public void executSessionThree(){
            //Third session of WebDriver
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        WebDriver driver = new ChromeDriver();
            //Goto guru99 site
        driver.get("https://www.gmail.com");
        //find user name text box and fill it
        driver.findElement(By.id("Email")).sendKeys("comission@gmail.com");
        }        
}

// RandomStringUtils.randomAlphabetic(5);
