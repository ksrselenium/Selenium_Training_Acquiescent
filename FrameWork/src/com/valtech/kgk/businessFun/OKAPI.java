package com.valtech.kgk.businessFun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class OKAPI {
	
	
	//This menthod is used to create connection to the HttpPost method using HTTP client  which requires REST API url , username and password as its input arguments
	//this also sets the content type as application/json
	public HttpPost createConnectivity(String restUrl, String username, String password)
	{
		HttpPost post = new HttpPost(restUrl);
		String auth=new StringBuffer(username).append(":").append(password).toString();
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		post.setHeader("AUTHORIZATION", authHeader);
		post.setHeader("Content-Type", "application/json");
		post.setHeader("Accept", "application/json");
		post.setHeader("X-Stream" , "true");
		return post;
	}
	
//this method is used to execute the HttpPost request , along with exception handling
	public String executeReq(String jsonData, HttpPost httpPost)
	{
		String Requestresult=null;

		try{
			Requestresult=executeHttpRequest(jsonData, httpPost);

		}
		catch (UnsupportedEncodingException e){
			System.out.println("error while encoding api url : "+e);
		}
		catch (IOException e){
			System.out.println("ioException occured while sending http request : "+e);
		}
		catch(Exception e){
			System.out.println("exception occured while sending http request : "+e);
		}
		finally{
			httpPost.releaseConnection();
		}
		return Requestresult;
	}
	//this method is used to execute the HttpRequest, tht requires input test data in Json format and HttpPOst
	String executeHttpRequest(String jsonData,  HttpPost httpPost)  throws UnsupportedEncodingException, IOException
	{
		HttpResponse response=null;
		String line = "";
		StringBuffer result = new StringBuffer();
		httpPost.setEntity(new StringEntity(jsonData));
		HttpClient client = HttpClientBuilder.create().build();
		response = client.execute(httpPost);
		System.out.println("Post parameters : " + jsonData );
		System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		while ((line = reader.readLine()) != null){ result.append(line); }

		return result.toString();
	}

}
