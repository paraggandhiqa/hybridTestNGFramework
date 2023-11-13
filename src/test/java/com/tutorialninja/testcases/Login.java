package com.tutorialninja.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialnijna.pages.HomePage;
import com.tutorialnijna.pages.LoginPage;
import com.tutorialninja.base.Base;
import com.tutorialninja.utils.Utilities;

//Before and after method added
//Browser conditions added
//generate email with timestamp
//valid email and password from properties file
//dataprovider with excel sheet // data driven testing
//page object model and page factory, locators at specific page levels

public class Login extends Base {

	public WebDriver driver;
	LoginPage loginPage;

	public Login() {
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
//		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		loginPage = homePage.selectLoginOption();
//		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
	}

	// hardcoded data
	@DataProvider(name = "loginData")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 1, dataProvider = "loginData")
	public void verifyLoginWithValidCredentials(String email, String password) throws InterruptedException {

		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.ClickOnLogin();

		Assert.assertTrue(loginPage.isEmailPasswordWarningDisplayed(), "Element not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {

		// generate unique id
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProperties.getProperty("invalidPassword"));
		loginPage.ClickOnLogin();

		Assert.assertTrue(loginPage.isEmailPasswordWarningDisplayed(), "Element not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidCredentials() throws InterruptedException {

		// generate unique id
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(properties.getProperty("password"));
		loginPage.ClickOnLogin();

		Assert.assertTrue(loginPage.isEmailPasswordWarningDisplayed(), "Element not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidCredentials() throws InterruptedException {

		loginPage.enterEmailAddress(properties.getProperty("email"));
		loginPage.enterPassword(dataProperties.getProperty("invalidPassword"));
		loginPage.ClickOnLogin();

		Assert.assertEquals(loginPage.getEmailPasswordWarningMessage(),
				dataProperties.getProperty("emailPasswordWarning"));

	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() throws InterruptedException {

		loginPage.enterEmailAddress("");
		loginPage.enterPassword("");
		loginPage.ClickOnLogin();

		Assert.assertEquals(loginPage.getEmailPasswordWarningMessage(),
				dataProperties.getProperty("emailPasswordWarning"));
	}

}
