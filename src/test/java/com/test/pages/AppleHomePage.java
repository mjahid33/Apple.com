package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.selenium.BasePage;

public class AppleHomePage extends BasePage {

	public AppleHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Buy']")
	private WebElement buyButton;

	@FindBy(css = "[id='noTradeIn_label']")
	private WebElement noTradeInButton;

	@FindBy(xpath = "(//span[text()='AppleCare+'])[1]")
	private WebElement appleCareCoveragePlus;

	@FindBy(xpath = "//span[text()='Monthly coverage until cancelled']")
	private WebElement coverageType;

	@FindBy(css = "[name='add-to-cart']")
	private WebElement addtoBag;

	@FindBy(css = "[name='proceed']")
	private WebElement reviewbag;

	@FindBy(css = "[id='shoppingCart.actions.navCheckout']")
	private WebElement checkOutButton;

	@FindBy(xpath = "//span[@class='rc-summaryheader-producttitle']")
	private WebElement verifyColour;

	@FindBy(css = "[id='signIn.guestLogin.guestLogin']")
	private WebElement continueAsGuestButton;

	@FindBy(xpath = "//span[text()='I’d like it delivered']")
	private WebElement likeToDelivered;

	@FindBy(xpath = "//span[contains(text(),'Continue to Shipping Address')]")
	private WebElement clickToContinue;

	@FindBy(xpath = "//input[contains(@id,'postalCode')]")
	private WebElement zipCodeInputBox;

	@FindBy(xpath = "//span[contains(@id,'postalCode_label')]")
	private WebElement zipcodeClick;

	@FindBy(xpath = "//span[text()='Apply']")
	private WebElement clickApply;

	@FindBy(xpath = "//span[text()='Standard Delivery']")
	private WebElement standardDelivery;

	@FindBy(css = "[id='rs-checkout-continue-button-bottom']")
	private WebElement continueShippingAddressButton;

	@FindBy(css = "[name='firstName']")
	private WebElement firstNameInputbox;

	@FindBy(css = "[name='lastName']")
	private WebElement lastNameInputbox;

	@FindBy(css = "[name='street']")
	private WebElement streetInputbox;

	@FindBy(css = "[name='street2']")
	private WebElement landmarkInputbox;

	@FindBy(css = "[name='emailAddress']")
	private WebElement emailInputbox;

	@FindBy(css = "[name='fullDaytimePhone']")
	private WebElement phoneInputbox;

	@FindBy(css = "[id='rs-checkout-continue-button-bottom']")
	private WebElement continuePayment;

	public void clickOnTheDesiredProductTab(String text) throws InterruptedException {
		Thread.sleep(2000);
		String locator = "//span[text()='" + text + "']";
		jsClick(driver.findElement(By.xpath(locator)));
	}

	public void theUserSearchesFor(String text) {
		String locator = "//a[@class='chapternav-link']//span[text()='" + text + "']";
		waitForElement(locator);
		waitAndClick(driver.findElement(By.xpath(locator)));
	}

	public void selectTheModel() {
		String locator = "//span[contains(text(),'6.1-inch')]/parent::span";
		waitForElement(locator);
		waitAndClick(driver.findElement(By.xpath(locator)));
	}

	public void selectTheProductColour(String text) {
		String locator = "//input[contains (@id,'" + text + "')]/parent::li";
		waitForElement(locator);
		jsClick(driver.findElement(By.xpath(locator)));
	}

	public void clickOnBuyButton() {
		waitForElementClickable(buyButton);
		waitAndClick(buyButton);
	}

	public void selectColour(String text) {
		String locator = "//span[text()='" + text + "']/parent::label";
		waitForElement(locator);
		waitAndClick(driver.findElement(By.xpath(locator)));
	}

	public String verifyChoosenColour() {
		return getText(verifyColour);
	}

	public void selectTheStorage(String text) {
		scrollDown();
		String locator = "//span[text()='" + text + "']/parent::span";
		waitForElement(locator);
		waitAndClick(driver.findElement(By.xpath(locator)));
	}

	public void clickOnNoTradeInButton() {
		waitAndClick(noTradeInButton);
	}

	public void selectPaymentOption(String text) throws InterruptedException {
		Thread.sleep(5000);
		scrollDown();
		String locator = "//span[text()='" + text + "']";
		waitForElement(locator);
		waitAndClick(driver.findElement(By.xpath(locator)));
	}

	public void chooseAPaymentCarrier(String text) {
		scrollDown();
		String locator = "//span[text()='" + text + "']/parent::span";
		waitForElementClickable(driver.findElement(By.xpath(locator)));
		jsClick(driver.findElement(By.xpath(locator)));
	}

	public void selectAppleCareCoverage() throws InterruptedException {
		Thread.sleep(5000);
		scrollIntoView(appleCareCoveragePlus);
		waitForElementClickable(appleCareCoveragePlus);
		jsClick(appleCareCoveragePlus);
	}

	public void selectCoverageType() {
		scrollIntoView(coverageType);
		jsClick(coverageType);
	}

	public void clickOnAddToBagButton() {
		waitForPageLoaded();
		jsClick(addtoBag);
	}

	public void clickOnReviewBag() {
		waitAndClick(reviewbag);
	}

	public void clickOnCheckoutButton() {
		waitForPageLoaded();
		waitAndClick(checkOutButton);
	}

	public void proceedAsGuestUser() {
		waitForPageLoaded();
		waitAndClick(continueAsGuestButton);
	}

	public void clickOnLikeToDelivered() {
		waitAndClick(likeToDelivered);
	}

	public void clickToContinue() {
		waitAndClick(clickToContinue);
	}

	public void enterValidZipcode(String zipCode) throws InterruptedException {
		waitAndClick(zipCodeInputBox);
		inputText(zipCodeInputBox, zipCode);
	}

	public void clickOnApply() {
		waitForElementClickable(clickApply);
		waitAndClick(clickApply);
	}

	public void clickOnStandardDelivery() {
		waitForElementClickable(standardDelivery);
		waitAndClick(standardDelivery);
	}

	public void clickOnContinueShippingAddressButton() {
		waitAndClick(continueShippingAddressButton);
	}

	public void enterFirstnameAndLastname(String firstname, String lastname) {
		waitAndClick(firstNameInputbox);
		inputText(firstNameInputbox, firstname);
		waitAndClick(lastNameInputbox);
		inputText(lastNameInputbox, lastname);
	}

	public void enterStreetAddressAndLandmark(String street, String landmark) {
		waitAndClick(streetInputbox);
		inputText(streetInputbox, street);
		waitAndClick(landmarkInputbox);
		inputText(landmarkInputbox, landmark);
	}

	public void enterEmailAndPhone(String email, String phone) {
		waitAndClick(emailInputbox);
		inputText(emailInputbox, email);
		waitAndClick(phoneInputbox);
		inputText(phoneInputbox, phone);
	}

	public void clickOnContinuePaymentButton() {
		waitAndClick(continuePayment);
	}

}
