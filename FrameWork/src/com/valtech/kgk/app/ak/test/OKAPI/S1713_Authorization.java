package com.valtech.kgk.app.ak.test.OKAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.testng.annotations.Test;

import com.valtech.kgk.utilities.DataReader;



/**
 *Company : Valtech
 *@author Varalakshmi M S
 *Description: S1713 - As a system owner I want to implement customer selection and authorization in OKAPI (VehicleModule)
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date Feb 15, 2017
 *@time 
 */
public class S1713_Authorization
{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	public Properties TEXT=readdata.readText();
	public mySoftAssert soft= gfun.myassert();


	@Test(priority=1)
	public void S1713_Customer_InformationVerification() throws IOException, InterruptedException 
	{
		
		//----------------------------KGK user--------------------------------
		
		//-----Verifying customer(on extranet) information and response and status code for KGK user 
		String expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/customers-v0/21217",200);
		System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("21217CustomerInfo"));	
		
		//-----Verifying customer(not on extranet- 21110) information and response and status code for KGK user 
				expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/customers-v0/21110",404);
				System.out.println("actual="+expectedResponse);
				AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("ErrormessageForCustomerNotFound"));	
		
		//-----------------------------Customer user---------------------------
		
		//-----Verifying Customer information of customer user having api right
		 expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/customers-v0/21214",200);
		 System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("21214Customerinfo"));	

		//-----Verifying Customer(not linked to the customer user) information of customer user having api right

		expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/customers-v0/21217",404);
		 System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("ErrormessageForCustomerNotFoundFor21217"));		
		
		soft.assertAll();

	}

	@Test(priority=2)
	public void S1713_Cart_InformationVerification() throws IOException, InterruptedException 
	{
		//-----------------------------Customer user---------------------------
		
		//-----Verifying Cart information of customer user having api right
		String expectedResponse=requestAndResponse("tests1713","123456","http://app-web.testak.kgk.ad/api/carts-v1/920093",200);
		System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("920093_CartInfo"));	

		//-----Verifying Cart(not linked to the customer user) information of customer user having api right

		expectedResponse=requestAndResponse("tests1713","123456","http://app-web.testak.kgk.ad/api/carts-v1/21217",404);
		System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("ErrormessageForCustomerNotFoundFor21217"));		
		
		soft.assertAll();

	}

	@Test(priority=3)
	public void S1713_Vehicle_InformationVerification() throws IOException, InterruptedException 
	{
		
		//----------------------------KGK user--------------------------------
		
		//-----Verifying Vehicle information and response and status code for KGK user 
		String expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717",200);
		System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("POB717Vehicleinfo"));	
			
		//-----------------------------Customer user---------------------------
		
		//-----Verifying Vehicle information of customer user having “KundApiFordonsInformation” & Behörighet att söka på registreringsnummer right
		 expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717",200);
		 System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("POB717Vehicleinfo"));	
		//-----Verifying Vehicle information of customer user not having “KundApiFordonsInformation” & Behörighet att söka på registreringsnummer right

		expectedResponse=requestAndResponse("s21311","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717",401);
		 System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("ErrormessageForMissingRights"));	
		
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
