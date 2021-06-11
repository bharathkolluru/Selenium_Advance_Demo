package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class OrangeHRM_Delete_All_Users {
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
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
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
	@Test(priority = 3)
	public void Delete_Employees() throws InterruptedException {
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

		//*[@id="resultTable"]/tbody/tr[1]/td[2]
		List<WebElement> rows=driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
		int rowsLength=rows.size();
		System.out.println(rowsLength);

		////*[@id='resultTable']/tbody/tr[]/td[2]

		String beforXpath="//*[@id='resultTable']/tbody/tr[";
		String AfterXpath="]/td[2]";
		for(int i=1; i<=rowsLength;i++)
		{
			String name=driver.findElement(By.xpath(beforXpath + i + AfterXpath)).getText();
			System.out.println(name);
			if(name.contains("Bharath"))
			{
				driver.findElement(By.xpath("//*[@id='resultTable']/tbody/tr["+i+"]/td[1]/input")).click();

			}
		}
		driver.findElement(By.id("btnDelete")).click();
		driver.findElement(By.id("dialogDeleteBtn")).click();
	}

	@AfterTest
	public void CloseBrowser()
	{
		driver.quit();
	}
}
