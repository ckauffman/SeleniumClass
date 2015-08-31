import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static java.lang.Thread.sleep;

public class TestBrowsers {
    private boolean runToGrid = true;
    public void testProjectByBrowser(String driverType) {
        RunBrowser runBrowser = new RunBrowser(driverType, runToGrid);
        runBrowser.driver.get("http://localhost:8080");
        runBrowser.driver.findElement(By.linkText("New Item")).click();
        runBrowser.driver.findElement(By.id("name")).clear();
        runBrowser.driver.findElement(By.id("name")).sendKeys("A selenium " + RandomStringUtils.randomAlphabetic(5));
        runBrowser.driver.findElement(By.name("mode")).click();
        runBrowser.driver.findElement(By.id("ok-button")).click();

        runBrowser.driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
        runBrowser.driver.findElement(By.id("yui-gen30-button")).click();
        runBrowser.driver.quit();

    }

    @Test
    public void testProjectByFirefox() throws Exception {
        testProjectByBrowser("Firefox");

    }
    @Test
    public void testProjectByIE() throws Exception {
        testProjectByBrowser("IE");
    }

    @Test
    public void testProjectByChrome() throws Exception {
        testProjectByBrowser("Chrome");
    }
}