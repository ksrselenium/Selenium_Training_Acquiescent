package com.TestNg_Demo;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTesting_Demo {
WebDriver driver;

@Test(priority=1)
public void OpeningIn_Different_Browser() throws InterruptedException {
		
 
 driver.get("https://www.hdfcbank.com/");
 
 driver.quit();

}



@BeforeMethod
@Parameters("Browser")
public void crossBrowserTesting_Demo(String browser) throws InterruptedException
{
 
	if (browser.equalsIgnoreCase("FF"))
	{
		 driver=new FirefoxDriver();
		 System.out.println("---opend in ff---");
	}
	else if (browser.equalsIgnoreCase("Chrome"))
	{
		System.setProperty("webdriver.ie.driver", "E:\\Suresh_Training_Workspace\\Suresh_Selenium\\ChromeDriver\\chromedriver.exe");
		 
		 driver=new InternetExplorerDriver();
	System.out.println("---opend in crome---");
	
	}


}

}

/**************************************XML CODE *********************/

/*

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Suite" parallel="tests">
  
  
  
  <test name="Test1">
  
  <parameter name="Browser" value="FF"></parameter>
  
    <classes>
      <class name="com.TestNg_Demo.CrossBrowserTesting_Demo"/>
    </classes>
  </test> <!-- Test -->
  
  
  
  
   <test name="Test2">
  
  <parameter name="Browser" value="Chrome"></parameter>
  
    <classes>
      <class name="com.TestNg_Demo.CrossBrowserTesting_Demo"/>
    </classes>
  </test> <!-- Test -->
  
  
</suite> <!-- Suite -->



*/

