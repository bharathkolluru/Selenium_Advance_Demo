package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

public class HeadlessBrowser_Test {
	ChromeDriver driver;
	ChromeOptions options = new ChromeOptions();
	@Test
	public void Headless() {
		driver.findElementByName("q").sendKeys("Selenium Testing",Keys.ENTER);
		//String ReturnText=driver.findElement(By.xpath("//span[text()='Selenium testing']")).getText();
		//Assert.assertEquals("Selenium testing", ReturnText);
		
	}

	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		//options.setHeadless(true);

		options.addArguments("incognito");

		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.google.co.in/");
	}

	@AfterTest
	public void CloseBrowser() {
		driver.close();//Close will close only current chrome browser
	}

}
