package com.valtech.kgk.app.ak.test.OKAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import generalFun.GeneralFun;
import generalFun.mySoftAssert;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
/**
 *Company : Valtech
 *@author Varalakshmi M S
 *Description: Mapped to TC14--OKAPI
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date June 13, 2016
 *@time 10:51:28 AM
 */

public class S1588_Article_suggestions  
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
	

	
	@Test(priority=1)
	public void S1588_article_suggestion()throws BiffException, IOException, InterruptedException
	{
		soft.clearMaps();
		//To get 5 suggestions for the article vl.....
		String expectedResponse=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/articles-v0/suggest/articleNumber?articleNumber=vl&length=5",200);
		System.out.println("actual="+expectedResponse);
		AssertJUnit.assertEquals(TEXT.getProperty("articlesuggestions_vl"),expectedResponse);
		
		//To verify the default suggestions to be displayed when there is no length
		String expectedResponse1=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/articles-v0/suggest/articleNumber?articleNumber=vl",200);
		System.out.println("actual="+expectedResponse1);
		AssertJUnit.assertEquals(TEXT.getProperty("defaultsuggestions_vl"),expectedResponse1);
	
		//When length is set to zero...it should display 10 suggestions(should work as default)
		String expectedResponse2=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/articles-v0/suggest/articleNumber?articleNumber=vl&length=0",200);
		System.out.println("actual="+expectedResponse2);
		AssertJUnit.assertEquals(TEXT.getProperty("defaultsuggestions_vl"),expectedResponse2);
		
		//Verifying suggestions for exhausted/invalid article
		 String v=URLEncoder.encode("CR 9EHVX 9 ", "UTF-8");
		 
		 String url="http://app-web.testak.kgk.ad/api/articles-v0/suggest/articleNumber?articleNumber="+v+"&length=1";
		 String expectedResponse5=requestAndResponse("kgk.new","test123",url,200);
		 System.out.println("actual="+expectedResponse5);
		AssertJUnit.assertEquals(TEXT.getProperty("Exhaustedarticlesuggestions"),expectedResponse5);
		
		//verifying article number can have any no of chars, alphabets,numerics,swedish chars

		String v1=URLEncoder.encode("95109 100 GÅ/RÖ", "UTF-8");
		
		String url1="http://app-web.testak.kgk.ad/api/articles-v0/suggest/articleNumber?articleNumber="+v1+"&length=1";
		String expectedResponse3=requestAndResponse("kgk.new","test123",url1,200);
		System.out.println("actual="+expectedResponse3);
		AssertJUnit.assertEquals(TEXT.getProperty("Swedisharticlesuggestions"),expectedResponse3);
		
		//Verifying for many suggestions
		String expectedResponse4=requestAndResponse("kgk.new","test123","http://app-web.testak.kgk.ad/api/articles-v0/suggest/articleNumber?articleNumber=da&length=100",200);
		System.out.println("actual="+expectedResponse4);
		AssertJUnit.assertEquals(TEXT.getProperty("many_suggestions"),expectedResponse4);

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
