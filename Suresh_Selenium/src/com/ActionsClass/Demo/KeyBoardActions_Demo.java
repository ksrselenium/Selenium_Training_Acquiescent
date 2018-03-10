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

public class KeyBoardActions_Demo {
	
	WebDriver driver;
	
	
	@BeforeMethod
	
	public void BrowserOpen()
	{
		System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
		 driver = new FirefoxDriver();
	}
	
	

@Test(priority=1)

public void Ctrl_A() throws InterruptedException
{

		
	driver.get("https://www.google.co.in/");
	driver.manage().window().maximize();
	Thread.sleep(2000);
	
	driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("Selenium");
	
	Actions act = new Actions(driver);
	
	act.sendKeys(Keys.ENTER).build().perform();
	Thread.sleep(1000);
	
		
  act.sendKeys(Keys.CONTROL+"a").build().perform();

System.out.println("selected all");


}

@Test(priority=2)

public void NewTab_Ctrl_t() throws InterruptedException
{
		
	driver.get("https://www.google.co.in/");
	driver.manage().window().maximize();
	
	Actions act = new Actions(driver);
	
	act.sendKeys(Keys.CONTROL+"t").build().perform();
	Thread.sleep(1000);



}

@Test(priority=3)

public void BackSpace() throws InterruptedException
{
		
	driver.get("https://www.google.co.in/");
	driver.manage().window().maximize();
	driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("Selenium");
	
	Actions act = new Actions(driver);
	act.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE).perform();
	
	Thread.sleep(1000);


}

@Test(priority=4)

public void PageDown() throws InterruptedException
{
		
	driver.get("https://www.google.co.in/");
	driver.manage().window().maximize();
	driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("Selenium");
	
	Actions act = new Actions(driver);
	act.sendKeys(Keys.ENTER).build().perform();
    act.sendKeys(Keys.PAGE_DOWN).perform();
	
	Thread.sleep(1000);


}



@AfterMethod

public void BrowserClose()
{
	driver.quit();
}


}
