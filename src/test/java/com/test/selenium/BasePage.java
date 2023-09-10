/*
  Class to initialize all page methods for the actions available
  under that page. 
  All scripts must call the respective methods from the respective
  pages to achieve any action.

 */
package com.test.selenium;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

	protected static final int DEFAULT_WAIT_4_ELEMENT = 30;
	protected static final int DEFAULT_WAIT_4_PAGE = 30;
	private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
	protected static WebDriverWait ajaxWait;
	protected WebDriver driver;
	protected String title;
	protected long timeout = 100;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Get WebDriver object
	 * 
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Wait Element and click
	 * 
	 * @param element
	 */
	public void waitAndClick(WebElement element) {
		logger.info("Wait and Click");
		waitForElementClickable(element);
		waitForElementVisible(element);
		element.click();
	}

	/**
	 * Click on element
	 * 
	 * @param element : element object
	 */
	public void clickOn(WebElement element) {
		logger.info("click on element " + element.toString());
		element.click();
	}

	/**
	 * click on element using element locator (XPath, CSS)
	 * 
	 * @param locator : element locator
	 */
	public void clickOn(String locator) {

		try {
			logger.info("click on element " + locator);
			WebElement el = getDriver().findElement(ByLocator(locator));
			el.click();
		} catch (StaleElementReferenceException ex) {
			logger.info("click on element " + locator);
			WebElement el = getDriver().findElement(ByLocator(locator));
			el.click();
		}
	}

	/**
	 * Click on element using java scripts
	 * 
	 * @param webElement
	 */
	public void jsClick(WebElement webElement) {
		logger.info("click on element " + webElement.toString());
		waitForElementVisible(webElement);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", webElement);
	}

	/**
	 * Get Page title
	 * 
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Get Page source
	 * 
	 * @return
	 */
	public String getPageSource() {
		return driver.getPageSource();
	}

	/**
	 * Enter text in to input box
	 * 
	 * @param element : WebElement object
	 * @param text    : text to enter in input box
	 */
	public void inputText(WebElement element, String text) {
		logger.info("inputText, text={}", text);
		waitForElementVisible(element);
		element.sendKeys(text);
	}

	/**
	 * Clear input box and enter text
	 * 
	 * @param element : WebElement object
	 * @param text    : text to enter in input box
	 */
	public void inputTextWitClear(WebElement element, String text) {
		logger.info("inputText, text={}", text);
		waitForElementVisible(element);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Handle locator type
	 */
	public By ByLocator(String locator) {
		By result;
		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.id(locator.replace("#", ""));
		} else if (locator.startsWith("name=")) {
			result = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		} else {
			result = By.cssSelector(locator);
		}
		return result;
	}

	/** Set wait for driver */
	public void setWaitTime(int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
	}

	/**
	 * Set wait for driver
	 */
	public void setWaitTimeToZero() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	/**
	 * check if element present
	 * 
	 * @param element
	 * @return
	 */
	public Boolean isElementPresent(WebElement element) {
		return element.isDisplayed();
	}

	/**
	 * check if Element present
	 * 
	 * @param element : element object
	 * @return
	 */
	public Boolean isElementDisplay(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (Exception ex) {
			return false;
		} catch (Error ex) {
			return false;
		}
	}

	/** Wait for element to be present */
	public WebElement waitForElementClickable(WebElement element) {
		logger.info("waitForElement" + element.toString());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/** Wait for element to be present */
	public WebElement waitForElementVisible(WebElement element) {
		logger.info("waitForElement" + element.toString());
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * @param by     : By object
	 * @param driver
	 */
	public void waitForElementToBecomeVisible(By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/** Wait for element by passing argument as string. */
	public void waitForElement(String locator) {
		logger.info("waitForElement " + locator);
		for (int i = 0; i < timeout; i++) {
			try {
				driver.findElement(ByLocator(locator));
				Thread.sleep(1000);
				break;
			} catch (Exception e) {
			}
		}
	}

	/** Wait for JSLoad to load */
	public boolean _waitForJStoLoad() {
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = driver -> {
			try {
				return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
			} catch (Exception e) {
				return true;
			}
		};

		/** wait for JavaScript to load */
		ExpectedCondition<Boolean> jsLoad = driver -> {
			Object rsltJs = ((JavascriptExecutor) driver).executeScript("return document.readyState");
			if (rsltJs == null) {
				rsltJs = "";
			}
			return rsltJs.toString().equals("complete") || rsltJs.toString().equals("loaded");
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

	/** Wait for element to be present */
	public WebElement waitForPresenceOfElementLocatedBy(final By by, int timeOutInSeconds) {
		WebElement element;
		try {
			// setWaitTimeToZero(driver);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param webElement
	 * @param text
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForTextPresentInElement(WebElement webElement, String text, int timeOutInSeconds) {
		boolean notVisible;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		notVisible = wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));

		return notVisible;
	}

	/**
	 * Wait for element contain text
	 * 
	 * @param by               : By object of element
	 * @param text             : Text to be present
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean waitForTextPresentInElement(By by, String text, int timeOutInSeconds) {
		boolean notVisible;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		notVisible = wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
		return notVisible;
	}

	/**
	 * Wait for element contains text
	 * 
	 * @param element
	 * @param text
	 */
	public void waitForTextPresentInElement(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * Wait element to be in visibility
	 * 
	 * @param by
	 */
	public void waitForElementToBecomeInvisible(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Wait for page load
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").equals("complete");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(expectation);
	}

	/**
	 * Select element by visible text
	 * 
	 * @param element
	 * @param targetValue : visible text of Option
	 */
	public void selectDropDownByText(WebElement element, String targetValue) {
		waitForElementVisible(element);
		logger.info("selecting visible text " + targetValue + " from dropdown " + element.toString());
		new Select(element).selectByVisibleText(targetValue);
	}

	/**
	 * Select drop down value using index
	 * 
	 * @param element : : WebELement object of drop down
	 * @param index   : index of option
	 */
	public void selectDropDownByIndex(WebElement element, int index) {
		waitForElementVisible(element);
		logger.info("selecting value index " + index + " from dropdown " + element.toString());
		new Select(element).selectByIndex(index);
	}

	/**
	 * Select drop down value using value
	 * 
	 * @param element     : WebELement object of drop down
	 * @param targetValue : value of option
	 */
	public void selectDropDownByValue(WebElement element, String targetValue) {
		logger.info("selecting value " + targetValue + " from dropdown " + element.toString());
		waitForElementVisible(element);
		new Select(element).selectByValue(targetValue);
	}

	/**
	 * Get text from locator
	 * 
	 * @param element : object of element
	 * @return
	 */
	public String getCssValueValue(WebElement element, String cssName) {
		_waitForJStoLoad();
		logger.info("getting css value from locator " + element.toString());
		Assert.assertTrue("Element Locator :" + element + " Not found", isElementPresent(element));
		return element.getCssValue(cssName);
	}

	/**
	 * Get text from locator
	 * 
	 * @param element : object of element
	 * @return
	 */
	public String getText(WebElement element) {
		logger.info("getting text from locator " + element.toString());
		_waitForJStoLoad();
		waitForElementVisible(element);
		Assert.assertTrue("Element Locator :" + element + " Not found", isElementPresent(element));
		return element.getText();
	}

	/**
	 * Get Attribute value of WebElement object
	 * 
	 * @param element       : WebElement object
	 * @param attributeName : attribute name
	 * @return
	 */
	public String getAttribute(WebElement element, String attributeName) {
		logger.info("getting attribute value of " + attributeName + " from elememt locator " + element.toString());
		waitForElementVisible(element);
		Assert.assertTrue("Element Locator :" + element + " Not found", isElementPresent(element));
		return element.getAttribute(attributeName);
	}

	/**
	 * Perform Drag and drop
	 * 
	 * @param drag : source webElement object
	 * @param drop : target webElement object
	 */
	public void dragAndDrop(WebElement drag, WebElement drop) {
		Actions builder = new Actions(driver);
		waitForElementVisible(drag);
		waitForElementVisible(drop);
		logger.info("drag and drop of element " + drag.toString() + " to element " + drop.toString());
		Action dragAndDrop = builder.clickAndHold(drag).moveToElement(drop).release(drop).build();
		dragAndDrop.perform();
	}

	/**
	 * Move to element
	 * 
	 * @param element : WebElement object
	 */
	public void moveToElement(WebElement element) {
		logger.info("mouse moving on element " + element.toString());
		waitForElementVisible(element);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).release().build();
	}

	/**
	 * Move and click on element
	 * 
	 * @param element : WebElement object
	 */
	public void moveAndClickOnElement(WebElement element) {
		logger.info("mouse click on element " + element.toString());
		waitForElementVisible(element);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click(element).release().build();
	}

	/**
	 * Move and right click on element
	 * 
	 * @param element : WebElement object
	 */
	public void moveAndRigtClickOnElement(WebElement element) {
		logger.info("right clicking on element " + element.toString());
		waitForElementVisible(element);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).contextClick(element).release().build();
	}

	public void executeJsScroolToElement(WebElement element) {
		waitForElementVisible(element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String getH1Value() {
		waitForElement("//h1");
		return getText(driver.findElement(By.xpath("//h1")));
	}

	public String getH2Value() {
		waitForElement("//h2");
		return getText(driver.findElement(By.xpath("//h2")));
	}

	public String getH2Value(String text) {
		// waitForTextPresentInElement(driver.findElement(By.xpath("//h2")), text);
		waitForElement("//h2");
		return getText(driver.findElement(By.xpath("//h2")));
	}

	public void clickOnLinkText(String linkText) {
		String locator = "//a[text()='" + linkText + "']";
		waitForElement(locator);
		WebElement element = driver.findElement(By.xpath(locator));
		executeJsScroolToElement(element);
		jsClick(element);
	}

	public void clickOnPartialLinkText(String linkText) {
		String locator = "//a[contains(text(), '" + linkText + "')]";
		waitForElement(locator);
		waitAndClick(driver.findElement(By.xpath(locator)));
	}

	public void sleepInSecond(int seconds) {
		int time = seconds * 1000;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 350);");
	}

	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -350);");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}