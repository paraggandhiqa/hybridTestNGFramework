package com.tutorialninja.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//Before and after method added
//Browser conditions added
//generate email with timestamp

public class LoginBackup02 {

	WebDriver driver;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() throws InterruptedException {

		String browserName = "chrome";
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() throws InterruptedException {

		driver.findElement(By.cssSelector("#input-email")).sendKeys("parag@test.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"Element not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys(generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys(generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys("parag@test.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() throws InterruptedException {

		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys("");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");
	}

	public String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "parag" + timeStamp + "@gmail.com";
	}

}
