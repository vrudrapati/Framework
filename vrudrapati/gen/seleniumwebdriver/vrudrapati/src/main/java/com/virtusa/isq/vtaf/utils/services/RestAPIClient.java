package com.virtusa.isq.vtaf.utils.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RestAPIClient {

	private String mainURL = "";
	
	private String port = "";
	
	private JSONObject jsonObject;
	
	private CloseableHttpClient client;

	public JSONObject getJsonObject(final String responseText)
			throws Exception {
		JSONParser parser = new JSONParser();
		return (JSONObject) parser.parse(responseText);
	}

	public JSONArray getJsonArray(final String responseText)
			throws Exception {
		JSONParser parser = new JSONParser();
		return (JSONArray) parser.parse(responseText);
	}
	
	public void addJsonProperty(String Key, String Value){
		this.getJsonObject().put(Key, Value);
	}
	
	public void addJsonProperty(JSONObject jObject, String Value){
		this.getJsonObject().put(jObject, Value);
	}
	
	public void addJsonProperty(String Value, JSONObject jObject){
		this.getJsonObject().put(Value, jObject);
	}
	
	public String getResponseString(HttpResponse res) throws Exception {
        BufferedReader br = getBuffer(res);

        StringBuffer result = new StringBuffer();

        String line = "";
        while ((line = br.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
	}
	
	public static BufferedReader getBuffer(HttpResponse res) throws Exception {
		return new BufferedReader(new InputStreamReader(res.getEntity()
				.getContent()));
	}

	public void setMainURL(String uRL) {
		mainURL = uRL;
	}

	public String getMainURL() {
		
	//	final String fullURL = this.mainURL + ":" + this.port;
		
		return mainURL;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public CloseableHttpClient getClient() {
		return client;
	}

	public void setClient(CloseableHttpClient client) {
		this.client = client;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
