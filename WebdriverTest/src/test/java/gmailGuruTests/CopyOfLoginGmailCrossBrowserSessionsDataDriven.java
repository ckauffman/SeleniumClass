package gmailGuruTests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CopyOfLoginGmailCrossBrowserSessionsDataDriven {

	WebDriver driver;
	private String baseUrl; // baseUrl of website Guru99

	/**
	 * create test data for testing The test data include set of username,
	 * password
	 * 
	 * @return
	 */
	@DataProvider(name = "GuruTest")
	public Object[][] testData() throws Exception {
		return Util.getDataFromExcel(Util.FILE_PATH, Util.SHEET_NAME,
				Util.TABLE_NAME);
	}

	/**
	 * 
	 * This function will execute before each Test tag in testng.xml
	 * 
	 * @param browser
	 * 
	 * @throws Exception
	 */

	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {

		// Check if parameter passed from TestNG is 'firefox'

		if (browser.equalsIgnoreCase("firefox")) {

			// create firefox instance

			driver = new FirefoxDriver();

		}

		// Check if parameter passed as 'chrome'

		else if (browser.equalsIgnoreCase("chrome")) {

			// set path to chromedriver.exe You may need to download it from
			// http://code.google.com/p/selenium/wiki/ChromeDriver

			System.setProperty("webdriver.chrome.driver",
					"C:\\chromedriver.exe");

			// create chrome instance

			driver = new ChromeDriver();

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
/*	@BeforeMethod
	public void setUp() throws Exception {

		File pathToBinary = new File(Util.FIREFOX_PATH);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);

		// Setting Base URL of website Guru99
		baseUrl = Util.BASE_URL;
		driver.manage().timeouts()
				.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		// Go to http://www.demo.guru99.com/V4/
		driver.get(baseUrl + "/V4/");
	}*/

	@Test(dataProvider = "GuruTest")
	public void testCase04(String username, String password) throws Exception {
		driver.get("http://demo.guru99.com/V4/");
		String actualTitle;
		String actualBoxMsg;
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		// delay some seconds
		// Use this statement if your internet speed is slow
		// driver.manage().timeouts()
		// .implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

		/*
		 * Determine Pass Fail Status of the Script If login credentials are
		 * correct, Alert(Pop up) is NOT present. An Exception is thrown and
		 * code in catch block is executed If login credentials are invalid,
		 * Alert is present. Code in try block is executed
		 */
		try {

			Alert alt = driver.switchTo().alert();
			actualBoxMsg = alt.getText(); // get content of the Alter Message
			alt.accept();
			// Compare Error Text with Expected Error Value
			Assert.assertEquals(actualBoxMsg, Util.EXPECT_ERROR);

		} catch (NoAlertPresentException Ex) {
			actualTitle = driver.getTitle();
			// On Successful login compare Actual Page Title with Expected Title
			Assert.assertEquals(actualTitle, Util.EXPECT_TITLE);
		}
	}

	/*
	 * @Test
	 * 
	 * public void testParameterWithXML() throws InterruptedException{
	 * 
	 * driver.get("http://demo.guru99.com/V4/");
	 * 
	 * //Find user name WebElement userName =
	 * driver.findElement(By.name("uid"));
	 * 
	 * //Fill user name
	 * 
	 * userName.sendKeys("guru99");
	 * 
	 * //Find password
	 * 
	 * WebElement password = driver.findElement(By.name("password"));
	 * 
	 * //Fill password
	 * 
	 * password.sendKeys("guru99");
	 * 
	 * }
	 */
}
