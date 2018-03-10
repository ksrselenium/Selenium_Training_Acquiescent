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
 *Description: s1515-----Add volume discount to an article in the response from OKAPI
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date 13-06-2016
 */
public class S1515_VolumeDiscount 
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
	public void S1515_VolumeDiscount_InfoTest() throws IOException, BiffException, InterruptedException
	{

		soft.clearMaps();
		//-----Verifying response and status code----and volume discount info
		String VolumeDiscountInfo_expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/articles-v0/21214/13929",200).trim();
		System.out.println("VolumeDiscountInfo_expectedResponse = "+VolumeDiscountInfo_expectedResponse);
						
		boolean volumeDiscount_info_exists=VolumeDiscountInfo_expectedResponse.contains("volumeDiscount");	;
		soft.assertTrue(volumeDiscount_info_exists);
       
		
		// For reading the unicode-text from textproperties we are using below unescapejava
		String volumeDiscountInfo = StringEscapeUtils.unescapeJava(VolumeDiscountInfo_expectedResponse);	
	
		System.out.println("volumeDiscountInfo="+volumeDiscountInfo);
							

		soft.assertAll();

			
	
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













