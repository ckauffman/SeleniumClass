package gmailGuruXp;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class TestBrowsers {

    public void testProjectLink(String browserVersion){
        RunBrowser runBrowser = new RunBrowser(browserVersion);
        runBrowser.driver.get("http://localhost:8080");
        runBrowser.driver.findElement(By.linkText("First Project")).click();
        runBrowser.driver.findElement(By.cssSelector("b")).click();
        runBrowser.driver.findElement(By.linkText("Configure")).click();
        runBrowser.driver.findElement(By.name("description")).clear();
        runBrowser.driver.findElement(By.name("description")).sendKeys("New Description");

        //runBrowser.driver.findElement(By.id("radio-block-0")).click();
        //runBrowser.driver.findElement(By.id("radio-block-1")).click();
        runBrowser.driver.findElement(By.xpath("//label[contains(text(),'None')]/input[@name='scm']")).click();
        runBrowser.driver.findElement(By.xpath("//label[contains(text(),'CVS')]/input[@name='scm']")).click();

        runBrowser.driver.findElement(By.name("_.cvsRoot")).clear();
        runBrowser.driver.findElement(By.name("_.cvsRoot")).sendKeys("test");
        new Select(runBrowser.driver.findElement(By.cssSelector("div[name=\"repositoryItems\"] > table > tbody > tr > td.setting-main > select.setting-input.dropdownList"))).selectByVisibleText("Branch");

        //runBrowser.driver.findElement(By.id("radio-block-0")).click();
        runBrowser.driver.findElement(By.xpath("//label[contains(text(),'None')]/input[@name='scm']")).click();

        runBrowser.driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        //runBrowser.driver.findElement(By.id("yui-gen30-button")).click();

        runBrowser.driver.quit();

    }

    @Test
    public void testBrowsers() {
       //testProjectLink("IE");
       testProjectLink("Chrome");
       testProjectLink("firefox");
//        startBrowser("Chrome");
//        driver.findElement(By.linkText("First Project")).click();
//        driver.findElement(By.cssSelector("b")).click();
//        driver.quit();
    }
}

