package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.test.selenium.BasePage;

public class AppleCartPage extends BasePage {

	public AppleCartPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "[id='globalnav-menubutton-link-bag']")
	private WebElement cartBagIcon;

	public void clickOnCartBagIcon() {
		waitAndClick(cartBagIcon);
	}

}
