package com.tutorialninja.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.base.Base;

public class SearchBackup01 extends Base {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = intializeBrowserAndOpenApplication("chrome");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(enabled = false, priority = 1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.cssSelector("[name=\"search\"]")).sendKeys("iphone");
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("iPhone")).isDisplayed(), "Search result not displayed");

	}

	@Test(enabled = false, priority = 2)
	public void verifySearchWithInvalidProduct() {
		driver.findElement(By.cssSelector("[name=\"search\"]")).sendKeys("test");
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		String noProductMessage = driver.findElement(By.xpath("//input[@id=\"button-search\"]/following-sibling::p"))
				.getText();
		Assert.assertEquals(noProductMessage, "There is no product that matches the search criteria.");
	}

	@Test(enabled = false, priority = 3)
	public void verifySearchWithoutAnyProduct() {
		driver.findElement(By.cssSelector("[name=\"search\"]")).sendKeys("");
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		String noProductMessage = driver.findElement(By.xpath("//input[@id=\"button-search\"]/following-sibling::p"))
				.getText();
		Assert.assertEquals(noProductMessage, "There is no product that matches the search criteria.");
	}

}
