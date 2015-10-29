package com.epam.byta.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpenedEmailPage {
	private WebDriver driver;

	@FindBy(xpath = "//img[@aria-label='Show details']")
	private WebElement imageShowDetails;

	public OpenedEmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void showDetails() {
		imageShowDetails.click();
	}

	public boolean isMailImportant() {
		showDetails();
		return this.driver
				.findElements(By.xpath("//span/img[@src='//ssl.gstatic.com/ui/v1/icons/mail/images/cleardot.gif']"))
				.size() != 0;
	}

	public boolean hasMailAttach() {
		return this.driver.findElements(By.xpath("//span/img[@src='images/cleardot.gif'][@alt='Attachments']"))
				.size() != 0;
	}
}
