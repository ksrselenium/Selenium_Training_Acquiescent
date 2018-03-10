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
import org.openqa.selenium.By;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.valtech.kgk.businessFun.Common;
import com.valtech.kgk.businessFun.OKAPI;
import com.valtech.kgk.businessFun.SearchArticle;
import com.valtech.kgk.config.BaseTest;
import com.valtech.kgk.utilities.DataReader;


/**
 *Company : Valtech
 *@author Suresh
 *Description: s1710:As a product owner of EasyRoad I would like fuelconsumption to be available through OKAPI
 *Required JArs---json-simple-1.1.jar and commons-httpclient-3.1jar
 *@date 15-02-2017
 */
public class S1710_EasyRoad_FuelConsumption_Info  extends BaseTest
{

	DataReader readdata=new DataReader();
	GeneralFun gfun=new GeneralFun();
	Common cmn=new Common();
	OKAPI okapi=new OKAPI();
	SearchArticle sa=new SearchArticle();
	public Properties TEXT=readdata.readText();
	public Properties OR=readdata.readObjectRepository();
	public mySoftAssert soft= gfun.myassert();
	JSONObject obj = new JSONObject();
	
	//Verifying fuelconsumption---in okapi response
	
	@Test(priority=1)
	
	public void S1710_Vehicle_FuelCosumption_Okapi_InfoTest() throws IOException, BiffException, InterruptedException
	{

		soft.clearMaps();
		//-----Verifying response and status code----and Fuel consumpation info
		String Vehicle_FuelConsumption_Info_expectedResponse=requestAndResponse("skgk.auto","123456","http://app-web.testak.kgk.ad/api/vehicles-v1/AAK278",200);
		System.out.println("Customer_usersInfo_expectedResponse = "+Vehicle_FuelConsumption_Info_expectedResponse);
						
		boolean Vehicle_FuelConsumption_Info__exists=Vehicle_FuelConsumption_Info_expectedResponse.contains("fuelConsumption");	
		soft.assertTrue(Vehicle_FuelConsumption_Info__exists);
       
		soft.assertAll();

		}

//Verifying fuelconsupation info in application---more info dialog
	
	@Test(priority=2)
	public void S1710_Vehicle_FuelCosumption_Application_MoreInfoTest () throws IOException, BiffException, InterruptedException
	{  

		soft.clearMaps();
		gfun.navigateUrl(driver, "AkUrl");

		cmn.loginToApp(driver,"Excelpath","Login",0,1);
		cmn.selectCustomer(driver,"Excelpath","Customer",0, 1);
	//RegNo having fuel consumption info--In more info 
       sa.regnumbersearch(driver, "AAK278");
		
		sa.clickSok(driver);
		
		// Fuel consuption_Info_field(Driv 1 förbrukning:)--Post It Note Verification
		
		gfun.isElementPresent(driver, OR.getProperty("PostIt_Driv1_förbrukning_Field"), false);
	
		//More_Info_Dialog_ Fuel consuption_Info_field(Driv 1 förbrukning:)_Verification
		
		driver.findElement(By.xpath(OR.getProperty("moreInfoLnk"))).click();
		
        gfun.waitForElement(driver, 5, OR.getProperty("moreInfo_Driv1_förbrukning_Field"));
        
		gfun.verifytextaftertrim(driver,  OR.getProperty("moreInfo_Driv1_förbrukning_Field"), "Driv 1 förbrukning:", false);

		//Field value
		gfun.verifytextaftertrim(driver,  OR.getProperty("moreInfo_Driv1_förbrukning_Field_Value"), "7,9 l/100 km", false);
		
		driver.findElement(By.xpath(OR.getProperty("moreInfoStangBtn"))).click();
		
		Thread.sleep(2000);	
		
		cmn.logout(driver);	
		
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













