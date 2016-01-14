package gmailTests;

import gmailGuruTests.Util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
	WebDriver driver;
	private String baseUrl; // baseUrl of gmail
	
	@BeforeTest(enabled = false)
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			driver = new FirefoxDriver();
			goToUrl();
		}

		// Check if parameter passed as 'chrome'

		else if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe You may need to download it from
			// http://code.google.com/p/selenium/wiki/ChromeDriver

			System.setProperty("webdriver.chrome.driver",
					"C:\\chromedriver.exe");

			// create chrome instance
			driver = new ChromeDriver();
			goToUrl();
		}

		else if (browser.equalsIgnoreCase("ie")) {
			// set path to IEdriver.exe You may need to download it from
			// 32 bits
			// http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_Win32_2.42.0.zip
			// 64 bits
			// http://selenium-release.storage.googleapis.com/2.42/IEDriverServer_x64_2.42.0.zip
			System.setProperty("webdriver.ie.driver", "C:\\IEDriverServer.exe");

			// create chrome instance
			driver = new InternetExplorerDriver();
			goToUrl();
		}

		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	/**
	 * Before Testing Setup test environment before executing test
	 * 
	 * @throws Exception
	 * 
	 */
	@BeforeMethod(enabled = true)
	public void setUp() throws Exception {
		//setup(String browser)
		File pathToBinary = new File(Util.FIREFOX_PATH);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);

		goToUrl();
	}

	private void goToUrl() {
		// Setting Base URL of website Gmail.com
		baseUrl = Util.BASE_URL;
		driver.manage().timeouts()
				.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		// Go to http://www.gmail.com
		driver.get(baseUrl);
	}
	
	/**
	 * Complete the testing
	 * 
	 * @throws Exception
	 */
	@AfterMethod
	public void tearDown() throws Exception {
		// driver.quit();
	}
	
	
	
	
}
