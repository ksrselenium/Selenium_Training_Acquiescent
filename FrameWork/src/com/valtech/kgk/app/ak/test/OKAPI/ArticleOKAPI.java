package com.valtech.kgk.app.ak.test.OKAPI;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
public class ArticleOKAPI{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	public Properties TEXT=readdata.readText();

	public mySoftAssert soft= gfun.myassert();


	@Test(priority=1)
	public void articleInfoFromInformationCollection() throws IOException, InterruptedException 
	{
		//-----Verifying response and status code for customer user having api right
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/articles-v0/download?infocol=157",200);
		soft.assertAll();

	}


	@Test(priority=2)
	public void articleInformation() throws IOException, InterruptedException 
	{
		soft.clearMaps();
		//-----Verifying response and status code for customer user having api right
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/articles-v0/8UW%20351%20248%20051",200);
		AssertJUnit.assertEquals(expectedResponse, TEXT.getProperty("articleInfoTxt1"));
		soft.assertAll();

	}

	@Test(priority=3)
	public void articleInformationForCustomer() throws IOException, InterruptedException 
	{
		soft.clearMaps();
		//-----Verifying response and status code for customer user having api right
		String expectedResponse=requestAndResponse("h21214","111111","http://app-web.testak.kgk.ad/api/articles-v0/21214/8UW%20351%20248%20051",200);
		AssertJUnit.assertEquals(expectedResponse, TEXT.getProperty("articleInfoTxt2"));
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
