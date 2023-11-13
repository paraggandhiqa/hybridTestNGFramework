package com.tutorialninja.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialnijna.pages.SearchPage;
import com.tutorialninja.base.Base;

//data from properties file
//intentionally 2nd method fails and 3rd method skipped

public class Search extends Base {

	public WebDriver driver;
	SearchPage searchPage;
	
	@BeforeMethod
	public void setup() {
		driver = intializeBrowserAndOpenApplication(properties.getProperty("browser"));
		searchPage = new SearchPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		 
		searchPage.enterSearchItem(dataProperties.getProperty("searchItem"));
		searchPage.clickSearchButton();
		Assert.assertTrue(driver.findElement(By.linkText("iPhone")).isDisplayed(), "Search result not displayed");

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage.enterSearchItem("test");
		searchPage.clickSearchButton();
		String noProductMessage = searchPage.getNoProductMessage();
		Assert.assertEquals(noProductMessage, dataProperties.getProperty("noProductSearchMessage"));
	}

	// making above method as fail so this test will be skipped
	@Test(priority = 3, dependsOnMethods= {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		searchPage.enterSearchItem("");
		searchPage.clickSearchButton();
		String noProductMessage = searchPage.getNoProductMessage();
		Assert.assertEquals(noProductMessage, dataProperties.getProperty("noProductSearchMessage"));
	}

}
