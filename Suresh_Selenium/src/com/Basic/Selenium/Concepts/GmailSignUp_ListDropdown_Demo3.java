package com.Basic.Selenium.Concepts;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GmailSignUp_ListDropdown_Demo3 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

				
		System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		 
		 driver.get("https://accounts.google.com/SignUp?continue=https%3A%2F%2Faccounts.google.com%2Fb%2F0%2FAddMailService");
		 driver.manage().window().maximize();
		 
		 driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("suresh");
		
		 
		 driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Reddy");
	
		 //Click on month list drop down
		
		
		 driver.findElement(By.xpath("//span[@id='BirthMonth']")).click();
		 Thread.sleep(3000);
		 
		List<WebElement> MonthList=driver.findElements(By.xpath("//span[@id='BirthMonth']/descendant::div[@class='goog-menuitem-content']"));
		
		int total_MonthList=MonthList.size();
		
		System.out.println("---total_Suggestions--"+total_MonthList);
		
		for ( int i=0;i<total_MonthList;i++)
		
		{
		
	String MonthList_displayed=MonthList.get(i).getText();
	System.out.println("----MonthList_displayed ="+MonthList_displayed);

		if (MonthList_displayed.equals("September"))
		{
			MonthList.get(i).click();
			break;
		}
		
		
		}
}
}
