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
 *@author Suresh
 *Description: s1754-As a system owner I would like a new OKAPI request that returns tecdocno only in order to be able to provide to partners
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date April 17, 2017
 *@time 10:35:28 PM
 */

public class S1754_Direct_Tecdoc_Search{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	public Properties TEXT=readdata.readText();

	public mySoftAssert soft= gfun.myassert();


	@Test(priority=1)
	public void S1754_Tecdoc_Info() throws IOException, InterruptedException 
	{

		soft.clearMaps();
		//---kgk user--Verifying response and status code for personbil regno mapped to single tecdoc
		String expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/AAB731/tecDocNumber",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedToTecdoc_Response"));
		
		//---Customer user--Verifying response and status code for personbil regno mapped to single tecdoc
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/AAB731/tecDocNumber",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedToTecdoc_Response"));
		
		// RegNo Not mapped to tecdoc 	
		
		//---kgk user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/AAA002/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_Not_MappedToTecdoc_Response"));
		
		//---Customer user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/AAA002/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_Not_MappedToTecdoc_Response"));	
		
		
		// RegNo Not mapped to tecdoc but make identified 	
		
		//---kgk user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/PNW850/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_Not_MappedToTecdoc_MakeIdentified_Response"));
		
		//---Customer user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/PNW850/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_Not_MappedToTecdoc_MakeIdentified_Response"));	
		
		// RegNo Not mapped to tecdoc but make and model identified 	
		
		//---kgk user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/LMM698/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_Not_MappedToTecdoc_Make_Model_Identified_Response"));
		
		//---Customer user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/LMM698/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_Not_MappedToTecdoc_Make_Model_Identified_Response"));	
		
	
		// RegNo mapped to multiple Tecdoc
		
		//---kgk user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/HYP741/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedTo_MultipleTecdoc_Response"));
		
		//---Customer user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/HYP741/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedTo_MultipleTecdoc_Response"));	
		
						
		
		// RegNo related to slapvagan 
		
		//---kgk user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/AAR998/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedTo_slapvagan_Response"));
		
		//---Customer user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/AAR998/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedTo_slapvagan_Response"));	
						
		
		// RegNo mapped to tecdoc commercial vehicle	
		
		//---kgk user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/BGJ730/tecDocNumber",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedToTecdoc_CommericialRegno_Response"));
		
		//---Customer user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/BGJ730/tecDocNumber",200);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("RegNo_MappedToTecdoc_CommericialRegno_Response"));	
		
					
		// Invalid RegNo
		
		//---kgk user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto_kgk","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/123456/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("Invalid_Regno_Response"));
		
		//---Customer user--Verifying response and status code
		expectedResponse=requestAndResponse("s1754_Auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/123456/tecDocNumber",404);
		AssertJUnit.assertEquals(expectedResponse,TEXT.getProperty("Invalid_Regno_Response"));	
		
	
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
		System.out.println("statusCode = "+statusCode);
		AssertJUnit.assertEquals(statusCode, ExpectedStatuscode);

		// Read the response body.
		InputStream responseBody = method.getResponseBodyAsStream();	

		byte[] bytes = IOUtils.toByteArray(responseBody);
		System.out.println("Response = " +new String(bytes));

		return(new String(bytes));


	}
}
