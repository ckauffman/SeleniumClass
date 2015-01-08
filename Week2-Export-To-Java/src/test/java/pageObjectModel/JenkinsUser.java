package pageObjectModel;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by Fresh on 1/6/2015.
 */
public class JenkinsUser {
    public String username;
    private WebDriver driver;

    public JenkinsUser(WebDriver driver) {
        this.driver = driver;
    }

    public void addUser(String baseUserName) {
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.linkText("Global credentials")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.linkText("Global credentials")).click();

        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.xpath("//a[contains(text(),'Add Credentials')]")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.xpath("//a[contains(text(),'Add Credentials')]")).click();
        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.cssSelector("select.setting-input.dropdownList")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText("SSH Username with private key");
        new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText("Username with password");
        driver.findElement(By.name("_.username")).clear();
        String userName = baseUserName + RandomStringUtils.random(5);
        driver.findElement(By.name("_.username")).sendKeys(userName);
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys("password");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Failed to wait!");
        }
        driver.findElement(By.id("yui-gen1-button")).click();

        this.username = userName;
    }

    public void editUser() {
        driver.findElement(By.linkText("Credentials")).click();
        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.linkText("Global credentials")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.linkText("Global credentials")).click();


        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.xpath("//a[contains(text(),'" + username + "')]")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.xpath("//a[contains(text(),'" + username + "')]")).click();
        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.linkText("Update")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }
        driver.findElement(By.linkText("Update")).click();
        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.name("_.password")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys("newpassword");
        driver.findElement(By.name("_.description")).clear();
        driver.findElement(By.name("_.description")).sendKeys("update");
        driver.findElement(By.id("yui-gen1-button")).click();
        driver.findElement(By.linkText("Back to Global credentials")).click();
    }

    public void deleteUser() {
        driver.findElement(By.linkText("Credentials")).click();

        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements(By.linkText("Global credentials"));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.linkText("Global credentials")).click();
        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.xpath("//a[contains(text(),'" + username + "')]")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.xpath("//a[contains(text(),'" + username + "')]")).click();

        for (int second = 0;; second++) {
            if (second >= 30) fail("timeout");
            List<WebElement> elements = driver.findElements((By.linkText("Delete")));
            if (elements.size() > 0) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                fail("Failed to pause...  Error details: " + e.toString());
            }
        }

        driver.findElement(By.linkText("Delete")).click();
    }

}
