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
 *@author hemanth.kumar
 *Description: Mapped to KGK_TC1
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date Sep 9, 2014
 *@time 5:35:28 PM
 */
public class VehicleOKAPI{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	public Properties TEXT=readdata.readText();

	public mySoftAssert soft= gfun.myassert();


	@Test(priority=1)
	public void getVehicleManufacturer() throws IOException, InterruptedException 
	{
		soft.clearMaps();
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/tecDocTypes/PASSENGER/manufacturers",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("passengerManufacturers"));	
		expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/tecDocTypes/COMMERCIAL/manufacturers",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("commercialManufacturers"));							
		soft.assertAll();

	}


	@Test(priority=2)
	public void getVehicleModels() throws IOException, InterruptedException 
	{
		soft.clearMaps();				
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/tecDocTypes/PASSENGER/manufacturers/16/models",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("modelsManufacturer16"));	
		expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/tecDocTypes/COMMERCIAL/manufacturers/2927/models",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("modelsManufacturer_2927_Models"));				
		soft.assertAll();

	}


	@Test(priority=3)
	public void getVehicleType() throws IOException, InterruptedException 
	{
		soft.clearMaps();				
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/tecDocTypes/PASSENGER/manufacturers/16/models/4088/types",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("typesMan16Model4088"));	
		expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/tecDocTypes/COMMERCIAL/manufacturers/2927/models/8665/types",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("typesMan2243Mode_8665_profss"));				
		soft.assertAll();

	}


	@Test(priority=4)
	public void getVehicleNodeLinkedArticles() throws IOException, InterruptedException 
	{
		soft.clearMaps();				
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717/articleNodes?nodeid=918264",200);
		System.out.println("expectedResponse_suku ="+expectedResponse);
		//Node names and properties are dynamic so we cont verify commenting below code
		
		//AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("Articles_POB717_Node_918264"));	
		
		
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
