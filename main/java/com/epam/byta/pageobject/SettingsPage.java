package com.epam.byta.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsPage {

	private WebDriver driver;

	private String inboxUrl = "https://mail.google.com/mail/#inbox";

	@FindBy(linkText = "Forwarding and POP/IMAP")
	private WebElement forwardingSettingsTab;

	@FindBy(linkText = "Filters and Blocked Addresses")
	private WebElement filtersTab;

	@FindBy(linkText = "Themes")
	private WebElement themesTab;

	@FindBy(xpath = "//input[@name='sx_sg'][@value='1']")
	private WebElement signatureRadioButton;

	@FindBy(xpath = "//div[@aria-label='Signature']")
	private WebElement signatureInput;

	@FindBy(xpath = "//button[text()='Save Changes']")
	private WebElement saveChangesButton;

	public SettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public ForwardingSettingsPage selectForwardingTab() {
		forwardingSettingsTab.click();
		return new ForwardingSettingsPage(this.driver);
	}

	public FilterSettingsPage selectFiltersTab() {
		filtersTab.click();
		return new FilterSettingsPage(this.driver);
	}

	public ThemesSettingsPage selectThemesTab() {
		themesTab.click();
		return new ThemesSettingsPage(this.driver);
	}

	public void setSignature(String msgSubject) {
		signatureInput.click();
		signatureInput.clear();
		signatureInput.sendKeys(msgSubject);
		saveChangesButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.urlToBe(inboxUrl));
	}

}
