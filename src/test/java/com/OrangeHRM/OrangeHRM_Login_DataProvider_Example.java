package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class OrangeHRM_Login_DataProvider_Example extends  OrangeHRM_Login_DataProvider_TestData {
	WebDriver driver;

	// ChromeDriver driver;
	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		// WebDriverManager.firefoxdriver().setup();
		// WebDriverManager.edgedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}
	@AfterTest
	public void CloseBrowser() {
		// driver.close();//Close will close only current chrome browser
		driver.quit();
	}

	@Test(dataProvider="Login")
	public void LoginToOrangeHRM(String Uname, String Upass) throws InterruptedException {

		// driver.manage().window().fullscreen();
		// This will wait for Page to load
		// driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		// This will store or rememember the cookies or navigation in terms of
		// back and forward
		
		driver.findElement(By.name("txtUsername")).sendKeys(Uname);
		driver.findElement(By.name("txtPassword")).sendKeys(Upass);
		driver.findElement(By.id("btnLogin")).click();
		// Verify that Dashboard page displayed
		String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
		String ExpElement = "Dashboard";
		Assert.assertEquals(ActElement, ExpElement);
		System.out.println(ActElement);
		// ----------------To Verify Logout Function----------------

		/*
		 * driver.findElement(By.id("welcome")).click(); Thread.sleep(3000);
		 * driver.findElement(By.linkText("Logout")).click(); String ActLogpanel
		 * = driver.findElement(By.id("logInPanelHeading")).getText(); String
		 * ExpLogpanel="LOGIN Panel"; Assert.assertEquals(ActLogpanel,
		 * ExpLogpanel); System.out.println(ActLogpanel);
		 */
		// ----------------To Verify Logout Function without using
		// Assert----------------
		Thread.sleep(3000);
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.id("logInPanelHeading")).isDisplayed();

	}

}
