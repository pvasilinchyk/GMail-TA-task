package com.epam.byta.steps;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.epam.byta.pageobject.ComposeEmailPage;
import com.epam.byta.pageobject.FilterSettingsPage;
import com.epam.byta.pageobject.ForwardingSettingsPage;
import com.epam.byta.pageobject.MailBoxPage;
import com.epam.byta.pageobject.OpenedEmailPage;
import com.epam.byta.pageobject.SettingsPage;
import com.epam.byta.pageobject.SignInPage;
import com.epam.byta.pageobject.ThemesSettingsPage;
import com.epam.byta.wdsingletone.WebDriverSingletone;

public class Steps {
	private static WebDriver driver;

	private String searchInSpam = "in:spam ";
	private String searchInTrash = "in:trash ";
	private String searchInInbox = "in:inbox ";

	public void startBrowser() {
		driver = WebDriverSingletone.getWebDriverInstance();
	}

	public void closeBrowser() {
		WebDriverSingletone.closeWebDriver();
	}

	public void loginAsRegisteredUser(String userName, String password) {
		SignInPage signInPage = new SignInPage(driver);
		signInPage.openPage();
		signInPage.logIn(userName, password);
	}

	public void sendMail(String toWhom, String subject, String message) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		ComposeEmailPage composeEmailPage = mailBoxPage.goToComposeEmail();
		mailBoxPage = composeEmailPage.sendEmail(toWhom, subject, message);
	}

	public void markEmailAsSpam(String msgSubject) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.submitToSearch(searchInInbox + msgSubject);
		mailBoxPage.findMailBySubject(msgSubject);
		mailBoxPage.markAsSpam();
	}

	public void restartBrowser() {
		closeBrowser();
		startBrowser();
	}

	public void goToSpamFolder() {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.showMore();
		mailBoxPage.clickSpamLink();
	}

	public boolean isEmailInSpam(String msgSubject) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.submitToSearch(searchInSpam + msgSubject);
		return mailBoxPage.findMailBySubject(msgSubject);
	}

	public void clickButtonSettings() {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.clickSettingsButton();

	}

	public void chooseSettingsMenu() {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.goToSettings();

	}

	public void chooseForwardPage() {
		SettingsPage settingsPage = new SettingsPage(driver);
		settingsPage.selectForwardingTab();

	}

	public void setForwardToUser(String userName) {
		ForwardingSettingsPage forwardingSettingsPage = new ForwardingSettingsPage(driver);
		forwardingSettingsPage.addForwardingAdress(userName);

	}

	public void confirmForward() {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.openMailBySubject("Gmail Forwarding Confirmation");
		mailBoxPage.clickConfirmationLink();

	}

	public void checkRadiobutton() {
		ForwardingSettingsPage forwardingSettingsPage = new ForwardingSettingsPage(driver);
		forwardingSettingsPage.radioButtonForwardCheck();

	}

	public void chooseFiltersTab() {
		SettingsPage settingsPage = new SettingsPage(driver);
		settingsPage.selectFiltersTab();

	}

	public void createNewFilter(String username) {
		driver.navigate().refresh();
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.clickSettingsButton();
		mailBoxPage.goToSettings();
		SettingsPage settingsPage = new SettingsPage(driver);
		settingsPage.selectFiltersTab();
		FilterSettingsPage filterSettings = new FilterSettingsPage(driver);
		filterSettings.clickCreateNewFilter();
		filterSettings.fillInFilterForm(username);
		filterSettings.checkHasAttach();
		filterSettings.createFilterWithThisSearch();

	}

	public void sendMailWithAttach(String toWhom, String subject, String message, String pathToFile)
			throws AWTException {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		ComposeEmailPage composeEmailPage = mailBoxPage.goToComposeEmail();
		mailBoxPage = composeEmailPage.sendEmailWithAttach(toWhom, subject, message, pathToFile);
	}

	public boolean checkIfMailIsInTrash(String subject) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.submitToSearch(searchInTrash + subject);
		mailBoxPage.openMailBySubject(subject);
		OpenedEmailPage openedEmailPage = new OpenedEmailPage(driver);
		return (openedEmailPage.hasMailAttach() && openedEmailPage.isMailImportant());
	}

	public boolean checkIfMailIsInInbox(String subject) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.submitToSearch(searchInInbox + subject);
		mailBoxPage.openMailBySubject(subject);
		OpenedEmailPage openedEmailPage = new OpenedEmailPage(driver);
		return !(openedEmailPage.hasMailAttach());
	}

	public boolean alertWindowAppears() {
		ComposeEmailPage composeEmailPage = new ComposeEmailPage(driver);
		try {
			// driver.switchTo().alert().accept();
			return composeEmailPage.isAlertPresent();
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	public void goToThemesTab() {
		SettingsPage settingsPage = new SettingsPage(driver);
		settingsPage.selectThemesTab();

	}

	public void chooseTheme() {
		ThemesSettingsPage themeSettingsPage = new ThemesSettingsPage(driver);
		themeSettingsPage.setTheme();

	}

	public void chooseImproperTheme(String pathToAttach) throws AWTException {
		ThemesSettingsPage themeSettingsPage = new ThemesSettingsPage(driver);
		themeSettingsPage.setImproperTheme(pathToAttach);
	}

	public boolean isThemeChosen() {
		ThemesSettingsPage themeSettingsPage = new ThemesSettingsPage(driver);
		return themeSettingsPage.checkTheme();
	}

	public boolean errorMessageAppears() {
		ThemesSettingsPage themeSettingsPage = new ThemesSettingsPage(driver);
		return themeSettingsPage.isMessagePresent();
	}

	public List<WebElement> sendMailWithEmoticon(String userName3, String msgSubject, String msgText) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		ComposeEmailPage composeEmailPage = mailBoxPage.goToComposeEmail();
		List<WebElement> emoticonsList = composeEmailPage.sendEmailWithEmoticon(userName3, msgSubject, msgText);
		return emoticonsList;
	}

	public void deleteAllMessages(String userName, String password) {
		SignInPage signInPage = new SignInPage(driver);
		signInPage.openPage();
		MailBoxPage mailBoxPage=signInPage.logIn(userName, password);
		mailBoxPage.deleteMessages(searchInSpam);
		mailBoxPage.deleteMessages(searchInInbox);
		mailBoxPage.deleteMessages(searchInTrash);
		

	}

	public void setDefaultTheme(String userName, String passwordUser) {
		ThemesSettingsPage themeSettingsPage = new ThemesSettingsPage(driver);
		themeSettingsPage.setRandomTheme();
		
	}

	public String getLastEmailSubject() {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		return mailBoxPage.returnLastSubject();
	}

	public void unmarkSpamLetter(String lastEmailSubject) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		mailBoxPage.markAsNotSpam(lastEmailSubject);
		
	}

	public void setSignature(String msgSubject) {
		SettingsPage settingsPage = new SettingsPage(driver);
		settingsPage.setSignature(msgSubject);
		
	}

	public boolean checkSignature(String msgSubject) {
		MailBoxPage mailBoxPage = new MailBoxPage(driver);
		ComposeEmailPage composeEmailPage = mailBoxPage.goToComposeEmail();
		return composeEmailPage.checkMailSignature(msgSubject);
	}

}
