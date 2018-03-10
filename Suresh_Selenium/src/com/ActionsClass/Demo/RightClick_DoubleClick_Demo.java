package com.ActionsClass.Demo;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class RightClick_DoubleClick_Demo {
	WebDriver driver;

@Test

public void RightClick_Demo() throws AWTException
{

	System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
	 driver = new FirefoxDriver();
	 driver.get("https://www.google.co.in/");
	driver.manage().window().maximize();

	WebElement SearchBox=	driver.findElement(By.id("lst-ib" ));
	
/*	
	Robot  rb = new Robot();
	rb.keyPress(KeyEvent.VK_ALT);
	rb.keyPress(KeyEvent.VK_F4);*/
	
	
	Actions act = new Actions(driver);
	
	
   act.contextClick(SearchBox).perform();
	
	
}


}
