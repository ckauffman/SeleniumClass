package pageObjectModel;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * Created by TestVM on 7/6/2015.
 */

public class JenkinsUser {
    public String fullUsername;
    private WebDriver driver;
    public JenkinsUser(WebDriver driver) {

        this.driver = driver;
    }

    public void addUser(String baseUsername) {

        fullUsername = baseUsername + RandomStringUtils.randomAlphabetic(5);
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText("Add Credentials")).click();
        new Select(driver.findElement(By.name("_.scope"))).selectByVisibleText("Global");
        new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText("SSH Username with private key");
        // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
        new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText("Username with password");
        driver.findElement(By.name("_.username")).clear();
        driver.findElement(By.name("_.username")).sendKeys(fullUsername);
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys("password");
        driver.findElement(By.id("yui-gen1-button")).click();
        }

    public void editUser(String fullUsername, String newPassword) {

        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText(fullUsername)).click();
        driver.findElement(By.linkText("Update")).click();
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys(newPassword);
        driver.findElement(By.id("yui-gen1-button")).click();

        }

    public void deleteUser(String fullUsername) {
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText(fullUsername)).click();
        driver.findElement(By.linkText("Delete")).click();
        driver.findElement(By.id("yui-gen1-button")).click();
    }
}