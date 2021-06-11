package com.OrangeHRM;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Capture_Video_Example {
	WebDriver driver;
	
	ATUTestRecorder recorder;

	@BeforeTest
	public void setup() throws Exception {
		recorder =  new ATUTestRecorder("\\Capture_Video");
		//To start video recording.
		recorder.start();  
		WebDriverManager.chromedriver().setup();
		//WebDriverManager.firefoxdriver().setup();
		//WebDriverManager.edgedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
	}

	@Test
	public void getScrollStatus() throws Exception {  
		driver.manage().window().setSize(new Dimension(400,768));
		Thread.sleep(5000);  

		driver.manage().window().setSize(new Dimension(400,400));
		Thread.sleep(5000);

		driver.manage().window().setSize(new Dimension(1024,400)); 
		Thread.sleep(5000);
	} 

	@AfterTest
	public void Close() throws Exception {
		driver.quit();
		//To stop video recording.
		recorder.stop();
	}
}
