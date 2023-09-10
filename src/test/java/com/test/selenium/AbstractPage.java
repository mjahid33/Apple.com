package com.test.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.test.pages.AppleHomePage;

public class AbstractPage {

	protected static WebDriver driver;

	protected static AppleHomePage appleHomePage;

	protected AppleHomePage getAppleHomePage() {
		if (appleHomePage == null) {
			appleHomePage = PageFactory.initElements(driver, AppleHomePage.class);
		}
		return appleHomePage;
	}

}
