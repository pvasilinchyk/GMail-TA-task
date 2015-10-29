package com.epam.byta.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThemesSettingsPage {

	private WebDriver driver;

	@FindBy(linkText = "Set Theme.")
	private WebElement setThemeLink;
	
	@FindBy(xpath = "//div/img[@src='//ssl.gstatic.com/ui/v1/icons/mail/themes/random/previewHD.png']")
	private WebElement randomThemeThumbnail;

	@FindBy(xpath = "//div/img[@src='//ssl.gstatic.com/ui/v1/icons/mail/themes/beach2/previewHD.png']")
	private WebElement beachThemeThumbnail;

	@FindBy(xpath = "//div[@role='button'][text()='Save']")
	private WebElement saveThemeButton;

	@FindBy(xpath = "//div[@role='button'][text()='My Photos']")
	private WebElement myPhotosButton;

	@FindBy(xpath = "//div[text()='Upload a photo']")
	private WebElement uploadPhotosTab;

	@FindBy(xpath = "//div[text()='Select a photo from your computer']")
	private WebElement selectPhotoFromComputerButton;

	@FindBy(xpath = "//iframe[@class = 'KA-JQ']")
	private WebElement setBackGroundImageFrame;

	public ThemesSettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void setTheme() {
		setThemeLink.click();
		beachThemeThumbnail.click();
		saveThemeButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alertdialog']")));
	}

	public boolean checkTheme() {
		return this.driver.getPageSource()
				.contains("//ssl.gstatic.com/ui/v1/icons/mail/themes/beach2/");
	}

	public void setImproperTheme(String pathToAttach) throws AWTException {
		try {
			setThemeLink.click();
			myPhotosButton.click();
			this.driver.switchTo().frame(setBackGroundImageFrame);
			uploadPhotosTab.click();
			selectPhotoFromComputerButton.click();
			StringSelection ss = new StringSelection(pathToAttach);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Robot robot = new Robot();
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(500);
			robot.keyRelease(KeyEvent.VK_V);
			robot.delay(500);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(500);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (NoSuchElementException ex) {
			this.driver.navigate().refresh();
			setImproperTheme(pathToAttach);
		}

	}

	public boolean isMessagePresent() {
		return this.driver.findElements(By.xpath("//div[contains(text(),' is not supported for upload.')]")).size() != 0;
	}

	public void setRandomTheme() {
		setThemeLink.click();
		randomThemeThumbnail.click();
		saveThemeButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='alertdialog']")));
		
	}
}
