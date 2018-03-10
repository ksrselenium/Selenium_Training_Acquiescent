package com.valtech.kgk.config;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.valtech.kgk.utilities.DataReader;



public class BaseTest
{
  
 public WebDriver driver;
 public Properties CONFIG;
 DataReader read = new DataReader();
 String oldpath = getClass().getClassLoader().getResource(".").getPath();  
 String drive =oldpath.substring(1, 2)+":";
 String UserDirectoryPath= System.getProperty("user.dir");
  /**
	 * This function will exencute before each class tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */

  
   @BeforeClass
   // Passing Browser parameter from TestNG xml
 @Parameters({"browser"})
    
   public void beforeTest(String browser) throws Exception {
  
   	
 
	 // If the browser is Firefox, then do this
  
   if(browser.equalsIgnoreCase("FF")) {
   
    System.out.println("Running Firefox");
   
   // System.setProperty("webdriver.firefox.marionette", drive+"\\KGKProject_Workspace\\KGKTestAutomation\\FFDriver\\geckodriver.exe"); 
     
    // To Fetch FF Driver with out depending on drivers ( like C,D,E )
   System.setProperty("webdriver.firefox.marionette",  UserDirectoryPath+"\\FFDriver\\geckodriver.exe"); 
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    
   // If browser is chrome, then do this   

   }  
   else if (browser.equalsIgnoreCase("chrome"))
   {
     System.out.println("Running Chrome");
     
     //System.setProperty("webdriver.chrome.driver",  UserDirectoryPath+"\\ChromeDriver\\chromedriver.exe");
     
   // System.out.println("Running Chrome--path ="+ UserDirectoryPath+"\\ChromeDriver\\chromedriver.exe");
     
     System.setProperty("webdriver.chrome.driver", drive+"\\KGKProject_Workspace\\KGKTestAutomation\\ChromeDriver\\chromedriver.exe");
     
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      
   }
 
   // If browser is ie, then do this  
   
   else if (browser.equalsIgnoreCase("ie")) 
   {
     System.out.println("Running Internet Explorer");
     
    System.setProperty("webdriver.ie.driver",drive+"\\KGKProject_Workspace\\KGKTestAutomation\\IEDriver\\IEDriverServer.exe");
  DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
     dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
  //If IE fail to work, please remove this line and remove enable protected mode for all the 4 zones from Internet options
     driver = new InternetExplorerDriver(dc);  
     driver.manage().window().maximize();
   
   }
   
   // If browser is edge, then do this 
   else if (browser.equalsIgnoreCase("edge"))
   {
     System.out.println("Running edge");
     
     System.setProperty("webdriver.edge.driver", drive+"\\KGKProject_Workspace\\KGKTestAutomation\\EdgeDriver\\MicrosoftWebDriver.exe");
     
     driver = new EdgeDriver();
   driver.manage().window().maximize();
   
   }
   
   
   else if (browser.equalsIgnoreCase("safari")) 
   {
     System.out.println("Running Safari");
     driver = new SafariDriver();
  }
   
    else if (browser.equalsIgnoreCase("opera"))
    {
     System.out.println("Running Opera");
  // driver = new OperaDriver();       --Use this if the location is set properly--
     DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setCapability("opera.binary", "C://Program Files (x86)//Opera//opera.exe");
     capabilities.setCapability("opera.log.level", "CONFIG");
     driver = new OperaDriver(capabilities);
  }
  else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		} 



 }   
   
   
  
/* @BeforeClass
 public void driverint() throws InterruptedException, MalformedURLException
 {
  //FireFox driver exceution through selenium 3 

  // To Fetch the current workspace path
  String UserDirectoryPath= System.getProperty("user.dir");
  
  System.out.println("UserDirectory = " + UserDirectoryPath);
  
  // To Fetch FF Driver with out depending on drivers ( like C,D,E )
  System.setProperty("webdriver.firefox.marionette",  UserDirectoryPath+"\\FFDriver\\geckodriver.exe");

  // Initiate driver
  driver = new FirefoxDriver();
  driver.manage().window().maximize();
  
  
 
  
 }*/
 
 @AfterClass
 public void teardown()
 {
  driver.quit();  
 
 }
 
 // Required when executing the scripts through Grid

 public WebDriver getDriver()
 {
  
  return driver;
 }
  
}