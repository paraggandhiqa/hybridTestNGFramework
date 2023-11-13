package com.tutorialninja.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialnijna.pages.HomePage;
import com.tutorialninja.base.Base;
import com.tutorialninja.utils.Utilities;

// get the test cases related data from dataproperties file

public class RegisterBackup01 extends Base {

	WebDriver driver;

	public RegisterBackup01() {
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
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void verifyRegiterAccountWithMandatoryFields() throws InterruptedException {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProperties.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProperties.getProperty("lastName"));
		// Give new email address everytime
		driver.findElement(By.xpath("//*[@name=\"email\"]")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.cssSelector("#input-telephone")).sendKeys(dataProperties.getProperty("telephone"));
		driver.findElement(By.xpath("//*[@type=\"password\"][@name=\"password\"]"))
				.sendKeys(properties.getProperty("password"));
		driver.findElement(By.cssSelector("[type=\"password\"][name=\"confirm\"]"))
				.sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath("//*[@name=\"agree\"]")).click();
		driver.findElement(By.xpath("//*[@value=\"Continue\"]")).click();
		Thread.sleep(2000);
		String actualSuccessHeader = driver.findElement(By.xpath("//div[@id=\"content\"]/h1")).getText();
		Assert.assertEquals(actualSuccessHeader, dataProperties.getProperty("accountCreationMessage"),
				"Account success is not displayed.");

	}

	@Test(priority = 2)
	public void verifyRegiterAccountWithoutFillingAnyDetails() throws InterruptedException {
		driver.findElement(By.id("input-firstname")).sendKeys("");
		driver.findElement(By.id("input-lastname")).sendKeys("");
		driver.findElement(By.xpath("//*[@name=\"email\"]")).sendKeys("");
		driver.findElement(By.cssSelector("#input-telephone")).sendKeys("");
		driver.findElement(By.xpath("//*[@type=\"password\"][@name=\"password\"]")).sendKeys("");
		driver.findElement(By.cssSelector("[type=\"password\"][name=\"confirm\"]")).sendKeys("");
//		driver.findElement(By.xpath("//*[@name=\"agree\"]")).click();

		driver.findElement(By.xpath("//*[@value=\"Continue\"]")).click();
		Thread.sleep(2000);
		String actualSuccessHeader = driver.findElement(By.xpath("//*[contains(@class,\"alert-danger\")]")).getText();
		Assert.assertEquals(actualSuccessHeader, dataProperties.getProperty("privacyPolicyWarning"),
				"Warning message not displayed.");
		String firstNameWarning = driver.findElement(By.xpath("//*[@id=\"input-firstname\"]/following-sibling::div"))
				.getText();
		Assert.assertEquals(firstNameWarning, dataProperties.getProperty("firstNameWarning"));
		String lastNameWarning = driver.findElement(By.xpath("//*[@id=\"input-lastname\"]/following-sibling::div"))
				.getText();
		Assert.assertEquals(lastNameWarning, dataProperties.getProperty("lastNameWarning"));
		String telephoneWarning = driver.findElement(By.xpath("//*[@id=\"input-telephone\"]/following-sibling::div"))
				.getText();
		String emailWarning = driver.findElement(By.xpath("//*[@id=\"input-email\"]/following-sibling::div")).getText();
		Assert.assertEquals(emailWarning, dataProperties.getProperty("emailWarning"));
		Assert.assertEquals(telephoneWarning, dataProperties.getProperty("telephoneWarning"));
		String passwordWarning = driver.findElement(By.xpath("//*[@id=\"input-password\"]/following-sibling::div"))
				.getText();

		Assert.assertEquals(passwordWarning, dataProperties.getProperty("passwordWarning"));

	}

	@Test(priority = 3)
	public void verifyRegiterAccountWithExistingEmailAddress() throws InterruptedException {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProperties.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProperties.getProperty("lastName"));
		// Give new email address everytime
		driver.findElement(By.xpath("//*[@name=\"email\"]")).sendKeys(properties.getProperty("email"));
		driver.findElement(By.cssSelector("#input-telephone")).sendKeys(dataProperties.getProperty("telephone"));
		driver.findElement(By.xpath("//*[@type=\"password\"][@name=\"password\"]")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.cssSelector("[type=\"password\"][name=\"confirm\"]")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath("//*[@name=\"agree\"]")).click();
		driver.findElement(By.xpath("//*[@value=\"Continue\"]")).click();
		Thread.sleep(2000);
		String actualSuccessHeader = driver.findElement(By.xpath("//*[contains(@class,\"alert-danger\")]")).getText();
		Assert.assertEquals(actualSuccessHeader, dataProperties.getProperty("emailRegisteredWarning"),
				"Warning message regarding duplidate email is not displayed.");

	}

}
