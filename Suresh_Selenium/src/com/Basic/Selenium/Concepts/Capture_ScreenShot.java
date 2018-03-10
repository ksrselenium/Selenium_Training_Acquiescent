package com.Basic.Selenium.Concepts;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;


public class Capture_ScreenShot {

@Test
public void takeScreenShot() throws IOException{
		System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		 driver.get("https://www.hdfcbank.com/");
		 mainScreenshot(driver, "bank");
		 driver.navigate().to("https://www.google.co.in");
		 mainScreenshot(driver,"Google");
		
	}

	
	static void mainScreenshot(WebDriver driver, String screenshotName) throws IOException
	{
		
    
		TakesScreenshot sr=  (TakesScreenshot) driver;
		
		File src=sr .getScreenshotAs(OutputType.FILE);
		
	File dec= new File ("C:\\Users\\suresh.kadenti\\Desktop\\Test Case Templates\\Autokatalogen\\"+screenshotName+".png");

		
			FileUtils.copyFile(src, dec);
	System.out.println("------------");
	
	
	}
	
	

	
	
	
	}


