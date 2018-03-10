package com.valtech.kgk.app.ak.test.OKAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.util.Properties;

import jxl.read.biff.BiffException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.testng.annotations.Test;

import com.valtech.kgk.businessFun.Common;
import com.valtech.kgk.businessFun.OKAPI;
import com.valtech.kgk.businessFun.SearchArticle;
import com.valtech.kgk.businessFun.ShoppingCartFun;
import com.valtech.kgk.utilities.DataReader;
import com.valtech.kgk.utilities.DataReader;
import com.valtech.kgk.config.BaseTest;


public class S1569_ViewCartInfo  extends BaseTest
{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	Common cmn=new Common();
	OKAPI okapi=new OKAPI();
	public Properties TEXT=readdata.readText();
	public Properties OR=readdata.readObjectRepository();
	public mySoftAssert soft= gfun.myassert();
	ShoppingCartFun Sc=new ShoppingCartFun();
	SearchArticle Sa=new SearchArticle();
	
	//.......NIGE..... 
	// Some steps are not feasible to automate... For eg: Delivery date, cart modified time, everytime while adding article to cart,Time and values will be
	// Changed. So, checking only the response code....
	//Not cross checked with the values from the application.................
	
	@Test(priority=1)
	public void S1569_VerifyCart()throws BiffException, IOException, InterruptedException
	{		
		soft.clearMaps();
		String expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/carts-v1/21217",200);
		System.out.println("actual="+expectedResponse);
			
		soft.assertAll();
		
	}
	
	@Test(priority=2)
	public void S1569_VerifyArticles()throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl"); 
		cmn.loginToApp(driver,"Excelpath","Login",0,1);
		cmn.selectCustomer(driver, "Excelpath", "Customer", 0, 11);
		Sc.selectcart(driver, "new1");
		Thread.sleep(2000);
		Sa.freeText(driver, "CX 1768030");//Test data.. 
		Sa.clickSok(driver);
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("clickKopBtn"))).click();
		gfun.waitForPageToLoad(driver);
	
		gfun.verifytextaftertrim(driver, OR.getProperty("cartDetailsTxt"), "1 artiklar. Totalt:688,00 kr (exkl. moms)", false);
		
		
		String expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/carts-v1/21217/new1",200);
		System.out.println("actual="+expectedResponse);
		
		soft.assertAll();
	}
	
	@Test(priority=3)
	public void S1569_ClearCart()throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		//Clear cart in OKAPI.....
		String expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/carts-v1/21217/new1/clear",200);
		System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("21217ClearCart"));
		
		//Verify in application whether the cart is cleared or not........
		gfun.navigateUrl(driver, "AkUrl"); 
		cmn.loginToApp(driver,"Excelpath","Login",0,1);
		cmn.selectCustomer(driver, "Excelpath", "Customer", 0, 11);
		Sc.selectcart(driver, "new1");
		Sc.navigatetoPOAK(driver);
		Thread.sleep(2000);
		
		//... Accommodated DE5182.......
		gfun.verifytextaftertrim(driver, OR.getProperty("cartDetailsTxt"), "MiniCartMsgNoArticles", true);
		Thread.sleep(1000);
		soft.assertAll();
	}

	
	public String requestAndResponse(String userName,String Password,String URL,int ExpectedStatuscode) throws HttpException, IOException
	{
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();

		//Setting the credentials using Basic Authentication
		Credentials defaultcreds = new UsernamePasswordCredentials(userName, Password);
		client.getState().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),  defaultcreds);

		GetMethod method = new GetMethod(URL);
		//Executing the GET method
		int statusCode = client.executeMethod(method);
		System.out.println(statusCode);
		AssertJUnit.assertEquals(statusCode, ExpectedStatuscode);

		// Read the response body.
		byte[] responseBody = method.getResponseBody();
		System.out.println(new String(responseBody));

		// Deal with the response.
		// Use caution: ensure correct character encoding and is not binary data
		return (new String(responseBody));

	}
	
	}
