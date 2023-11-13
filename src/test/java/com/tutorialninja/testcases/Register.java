package com.tutorialninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialnijna.pages.HomePage;
import com.tutorialnijna.pages.RegisterPage;
import com.tutorialninja.base.Base;
import com.tutorialninja.utils.Utilities;

// get the test cases related data from dataproperties file
// page object model and page factory

public class Register extends Base {

	public WebDriver driver;
	RegisterPage registerPage;

	public Register() {
		super();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {

		driver = intializeBrowserAndOpenApplication(properties.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		Thread.sleep(2000);
		registerPage = homePage.clickOnRegister();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void verifyRegiterAccountWithMandatoryFields() throws InterruptedException {

		registerPage.enterFirstName(dataProperties.getProperty("firstName"));
		registerPage.enterLastName(dataProperties.getProperty("lastName"));
		// Give new email address everytime
		registerPage.enterEmail(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProperties.getProperty("telephone"));
		registerPage.enterPassword(properties.getProperty("password"));
		registerPage.enterConfirmPassword(properties.getProperty("password"));
		registerPage.clickOnPrivacyPolicyButton();
		registerPage.clickOnContinueButton();
		Thread.sleep(2000);
		String actualSuccessHeader = registerPage.getSuccessRegisterText();
		Assert.assertEquals(actualSuccessHeader, dataProperties.getProperty("accountCreationMessage"),
				"Account success is not displayed.");

	}

	@Test(priority = 2)
	public void verifyRegiterAccountWithoutFillingAnyDetails() throws InterruptedException {

		registerPage.enterFirstName("");
		registerPage.enterLastName("");
		// Give new email address everytime
		registerPage.enterEmail("");
		registerPage.enterTelephone("");
		registerPage.enterPassword("");
		registerPage.enterConfirmPassword("");
		registerPage.clickOnContinueButton();

		Thread.sleep(2000);
		String privacyPolicyWarning = registerPage.getPrivacyPolicyWarningText();
		Assert.assertEquals(privacyPolicyWarning, dataProperties.getProperty("privacyPolicyWarning"),
				"Warning message not displayed.");

		String firstNameWarning = registerPage.getFirstNameWarningText();
		String lastNameWarning = registerPage.getLastNameWarningText();
		String telephoneWarning = registerPage.getTelephoneWarningText();
		String emailWarning = registerPage.getEmailWarningText();
		String passwordWarning = registerPage.getPasswordWarningText();

		Assert.assertEquals(firstNameWarning, dataProperties.getProperty("firstNameWarning"));
		Assert.assertEquals(lastNameWarning, dataProperties.getProperty("lastNameWarning"));
		Assert.assertEquals(emailWarning, dataProperties.getProperty("emailWarning"));
		Assert.assertEquals(telephoneWarning, dataProperties.getProperty("telephoneWarning"));
		Assert.assertEquals(passwordWarning, dataProperties.getProperty("passwordWarning"));

	}

	@Test(priority = 3)
	public void verifyRegiterAccountWithExistingEmailAddress() throws InterruptedException {

		registerPage.enterFirstName(dataProperties.getProperty("firstName"));
		registerPage.enterLastName(dataProperties.getProperty("lastName"));
		registerPage.enterEmail(properties.getProperty("email"));
		registerPage.enterTelephone(dataProperties.getProperty("telephone"));
		registerPage.enterPassword(properties.getProperty("password"));
		registerPage.enterConfirmPassword(properties.getProperty("password"));
		registerPage.clickOnPrivacyPolicyButton();
		registerPage.clickOnContinueButton();
		Thread.sleep(2000);
		String emailRegisteredWarning = registerPage.getPrivacyPolicyWarningText();
		Assert.assertEquals(emailRegisteredWarning, dataProperties.getProperty("emailRegisteredWarning"),
				"Warning message not displayed.");

	}

}
