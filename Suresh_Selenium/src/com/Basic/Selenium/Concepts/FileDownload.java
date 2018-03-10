package com.Basic.Selenium.Concepts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
//Note: Refer - http://www.freeformatter.com/mime-types-list.html for complete list of MIME type
//Multipurpose Internet Mail Extensions 
public class FileDownload {

@Test(priority=1)
	
public void downlod_Excel_File() throws InterruptedException{
	
		System.setProperty("webdriver.firefox.marionette", "E:\\Selenium_Trainning\\Training_Selenium3\\FFDriver\\geckodriver.exe");
		//common to all the cases
		
				
		
		FirefoxProfile pf=new FirefoxProfile ();
		/*	
		//Case-1---""--MIME Type of the application----desktop
		
		pf.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
		pf.setPreference("browser.download.folderList", 0);//desktop
		
  //Case-2---""--MIME Type of the application--Default download location
		
	    pf.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
		pf.setPreference("browser.download.folderList", 1);
	*/
		//Case 3 --""--MIME Type of the application--desired download location
		
		pf.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.ms-excel");
		pf.setPreference("browser.download.folderList", 2);
		
		pf.setPreference("browser.download.dir","D:\\downloadFiles");//File saving location path
	
	
		WebDriver driver = new FirefoxDriver(pf);
		
		driver.get("http://spreadsheetpage.com/index.php/file/climate_data_workbook/");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("climate_data.xls")).click();
		Thread.sleep(1000);
		
		driver.close();

	}

//@Test(priority=2)
public void downlod_Text_File() throws InterruptedException{
	
	System.setProperty("webdriver.firefox.marionette", "E:\\Selenium_Trainning\\Training_Selenium3\\FFDriver\\geckodriver.exe");
	//common to all the cases
	
			
	
	FirefoxProfile pf=new FirefoxProfile ();
	
/*	//Case-1---""--MIME Type of the application----desktop
	
	pf.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
	pf.setPreference("browser.download.folderList", 0);//desktop
	
 //Case-2---""--MIME Type of the application--Default download location
	
    pf.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
	pf.setPreference("browser.download.folderList", 1);

*/	//Case 3 --""--MIME Type of the application--desired download location
	
	pf.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
	pf.setPreference("browser.download.folderList", 2);
	
	pf.setPreference("browser.download.dir","D:\\downloadFiles");//File saving location path


	WebDriver driver = new FirefoxDriver(pf);
	
	driver.get("http://www.seleniumeasy.com/test/generate-file-to-download-demo.html");
	driver.manage().window().maximize();
	driver.findElement(By.xpath("//textarea[contains(@id,'textbox')]")).sendKeys("fileDownload");
	
	driver.findElement(By.xpath("//button[@id='create']")).click();
	Thread.sleep(10000);
	
	driver.findElement(By.linkText("Download")).click();
	Thread.sleep(1000);
	
	driver.close();
	


}




}

	
	
	


