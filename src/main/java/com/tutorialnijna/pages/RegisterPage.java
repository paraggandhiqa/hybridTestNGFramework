package com.tutorialnijna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	WebElement firstName;

	@FindBy(id = "input-lastname")
	WebElement lastName;

	@FindBy(xpath = "//*[@name='email']")
	WebElement email;

	@FindBy(css = "#input-telephone")
	WebElement telephone;

	@FindBy(xpath = "//*[@type=\"password\"][@name=\"password\"]")
	WebElement password;

	@FindBy(css = "[type=\"password\"][name=\"confirm\"]")
	WebElement confirmPassword;

	@FindBy(xpath = "//*[@name=\"agree\"]")
	WebElement privacyPolicyButton;

	@FindBy(xpath = "//*[@value=\"Continue\"]")
	WebElement continueButton;

	@FindBy(xpath = "//div[@id=\"content\"]/h1")
	WebElement successRegisterHeader;
	
	@FindBy(xpath="//*[contains(@class,\"alert-danger\")]")
	WebElement privacyPolicyWarningHeader;
	
	@FindBy(xpath="//*[@id=\"input-firstname\"]/following-sibling::div")
	WebElement firstNameWarning;
	
	@FindBy(xpath="//*[@id=\"input-lastname\"]/following-sibling::div")
	WebElement lastNameWarning;
	
	@FindBy(xpath="//*[@id=\"input-telephone\"]/following-sibling::div")
	WebElement telephoneWarning;
	
	@FindBy(xpath="//*[@id=\"input-email\"]/following-sibling::div")
	WebElement emailWarning;
	
	@FindBy(xpath="//*[@id=\"input-password\"]/following-sibling::div")
	WebElement passwordWarning;
	

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText) {
		firstName.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastName.sendKeys(lastNameText);
	}

	public void enterEmail(String emailText) {
		email.sendKeys(emailText);
	}

	public void enterTelephone(String telephoneText) {
		telephone.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		password.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {
		confirmPassword.sendKeys(confirmPasswordText);
	}

	public void clickOnPrivacyPolicyButton() {
		privacyPolicyButton.click();
	}

	public void clickOnContinueButton() {
		continueButton.click();
	}

	public String getSuccessRegisterText() {
		return successRegisterHeader.getText();
	}
	
	public String getPrivacyPolicyWarningText() {
		return privacyPolicyWarningHeader.getText();
	}
	
	public String getFirstNameWarningText() {
		return firstNameWarning.getText();
	}
	
	public String getLastNameWarningText() {
		return lastNameWarning.getText();
	}
	
	public String getEmailWarningText() {
		return emailWarning.getText();
	}
	
	public String getTelephoneWarningText() {
		return telephoneWarning.getText();
	}
	
	public String getPasswordWarningText() {
		return passwordWarning.getText();
	}

}
