package com.epam.byta.wdsingletone;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingletone {

	private static WebDriver driver;

	private WebDriverSingletone() {
	}

	public static WebDriver getWebDriverInstance() {
		if (null == driver) {
			driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		}
		return driver;

	}

	public static void closeWebDriver() {
		driver.quit();
		driver = null;
	}
}
