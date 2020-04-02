package com.virtusa.isq.vtaf.utils.services;

import org.apache.http.impl.client.HttpClientBuilder;

public class QtestClient extends RestHttpClient {

	private String URL;

	private String port;

	private String endPoint;

	public QtestClient(final String URL) {
		this.URL = URL;
		setClient(HttpClientBuilder.create().build());
		setMainURL(URL);
	}
	
	public QtestClient() {
		
	}
	


}
