package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class OrangeHRM_Add_User_And_Verify_In_Table {
	ChromeDriver driver;
	@BeforeTest
	public void LaunchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}
	@Test(priority=1)
	// This is for Sign On Functionality
	public void Sign_On() throws InterruptedException

	{
		driver.findElementByName("txtUsername").sendKeys("Admin");
		driver.findElementByName("txtPassword").sendKeys("admin123");
		driver.findElementById("btnLogin").click();
		String Element = driver.findElement(By.linkText("Dashboard")).getText();
		System.out.println(Element);
		Assert.assertEquals("Dashboard", Element);
		
	}
	
	@Test(priority=2)
	public void AddUsers_Page()
	{
		WebElement admin = driver.findElementByLinkText("Admin");
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		WebElement usermanagement = driver.findElementByLinkText("User Management");
		action.moveToElement(usermanagement).build().perform();
		driver.findElementByLinkText("Users").click();
		//User Click on Add Button to add users
		driver.findElementById("searchBtn").click();
		driver.findElementById("btnAdd").isDisplayed();
		
	}
	@Test(priority=3,enabled=true)
	public void VerifyAddedUser() throws InterruptedException
	{
		driver.findElementById("btnAdd").click();
		
		//Enter all the mandatory Fields
		Select SelectPass = new Select(driver.findElementById("systemUser_userType"));
		//SelectPass.selectByValue("1");
		SelectPass.selectByVisibleText("Admin");
		
		//SelectPass.selectByIndex(0);
		driver.findElementById("systemUser_employeeName_empName").sendKeys("Fiona Grace");
		
		Random randomGenerator = new Random();
		
		int randomInt = randomGenerator.nextInt(1000); 
		driver.findElementById("systemUser_userName").sendKeys("Bharath"+randomInt);
		driver.findElementById("systemUser_password").sendKeys("Pass@2212");
		driver.findElementById("systemUser_confirmPassword").sendKeys("Pass@2212");
		Thread.sleep(5000);
		driver.findElementById("btnSave").click();
		Thread.sleep(5000);
		
		String ExpUserName="Bharath"+randomInt;
		System.out.println(ExpUserName);
	    WebElement cellIneed = driver.findElementByXPath("//a[text()='"+ExpUserName+"']");
		//WebElement cellIneed = driver.findElementByXPath("//a[contains(text(),'abhidixit"+randomInt+"')]");
		//WebElement cellIneed = driver.findElementByXPath("//a[text()='Dixit"+randomInt+"')]");
		//a[contains(text(),'abhidixit699')]
		//---//a[text()='"+ExpUserName+"']
	    String valueIneed = cellIneed.getText();
	    System.out.println("Cell value is : " + valueIneed); 
	    Assert.assertEquals(ExpUserName, valueIneed);
	}
	@AfterTest
	public void CloseBrowser()
	{
		driver.quit();
	}

}
