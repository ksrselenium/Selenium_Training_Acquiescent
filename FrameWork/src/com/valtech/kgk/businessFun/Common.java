package com.valtech.kgk.businessFun;


import generalFun.GeneralFun;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.valtech.kgk.utilities.DataReader;

public class Common
{
	DataReader readdata=new DataReader();           
	public Properties CONFIG = readdata.readConfig();
	public Properties OR = readdata.readObjectRepository();

	GeneralFun gfun= new GeneralFun();

	public void loginToApp(WebDriver driver,String Excelpath,String sheetname,int colnum,int rownum) throws BiffException, IOException, InterruptedException

	{

		if(gfun.isElementPresent(driver, OR.getProperty("logoutBtn"))==true)
		{
			logout(driver);
		}



		System.out.println("entered login");
		String username = readdata.excel(Excelpath,sheetname, colnum, rownum);
		System.out.println("username=" + username);

		driver.findElement(By.xpath(OR.getProperty("userNameEdtBox"))).sendKeys(username);


		String password = readdata.excel(Excelpath,sheetname, colnum+1, rownum);
		WebElement PWD = driver.findElement(By.xpath(OR.getProperty("passwordEdtBox")));
		PWD.sendKeys(password);
		driver.findElement(By.xpath(OR.getProperty("loginBtn"))).click();
		gfun.waitForPageToLoad(driver);                             

	}
	

	
	
	/**
  * Method Name: selectCustomer
  * Description: selecting the customer from select customer dialog
  */

     public  void selectCustomer(WebDriver driver,String Excelpath,String sheetname,int colnum,int rownum) throws BiffException,
     IOException, InterruptedException {
    System.out.println("Select the customer");
      String custID = readdata.excel(Excelpath,sheetname, colnum, rownum);
    System.out.println(custID);
    driver.findElement(By.xpath(OR.getProperty("selectCustomerBtn"))).click();
    System.out.println("enetered select customer dialog");
    driver.findElement(By.xpath(OR.getProperty("enterCustEdtBox"))).sendKeys(custID);
    System.out.println("Entering the data");
    driver.findElement(By.xpath(OR.getProperty("enterCustSearchBtn"))).click();
    System.out.println("Selecting customer is done");
    gfun.waitForElementtillinvisible(driver, 10, OR.getProperty("enterCustEdtBox"));

   }

	
	
	/**
	 * Method Name: navigatetolisting
	 * Description: navigation to article listing page
	 */

	public void navigatetolisting(WebDriver driver)
	{
		System.out.println("Clicking on node link");
		driver.findElement(By.xpath(OR.getProperty("clickLandingPageLinkNode"))).click();
		gfun.waitForPageToLoad(driver);
		}

	/**
	 * Method Name: placeOrder
	 * Description: clicking on place order button
	 */

	public void placeOrder(WebDriver driver) {

		driver.findElement(By.xpath(OR.getProperty("placeorderskicksBtn"))).click();

	}

	/**
	 * Method Name: logout
	 * Description: clicking on logout to logout of the application
	 */

	public void logout(WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("logoutBtn"))).click();
		gfun.waitForPageToLoad(driver);

	}
	/**
	 * Method Name: clickOnSearchArticleTab
	 * Description: to navigate to search article tab
	 */

	public void clickOnSearchArticleTab(WebDriver driver) throws TimeoutException, IOException
 	{

  	System.out.println("Clicking on sok artikel tab");
  	driver.findElement(By.xpath(OR.getProperty("searchArtikelTab"))).click(); 
 	 gfun.waitForElement(driver, 10, OR.getProperty("productTreeLabel"));
 	}

	/**
	 * Method Name: acessAndLogout
	 * Description: If it is in Logged in state of Admin page or customer page the function will logout and be in idle state
	 */      

	public void acessAndLogout(WebDriver driver) throws BiffException, IOException
	{

		boolean flag1=gfun.isElementPresent(driver,"logoutBtn");
		if (flag1==true)
		{
			driver.findElement(By.xpath(OR.getProperty("logoutBtn"))).click();


		}

		/* boolean flag2=gfun.isElementPresent(driver, strt.getLogoutBtn());
                  if (flag2==true)
                  {
                                 strt.getLogoutBtn().click(); 
                  }*/                          

	}

	/**
	 * Method Name: clickOnMinaSidor
	 * Description: this method clicks on mina sidor tab
	 */  


	public void clickOnMinaSidor (WebDriver driver)
	{
		driver.findElement(By.xpath(OR.getProperty("minasidorTab"))).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}


}      
