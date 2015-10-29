package com.epam.byta.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBoxPage {

	private WebDriver driver;

	@FindBy(xpath = "//div[@class='ar9 T-I-J3 J-J5-Ji']")
	private WebElement buttonDelete;

	@FindBy(xpath = "//div[@class='asl T-I-J3 J-J5-Ji']")
	private WebElement buttonReportSpam;

	@FindBy(xpath = "//div[text()='Not spam'] [@role='button']")
	private WebElement buttonNotSpam;

	@FindBy(xpath = "//div[text()='COMPOSE']")
	private WebElement buttonCompose;

	@FindBy(xpath = "//div[@class='T-Jo-auh']")
	private WebElement checkboxLastEmail;

	@FindBy(xpath = "//span[text()='More']")
	private WebElement menuMore;

	@FindBy(partialLinkText = "Spam")
	private WebElement menuSpam;

	@FindBy(partialLinkText = "Trash")
	private WebElement menuTrash;

	@FindBy(xpath = "//div[@id='ms']")
	private WebElement menuSettings;

	@FindBy(xpath = "//div[@class='aos T-I-J3 J-J5-Ji']")
	private WebElement buttonSettings;

	@FindBy(xpath = "//input[@id = 'gbqfq']")
	private WebElement searchInput;

	@FindBy(xpath = "//input[@id = 'gs_taif50']")
	private WebElement messageSubject;

	@FindBy(xpath = "//span[text()='Delete all spam messages now']")
	private WebElement linkDeleteAllSpam;

	@FindBy(xpath = "//span[text()='Empty Trash now']")
	private WebElement linkEmptyTrash;

	@FindBy(xpath = "//span[@aria-label = 'Starred'][@title = 'Starred']")
	private WebElement starBadge;

	@FindBy(xpath = "//img[@alt = 'Attachment'][@src = 'images/cleardot.gif']")
	private WebElement attachBadge;

	public MailBoxPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public ComposeEmailPage goToComposeEmail() {
		buttonCompose.click();
		return new ComposeEmailPage(this.driver);
	}

	public void markAsSpam() {
		checkboxLastEmail.click();
		buttonReportSpam.click();
	}

	public void showMore() {
		menuMore.click();
	}

	public void clickSpamLink() {
		menuSpam.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(linkDeleteAllSpam));
	}

	public void clickTrashLink() {
		menuTrash.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(linkEmptyTrash));
	}

	public void clickSettingsButton() {
		buttonSettings.click();
	}

	public SettingsPage goToSettings() {
		menuSettings.click();
		return new SettingsPage(this.driver);
	}

	public boolean findMailBySubject(String subject) {
		return this.driver.findElements(By.xpath("//span/b[text()='" + subject + "']")).size() != 0;
	}

	public void openMailBySubject(String subject) {
		this.driver.findElement(By.xpath("//span/b[contains(text(),'" + subject + "')]")).click();
	}

	public void clickConfirmationLink() {
		this.driver.findElement(By.partialLinkText(".google.com/mail/")).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		this.driver.findElement(By.xpath("//input[@value='Confirm']")).click();
		this.driver.switchTo().defaultContent();
		this.driver.close();
	}

	public void submitToSearch(String query) {
		searchInput.click();
		searchInput.clear();
		searchInput.sendKeys(query);
		searchInput.sendKeys(Keys.RETURN);
		searchInput.submit();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 3);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@class = 'Dj']/b[3]"), "1"));
		} catch (TimeoutException ex) {
			submitToSearch(query);
		}
	}

	public void deleteMessages(String searchIn) {
		submitToSearchWithoutWait(searchIn);
		checkboxLastEmail.click();
		buttonDelete.click();
	}

	private void submitToSearchWithoutWait(String searchIn) {
		try {
			while (this.driver.findElements(By.xpath("//span[@class = 'Dj']")).size() != 0) {
				searchInput.click();
				searchInput.clear();
				searchInput.sendKeys(searchIn);
				searchInput.sendKeys(Keys.RETURN);
				searchInput.submit();
			}
		} catch (ElementNotVisibleException ex) {
			return;
		}
	}

	public String returnLastSubject() {
		return this.driver.findElement(By.xpath("//td/div/div/div/span/b")).getText();
	}

	public void markAsNotSpam(String lastEmailSubject) {
		this.driver.navigate().refresh();
		checkboxLastEmail.click();
		buttonNotSpam.click();
	}

}
