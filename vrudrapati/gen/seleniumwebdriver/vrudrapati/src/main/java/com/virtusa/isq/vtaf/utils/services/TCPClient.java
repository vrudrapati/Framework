package com.virtusa.isq.vtaf.utils.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 * Created by snmuhandiram on 2/27/2017.
 */
public class TCPClient extends CallWebService{

    Socket client;

    /****************** responseStr ******************/
    private String responseStr;
    public String getResponseStr() { return responseStr; }
    public void setResponseStr(String response) { this.responseStr = response; }

    public TCPClient(String url, int port) throws Exception{
        this.client = new Socket(url, port);
    }

    @Override
    public void addHeader(String key, String value) throws Exception {
        throw new NotImplementedException();
    }

    @Override
    public void addParameter(String key, String value) throws Exception {
        throw new NotImplementedException();
    }

    @Override
    public void addBasicAuth(String username, String password) throws Exception {
        throw new NotImplementedException();
    }

    @Override
    public void send() throws Exception {
        String result = "";
        BufferedReader in = new BufferedReader(new
                InputStreamReader(client.getInputStream()));

        while (!in.ready()) {}
            result += in.readLine();

        setResponseStr(result);
        in.close();
    }
}
