package com.TestNg_Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Dataprovider_Demo {

	  @DataProvider(name = "Authentication")
	  
	  public  Object[][] credentials() {
	 
		  
		//Rows - Number of times your test has to be repeated.---3
			//Columns - Number of parameters in test data.---2
		  
		  Object[][] data = new Object[3][2];
		  
		// 1st row
			data[0][0] ="sampleuser1";
			data[0][1] = "abcdef";

			// 2nd row
			data[1][0] ="testuser2";
			data[1][1] = "zxcvb";
			
			// 3rd row
			data[2][0] ="guestuser3";
			data[2][1] = "pass123";

			return data;
		  
	  
	 
	  }
	 
	  // Here we are calling the Data Provider object with its Name
	//This test method declares that its data should be supplied by the Data Provider
		// "Authentication" is the function name which is passing the data
	       // Number of columns should match the number of input parameters
	  @Test(dataProvider="Authentication")
	 
	  public void test(String sUsername, String sPassword) {
	 
		  System.out.println("you have provided username as::"+sUsername);
			System.out.println("you have provided password as::"+sPassword);
		  
/*	 WebDriver  driver = new FirefoxDriver();
	 
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 
	      driver.get("http://www.store.demoqa.com");
	 
	      driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	 
	      driver.findElement(By.id("log")).sendKeys(sUsername);
	 
	      driver.findElement(By.id("pwd")).sendKeys(sPassword);
	 
	      driver.findElement(By.id("login")).click();
	 
	      driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
	 
	      driver.quit();*/
	 


	  }
}
