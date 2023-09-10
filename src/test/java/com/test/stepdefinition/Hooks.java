package com.test.stepdefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.test.selenium.AbstractPage;
import com.test.selenium.Configuration;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks extends AbstractPage {

	public enum DriverType {
		Firefox, Edge, Chrome
	}

	@Before
	public void setUp() throws Exception {

		String browserType = Configuration.getConfig("Browser");
		if (DriverType.Firefox.toString().toLowerCase().equals(browserType.toLowerCase())) {
			driver = new FirefoxDriver();
		} else if (DriverType.Edge.toString().toLowerCase().equals(browserType.toLowerCase())) {
			driver = new EdgeDriver();
		} else if (DriverType.Chrome.toString().toLowerCase().equals(browserType.toLowerCase())) {

			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
		} else {
			throw new Exception("Please pass a valid browser type value");
		}
		// Maximize window
		driver.manage().window().maximize();

		// Delete cookies
		driver.manage().deleteAllCookies();
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			//scenario.attach(screenshot, "image/png"); // stick it in the report
		}
		driver.close();
	}

}
