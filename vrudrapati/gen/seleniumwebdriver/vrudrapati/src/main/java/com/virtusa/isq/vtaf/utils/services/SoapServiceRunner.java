package com.virtusa.isq.vtaf.utils.services;

/**
 * Created by snmuhandiram on 3/8/2017.
 */
public class SoapServiceRunner {
    SOAPClient client;

    public SoapServiceRunner(String url, String headers, String parameters, String authType, String user, String pass) throws Exception{
        client = new SOAPClient(url);
        addHeadersFromString(parameters);
    }

    public void addHeadersFromString(String parameters) throws Exception{
        if(!parameters.equalsIgnoreCase("null") && parameters != null){
            client.addParameter("xml", parameters);
        }
    }

    public String run() throws Exception{
        client.send();
        return client.readResponseAsString();
    }
}
