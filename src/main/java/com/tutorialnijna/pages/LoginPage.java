package com.tutorialnijna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	@FindBy(css = "#input-email")
	private WebElement emailAddress;

	@FindBy(xpath = "//input[@name=\"password\"]")
	private WebElement password;

	@FindBy(xpath = "//*[@value=\"Login\"]")
	private WebElement loginButton;

	@FindBy(xpath = "//*[contains(@class,\"alert\")]")
	private WebElement emailPasswordWarningMessage;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterEmailAddress(String email) {
		this.emailAddress.sendKeys(email);
	}

	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}

	public void ClickOnLogin() {
		loginButton.click();
	}

	public boolean isEmailPasswordWarningDisplayed() {
		return emailPasswordWarningMessage.isDisplayed();
	}

	public String getEmailPasswordWarningMessage() {
		return emailPasswordWarningMessage.getText();
	}

}
