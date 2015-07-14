package pageObjectModel;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.fail;

/**
 * Created by cdorsey on 7/14/2015.
 */
public class JenkinsUser {
    private WebDriver driver;
    private String userName;

    public JenkinsUser(WebDriver driver) {
        this.driver = driver;
    }

    public void addUser(String userName) throws InterruptedException {

        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();

        for (int second = 0;; second++) {

            if (second >= 60) fail("timeout");

            try {
                if (isElementPresent(By.linkText("Global credentials"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText("Add Credentials")).click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.name("_.username"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.name("_.username")).clear();
        driver.findElement(By.name("_.username")).sendKeys(userName);
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys("password");
        driver.findElement(By.id("yui-gen1-button")).click();
    }

    public void editUser(String userName) throws InterruptedException {

        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.linkText("Global credentials"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.partialLinkText("test3/******")).click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.linkText("Update"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Update")).click();
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys("newpassword");
        driver.findElement(By.name("_.description")).clear();
        driver.findElement(By.name("_.description")).sendKeys("update");
        driver.findElement(By.id("yui-gen1-button")).click();

        driver.findElement(By.linkText("Back to Global credentials")).click();
        Assert.assertEquals("update", driver.findElement(By.xpath("//div[@id='main-panel']/table/tbody/tr[4]/td[4]")).getText());
    }

    public void deleteUser(String userName) throws InterruptedException {

        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.linkText("Global credentials"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Global credentials")).click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.partialLinkText(userName + "/******"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.partialLinkText(userName + "/******")).click();

        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.linkText("Delete"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Delete")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if (isElementPresent(By.id("yui-gen1-button"))) break;
            } catch (Exception e) {}

            Thread.sleep(1000);
        }

        driver.findElement(By.id("yui-gen1-button")).click();
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
