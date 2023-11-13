package com.tutorialnijna.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	String searchItem;

	@FindBy(css = "[name=\"search\"]")
	WebElement search;

	@FindBy(xpath = "//div[@id=\"search\"]/descendant::button")
	WebElement searchButton;

	@FindBy(linkText = "iPhone")
	WebElement searchResult;
	
	@FindBy(xpath="//input[@id=\"button-search\"]/following-sibling::p")
	WebElement noProductMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void enterSearchItem(String searchText) {
		search.sendKeys(searchText);
	}

	public void clickSearchButton() {
		searchButton.click();
	}

	public boolean isSearchItemDisplayed() {
		return searchResult.isDisplayed();
	}
	
	public String getNoProductMessage() {
		return noProductMessage.getText();
	}
}
