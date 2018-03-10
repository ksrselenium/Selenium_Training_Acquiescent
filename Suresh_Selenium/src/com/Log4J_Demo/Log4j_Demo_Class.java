package com.Log4J_Demo;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Log4j_Demo_Class {

	@Test
	
	public void log4j_demo1 () throws InterruptedException
	{

		// Here we need to create logger instance so we need to pass Class name for
		// which we want to create log file in my case Log4j_Demo_Class is classname
		Logger logger = Logger.getLogger("Log4j_Demo_Class");

		// configure log4j properties file
		PropertyConfigurator.configure("E:\\Suresh_Training_Workspace\\Suresh_Selenium\\Log4j_PropertiesFile\\log4j.properties");
		
		
		
		System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		logger.info("browser Opend");
		 driver.get("https://www.google.co.in");
		 logger.info("URL Opend");
		 driver.manage().window().maximize();
		 
		 logger.info("Window Maximized");
		 driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("java");
		 Thread.sleep(1000);
		 logger.info("input entered");
		 
		driver.close();
		 logger.info("Browser Closed");
		
		
}

public void fuentWait()
{
	WebDriver driver=new FirefoxDriver();
	
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			 
		    .withTimeout(20, TimeUnit.SECONDS)
		 
		    .pollingEvery(5, TimeUnit.SECONDS)
		 
		    .ignoring(NoSuchElementException.class);
		 //here WebDriver--input
	//here WebElement---ouput
		  WebElement element = wait.until(new Function<WebDriver,WebElement>() {
		 
		    public WebElement apply(WebDriver driver) {
		 
		    return driver.findElement(By.id("textBoxId"));
		 
		    }
		}
		);

		  //element.isDisplayed();

}









}