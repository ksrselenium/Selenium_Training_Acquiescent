package com.ActionsClass.Demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


public class DragAndDrop_Demo  {
	WebDriver driver;

@Test

public void DragAndDrop_JqueryUiElements ()
{

/*	System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
	 driver = new FirefoxDriver();*/
	 driver.get("https://jqueryui.com/droppable/");
	driver.manage().window().maximize();

		
	driver.switchTo().frame(0);
	WebElement Drag_Element=driver.findElement(By.id("draggable"));
	
	WebElement Drop_Element=driver.findElement(By.id("droppable"));
	
	Actions act = new Actions(driver);
	
	//Case_1:
	act.clickAndHold(Drag_Element).moveToElement(Drop_Element).release().build().perform();
	//Case_2:

	//act.dragAndDrop(Drag_Element, Drop_Element).perform();
	
	
}




}
