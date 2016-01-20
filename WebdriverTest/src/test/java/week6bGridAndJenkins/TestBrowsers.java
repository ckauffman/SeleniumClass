package week6bGridAndJenkins;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import util.RunBrowser;
import static java.lang.Thread.sleep;

public class TestBrowsers {
	RunBrowser runBrowser;
	private boolean runToGrid = true;

	// @Ignore
	@Test
	public void testProjectByFirefox() throws Exception {
		testProjectByBrowser("Firefox");
	}

	@Test
	public void testProjectByIE() throws Exception {
		testProjectByBrowser("ie");
	}

	// @Ignore
	@Test
	public void testProjectByChrome() throws Exception {
		testProjectByBrowser("chrome");
	}

	// Close browser
	@AfterClass
	public void tearDown() throws Exception {
		runBrowser.driver.quit();

	}

	
	/**
	 * Run testProjectByBrowser through specified browser in @Test choose to run
	 * on Grid
	 * 
	 * @param driverType
	 */
	public void testProjectByBrowser(String driverType) {
		runBrowser = new RunBrowser(driverType, runToGrid);
		runBrowser.driver.get("http://localhost:8080");
		runBrowser.driver.findElement(By.linkText("New Item")).click();
		runBrowser.driver.findElement(By.id("name")).clear();
		runBrowser.driver.findElement(By.id("name")).sendKeys(
				"A selenium " + RandomStringUtils.randomAlphabetic(5));
		runBrowser.driver.findElement(By.name("mode")).click();
		runBrowser.driver.findElement(By.id("ok-button")).click();
		runBrowser.driver.findElement(
				By.xpath("//button[contains(text(),'Save')]")).click();
	}
}