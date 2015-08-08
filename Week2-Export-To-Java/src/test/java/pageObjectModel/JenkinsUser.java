package pageObjectModel;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


/**
 * Created by thanley on 8/4/2015.
 */
public class JenkinsUser {
    public String username;
    private WebDriver driver;

    public JenkinsUser(WebDriver driver) {
        this.driver = driver;
    }


    public void addUser(String baseUsername) {

        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText("Add Credentials")).click();
        new Select(driver.findElement(By.name("_.scope"))).selectByVisibleText("Global");
        new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText("SSH Username with private key");
        // ERROR: Caught exception [Error: Dom locators are not implemented yet!]
        new Select(driver.findElement(By.cssSelector("select.setting-input.dropdownList"))).selectByVisibleText("Username with password");
        driver.findElement(By.name("_.username")).clear();
        username = baseUsername + " " + RandomStringUtils.randomAlphanumeric(5);
        driver.findElement(By.name("_.username")).sendKeys(username);
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys("password");
        driver.findElement(By.id("yui-gen1-button")).click();
    }

    public void editUser(String username, String newPassword) {
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText(username)).click();
        driver.findElement(By.linkText("Update")).click();
        driver.findElement(By.name("_.password")).clear();
        driver.findElement(By.name("_.password")).sendKeys(newPassword);
        driver.findElement(By.name("_.description")).clear();
        driver.findElement(By.name("_.description")).sendKeys("update");
        driver.findElement(By.id("yui-gen1-button")).click();

    }

    public void deleteUser(String username) {
        driver.findElement(By.linkText("Jenkins")).click();
        driver.findElement(By.linkText("Credentials")).click();
        driver.findElement(By.linkText("Global credentials")).click();
        driver.findElement(By.linkText(username)).click();
        driver.findElement(By.linkText("Delete")).click();
        driver.findElement(By.id("yui-gen1-button")).click();
    }

}









