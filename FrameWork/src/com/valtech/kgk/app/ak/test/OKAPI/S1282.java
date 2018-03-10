package com.valtech.kgk.app.ak.test.OKAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import com.valtech.kgk.utilities.DataReader;

/**
 *Company : Valtech
 *@author hemanth.kumar
 *Description: Mapped to KGK_TC2
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date Sep 9, 2014
 *@time 5:35:28 PM
 */

public class S1282{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	public Properties TEXT=readdata.readText();

	public mySoftAssert soft= gfun.myassert();


	@Test(priority=1)
	public void S1282AuthenticationTest() throws IOException, InterruptedException 
	{
		//-----Verifying response and status code for customer user having api right
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717/articleNodes",200);
		//soft.assertEquals(expectedResponse,TEXT.getProperty("POB717Articlenodes"));	//---commenting as article in nodes is dynamic and we cannot verify it at high level

		//-----Verifying response and status code for customer user having not api right
		expectedResponse=requestAndResponse("h21214km","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717/articleNodes",401);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("ErrorNoVehicleinformationHandlerightTxt"));

		//-----Verifying response and status code for standard kgk user(not having api right and superadmin right)

		expectedResponse=requestAndResponse("Autostandardkgk","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717/articleNodes",401);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("ErrorNoVehicleinformationHandlerightTxt"));
		
		//-----Verifying response and status code for kgk user having only api right
		
		expectedResponse=requestAndResponse("AutoAPIKGK","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717/articleNodes",200);

		//-----Verifying response and status code for kgk user having only superadmin right
		
		expectedResponse=requestAndResponse("Autosupradminonly","111111","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717/articleNodes",200);


		//-----Verifying response and status code for KGK user having Super admin right and api right
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/POB717/articleNodes",200);
		//soft.assertEquals(expectedResponse,TEXT.getProperty("POB717Articlenodes"));	//---commenting as article in nodes is dynamic and we cannot verify it at high level
		soft.assertAll();

	}



	@Test(priority=2)
	public void S1282regNumberTest() throws IOException, InterruptedException 
	{
		
		soft.clearMaps();
		//-----Verifying response and status code for personbil
		String expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/AAA006/articleNodes",200);
		//soft.assertEquals(expectedResponse,TEXT.getProperty("AAA006Articlenodes"));//---commenting as article in nodes is dynamic and we cannot verify it at high level	
		//-----Verifying response and status code for Commercial
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/AEZ457/articleNodes",200);
		//soft.assertEquals(expectedResponse,TEXT.getProperty("AAU349Articlenodes"));
		//-----Verifying response and status code for Trailer identified
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/AAA009/articleNodes",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("AAA009Articlenodes"));
		//-----Verifying response and status code for Only Hansen identified
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/AAB328/articleNodes",200);
		//---commenting as article in nodes is dynamic and we cannot verify it at high level
		//AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("AAA007Articlenodes"));
		
		//-----Verifying response and status code for Trailer not identified
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/APC474/articleNodes",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("APC474Articlenodes"));
		//-----Verifying response and status code for vehiclenotidentified
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/AAA002/articleNodes",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("AAA002Notfound"));

			
		//-----Verifying response and status code for Norway single tecdoc 
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/AA22449/articleNodes",200);
		//AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("AA224499Articlenodes"));
		//-----Verifying response and status code for Norway multilpe tecdoc
		expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v0/DK47834/articleNodes",200);
		//AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RH19852Notfound"));
		
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

		int statusCode = client.executeMethod(method);
		System.out.println(statusCode);
		AssertJUnit.assertEquals(statusCode, ExpectedStatuscode);

		// Read the response body.
		InputStream responseBody = method.getResponseBodyAsStream();	

		byte[] bytes = IOUtils.toByteArray(responseBody);
		System.out.println(new String(bytes));

		return(new String(bytes));


	}
}
