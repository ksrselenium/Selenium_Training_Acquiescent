package com.valtech.kgk.app.ak.test.OKAPI;

import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import jxl.read.biff.BiffException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.http.client.methods.HttpPost;
import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.valtech.kgk.businessFun.Common;
import com.valtech.kgk.businessFun.OKAPI;
import com.valtech.kgk.config.BaseTest;
import com.valtech.kgk.utilities.DataReader;


/**
 *Company : Valtech
 *@author Suresh
 *Description: S1702: As a system owner I would like a method in OKAPI that returns 
 *basic information about customer users in order to be able to create better functionality in RAUK
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date 14-02-2017
 */
public class S1702_Customer_Users_Info 
{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	Common cmn=new Common();
	OKAPI okapi=new OKAPI();
	public Properties TEXT=readdata.readText();
	public Properties OR=readdata.readObjectRepository();
	public mySoftAssert soft= gfun.myassert();
	JSONObject obj = new JSONObject();
	
	@Test(priority=1)
	public void S1702_Customer_Users_InfoTest() throws IOException, BiffException, InterruptedException
	{

		soft.clearMaps();
		//-----Verifying response and status code----and Customer info
		String Customer_usersInfo_expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/customers-v0/21217/users",200);
		System.out.println("Customer_usersInfo_expectedResponse = "+Customer_usersInfo_expectedResponse);
						
		boolean customer_users_userName_info_exists=Customer_usersInfo_expectedResponse.contains("userName");	
		soft.assertTrue(customer_users_userName_info_exists);
       
		boolean customer_users_givenName_info_exists=Customer_usersInfo_expectedResponse.contains("givenName");	
		soft.assertTrue(customer_users_givenName_info_exists);
		
		boolean customer_users_surName_info_exists=Customer_usersInfo_expectedResponse.contains("surName");	
		soft.assertTrue(customer_users_surName_info_exists);
		
		boolean customer_users_eMailAddress_info_exists=Customer_usersInfo_expectedResponse.contains("eMailAddress");	
		soft.assertTrue(customer_users_eMailAddress_info_exists);
		
		boolean customer_phone_info_exists=Customer_usersInfo_expectedResponse.contains("phone");	
		soft.assertTrue(customer_phone_info_exists);
		
		boolean customer_salesNumber_info_exists=Customer_usersInfo_expectedResponse.contains("salesNumber");	
		soft.assertTrue(customer_salesNumber_info_exists);
		
		boolean customer_userType_info_exists=Customer_usersInfo_expectedResponse.contains("userType");	
		soft.assertTrue(customer_userType_info_exists);
			

		soft.assertAll();

			
	
	}

	@Test(priority=2)
	public void S1702_InvaliCustomer_Users_InfoTest() throws IOException, BiffException, InterruptedException
	{

		soft.clearMaps();
		//-----Verifying response and status code----and Customer info
		String Customer_usersInfo_expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/customers-v0/123456/users",404);
		System.out.println("Customer_usersInfo_expectedResponse = "+Customer_usersInfo_expectedResponse);
			
		boolean customer_Error_info_exists=Customer_usersInfo_expectedResponse.contains("customer not found:");	
		soft.assertTrue(customer_Error_info_exists);
		
	}
	
	public String executerequest(String restUrl,String username,String password )
	{
		String jsonData=obj.toString();
		System.out.println(jsonData);
		//Creating connectivity to the  REST api url using Http client as it is post method,HttpPost 
		HttpPost httpPost=okapi.createConnectivity(restUrl , username, password);
		// Executing the request and storing the response 
		String httprequestResult=okapi.executeReq( jsonData, httpPost);
		return httprequestResult;
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
		InputStream responseBody = method.getResponseBodyAsStream();

		String result=getStringFromInputStream(responseBody);


		System.out.println(result);

		// Deal with the response.
		// Use caution: ensure correct character encoding and is not binary data
		return result;



	}
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}
}













