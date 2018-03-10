package com.valtech.kgk.app.ak.test.OKAPI;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import generalFun.mySoftAssert;


import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.methods.HttpPost;
import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.valtech.kgk.businessFun.Common;
import com.valtech.kgk.businessFun.OKAPI;
import com.valtech.kgk.config.BaseTest;
import com.valtech.kgk.utilities.DataReader;

/**
 * Company : Valtech
 * @author nirmala.george
 * Date : 08-12-14
 * Description : Accommodated S1342 --- Creating NEWS in AKA through OKAPI
 */
public class S1342_NewsAKA extends BaseTest
{
	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	Common cmn=new Common();
	OKAPI okapi=new OKAPI();
	public Properties TEXT=readdata.readText();
	public Properties OR=readdata.readObjectRepository();
	public mySoftAssert soft= gfun.myassert();


	@Test(priority=1)
	public void CreateDeleteNews() throws IOException, InterruptedException
	{
		soft.clearMaps();

		//Deleting the existing NEWS created with the same name.........
		
		DeleteNews();

		// Using REST API - to create user for above added Customer

		String restUrl="http://app-web.testak.kgk.ad/api/news-v0";//.....URL used to create NEWS under AKA

		//Authentication required-here we pass the login credentials required
		String username="admin";
		String password="2much4u";

		// To retrieve the current date....
		String currentdate=gfun.getDate(driver, 0, 0, 0);
		currentdate=currentdate+" "+"06:00";

		// Creating an object of JSON as we are passing the input parameters and its values with Content type as json/application

		JSONObject user=new JSONObject();
		user.put("folderId", "3");
		user.put("name", "AutomationNews");
		user.put("title", "AutomationNews");
		user.put("preamble", "AutomationNews");
		user.put("text", "AutomationNews");
		user.put("publishingDate", currentdate);

		String jsonData=user.toString();
		//Creating connectivity to the  REST api url using Http client as it is post method,HttpPost 
		HttpPost httpPost=okapi.createConnectivity(restUrl , username, password);
		// Executing the request and storing the response 
		String httprequestResult=okapi.executeReq( jsonData, httpPost);
		System.out.println(httprequestResult);	

		/*...............Split and verifying the response code..... Here we are not verifying 
		 the created news id and published date........Since it depends on runtime execution
		 and date depends on the current data. So cant able to hardcode the values...........*/
		
		String a[]=httprequestResult.split(",");
	
			AssertJUnit.assertEquals(a[1],TEXT.getProperty("Response_Name_News"));                
			AssertJUnit.assertEquals(a[2],TEXT.getProperty("Response_Title_News"));
			AssertJUnit.assertEquals(a[3],TEXT.getProperty("Response_Preamble_News"));
			AssertJUnit.assertEquals(a[4],TEXT.getProperty("Response_Text_News"));
			AssertJUnit.assertEquals(a[6],TEXT.getProperty("Response_FolderId_News"));
		
		//Deleting the above created NEWS.........
		DeleteNews();

		soft.assertAll();
	}
	
	@Test(priority=2)
	public void CreateNewsUnauthorize() throws IOException, InterruptedException
	{
		soft.clearMaps();
		// Using REST API - to create user for above added Customer

		String restUrl="http://app-web.testak.kgk.ad/api/news-v0";//.....URL used to create NEWS under AKA

		//Authentication required-here we pass the login credentials required
		String username="n21214";
		String password="123456";

		// To retrieve the current date....
		String currentdate=gfun.getDate(driver, 0, 0, 0);
		currentdate=currentdate+" "+"06:00";

		// Creating an object of JSON as we are passing the input parameters and its values with Content type as json/application

		JSONObject user=new JSONObject();
		user.put("folderId", "3");
		user.put("name", "Auto_Unauth");
		user.put("title", "Auto_Unauth");
		user.put("preamble", "Auto_Unauth");
		user.put("text", "Auto_Unauth");
		user.put("publishingDate", currentdate);

		String jsonData=user.toString();
		//Creating connectivity to the  REST api url using Http client as it is post method,HttpPost 
		HttpPost httpPost=okapi.createConnectivity(restUrl , username, password);
		// Executing the request and storing the response 
		String httprequestResult=okapi.executeReq( jsonData, httpPost);
		System.out.println(httprequestResult);	
		
		AssertJUnit.assertEquals(httprequestResult,TEXT.getProperty("Response_Error1_News"));
		soft.assertAll();
	}


	public void DeleteNews() throws IOException, InterruptedException 
	{
		//........Deleting the above created news in AKA...........
		gfun.navigateUrl(driver, "AKA_URL"); 
		Thread.sleep(3000);
		//..........Login to AKA........
		driver.findElement(By.xpath(OR.getProperty("AKA_UserName"))).sendKeys("admin");
		WebElement PWD = driver.findElement(By.xpath(OR.getProperty("AKA_passwordEdtBox")));
		PWD.sendKeys("2much4u");
		driver.findElement(By.xpath(OR.getProperty("AKA_loginBtn"))).click();
		Thread.sleep(5000);	
		driver.findElement(By.xpath(OR.getProperty("AKA_Autokatalogen_AKLnk"))).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElement(By.xpath(OR.getProperty("AKA_Autokatalogen_AKLnk"))).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.findElement(By.xpath(OR.getProperty("AKA_Autokatalogen_AKLnk"))).click();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		boolean news=gfun.isElementPresent(driver, "AKA_DeleteNewsImg");
		if(news==true)
		{
			driver.findElement(By.xpath(OR.getProperty("AKA_DeleteNewsImg"))).click();
			Alert alert = driver.switchTo().alert();
			String alertmsg=alert.getText();
			gfun.verifyvalue(driver, alertmsg, "Do you really want to delete?");
			alert.accept();
		}
		else
			driver.findElement(By.xpath(OR.getProperty("AKA_LogoutBtn"))).click();
		Thread.sleep(2000);

	}


}
