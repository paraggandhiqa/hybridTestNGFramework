package com.tutorialnijna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'My Account')]")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText ="Register")
	private WebElement registerOption;

	// constructor
	// initialize all webelements automatically with init elements
	public HomePage(WebDriver driver) {

		this.driver = driver;

		// this whole home page
		PageFactory.initElements(driver, this);
	}
	
	//actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public RegisterPage clickOnRegister() {
		registerOption.click();
		RegisterPage registerPage = new RegisterPage(driver);
		return registerPage;
	}
	
	public LoginPage selectLoginOption() {
		loginOption.click();
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}

}
