package com.Basic.Selenium.Concepts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TTD_site_Automation {

	public static void main(String[] args) throws IOException {
	
		
	
		System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		 
		 driver.get("https://ttdsevaonline.com/#/login");
		
}
}