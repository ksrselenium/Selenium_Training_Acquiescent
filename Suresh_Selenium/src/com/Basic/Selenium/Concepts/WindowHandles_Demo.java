package com.Basic.Selenium.Concepts;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WindowHandles_Demo {
WebDriver driver;

@Test

public void WindowHndles() throws InterruptedException

{
		
System.setProperty("webdriver.gecko.driver", "E:\\KGKProject_Workspace\\Suresh_Selenium\\FFDriver\\geckodriver.exe");
WebDriver driver = new FirefoxDriver();
 
 driver.get("https://www.hdfcbank.com/");
 
 
 
 driver.findElement(By.xpath("//a[@id='loginsubmit']")).click();
 Thread.sleep(2000);
 
String ParentWindow_Id= driver.getWindowHandle();

System.out.println("---ParentWindow_Id--"+ParentWindow_Id);

System.out.println(driver.getTitle());


Set<String>total_Windows_Opened= driver.getWindowHandles();

int totalWds=total_Windows_Opened.size();
System.out.println("--totalWds--"+totalWds);

for ( String x :total_Windows_Opened  )
{
	System.out.println(x);
	
	if (!ParentWindow_Id.equalsIgnoreCase(x))
		
	{
	 driver.switchTo().window(x);
	
	}
	
}
}






}


/*

Iterator<String> it=total_Windows_Opened.iterator();

String parent_Window=it.next();
System.out.println("---parent_Window--"+parent_Window);

String Child_Window=it.next();

System.out.println("---Child_Window--"+Child_Window);

driver.switchTo().window(Child_Window);

System.out.println(driver.getTitle());
 driver.close();

driver.switchTo().window(parent_Window);

System.out.println("-----"+driver.getTitle());


for ( String x : total_Windows_Opened)
 
{
		System.out.println(x);
		
		
}
driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys("suresh");


}
}
*/
/*

Test case: We need to open 'http://linkedin.com' and click on 'Help Center' link at the bottom which will open new window.
1. Verify the title of the new window
2. Verify text 'Welcome' on the page.
3. Search for a Question with text "Frequently Asked Questions" and verify the result.




public class WindowExamples {
	public static WebDriver driver;

	@Test
	public void verifySearchInNewWindow() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.navigate().to("http://linkedin.com/");
		driver.manage().window().maximize();
		String mainHandle = driver.getWindowHandle();
		
		//Wait for the element to be present
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cust-svc-link")));
		driver.findElement(By.linkText("Help Center")).click();
		
		//Switch to new window and verify the title
		waitForNewWindowAndSwitchToIt(driver);
		String newTitle = getCurrentWindowTitle();
		Assert.assertEquals(newTitle, "LinkedIn Help Center", "New window title is not matching");
		
		//Verify the text present on the page
		String textOnpage=driver.findElement(By.cssSelector(".welcome")).getText().trim();
		Assert.assertEquals(textOnpage, "Welcome!");
		
		//Verify search text on the page
		String searchText="Frequently Asked Questions";
		WebElement searchInputBox=driver.findElement(By.id("kw"));
		searchInputBox.sendKeys(searchText);
		
		WebElement searchButton = driver.findElement(By.cssSelector(".button.leftnoround.blue"));
		searchButton.click();
		
		WebElement resultedElement = driver.findElement(By.cssSelector(".rn_Element2"));
		String resultedText = resultedElement.getText().trim();
		System.out.println(resultedText);
		Assert.assertTrue(resultedText.contains(searchText), "Search successfull");
		
		closeAllOtherWindows(driver, mainHandle);
	}






//Below method is used to get the main window handle. we will the driver as parameter.


public static String getMainWindowHandle(WebDriver driver) {
	return driver.getWindowHandle();
}

//Below method is used to get the current window title
public static String getCurrentWindowTitle() {
	String windowTitle = driver.getTitle();
	return windowTitle;
}


//Below method is used to close all the other windows except the main window.

public static boolean closeAllOtherWindows(WebDriver driver, String openWindowHandle) {
	Set<String> allWindowHandles = driver.getWindowHandles();
	for (String currentWindowHandle : allWindowHandles) {
		if (!currentWindowHandle.equals(openWindowHandle)) {
			driver.switchTo().window(currentWindowHandle);
			driver.close();
		}
	}
	
	driver.switchTo().window(openWindowHandle);
	if (driver.getWindowHandles().size() == 1)
		return true;
	else
		return false;
}


}


//Below method is used to wait for the new window to be present and switch to it.


public static void waitForNewWindowAndSwitchToIt(WebDriver driver) throws InterruptedException {
    String cHandle = driver.getWindowHandle();
    String newWindowHandle = null;
    Set<String> allWindowHandles = driver.getWindowHandles();
    
    //Wait for 20 seconds for the new window and throw exception if not found
    for (int i = 0; i < 20; i++) {
        if (allWindowHandles.size() > 1) {
            for (String allHandlers : allWindowHandles) {
                if (!allHandlers.equals(cHandle))
                	newWindowHandle = allHandlers;
            }
            driver.switchTo().window(newWindowHandle);
            break;
        } else {
            Thread.sleep(1000);
        }
    }
    if (cHandle == newWindowHandle) {
        throw new RuntimeException(
                "Time out - No window found");
    }
}
}
*/






