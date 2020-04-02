package com.virtusa.isq.vtaf.utils.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

public class RestHttpClient extends RestAPIClient{

	private HttpPost postRequest;

	private HttpGet getRequest;

	 public void addPostHeader(final String Key, final String Value){
		 this.postRequest.addHeader(Key, Value);
	 }
	 
	 public void setEntity(StringEntity parameter){
		 this.postRequest.setEntity(parameter);
	 }

	public HttpPost getHttpPostRequest() {
		return postRequest;
	}
	
	public HttpPost setHttpPostRequest(String URL) {
		postRequest = new HttpPost(URL);
		return postRequest;
	}
	
	public HttpPost setHttpPostRequest() {
		postRequest = new HttpPost(getMainURL());
		return postRequest;
	}
	
	public void addGetHeader(final String Key, final String Value){
		getRequest.addHeader(Key, Value);
	 }

	public HttpGet getGetRequest() {
		return getRequest;
	}

	public void setGetRequest(String URL) {
		this.getRequest = new HttpGet(URL);
	}
	
	public void setGetRequest() {
		this.getRequest = new HttpGet(getMainURL());
	}
	

	public String getBasicAuthentication(final String name,
			final String password) {
		String userName = name + ":" + password;
		final byte[] authBytes = userName.getBytes(StandardCharsets.UTF_8);
		return Base64.getEncoder().encodeToString(authBytes);
		// request.setHeader("Authorization", "Basic " + encoded);
	}

}
