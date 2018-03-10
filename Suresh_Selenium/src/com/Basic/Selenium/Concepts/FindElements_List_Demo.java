package com.Basic.Selenium.Concepts;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FindElements_List_Demo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		
		System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		 
		 driver.get("https://gsuite.google.com/signup/basic/welcome");
		
		 driver.findElement(By.xpath("//span[@class='RveJvd snByac'][text()='NEXT']")).click();
		 Thread.sleep(4000);
		 
		 driver.findElement(By.xpath("//div[@class='t5nRo Id5V1']")).click();
		 Thread.sleep(1000);
		 
		 
		 driver.findElement(By.xpath("(//span[@class='RveJvd snByac'][text()='NEXT'])[2]")).click();
		 Thread.sleep(5000);
		 
		List<WebElement> options=driver.findElements(By.xpath("//div[@role='option']/descendant::content[@class='vRMGwf oJeWuf']"));
		
		int totalOptions=options.size();
		
		System.out.println("---totalOptions--"+totalOptions);
		
		for ( int i=0;i<totalOptions;i++)
		
		{
		
	String options_Available=options.get(i).getText();
	System.out.println("----options_Available ="+options_Available);

		
		
		}
}
}
