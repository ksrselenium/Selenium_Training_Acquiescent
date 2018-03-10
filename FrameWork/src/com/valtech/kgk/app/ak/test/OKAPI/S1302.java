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
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.client.methods.HttpPost;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.valtech.kgk.businessFun.OKAPI;
import com.valtech.kgk.utilities.DataReader;

public class S1302 {

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	public Properties TEXT=readdata.readText();

	public mySoftAssert soft= gfun.myassert();

	OKAPI okapi=new OKAPI();
	

	@Test(priority=1)
        // Accommodated DE5134.........
	public void S1302AddUserToCustomerTest() throws IOException, InterruptedException 
	{
		// Using REST API - to create user for added Customer

				String restUrl="http://app-web.testak.kgk.ad/api/customers-v0/21217/users";//--URL used to create user under customer
				//Authentication required-here we pass the login credentials required
				String username="kgk.new";
				String password="test123";
				// Creating an object of JSON as we are passing the input parameters and its values with Content type as json/application
//
				JSONObject user=new JSONObject();
				user.put("userName", "custak");//"ccususr" existing user under 21214...trying copy to 21217
					
							
				String jsonData=user.toString();
				System.out.println("jsondata="+jsonData);
				
				//Creating connectivity to the  REST api url using Http client as it is post method,HttpPost 
				HttpPost httpPost=okapi.createConnectivity(restUrl , username, password);
				// Executing the request and storing the response 
				String httprequestResult=okapi.executeReq( jsonData, httpPost);
				System.out.println("httprequestResult="+httprequestResult);
				
				AssertJUnit.assertEquals(httprequestResult, TEXT.getProperty("UsercreationSuccessMessage_S1302"));
		       soft.assertAll();
	}

	@Test(priority=2)
	public void S1302DeleteUserFromCustomerTest() throws IOException, InterruptedException 
	{
		soft.clearMaps();
		
		DeleteMethod delete = new DeleteMethod("http://app-web.testak.kgk.ad/api/customers-v0/21217/users/custak");
		delete.releaseConnection();
		soft.assertAll();

	}
	
	


}
