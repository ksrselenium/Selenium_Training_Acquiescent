package com.Basic.Selenium.Concepts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class FileDownlod_Using_Robort_Class {

	//@Test(priority = 1)

	public void Downlod_JavaSoftware() throws InterruptedException, AWTException {

		System.setProperty("webdriver.firefox.marionette","E:\\Selenium_Trainning\\Training_Selenium3\\FFDriver\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();

		driver.get("https://www.google.co.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("java download");
		Thread.sleep(1000);

		// PEROFRMING ENTER OPERATION

		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		// Clicking on first download link
		driver.findElement(By.xpath("//a[contains(text(),'Download Free Java Software')]")).click();
		Thread.sleep(3000);

		// Clicking on java download link

		driver.findElement(By.xpath("//span[contains(text(),'Free Java Download')]")).click();
		Thread.sleep(1000);

		// Clicking on "Agree and Start Free Download'" link
		driver.findElement(By.xpath("//span[contains(text(),'Agree and Start Free Download')]")).click();

		Thread.sleep(1000);

		// Use robort class to handle window based download popup

		Robot rbt = new Robot();
		// Performing tab operation--so that focus will shift to save file
		// button
		rbt.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(1000);

		// Performing Enter operation
		rbt.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);

		driver.close();

	}


	@Test(priority = 2)

	public void Downlod_WebdriverJar() throws InterruptedException, AWTException {

		System.setProperty("webdriver.firefox.marionette","E:\\Selenium_Trainning\\Training_Selenium3\\FFDriver\\geckodriver.exe");

		WebDriver driver = new FirefoxDriver();

		driver.get("http://www.seleniumhq.org/download/");
		driver.manage().window().maximize();

		// Clicking on "Selenium Standalone Server download'" link
		driver.findElement(By.xpath("(//a[@href='https://goo.gl/hvDPsK'])[1]")).click();

		Thread.sleep(1000);

		// Use robort class to handle window based download popup

		Robot rbt = new Robot();
		// Performing tab operation--so that focus will shift to save file
		// button
		rbt.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(1000);

		// Performing Enter operation
		rbt.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
	
		
		driver.close();

	}

	
	
	
	
	
	
	
	
}
	
	
	


