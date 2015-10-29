package com.epam.byta.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComposeEmailPage {
	private WebDriver driver;

	@FindBy(xpath = "//textarea[@name='to']")
	private WebElement inputTo;

	@FindBy(xpath = "//input[@name='subjectbox']")
	private WebElement inputSubject;

	@FindBy(xpath = "//div[@aria-label='Message Body']")
	private WebElement inputMessage;

	@FindBy(xpath = "//div[text()='Send']")
	private WebElement buttonSend;

	@FindBy(xpath = "//div[@class = 'a1 aaA aMZ']")
	private WebElement buttonAttach;

	@FindBy(xpath = "//span[text()='Large files must be shared with Google Drive']")
	private WebElement alertLargeFile;
	
	@FindBy(xpath = "//div[@class = 'QT aaA aMZ']")
	private WebElement buttonEmoticon;
	
	@FindBy(xpath = "//div[@class='gmail_signature']")
	private WebElement emailSignature;
	
	public ComposeEmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public MailBoxPage sendEmail(String toWhom, String subject, String message) {
		inputTo.sendKeys(toWhom);
		inputSubject.sendKeys(subject);
		inputMessage.sendKeys(message);
		buttonSend.click();
		return new MailBoxPage(this.driver);
	}

	public MailBoxPage sendEmailWithAttach(String toWhom, String subject, String message, String pathToAttach)
			throws AWTException {
		try {
			inputTo.sendKeys(toWhom);
			inputSubject.sendKeys(subject);
			inputMessage.sendKeys(message);
		} catch (ElementNotVisibleException | StaleElementReferenceException ex) {
			this.driver.navigate().refresh();
			sendEmailWithAttach(toWhom, subject, message, pathToAttach);
		}
		buttonAttach.click();
		StringSelection ss = new StringSelection(pathToAttach);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.delay(500);
		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.delay(500);
		robot.keyPress(KeyEvent.VK_V);
//		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_V);
//		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_CONTROL);
//		robot.delay(500);
		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_ENTER);
//		robot.delay(3000);
//		if (this.driver.findElements(By.xpath("//div[@role='button'][@class='vq']")).size() == 0) {
//			robot.keyPress(KeyEvent.VK_ENTER);
//			robot.delay(500);
//			robot.keyRelease(KeyEvent.VK_ENTER);
//		}
		robot.delay(3000);
		buttonSend.click();
		robot.delay(500);
		return new MailBoxPage(this.driver);
	}

	public boolean isAlertPresent() {
		return this.driver.findElements(By.xpath("//span[text()='Large files must be shared with Google Drive']"))
				.size() != 0;
	}

	public List<WebElement> sendEmailWithEmoticon(String toWhom, String subject, String message) {
		inputTo.sendKeys(toWhom);
		inputSubject.sendKeys(subject);
		inputMessage.sendKeys(message);
		buttonEmoticon.click();
		List<WebElement> allEmoticons = driver.findElements(By.xpath("//div[@class='wVboN'][@style='']/button[@class='a8m']"));
		for(WebElement emoticon:allEmoticons){
			System.out.println(emoticon.getAttribute("data-emo"));
//			inputMessage.sendKeys(emoticon);
			new Actions(driver).moveToElement(emoticon).click().perform();
//		     emoticon.click();
		}
		buttonSend.click();
		return allEmoticons;
	}

	public boolean checkMailSignature(String msgSubject) {
		return emailSignature.getText().equals(msgSubject);
	}
}
