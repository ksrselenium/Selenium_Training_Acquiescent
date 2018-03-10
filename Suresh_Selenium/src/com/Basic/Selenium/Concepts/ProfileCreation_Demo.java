package com.Basic.Selenium.Concepts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.Test;
//Note: Refer - http://www.freeformatter.com/mime-types-list.html for complete list of MIME type

public class ProfileCreation_Demo {

@Test(priority=1)
	
public void downlod_Excel_File() throws InterruptedException{
	
		System.setProperty("webdriver.firefox.marionette", "E:\\Selenium_Trainning\\Training_Selenium3\\FFDriver\\geckodriver.exe");
		//common to all the cases
		
				
		
		ProfilesIni pi=new ProfilesIni();
		
	FirefoxProfile allProfiles=pi.getProfile("SureshReddy");
		
		
		WebDriver driver=new FirefoxDriver(allProfiles);


		driver.get("http://google.com");

}

}

	
	
	


