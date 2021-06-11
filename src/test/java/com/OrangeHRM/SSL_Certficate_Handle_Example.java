package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class SSL_Certficate_Handle_Example {
	/* Two way we can handle SSL Certificate
	 * 1. Using "capabilities.setAcceptInsecureCerts(true);"
	 * 2. Using capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	 */

	WebDriver driver;

	/*	@BeforeTest
	public void LaunchBrowserChrome() {
		 // Create object of DesiredCapabilities class
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Set ACCEPT_SSL_CERTS  variable to true
		capabilities.setAcceptInsecureCerts(true);
		ChromeOptions options = new ChromeOptions();
		options.merge(capabilities);  //this will merge the capabilities to the ChromeOptions
		// setting system property for Chrome browser
		WebDriverManager.chromedriver().setup();
		// create Google Chrome instance and maximize it
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}*/


	/*@BeforeTest
	public void LaunchBrowserFirefox() {
		 // Create object of DesiredCapabilities class
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// Set ACCEPT_SSL_CERTS  variable to true
		capabilities.setAcceptInsecureCerts(true);
		FirefoxOptions options = new FirefoxOptions();
		options.merge(capabilities);  //this will merge the capabilities to the ChromeOptions
		// setting system property for Firefox browser
		WebDriverManager.firefoxdriver().setup();
		// create Google Firefox instance and maximize it
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
	}*/

	@BeforeTest
	public void LaunchBrowserChrome() {
		// Create object of DesiredCapabilities class
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// other way to handle ACCEPT_SSL_CERTS or any insecure website.
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		options.setHeadless(true);
		options.merge(capabilities);
		//this will merge the capabilities to the ChromeOptions
		// setting system property for Chrome browser
		WebDriverManager.chromedriver().setup();
		// create Chrome Object and maximize it
		//driver = new ChromeDriver(capabilities);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@Test
	public void openApplication() {
		System.out.println("Navigating to application");
		driver.get("https://expired.badssl.com/");
		String ActTitle=driver.getTitle();
		String ExpTitle="expired.badssl.com";
		Assert.assertEquals(ActTitle, ExpTitle);
	}

	@AfterTest
	public void tearDown() {
		/*if (driver != null)
			driver.quit();*/
	}

}
