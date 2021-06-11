package com.OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_CrossBrowser_Example {

	WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void LaunchBrowser(String browser) throws Exception {

		if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("ie")){
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}

	}

	/*@Test(dataProvider="Login")
	public void OrangeHRM_Login(String uname, String upass) {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.name("txtUsername")).clear();
		driver.findElement(By.name("txtUsername")).sendKeys(uname);
		driver.findElement(By.name("txtPassword")).clear();
		driver.findElement(By.name("txtPassword")).sendKeys(upass);
		driver.findElement(By.id("btnLogin")).click();
		String Element = driver.findElement(By.linkText("Dashboard")).getText();
		System.out.println(Element);

	}*/

	@Test
		public void OrangeHRM_Login() {
				driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
				driver.findElement(By.name("txtUsername")).clear();
				driver.findElement(By.name("txtUsername")).sendKeys("Admin");
				driver.findElement(By.name("txtPassword")).clear();
				driver.findElement(By.name("txtPassword")).sendKeys("admin123");
				driver.findElement(By.id("btnLogin")).click();
				String Element = driver.findElement(By.linkText("Dashboard")).getText();
				System.out.println(Element);

		}
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

}
