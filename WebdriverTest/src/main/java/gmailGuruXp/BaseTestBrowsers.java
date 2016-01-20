package gmailGuruXp;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseTestBrowsers {
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
    public void testAddProject() throws Exception {
        driver.get("http://localhost:8080");
        driver.findElement(By.linkText("New Item")).click();
        driver.findElement(By.cssSelector("b")).click();
        driver.findElement(By.linkText("Configure")).click();
        driver.findElement(By.name("description")).clear();
        driver.findElement(By.name("description")).sendKeys("New Description");

        driver.findElement(By.id("radio-block-0")).click();
        driver.findElement(By.id("radio-block-1")).click();

        driver.findElement(By.name("_.cvsRoot")).clear();
        driver.findElement(By.name("_.cvsRoot")).sendKeys("test");
        new Select(driver.findElement(By.cssSelector("div[name=\"repositoryItems\"] > table > tbody > tr > td.setting-main > select.setting-input.dropdownList"))).selectByVisibleText("Branch");

        driver.findElement(By.id("radio-block-0")).click();

        driver.findElement(By.id("yui-gen30-button")).click();

        driver.quit();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
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
