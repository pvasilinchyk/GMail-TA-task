package com.epam.byta.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForwardingSettingsPage {

	private WebDriver driver;

	@FindBy(xpath = "//input[@value = 'Add a forwarding address']")
	private WebElement addForwardingAdressButton;

	@FindBy(xpath = "//button[@name = 'next']")
	private WebElement buttonNext;

	@FindBy(xpath = "//input[@type= 'text']")
	private WebElement inputForAdress;

	@FindBy(xpath = "//button[@class='J-at1-auR'][@name='ok']")
	private WebElement buttonOK;

	@FindBy(xpath = "//iframe[@class='ds']")
	private WebElement forwardIframe;

	@FindBy(xpath = "//input[@value='1'][@name='sx_em']")
	private WebElement radioButtonForwardACopy;

	@FindBy(xpath = "//button[text()='Save Changes']")
	private WebElement saveChangesButton;

	public ForwardingSettingsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void addForwardingAdress(String adress) {
		addForwardingAdressButton.click();
		driver.switchTo().activeElement().sendKeys(adress);
		buttonNext.click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.findElement(By.xpath("//input[@value= 'Proceed']")).click();
	}

	public void radioButtonForwardCheck() {
		radioButtonForwardACopy.click();
		WebDriverWait wait = new WebDriverWait(driver, 2);
		try {

			wait.until(ExpectedConditions.elementToBeClickable(saveChangesButton));
			saveChangesButton.click();
		} catch (TimeoutException ex) {
			this.driver.navigate().refresh();
			radioButtonForwardCheck();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Primary']")));
	}

}
