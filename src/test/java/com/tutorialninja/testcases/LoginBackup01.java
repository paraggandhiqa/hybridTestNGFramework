package com.tutorialninja.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginBackup01 {

	@Test(enabled = false, priority = 1)
	public void verifyLoginWithValidCredentials() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#input-email")).sendKeys("parag@test.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"Element not displayed");

		driver.quit();

	}

	@Test(enabled = false, priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys("parag" + generateTimeStamp() + "@test.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");

		driver.quit();

	}

	@Test(enabled = false, priority = 3)
	public void verifyLoginWithInvalidEmailAndValidCredentials() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys("parag" + generateTimeStamp() + "@test.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("12345");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");

		driver.quit();

	}

	@Test(enabled = false, priority = 4)
	public void verifyLoginWithValidEmailAndInvalidCredentials() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys("parag@test.com");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");

		driver.quit();

	}

	@Test(enabled = false, priority = 5)
	public void verifyLoginWithoutCredentials() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo");

		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(2000);
		// generate unique email id with datetime stamp
		driver.findElement(By.cssSelector("#input-email")).sendKeys("");
		driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@value=\"Login\"]")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@class,\"alert\")]")).getText(),
				"Warning: No match for E-Mail Address and/or Password.");

		driver.quit();

	}

	public Date generateTimeStamp() {
		Date date = new Date();
		date.toString().replace(" ", "_").replace(":", "_");
		return date;
	}

}
