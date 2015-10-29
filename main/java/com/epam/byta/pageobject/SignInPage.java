package com.epam.byta.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {

	private static final String URL = "http://gmail.com";

	private WebDriver driver;

	@FindBy(id = "Email")
	private WebElement inputEmail;

	@FindBy(id = "next")
	private WebElement buttonNext;

	@FindBy(id = "Passwd")
	private WebElement inputPassword;

	@FindBy(id = "signIn")
	private WebElement buttonSignIn;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void openPage() {
		this.driver.get(URL);
	}

	public MailBoxPage logIn(String username, String password) {
		inputEmail.sendKeys(username);
		buttonNext.click();
		inputPassword.sendKeys(password);
		buttonSignIn.click();
		return new MailBoxPage(this.driver);
	}
}
