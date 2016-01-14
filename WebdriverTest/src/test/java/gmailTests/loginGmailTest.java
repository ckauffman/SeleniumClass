package gmailTests;

/*Script for Day 4
 */

//import static org.junit.Assert.assertEquals;
import gmail.LoginPage;
import gmailGuruTests.Util;

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
 * @author Charles Kauffman Test Script: Verify the Login Section. The
 *         script uses parameterization to verify more test cases.
 *         Parameterization using TestNG
 * 
 */
public class loginGmailTest extends BaseTest {

	//private WebDriver driver; // Selenium control driver
	private String baseUrl; // baseUrl of gmail
	LoginPage loginPage;

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
	public void loginTest(String username, String password) throws Exception {
		loginPage = new LoginPage(driver);

		System.out.println("username: " + username);
		System.out.println("password: " + password);
		String incorrectUsername = "1111";

		/**
		 * Login with username and pass in the username
		 */
		loginPage.loginWithUsername(username);

		/**
		 * Verifies when incorrect Username is entered that the displays the
		 * identifier captcha input or the error message
		 * "Sorry, Google doesn't recognize that email."
		 */
		if (username.equals(incorrectUsername)) {

			if (loginPage.equals("")) {
				boolean textboxCaptcha = loginPage.txtIdentifierCaptchaInput
						.isDisplayed();
				System.out.println("textboxCaptcha: " + textboxCaptcha);
				Assert.assertTrue(true,
						"Validate that identifier-captcha-input is displayed");
			} else {
				System.out.println("errorMessage: "
						+ loginPage.lblUsernameErrorMessage.getText());
				Assert.assertEquals(
						loginPage.lblUsernameErrorMessage.getText(),
						"Sorry, Google doesn't recognize that email.",
						"Validate error message when email is incorrect");
			}
		}

		/**
		 * If the next line in the excel sheet is the correct username and
		 * incorrect password then check that the error message about the
		 * username and password not matching or the gmail's identifier captcha
		 * input is displayed. This verifies that the user's username worked but
		 * the password didn't worked.
		 */
		if (username.equals("TRcomission@gmail.com") && password.equals("1111")) {
			try {
				String errorMessage = loginPage.lblPasswordErrorMessage.getText();
				System.out.println("errorMessage: " + errorMessage);
				Assert.assertEquals(errorMessage,
						"The email and password you entered don't match.",
						"Validate error message when email is correct but password is incorrect");
			} catch (Exception e) {
				boolean textboxCaptcha1 = loginPage.txtIdentifierCaptchaInput.isDisplayed();
				System.out.println("textboxCaptcha1: " + textboxCaptcha1);
				Assert.assertTrue(true,
						"Validate that identifier-captcha-input is displayed1");
			}
		}

		// Login with Password
		loginPage.loginWithPassword(password);

		/**
		 * If the next line in the excel sheet is the correct username and
		 * correct password then check that the user email searchbar is
		 * displayed. This verifies that the user's username and password
		 * worked.
		 */
		if (username.equals("TRcomission@gmail.com")
				&& password.equals("comission")) {
			boolean emailSearchbar = loginPage.txtEmailSearchBar.isDisplayed();
			System.out.println("emailSearchbar: " + emailSearchbar);
			Assert.assertTrue(true,
					"Validate that iemailSearchbar is displayed");
		}

	}

}
