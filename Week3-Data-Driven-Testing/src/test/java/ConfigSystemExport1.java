package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ConfigSystemExport1 {
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
  public void testConfigSystemExport1() throws Exception {
    driver.get(baseUrl + "/credential-store/domain/_/");
    driver.findElement(By.linkText("Jenkins")).click();
    driver.findElement(By.linkText("Manage Jenkins")).click();
    driver.findElement(By.linkText("Configure System")).click();
    driver.findElement(By.name("_.numExecutors")).clear();
    driver.findElement(By.name("_.numExecutors")).sendKeys("3");
    driver.findElement(By.id("cb4")).click();
    driver.findElement(By.id("radio-block-1")).click();
    driver.findElement(By.id("radio-block-0")).click();
    new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText("Settings file in filesystem");
    driver.findElement(By.cssSelector("option[value=\"1\"]")).click();
    driver.findElement(By.name("_.path")).clear();
    driver.findElement(By.name("_.path")).sendKeys("c:\\test");
    driver.findElement(By.id("yui-gen10-button")).click();
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
