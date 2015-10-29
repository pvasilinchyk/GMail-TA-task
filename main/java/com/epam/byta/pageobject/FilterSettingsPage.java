package com.epam.byta.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FilterSettingsPage {
	private WebDriver driver;

	@FindBy(xpath = "//button[@class='J-at1-auR J-at1-atl']")
	private WebElement buttonOK;

	@FindBy(xpath = "//span[@class = 'sA'][contains(text(), 'Create a new filter')]")
	private WebElement createNewFilterLink;

	@FindBy(xpath = "//input[@class = 'ZH nr aQa']")
	private WebElement inputFrom;

	@FindBy(xpath = "//div[text()='Create filter']")
	private WebElement buttonCreateFilter;

	@FindBy(xpath = "//span[@class='w-Pv ZG']/input[@type = 'checkbox']")
	private WebElement checkBoxHasAttachment;

	@FindBy(xpath = "//div[@class='acM'][contains(text(), 'Create filter with this search »')]")
	private WebElement createFilterWithThisSearchLink;

	@FindBy(xpath = "//label[contains(.,'Delete it')]/preceding-sibling::input[@type='checkbox']")
	private WebElement checkBoxDeleteIt;

	@FindBy(xpath = "//label[contains(.,'Always mark it as important')]/preceding-sibling::input[@type='checkbox']")
	private WebElement checkBoxAlwaysMarkItAsImportant;

	public FilterSettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void clickCreateNewFilter() {
		createNewFilterLink.click();
	}

	public void fillInFilterForm(String username) {
		inputFrom.sendKeys(username);

	}

	public void checkHasAttach() {
		checkBoxHasAttachment.click();
	}

	public void createFilterWithThisSearch() {
		createFilterWithThisSearchLink.click();
		this.driver.switchTo().activeElement();
//		if (buttonOK.isDisplayed()) {
//			buttonOK.click();
//		}
		checkBoxDeleteIt.click();
		checkBoxAlwaysMarkItAsImportant.click();
		buttonCreateFilter.click();
	}
}
