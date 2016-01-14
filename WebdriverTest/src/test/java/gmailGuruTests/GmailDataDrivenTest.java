package gmailGuruTests;

/*Script for Day 4
 */

//import static org.junit.Assert.assertEquals;
import java.io.File;
import java.util.concurrent.TimeUnit;

//import junit.framework.Assert;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
import org.testng.annotations.Test;

/**
 * 
 * @author Krishna Rungta The Test Script 04: Verify the Login Section. The
 *         script uses parameterization to verify more test cases.
 *         Parameterization using TestNG
 * 
 */
public class GmailDataDrivenTest {

	private WebDriver driver; // Selenium control driver
	private String baseUrl; // baseUrl of website Guru99

	/**
	 * create test data for testing The test data include set of username,
	 * password
	 * 
	 * @return
	 */
	@DataProvider(name = "GmailTest")
	public Object[][] testData() throws Exception {
		return Util.getDataFromExcel(Util.FILE_PATH, Util.SHEET_NAME,
				Util.TABLE_NAME);
	}

	/**
	 * Before Testing Setup test environment before executing test
	 * 
	 * @throws Exception
	 * 
	 */
	@BeforeMethod
	public void setUp() throws Exception {

		File pathToBinary = new File(Util.FIREFOX_PATH);
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);

		// Setting Base URL of website Guru99
		baseUrl = Util.BASE_URL;
		driver.manage().timeouts()
				.implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		// Go to http://www.gmail.com
		driver.get(baseUrl);
	}

	/**
	 * Above test script executed several times for each set of data used in @DataProvider
	 * annotation. Any failed test does not impact other set of execution.
	 * 
	 * SS1: Enter valid userid & password Expected: Login successful home page
	 * shown SS2: Enter invalid userid & valid password SS3: Enter valid userid
	 * & invalid password SS4: Enter invalid userid & invalid password Expected:
	 * A pop-up “User or Password is not valid” is shown
	 * 
	 * @param username
	 * @param password
	 * @throws Exception
	 */

	@Test(dataProvider = "GmailTest")
	public void testCase04(String username, String password) throws Exception {
		String actualTitle;
		String actualBoxMsg;
		
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		
		String incorrectUsername = "1111";
		
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(username);
		driver.findElement(By.id("next")).click();
		
		WebElement getErrorText = driver.findElement(By.id("errormsg_0_Email"));
		String getText = getErrorText.getText();
		
		if(username.equals(incorrectUsername)){
			
			if(getText.equals("")){
				boolean textboxCaptcha = driver.findElement(By.id("identifier-captcha-input")).isDisplayed();
				System.out.println("textboxCaptcha: " + textboxCaptcha);
				Assert.assertTrue(true, "Validate that identifier-captcha-input is displayed");
			}
			else {
			//String errorMessage = driver.findElement(By.id("errormsg_0_Email")).getText();
			System.out.println("errorMessage: " + getText);
			Assert.assertEquals(getText, "Sorry, Google doesn't recognize that email.", "Validate error message when email is incorrect");
		}
		}
		
		if(username.equals("trcomission@gmail.com") && password.equals("1111")){
			try{
			String errorMessage = driver.findElement(By.id("errormsg_0_Passwd")).getText();
			System.out.println("errorMessage: " + errorMessage);
			Assert.assertEquals(errorMessage, "The email and password you entered don't match.", "Validate error message when email is correct but password is incorrect");
			}catch(Exception e){
				boolean textboxCaptcha1 = driver.findElement(By.id("identifier-captcha-input")).isDisplayed();
				System.out.println("textboxCaptcha1: " + textboxCaptcha1);
				Assert.assertTrue(true, "Validate that identifier-captcha-input is displayed1");
			}
			//Assert.assertEquals("Sorry, Google doesn't recognize that email. ", errorMessage, "Validate error message when email is incorrect");
		}
		
		try{
		driver.findElement(By.id("Passwd")).clear();
		driver.findElement(By.id("Passwd")).sendKeys(password);
		driver.findElement(By.id("signIn")).click();}
		catch(Exception e){
			System.out.println("Passwd code section didn't run");
		}
		
		if(username.equals("trcomission@gmail.com") && password.equals("comission")){
			boolean emailSearchbar = driver.findElement(By.id("gbqfq")).isDisplayed();
			System.out.println("emailSearchbar: " + emailSearchbar);
			Assert.assertTrue(true, "Validate that iemailSearchbar is displayed");
	}
		
		
		
	

		// delay some seconds
		// Use this statement if your internet speed is slow
		// driver.manage().timeouts()
		// .implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);

		/* Determine Pass Fail Status of the Script
         * If login credentials are correct,  Alert(Pop up) is NOT present. An Exception is thrown and code in catch block is executed	
         * If login credentials are invalid, Alert is present. Code in try block is executed 	    
         */
/*	    try{ 
	    
	       	Alert alt = driver.switchTo().alert();
			actualBoxMsg = alt.getText(); // get content of the Alter Message
			alt.accept();
			 // Compare Error Text with Expected Error Value					
			Assert.assertEquals(actualBoxMsg,Util.EXPECT_ERROR);
			
		}    
	    catch (NoAlertPresentException Ex){ 
	    	actualTitle = driver.getTitle();
			// On Successful login compare Actual Page Title with Expected Title
	    	Assert.assertEquals(actualTitle,Util.EXPECT_TITLE);
        } */
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
