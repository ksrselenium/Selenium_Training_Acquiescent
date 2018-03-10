package generalFun;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.valtech.kgk.config.BaseTest;
import com.valtech.kgk.utilities.DataReader;

public class GeneralFun extends BaseTest
{
	DataReader readdata=new DataReader();	
	public Properties CONFIG = readdata.readConfig();
	public Properties OR = readdata.readObjectRepository();
	public Properties TEXT = readdata.readText();
	public mySoftAssert soft= new mySoftAssert();

	/**
	 * Method Name: waitForElement
	 * Description: Waiting till the element is visible using explicit wait
	 * @throws IOException 
	 */

	public void waitForElement(WebDriver driver,long waitTimeinSeconds,String Xpathofelement) throws IOException,TimeoutException
	{   
		try{

			WebDriverWait wait = new WebDriverWait(driver,waitTimeinSeconds); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpathofelement)));

		}
		catch(TimeoutException e)
		{
			driver.navigate().refresh();
			System.err.println(shortenedStackTrace(e, 5));
			
		}

	}

 public void waitforElementAndRefreshcyles(WebDriver driver,long waitTimeinSeconds,String Xpathofelement,int cycle) throws TimeoutException, IOException
 {
	 int i=0;
	 long time;
	 while (i<cycle)
		{
		 time=(i+1)*waitTimeinSeconds;
			System.out.println("Entering into cycle "+i);
			if(isElementPresent(driver,Xpathofelement )==true)
				break;
			else
			{
				waitForElement(driver,waitTimeinSeconds,Xpathofelement);
				System.out.println("elapsed time = "+time+"secs");
				i++;
			}


		}
 }


		
	
	/**
	 * Method Name: waitForElementCss
	 * Description: Checking the status of the processed order
	 * @throws IOException 
	 */

	public void waitForElementCss(WebDriver driver,String CssSelector) throws IOException
	{   
		System.out.println("Waiting for the process to be Ok");
		WebDriverWait wait = new WebDriverWait(driver,800); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(CssSelector)));
	}

	public void waitForPageToLoad(WebDriver driver)
	{   
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

	}

/**
	 * Method Name: naviagetUrl
	 * Description: To navigate to any specific URL
	 */

	public void navigateUrl(WebDriver driver,String Url) throws IOException
	{


		driver.get(readdata.CONFIG.getProperty(Url));

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}






	/**
	 * Method Name: verifytext
	 * Description: verification of fields in all the pages
	 * @throws IOException 
	 */

	public void verifytext(WebDriver driver,String xpathofelement,String expectedValue,Boolean fromtextpropertyfile) throws IOException
	{    
		if (fromtextpropertyfile==true)
		{

			String actual = driver.findElement(By.xpath(xpathofelement)).getText();                
			System.out.println(actual);
			soft.assertEquals(actual,TEXT.getProperty(expectedValue));  
		}


		else
		{

			String actual = driver.findElement(By.xpath(xpathofelement)).getText();                  
			System.out.println(actual);
			soft.assertEquals(actual,expectedValue);      



		}

	}




	/**
	 * Method Name: verifyvalue
	 * Description: To compare two String values
	 * @throws IOException 
	 */
	public void verifyvalue(WebDriver driver,String actualvalue,String expectedValue) throws IOException
	{

		soft.assertEquals(actualvalue,expectedValue);  


	}
	/**
	 * Method Name:Refresh
	 * Description:This method refreshes the browser.
	 * @throws IOException 
	 * 
	 */
	public void Refresh(WebDriver driver) throws IOException
	{  
		System.out.println("Refreshing the browser");
		driver.navigate().refresh();
	}
	/**
	 * Method Name: isElementPresent
	 * Description: To check that an element whose xpath is passed as an 
	 * argument exists or does not exists in the respective webpage 
	 * if 
	 * @throws IOException 
	 */


	public boolean isElementPresent(WebDriver driver,String xpathOfElement) throws IOException 
	{
		boolean present;
		try {
			driver.findElement(By.xpath(xpathOfElement));
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		return present;
	}

	public String getDate(WebDriver driver,int modifyyear,int modifydate,int modifymonth) throws IOException
	{
		String currentDate;
		Calendar cal=Calendar.getInstance();

		cal.add(Calendar.YEAR, modifyyear);
		cal.add(Calendar.DATE, modifydate);
		cal.add(Calendar.MONTH, modifymonth);

		int year=cal.get(Calendar.YEAR);
		int date = cal.get(Calendar.DATE);
		int Month = cal.get(Calendar.MONTH);
		int newMonth = Month +1;



		if(newMonth<10)
		{

			if(date<10)
			{
				currentDate = (year+"-"+"0"+newMonth+"-"+"0"+date);

			}
			else
			{
				currentDate = (year+"-"+"0"+newMonth+"-"+date);

			}
		}
		else
		{
			if(date<10)
			{
				currentDate = (year+"-"+newMonth+"-"+"0"+date);

			}
			else
			{
				currentDate = (year+"-"+newMonth+"-"+date);

			} 
		}

		return currentDate;

	}


/**
	 * Method Name: waitForElement
	 * Description: Waiting till the element is visible using explicit wait
	 * @throws IOException 
	 */
   public void waitForElementtillinvisible(WebDriver driver,long seconds,String Xpathofelement) throws IOException
	 {
	  try{
	  WebDriverWait wait = new WebDriverWait(driver, seconds); 
	  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Xpathofelement)));
	  }
	  catch(TimeoutException e)
	  {
	   System.err.println(shortenedStackTrace(e, 11));
	  }
	 }

   /**
	 * Method Name: splitOnCapitals
	 * Description: use this function to split the provided string on capital letters and store them in an array
	 * 
	 */

	public  String[] splitOnCapitals(String str) throws IOException 

	{
		ArrayList<String> array = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < str.length(); i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				String line = builder.toString().trim();
				if (line.length() > 0) array.add(line);
				builder = new StringBuilder();
			}
			builder.append(str.charAt(i));
		}
		array.add(builder.toString().trim()); // get the last little bit too
		return array.toArray(new String[0]);
	}


	/**
	 * Method Name: goTopreviousdaydate
	 * Description: use this function to go to previous date
	 * 
	 */

	public void goTopreviousdaydate(WebDriver driver,String datefieldxpath)
	{
		String fromdate=driver.findElement(By.xpath(datefieldxpath)).getAttribute("value");
		String Arrdate[]=fromdate.split("-");
		System.out.println(Arrdate.length);
		int newdate=Integer.parseInt(Arrdate[2])-1;
		driver.findElement(By.xpath(datefieldxpath)).clear();
		System.out.println(Arrdate[0]+"-"+Arrdate[1]+"-"+newdate);
		driver.findElement(By.xpath(datefieldxpath)).sendKeys(Arrdate[0]+"-"+Arrdate[1]+"-"+newdate);
	}
	/**
	 * Method Name: isElementPresent
	 * Description: finds whether element is present or not based on attribute passed true or false
	 * 
	 */


	public void isElementPresent(WebDriver driver,String xpathOfElement,boolean Expectedoutcome) throws IOException 
	{
		boolean present;
		try {
			driver.findElement(By.xpath(xpathOfElement));
			present = true;
		} catch (NoSuchElementException e) {
			present = false;
		}
		soft.assertEquals(present,Expectedoutcome );
	}
	/**
	 * Method Name: isElementVisible
	 * Description: finds whether element is visible or not based on attribute passed true or false
	 * 
	 */
	public void isElementVisible(WebDriver driver,String xpathOfElement,boolean Expectedoutcome) throws IOException 
	{

		boolean present=driver.findElement(By.xpath(xpathOfElement)).isDisplayed(); 
		soft.assertEquals(present,Expectedoutcome );
	}







	////the below Strtech is for soft assertions	
	public void verifytextaftertrim(WebDriver driver,String xpathofelement,String expectedValue,boolean fromtextpropertyfile) throws IOException, InterruptedException
	{

		if (fromtextpropertyfile==true)

		{

			String actual = driver.findElement(By.xpath(xpathofelement)).getText().trim();   
			System.out.println(actual);
			soft.assertEquals(actual,readdata.TEXT.getProperty(expectedValue));
			Thread.sleep(2000);

		}



		else
		{
			String actual = driver.findElement(By.xpath(xpathofelement)).getText().trim();
			System.out.println(actual);
			soft.assertEquals(actual,expectedValue);
			Thread.sleep(2000);

		}

	}



	/**
	 * Method Name: isSeelcted
	 * Description: finds whether element is selected or not based on attribute passed true or false
	 * 
	 */


	public void isSelected(WebDriver driver,String xpathOfElement,boolean Expectedoutcome) throws IOException 
	{

		boolean display=driver.findElement(By.xpath(xpathOfElement)).isSelected();
		soft.assertEquals(display,Expectedoutcome );
	}

	/**
	 * Method Name: returnDrive
	 * Description: This method is used to return the drive the workspace >project is present
	 * 
	 */
	public String returnDrive()
	{
		String oldpath = getClass().getClassLoader().getResource(".").getPath();		
		String drive =oldpath.substring(1, 2)+":";	
		return drive;
	}


	/**
	 * Method Name: mySoftAssert
	 * Description: This method is used to return the soft instance to always have only one instance and use same across the tests
	 * 
	 */
	public mySoftAssert myassert()
	{
		return soft;

	}

	public static String shortenedStackTrace(Exception e, int maxLines) {
	    StringWriter writer = new StringWriter();
	    e.printStackTrace(new PrintWriter(writer));
	    String[] lines = writer.toString().split("\n");
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < Math.min(lines.length, maxLines); i++) {
	        sb.append(lines[i]).append("\n");
	    }
	    return sb.toString();
	}
	
	public int getWeekDay()
	{
		Calendar c = Calendar.getInstance();		
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}
	
	
	

}













