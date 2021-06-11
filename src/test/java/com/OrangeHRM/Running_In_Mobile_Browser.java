package com.OrangeHRM;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Running_In_Mobile_Browser {
	ChromeDriver driver;
	@Test
	public void SignOn() {

		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
			driver.findElement(By.name("txtPassword")).sendKeys("admin123");
			driver.findElement(By.id("btnLogin")).click();

	}
	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		ChromeOptions iPhoneOption = new ChromeOptions();

		driver = new ChromeDriver(iPhoneOption);
		driver.manage().window().maximize();
		driver.manage().window().setSize( new Dimension(375, 812));

	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

}
