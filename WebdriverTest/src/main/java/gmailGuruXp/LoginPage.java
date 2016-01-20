package gmailGuruXp;

import gmailGuruTests.Util;
import junit.framework.Assert;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;

	@FindBy(id = "Email")
	WebElement emailUsername;

	@FindBy(id = "next")
	WebElement btnEmailUsernameNext;

	@FindBy(id = "Passwd")
	WebElement emailPassword;

	@FindBy(id = "signIn")
	WebElement btnSignIn;
	
	@FindBy(id = "errormsg_0_Email")
	public WebElement lblUsernameErrorMessage;
	               
	@FindBy(id = "errormsg_0_Passwd")
	public WebElement lblPasswordErrorMessage;

	@FindBy(id = "identifier-captcha-input")
	public WebElement txtIdentifierCaptchaInput;
	
	@FindBy(id = "gbqfq")
	public WebElement txtEmailSearchBar;


	public LoginPage(WebDriver driver) {
		this.driver = driver;
		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * Login into Gmail with username
	 * 
	 * @param username
	 */
	public void loginWithUsername(String username) {
		emailUsername.clear();
		emailUsername.sendKeys(username);
		btnEmailUsernameNext.click();
	}

	/**
	 * Login into Gmail with password
	 * 
	 * @param password
	 */
	public void loginWithPassword(String password) {

		try {
			emailPassword.clear();
			emailPassword.sendKeys(password);
			btnSignIn.click();
		} catch (Exception e) {
			System.out.println("Passwd code section didn't run");
		}
	}
}
