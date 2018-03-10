package com.ExtentReport.Demo;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ExtentReport_With_Logs_Demo  {

	WebDriver driver;
	

	
	
@Test	
public void UploadFile_Using_SendKeys() {
	//<input type="file" name="datafile">	
	
	
	WebDriver driver = new FirefoxDriver();
	
		driver.get("http://nervgh.github.io/pages/angular-file-upload/examples/simple/");
	
		//driver.get("http://the-internet.herokuapp.com/upload");
		 driver.manage().window().maximize();
		 
		//Find the element of upload button and send the path
		 WebElement element= driver.findElement(By.xpath("//input[@type='file']"));
		 element.sendKeys("E:\\Suresh_Training_Workspace\\Suresh_Selenium\\autoItFiles\\autoit.exe");
		 
		 driver.quit();

}

@BeforeMethod
public void browserLunc() {
	
	System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
	 driver = new FirefoxDriver();

}




//The below is the AutoIt script:

/*

WinWaitActive("File Upload")
Send("G:\Tutorial\AutoItScripts\TestScripts\Test.doc")
Send("{ENTER}")

The first and foremost thing we need to do is save the above script with '.au3' which is AutoIt file extension. 

We actually need to generate the '.exe' file by compiling it into a standalone executable

Step-1: Navigate to the .au3 file that you wish to compile.
Step-2: Select the file and Right-click on it to access the pop-up menu.
Step-3: You will get an option as 'Compile Script'. Click on 'Compile Script.
*/
}


/*What If there is no text box / input tag to send the file path using Sendkeys method????

You will have only a button (Customized button) and when click on browse button, browser opens a window popup and to select the file from windows, And as we know selenium will not support OS controls.

We can use AutoIt tool (open source tool) which takes the responsibility to upload the file.

About AutoIT : AutoIt v3 is a free ware BASIC-like scripting language designed for automating the Windows GUI and general scripting.

We need to call the AutoIt script after clicking on the upload button. Immediately ater clicking on Upload button, the control should be transfered to AutoIt which takes care of uploading the file.

Syntax: 

      Runtime.getRuntime().exec("AutoIt .exe filepath");
      
      Example:  Runtime.getRuntime().exec("D:/fileupload.exe");
AutoIt script should have the following:

Step 1 : First After clicking on 'Upload' button, the cursor will move to the Window popup.

The below step should be the first line in the script. 'File Upload' is the name of the window popup when opened with Mozilla. The name changes depending on the browser.

             Mozilla - File Upload
             Chrome - Open
             and for IE - Choose File to Upload

                        WinWaitActive("File Upload")

Step 2 : Once the window popup is active, we should send the path of the document which need to be uploaded.

                       Send("Full path of the document")

Step 3 : After that we need to click on Open button which will upload the document.

                         Send("{ENTER}")

*/


