package com.epam.byta.tests;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.byta.core.Utils;
import com.epam.byta.steps.Steps;

public class TestUnits {

	private Steps steps;

	private String userName1 = Utils.getUserName1();
	private String userName2 = Utils.getUserName2();
	private String userName3 = Utils.getUserName3();
	private String passwordUser1 = Utils.getPasswordUser1();
	private String passwordUser2 = Utils.getPasswordUser2();
	private String passwordUser3 = Utils.getPasswordUser3();
	private String msgSubject = Utils.getMsgSubject();
	private String msgText = Utils.getMsgText();
	private String pathToAttach = Utils.getPathToAttach();
	private String pathToBigAttach = Utils.getPathToBigAttach();

	@BeforeMethod
	public void setUp() {
		steps = new Steps();
		steps.startBrowser();
	}

	@Test(enabled = false, description = "Spam Test")
	public void spamTest() {
		steps.loginAsRegisteredUser(userName1, passwordUser1);
		steps.sendMail(userName2, msgSubject, msgText);
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName2, passwordUser2);
		steps.markEmailAsSpam(msgSubject);
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName1, passwordUser1);
		steps.sendMail(userName2, msgSubject, msgText);
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName2, passwordUser2);
		Assert.assertTrue(steps.isEmailInSpam(msgSubject));

	}

	@Test(enabled = false, description = "Forward Test")
	public void forwardTest() throws AWTException {
		steps.loginAsRegisteredUser(userName2, passwordUser2);
		steps.clickButtonSettings();
		steps.chooseSettingsMenu();
		steps.chooseForwardPage();
		steps.setForwardToUser(userName3);
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName3, passwordUser3);
		steps.confirmForward();
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName2, passwordUser2);
		steps.clickButtonSettings();
		steps.chooseSettingsMenu();
		steps.chooseForwardPage();
		steps.checkRadiobutton();
		steps.createNewFilter(userName1);
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName1, passwordUser1);
		steps.sendMailWithAttach(userName2, msgSubject, msgText, pathToAttach);
		steps.sendMail(userName2, msgSubject, msgText);
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName2, passwordUser2);
		Assert.assertTrue(steps.checkIfMailIsInTrash(msgSubject));
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName2, passwordUser2);
		Assert.assertTrue(steps.checkIfMailIsInInbox(msgSubject));
		steps.restartBrowser();
		steps.loginAsRegisteredUser(userName3, passwordUser3);
		Assert.assertTrue(steps.checkIfMailIsInInbox(msgSubject));
	}

	@Test(enabled = false, description = "Main mail box page")
	public void attachBigFileTest() throws AWTException {
		steps.loginAsRegisteredUser(userName3, passwordUser3);
		steps.sendMailWithAttach(userName2, msgSubject, msgText, pathToBigAttach);
		Assert.assertTrue(steps.alertWindowAppears());
	}

	@Test(enabled = false, description = "Change user theme")
	public void themesTest() {
		steps.loginAsRegisteredUser(userName3, passwordUser3);
		steps.clickButtonSettings();
		steps.chooseSettingsMenu();
		steps.goToThemesTab();
		steps.chooseTheme();
		Assert.assertTrue(steps.isThemeChosen());
	}

	@Test(enabled = false, description = "Send mail with emoticons")
	public void emoticonsTest() {
		steps.loginAsRegisteredUser(userName3, passwordUser3);
		List<WebElement> emoticons = steps.sendMailWithEmoticon(userName3, msgSubject, msgText);
		System.out.println(emoticons.toString());
	}

	@Test(enabled = false, description = "Change user theme to improper extension")
	public void themesNegativeTest() throws AWTException {
		steps.loginAsRegisteredUser(userName1, passwordUser1);
		steps.clickButtonSettings();
		steps.chooseSettingsMenu();
		steps.goToThemesTab();
		steps.chooseImproperTheme(pathToAttach);
		Assert.assertTrue(steps.errorMessageAppears());

	}

	@Test(enabled = false, description = "Mark as spam and not spam")
	public void spamAndNotSpamTest() {
		steps.loginAsRegisteredUser(userName1, passwordUser1);
		String lastEmailSubject = steps.getLastEmailSubject();
		steps.markEmailAsSpam(lastEmailSubject);
		steps.goToSpamFolder();
		steps.unmarkSpamLetter(lastEmailSubject);
		Assert.assertTrue(steps.checkIfMailIsInInbox(lastEmailSubject));
	}

	@Test(enabled = true, description = "Checking signature")
	public void signatureTest() {
		steps.loginAsRegisteredUser(userName1, passwordUser1);
		steps.clickButtonSettings();
		steps.chooseSettingsMenu();
		steps.setSignature(msgSubject);
		Assert.assertTrue(steps.checkSignature(msgSubject));
	}
	
	@Test(enabled = false, description = "Check 'star' selection")
	public void starredEmailTest() {
		steps.loginAsRegisteredUser(userName1, passwordUser1);
		String lastEmailSubject = steps.getLastEmailSubject();
//		steps.markEmailWithStar(lastEmailSubject);
//		steps.goToStarFolder();
		Assert.assertTrue(steps.checkIfMailIsInInbox(lastEmailSubject));
	}

	@AfterMethod
	public void tearDown() {
		steps.closeBrowser();
	}

	// @AfterClass
	// public void cleanUp(){
	// steps.startBrowser();
	// steps.deleteAllMessages(userName1,passwordUser1);
	// steps.restartBrowser();
	// steps.deleteAllMessages(userName2,passwordUser2);
	// steps.restartBrowser();
	// steps.deleteAllMessages(userName3,passwordUser3);
	// steps.restartBrowser();
	// steps.setDefaultTheme(userName3,passwordUser3);
	// steps.deleteFiltersAndForwarding(userName2,passwordUser2);
	// }

}
