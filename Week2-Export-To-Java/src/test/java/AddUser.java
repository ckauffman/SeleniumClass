
//Test
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AddUser {
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
  public void testAddUser() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Jenkins")).click();
    driver.findElement(By.linkText("Credentials")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.linkText("Global credentials"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("Global credentials")).click();
    driver.findElement(By.linkText("Add Credentials")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.name("_.username"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    String userName = "Username " + RandomStringUtils.randomAlphabetic(5);

    driver.findElement(By.name("_.username")).clear();
    driver.findElement(By.name("_.username")).sendKeys(userName);
    driver.findElement(By.name("_.password")).clear();
    driver.findElement(By.name("_.password")).sendKeys("password");
    driver.findElement(By.id("yui-gen1-button")).click();
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
