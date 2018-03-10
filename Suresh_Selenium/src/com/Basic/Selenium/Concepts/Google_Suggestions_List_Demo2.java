package com.Basic.Selenium.Concepts;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Google_Suggestions_List_Demo2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		 // Here we need to create logger instance so we need to pass Class name for 
	     //which  we want to create log file in my case Google is classname
	         Logger logger=Logger.getLogger("Google_Suggestions_List_Demo2");
	        
	 
	       // configure log4j properties file
	       PropertyConfigurator.configure("Log4j.properties");		
		
		
		
		
		System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		logger.info("browser Opend");
		 driver.get("https://www.google.co.in");
		 logger.info("URL Opend");
		 driver.manage().window().maximize();
		 
		 logger.info("Window Maximized");
		 driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("java");
		 Thread.sleep(1000);
		
		 
		List<WebElement> Suggestions=driver.findElements(By.xpath("//ul[@role='listbox']//li/descendant::div[@class='sbqs_c']"));
		
		int total_Suggestions=Suggestions.size();
		
		System.out.println("---total_Suggestions--"+total_Suggestions);
		
		for ( int i=0;i<total_Suggestions;i++)
		
		{
		
	String Suggestions_displayed=Suggestions.get(i).getText();
	System.out.println("----Suggestions_displayed ="+Suggestions_displayed);

		if (Suggestions_displayed.equals("java jdk"))
		{
			Suggestions.get(i).click();
			break;
		}
		
		
		}
}
}
