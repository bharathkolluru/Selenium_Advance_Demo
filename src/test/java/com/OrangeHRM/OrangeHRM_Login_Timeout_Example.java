package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

public class OrangeHRM_Login_Timeout_Example {
	ChromeDriver driver;
	@Test(timeOut=4000,enabled=false)
	public void Login() {
		driver.findElementByName("txtUsername").sendKeys("Admin");
		driver.findElementByName("txtPassword").sendKeys("admin123");
		driver.findElementById("btnLogin").click();
		driver.findElementByLinkText("Dashboard").isDisplayed();
	}
	@BeforeTest
	public void LaunchBrowser() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@AfterTest
	public void CloseBrowser() {

		driver.quit(); // Quit will close all browser opened by Selenium
	}

}
