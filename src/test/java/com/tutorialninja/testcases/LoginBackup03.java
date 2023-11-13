package com.tutorialninja.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;
import com.tutorialninja.utils.Utilities;

//Before and after method added
//Browser conditions added
//generate email with timestamp
//valid email and password from properties file

public class LoginBackup03 extends Base {

	WebDriver driver;

	public LoginBackup03() {
		super();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {

		driver = intializeBrowserAndOpenApplication(properties.getProperty("browser"));

		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
	}

	// hardcoded data
	@DataProvider(name="loginData")
	public Object[][] supplyTestData() {
		Object[][] data = { { "parag@test.com", "12345" }, { "parag1@test.com","123456" } };
		return data;
	}

	@Test(priority = 1, dataProvider = "loginData")
	public void verifyLoginWithValidCredentials(String email, String password) throws InterruptedException {

//		driver.findElement(By.cssSelector("#input-email")).sendKeys(properties.getProperty("email"));
//		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(properties.getProperty("password"));
//		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		driver.findElement(By.cssSelector("#input-email")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"Element not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				dataProperties.getProperty("emailPasswordWarning"));

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				dataProperties.getProperty("emailPasswordWarning"));

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys(properties.getProperty("email"));
		driver.findElement(By.xpath("//input[@name=\"password\"]"))
				.sendKeys(dataProperties.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				dataProperties.getProperty("emailPasswordWarning"));

	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys("");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				dataProperties.getProperty("emailPasswordWarning"));
	}

}
