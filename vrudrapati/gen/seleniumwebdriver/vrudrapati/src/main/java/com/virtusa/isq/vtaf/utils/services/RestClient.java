package com.virtusa.isq.vtaf.utils.services;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public abstract class RestClient extends CallWebService{
	/*
	 * Properties
	 */
	/****************** url ******************/
	private String url;
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    /****************** responseStr ******************/
	private String responseStr;
    public String getResponseStr() { return responseStr; }
    public void setResponseStr(String response) { this.responseStr = response; }




    /*
     * Overriding
     */
    @Override
    public String readResponseAsString() throws Exception {
        return getResponseStr();
    }






    /*
     * Helper Static Methods
     */
	public  static String getStrOfResponse(HttpResponse res) throws Exception {
        BufferedReader br = getBufferOfRes(res);

        StringBuffer result = new StringBuffer();

        String line = "";
        while ((line = br.readLine()) != null) {
            result.append(line);
        }

        return result.toString();
	}

    public static BufferedReader getBufferOfRes(HttpResponse res) throws Exception {
        return new BufferedReader(
                new InputStreamReader(
                        res.getEntity().getContent()));
    }


}
