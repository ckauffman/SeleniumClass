package pageObjectModel;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by TestVM on 6/8/2015.
 */
public class JenkinsUser {
    public String username;
    private WebDriver driver;
    public JenkinsUser(WebDriver driver){

        this.driver = driver;
    }

    public void adduser (String baseUsername) throws InterruptedException {
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

        username = baseUsername + RandomStringUtils.randomAlphabetic(5);

        driver.findElement(By.name("_.username")).clear();
        driver.findElement(By.name("_.username")).sendKeys(username);
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys("password");
        driver.findElement(By.id("yui-gen1-button")).click();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void editUser (String username, String newPassword) throws InterruptedException {
        username = this.username;
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.linkText("Global credentials"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText(username+"/******")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.linkText("Update"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Update")).click();
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys(newPassword);
        driver.findElement(By.name("_.description")).clear();
        driver.findElement(By.name("_.description")).sendKeys("update");
        driver.findElement(By.id("yui-gen1-button")).click();
    }

    public void deleteUser(String username) throws InterruptedException {
        username = this.username;

        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.linkText("Global credentials"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Global credentials")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.linkText(username + "/****** (update)"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.linkText(username + "/****** (update)")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.linkText("Delete"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.linkText("Delete")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try { if (isElementPresent(By.id("yui-gen1-button"))) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }

        driver.findElement(By.id("yui-gen1-button")).click();
    }

}
