package com.Basic.Selenium.Concepts;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Printing_AllLinks_FindElments_Demo {

@Test

public void Printing_All_Links_GooglePage() {
		
		
		
		
		// TODO Auto-generated method stub

		// To Fetch the current workspace path
		String UserDirectoryPath= System.getProperty("user.dir");
		
		System.out.println("UserDirectory = " + UserDirectoryPath);
		
		// To Fetch FF Driver with out depending on drivers ( like C,D,E )
		System.setProperty("webdriver.firefox.marionette",  UserDirectoryPath+"\\FFDriver\\geckodriver.exe");
		
		// Open browser
		WebDriver driver = new FirefoxDriver();
		// To maximize the window
		driver.manage().window().maximize();
		// Open application
		driver.get("https://www.google.co.in/");
		// Get the list of all links
	String Findelemt_ExP=driver.findElement(By.tagName("a")).getText();	
		
		
		System.out.println("Findelemt_ExP---"+Findelemt_ExP);
		
		
		
	List<WebElement> allLnks=driver.findElements(By.tagName("a"));
		
int totalLnks=	allLnks.size();

		System.out.println("-----totalLnks--"+totalLnks);
		
		for (int i = 0; i < totalLnks; i++)
		{
		
		String Linknames=allLnks.get(i).getText();
		
		System.out.println("Linknames--"+Linknames);
	}

	}
}













