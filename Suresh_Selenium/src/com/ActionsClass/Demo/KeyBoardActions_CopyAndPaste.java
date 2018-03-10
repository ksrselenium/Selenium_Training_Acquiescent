package com.ActionsClass.Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class KeyBoardActions_CopyAndPaste {
	
	
	

@Test(priority=1)

public void CopyAndPaste() throws InterruptedException
{
	System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
	WebDriver driver = new FirefoxDriver();
		
	driver.get("https://www.google.com/advanced_search");
	driver.manage().window().maximize();
	
	//Enter the data on first text box 
	
	driver.findElement(By.xpath("//input[@id='_dKg']")).sendKeys("Selenium");
	
  //Copying above entered data ( Ctrl+a and ctrl+c)
	
	driver.findElement(By.xpath("//input[@id='_dKg']")).sendKeys(Keys.CONTROL+"a");
	driver.findElement(By.xpath("//input[@id='_dKg']")).sendKeys(Keys.CONTROL+"c");
	
	//Paste the data into next text box
	
	driver.findElement(By.xpath("//input[@id='_aKg']")).sendKeys(Keys.CONTROL+"v");
	
	driver.findElement(By.xpath("//input[@id='_aKg']")).sendKeys(Keys.CONTROL+"v");
	driver.findElement(By.xpath("//input[@id='_aKg']")).sendKeys(Keys.CONTROL+"v");
	
	driver.close();


}



}
