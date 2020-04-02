package com.virtusa.isq.vtaf.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.common.net.HttpHeaders;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.virtusa.isq.vtaf.utils.files.JSONReader;


public class HttpWebService {
	
	private static String storeToken;
	String token = "";

	// get the stored Token
	public static String getstoreToken() {
		return HttpWebService.storeToken;
	}

	// Store the Token
	public static void setstoreToken(String value) {
		HttpWebService.storeToken = value;
	}

	// set the get token for the first time
	public static boolean notfirst;

	public static void setfirst() {
		HttpWebService.notfirst = true;
	}
	
	//Used to get the response Code and response message
	public String[] httpURLConnectionRunner(String url, String inputresponseCode, String headers, String parameters,
			String AuthType, String username, String password) throws Exception {
		String[] ResponseDetails = new String[3];

		HttpURLConnection request = null;

		// send Https request
		if (url.contains("https")) {
			request = this.getHttpsRequest(url, inputresponseCode, headers, parameters, AuthType, username, password);
		}
		// send Http request
		else if (url.contains("http")) {
			request = this.getHttpRequest(url, inputresponseCode, headers, parameters, AuthType, username, password);
		}

		if (null != request) {
			// get the response code
			int ResponseCode = request.getResponseCode();
			// get the response message related to code
			String ResponseMessage = request.getResponseMessage();
			String responseText = "";
			// get the content type of the request using header field
			String ContentType = request.getHeaderField("Content-Type");

			if(null ==ContentType )
			{
				responseText = null;
			}
			// get the json response as a String to a variable.
			else if (ContentType.contains("application/json")) {
				try
				{
				JsonParser jp = new JsonParser();
				JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
				responseText = root.toString();
				}
				catch(Exception e)
				{
					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getErrorStream()));
					responseText = root.toString();
				}
			}
			// get the XML response as a String to a variable
			else if (ContentType.contains("application/xml")) {
				//create Input Stream Reader
				InputStreamReader inputReader = null;
				//create String Builder to append xml text
				StringBuilder sb = new StringBuilder();
				try{
				inputReader = new InputStreamReader(request.getInputStream(),
						Charset.defaultCharset());
				}
				catch(Exception e)
				{
					inputReader = new InputStreamReader(request.getErrorStream(),
							Charset.defaultCharset());
				}
				BufferedReader bufferedReader = new BufferedReader(inputReader);
				if (bufferedReader != null) {
					int lines;
					//read xml
					while ((lines = bufferedReader.read()) != -1) {
						sb.append((char) lines);
					}
					bufferedReader.close();
				}
				responseText = sb.toString();
			}

			// adding the response code and message into the list
			ResponseDetails[0] = String.valueOf(ResponseCode);
			ResponseDetails[1] = ResponseMessage;
			ResponseDetails[2] = responseText;			
		} else {
			// when response not retrieved
			ResponseDetails[0] = "null";
			ResponseDetails[1] = "null";
			ResponseDetails[2] = "null";			
		}
		return ResponseDetails;
	}
    
	
	//This method is used to add Headers
	public void addHeaders(String headers,HttpURLConnection request)
    {
		if(null != headers && !headers.equals("null")){
            //split multiple headers
			String[] kVList = headers.split("&");

            for(int i = 0; i < kVList.length; i++){
            	//split parameters of header
                String[] kv = kVList[i].split("=");
                request.setRequestProperty(kv[0], kv[1]);
            }
        }
    }
	
	//This method used to add Parameters to the URL
	public String addParameters(String param,String url){
		String fullurl;
		//check whether url should have parameters
		if(!param.equals("null") && param != null){
            
        	 fullurl = url+"?"+param; 
        }
        else
        {
        	fullurl = url;
        }
		return fullurl;
    }
	
	
	//Verify the response Text with a given input String and check whether it is matched with expected value.
	public String verifyresponseText(String PathToVerify, String ExpectedValue, String url, String inputresponseCode,
			String headers, String parameters, String AuthType, String username, String password) throws Exception {
		
		HttpURLConnection request = null;
		
		if(url.contains("https"))
		{
			request = this.getHttpsRequest(url, inputresponseCode, headers, parameters, AuthType, username,
				password);
		}
		else if(url.contains("http"))
		{
			request = this.getHttpRequest(url, inputresponseCode, headers, parameters, AuthType, username,
					password);
		}

		if (null != request) {
			// get the response code
			int ResponseCode = request.getResponseCode();
			// check whether response code is matched with given response code.
			if (ResponseCode == Integer.parseInt(inputresponseCode)) {
				// identify the content type using header field.
				String ContentType = request.getHeaderField("Content-Type");
				String resultText = "";

				// check whether response Text is JSON
				if (ContentType.contains("application/json")) {
					JsonParser jp = new JsonParser(); // from gson
					JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
					// Get the Json Element as an object
					JsonObject rootobj = root.getAsJsonObject();

					JSONParser parser = new JSONParser();
					Object jsonObj = parser.parse(rootobj.toString());
					JSONReader jr = new JSONReader();

					// get the text content of given string of json
					try {
						resultText = jr.getNode(PathToVerify, jsonObj).get("value");
					} catch (Exception e) {
						return null;
					}
				}
				// check whether response Text is XML
				if (ContentType.contains("application/xml")) {
					// create Document Factory Instance
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					// create Document Builder instance
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document doc = db.parse(new URL(url).openStream());
										
					// create new Xpath instance
					XPath xpath = XPathFactory.newInstance().newXPath();
					XPathExpression expr = xpath.compile(PathToVerify);
					Object result = expr.evaluate(doc, XPathConstants.NODESET);
					// get the relevant node
					NodeList nodes = (NodeList) result;
					// get the text content of the given node
					try {
						resultText = nodes.item(0).getTextContent();
					} catch (Exception e) {
						return null;
					}

				}
				return resultText;
			} else {
				return "ResponceCodeError" + ResponseCode;
			}
		} else {
			return null;
		}
	}
	
	//Get the Token	
	public String getToken(String url,String headers, String parameters,
			 String username, String password) throws Exception
	{
		//get the URL
		String fullURL = addParameters(parameters, url);
		String sURL = fullURL;
		URL surl = new URL(sURL);
		HttpURLConnection request = (HttpURLConnection) surl.openConnection();
		
		if ((!username.equals("null") && !password.equals("null")))
		{

			String authString = username + ":" + password;
			// Encoding authentication credentials
			byte[] authEncBytes = java.util.Base64.getEncoder().encode(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			request.setRequestProperty("Authorization", "Basic " + authStringEnc);

		}
		
		final String AUTHENTICATION_SCHEME = "Bearer";

			// get header fields authorization
			String authorizationHeader = request.getHeaderField(HttpHeaders.AUTHORIZATION);
			
			// check whether header contain authorization token.
			if (null != authorizationHeader
					&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase())) {
				// retrieve the authentication token.
				token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
				//Store the Token
				HttpWebService.setstoreToken(token);
			}
			// return the Token.
			return token;
			
	}
	
	//save the Token in the first time execution
	public void setInitialToken(String url,String headers, String parameters,
			 String username, String password) throws Exception
	{
		
		String fullURL = addParameters(parameters, url);
		String sURL = fullURL;
		URL surl = new URL(sURL);
		HttpURLConnection request = (HttpURLConnection) surl.openConnection();
		
		if ((!username.equals("null") && !password.equals("null")))
		{

			String authString = username + ":" + password;
			// Encoding authentication credentials
			byte[] authEncBytes = java.util.Base64.getEncoder().encode(authString.getBytes());
			String authStringEnc = new String(authEncBytes);
			request.setRequestProperty("Authorization", "Basic " + authStringEnc);

		}
		final String AUTHENTICATION_SCHEME = "Bearer";
			//get the authorization header.
			String authorizationHeader = request.getHeaderField(HttpHeaders.AUTHORIZATION);
			if (null != authorizationHeader
					&& authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase())) {
				token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
				
				
			}
			//set the token
			HttpWebService.setstoreToken(token);
			
	}
	
	//Used to get the HTTP request.
		public HttpURLConnection getHttpRequest(String url,String inputresponseCode, String headers, String parameters, String AuthType,
				String username, String password) throws Exception {
			String fullURL = addParameters(parameters, url);
			String sURL = fullURL;
			URL surl = new URL(sURL);
			
			HttpURLConnection request = (HttpURLConnection) surl.openConnection();
			//for basic and oAuth2 authentication type
			if (!AuthType.toLowerCase().equals("noauth")) {
				
				if ((!username.equals("null") && !password.equals("null"))
						&& (AuthType.toLowerCase().equals("basic"))) {

					String authString = username + ":" + password;
					// Encoding authentication credentials
					byte[] authEncBytes = java.util.Base64.getEncoder().encode(authString.getBytes());
					String authStringEnc = new String(authEncBytes);
					request.setRequestProperty("Authorization", "Basic " + authStringEnc);

				}
				else if (AuthType.toLowerCase().equals("oauth2")) {
					
					//alreadyExecuted;
					if(HttpWebService.notfirst == false) {
						//set the Token for the first execution
						setInitialToken(sURL,headers, parameters, username, password);
						HttpWebService.setfirst();
					}		
					//get the stored Token
					token = HttpWebService.getstoreToken();				
					request.setRequestProperty("Authorization", ": Bearer " + token);
					addHeaders(headers, request);
					//check whether authentication failed and if so request for a new Token
					if(request.getResponseCode() == 401 || request.getResponseCode() == 403)
					{
						//get a new Token
						token = getToken(sURL,headers, parameters, username, password);
						request.setRequestProperty("Authorization", ": Bearer " + token);
						addHeaders(headers, request);
						
					}
					return request;
					
				}
				//when authentication type is basic and user name and password are empty.
				else if ((username.equals("null") || password.equals("null")) && AuthType.toLowerCase().equals("basic"))
				{
					return null;
				}
			}
			addHeaders(headers, request);
			return request;
		}
		
	//Used to get the HTTPS request.
	public HttpURLConnection getHttpsRequest(String url,String inputresponseCode, String headers, String parameters, String AuthType,
			String username, String password) throws KeyManagementException, NoSuchAlgorithmException, IOException, Exception {
		
		//This is to ignore the certification Error
		SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
        SSLContext.setDefault(ctx);

		
		String fullURL = addParameters(parameters, url);
		String sURL = fullURL;
		URL surl = new URL(sURL);
		
		HttpURLConnection request = (HttpURLConnection) surl.openConnection();
		
		//This is to ignore the certification Error
		((HttpsURLConnection) request).setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
		//check whether authentication type is basic or oAuth2
		if (!AuthType.toLowerCase().equals("noauth")) {
			
			if ((!username.equals("null") && !password.equals("null"))
					&& (AuthType.toLowerCase().equals("basic"))) {

				String authString = username + ":" + password;
				// Encoding authentication credentials
				byte[] authEncBytes = java.util.Base64.getEncoder().encode(authString.getBytes());
				String authStringEnc = new String(authEncBytes);
				request.setRequestProperty("Authorization", "Basic " + authStringEnc);

			}
			else if (AuthType.toLowerCase().equals("oauth2")) {
				
				//Check whether alreadyExecuted;
				if(HttpWebService.notfirst == false) {
					setInitialToken(sURL,headers, parameters, username, password);
					HttpWebService.setfirst();
				}		
				//get stored Token
				token = HttpWebService.getstoreToken();				
				request.setRequestProperty("Authorization", ": Bearer " + token);
				addHeaders(headers, request);
				//check whether authentication failed and if so request for a new Token
				if(request.getResponseCode() == 401 || request.getResponseCode() == 403)
				{
					//get the Token
					token = getToken(sURL,headers, parameters, username, password);
					request.setRequestProperty("Authorization", ": Bearer " + token);
					addHeaders(headers, request);
					
				}
				return request;
				
			}
			//when authentication type is basic and user name and password are empty.
			else if ((username.equals("null") || password.equals("null")) && AuthType.toLowerCase().equals("basic"))
			{
				return null;
			}
		}
		addHeaders(headers, request);
		return request;
	}
	
	
	
	//Used to post/Put a string 
	public String[] handlePutPost(String URL, String inputresponseCode, String method, String headers, String parameters,
			String inputString, String AuthType, String username, String password) throws Exception {
		URL url = new URL(URL);
		// Open URL connection
		URLConnection con = url.openConnection();
		HttpWebService request = new HttpWebService();
		HttpURLConnection http = (HttpURLConnection) con;
		
		String[] ResponseDetails = new String[3];
		String ResponseMessage = "null";
		
		if (!(username.equals("null") && password.equals("null"))
				&& (AuthType.toLowerCase().equals("basic") || AuthType.toLowerCase().equals("oauth2"))) {
			http = this.getHttpRequest(URL, inputresponseCode, headers, parameters, AuthType, username, password);
		}
		// when response not retrieved
		if (null == http) {
			return null;
			// when user name and password null for basic and oAuth2
			// authentication types
		} else if ((username.equals("null") || password.equals("null")) && AuthType.toLowerCase().equals("basic")
				|| AuthType.toLowerCase().equals("oAuth2")) {
			return null;
		}

		int code = 0;
		String responseText = "";
		// Handle the request for DELETE method
		if (method.toUpperCase().equals("DELETE")) {
			http.setRequestMethod(method.toUpperCase());
			request.addHeaders(headers, http);
			code = http.getResponseCode();
			
			// Handle the request for POST and PUT methods.
		} else if (method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")) {
			try {
				http.setRequestMethod(method.toUpperCase());
			} catch (Exception e) {
				return null;
			}

			// check whether need to do an output
			http.setDoOutput(true);
			byte[] out = null;
			// check whether input string value is empty
			if (null != inputString) {
				String postinput = inputString;
				// convert the post input into a byte stream
				out = postinput.getBytes(StandardCharsets.UTF_8);
				int length = out.length;

				http.setFixedLengthStreamingMode(length);
				// add headers
				request.addHeaders(headers, http);
				http.connect();
			}
			
			// write the string as outputstream.
			try (OutputStream os = http.getOutputStream()) {
				// write the string as output stream.
				os.write(out);
				code = http.getResponseCode();
				ResponseMessage = http.getResponseMessage();
				String ContentType = http.getHeaderField("Content-Type");

				try{
				if (code == 200 && ContentType.contains("application/json")) {
					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader((InputStream) http.getContent()));
					responseText = root.toString();
				}
				
				else if (code != 200 && ContentType.contains("application/json")) {
					
					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader((InputStream) http.getErrorStream()));
					responseText = root.toString();
				}
				else if(code != 200 && null != ContentType &&  ContentType.contains("text/html"))
				{
					BufferedReader in = new BufferedReader(new InputStreamReader(http.getErrorStream())); 
					String inputLine; 
					while ((inputLine = in.readLine()) != null) { 
					responseText = responseText+inputLine;
					} 
					in.close(); 					
				}
				}
				catch(Exception e)
				{
					System.out.print(e.getMessage());
				}
				

			} catch (Exception e) {
				System.out.println("Issue in writing the input stream");
			} finally {
				http.disconnect();
			}
		}
		ResponseDetails[0] = String.valueOf(code);
		ResponseDetails[1] = ResponseMessage;
		ResponseDetails[2] = responseText;
		
		if(null != ResponseDetails[1])
		{
		ResponseDetails[1] = ResponseMessage;
		}
		else
		{
			ResponseDetails[1] = "null";
		}
		if(null != ResponseDetails[2])
		{
			ResponseDetails[2] = responseText;
		}
		else
		{
			ResponseDetails[2] = "null";
		}
		return ResponseDetails;
	}
	
	//Post HTTPS request
	public String[] handlePutPosthttps(String URL, String inputresponseCode, String method, String headers,
			String parameters, String inputString, String AuthType, String username, String password) throws Exception {

		HttpWebService request = new HttpWebService();

		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		SSLContext.setDefault(ctx);
		URL url = new URL(URL);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		String[] ResponseDetails = new String[3];
		String ResponseMessage = "null";
		String responseText= "";
		// This is used to ignore the certification error
		conn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		int code = 0;
		// Handle the request for DELETE method
		if (method.toUpperCase().equals("DELETE")) {
			conn.setRequestMethod(method.toUpperCase());
			request.addHeaders(headers, conn);
			code = conn.getResponseCode();
			// Handle the request for POST and PUT methods.
		} else if (method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")) {
			try {
				conn.setRequestMethod(method.toUpperCase());
			} catch (Exception e) {
				return null;
			}
			// check whether need to do an output
			conn.setDoOutput(true);

			byte[] out = null;
			// check whether
			if (null != inputString) {
				String postinput = inputString;
				out = postinput.getBytes(StandardCharsets.UTF_8);
				int length = out.length;

				conn.setFixedLengthStreamingMode(length);
				// add headers
				request.addHeaders(headers, conn);
				conn.connect();

			}

			// write the string as outputstream.
			try (OutputStream os = conn.getOutputStream()) {
				// write the string as output stream.
				os.write(out);
				code = conn.getResponseCode();
				ResponseMessage = conn.getResponseMessage();
				String ContentType = conn.getHeaderField("Content-Type");

				try{
				if (code == 200 && ContentType.contains("application/json")) {
					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getContent()));
					responseText = root.toString();
				}
				
				else if (code != 200 && ContentType.contains("application/json")) {
					
					JsonParser jp = new JsonParser();
					JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getErrorStream()));
					responseText = root.toString();
				}
				else if(code != 200 && null != ContentType &&  ContentType.contains("text/html"))
				{
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream())); 
					String inputLine; 
					while ((inputLine = in.readLine()) != null) { 
					responseText = responseText+inputLine;
					} 
					in.close(); 					
				}
				}
				catch(Exception e)
				{
					System.out.print(e.getMessage());
				}
				
				
			} catch (Exception e) {
				System.out.println("Issue in writing the input stream");
			} finally {
				conn.disconnect();
			}
		}
		
		ResponseDetails[0] = String.valueOf(code);
		ResponseDetails[1] = ResponseMessage;
		ResponseDetails[2] = responseText;
		
		if(null != ResponseDetails[1])
		{
		ResponseDetails[1] = ResponseMessage;
		}
		else
		{
			ResponseDetails[1] = "null";
		}
		if(null != ResponseDetails[2])
		{
			ResponseDetails[2] = responseText;
		}
		else
		{
			ResponseDetails[2] = "null";
		}
		
		return ResponseDetails;
	}
	
	
	public String VerifyResponsePutPost(String PathToVerify, String ExpectedValue, String URL, String inputresponseCode,
			String method, String headers, String parameters,String inputString, String AuthType, String username, String password) throws Exception
	{
		URL url = new URL(URL);
		// Open URL connection
		URLConnection con = url.openConnection();
		HttpWebService request = new HttpWebService();
		HttpURLConnection http = (HttpURLConnection) con;
		
		
		String resultText = "null";
		
		if (!(username.equals("null") && password.equals("null"))
				&& (AuthType.toLowerCase().equals("basic") || AuthType.toLowerCase().equals("oauth2"))) {
			http = this.getHttpRequest(URL, inputresponseCode, headers, parameters, AuthType, username, password);
		}
		// when response not retrieved
		if (null == http) {
			return null;
			// when user name and password null for basic and oAuth2
			// authentication types
		} else if ((username.equals("null") || password.equals("null")) && AuthType.toLowerCase().equals("basic")
				|| AuthType.toLowerCase().equals("oAuth2")) {
			return null;
		}

		int code = 0;
		
		// Handle the request for DELETE method
		if (method.toUpperCase().equals("DELETE")) {
			http.setRequestMethod(method.toUpperCase());
			request.addHeaders(headers, http);
			code = http.getResponseCode();
			
			// Handle the request for POST and PUT methods.
		} else if (method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")) {
			try {
				http.setRequestMethod(method.toUpperCase());
			} catch (Exception e) {
				return null;
			}

			// check whether need to do an output
			http.setDoOutput(true);
			byte[] out = null;
			// check whether input string value is empty
			if (null != inputString && inputString != "null") {
				String postinput = inputString;
				// convert the post input into a byte stream
				out = postinput.getBytes(StandardCharsets.UTF_8);
				int length = out.length;

				http.setFixedLengthStreamingMode(length);
				// add headers
				request.addHeaders(headers, http);
				http.connect();
			}
			
			// write the string as outputstream.
			try (OutputStream os = http.getOutputStream()) {
				// write the string as output stream.
				os.write(out);
				code = http.getResponseCode();
				String ResponseMessage = http.getResponseMessage();
				String ContentType = http.getHeaderField("Content-Type");

				try{
					if (ContentType.contains("application/json")) {
						JsonParser jp = new JsonParser(); // from gson
						JsonElement root = jp.parse(new InputStreamReader((InputStream) http.getContent()));
						// Get the Json Element as an object
						JsonObject rootobj = root.getAsJsonObject();

						JSONParser parser = new JSONParser();
						Object jsonObj = parser.parse(rootobj.toString());
						JSONReader jr = new JSONReader();

						// get the text content of given string of json
						try {
							resultText = jr.getNode(PathToVerify, jsonObj).get("value");
						} catch (Exception e) {
							return null;
						}
					}
					// check whether response Text is XML
					if (ContentType.contains("application/xml")) {
						// create Document Factory Instance
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						// create Document Builder instance
						DocumentBuilder db = dbf.newDocumentBuilder();
						Document doc = db.parse(new URL(URL).openStream());
											
						// create new Xpath instance
						XPath xpath = XPathFactory.newInstance().newXPath();
						XPathExpression expr = xpath.compile(PathToVerify);
						Object result = expr.evaluate(doc, XPathConstants.NODESET);
						// get the relevant node
						NodeList nodes = (NodeList) result;
						// get the text content of the given node
						try {
							resultText = nodes.item(0).getTextContent();
						} catch (Exception e) {
							return null;
						}

					}
					
				}
				catch(Exception e)
				{
					System.out.print(e.getMessage());
				}
				

			} catch (Exception e) {
				System.out.println("Issue in writing the input stream");
			} finally {
				http.disconnect();
			}
		}
		return resultText;
	}
	
	public String VerifyResponsePutPosthttps(String PathToVerify, String ExpectedValue, String URL, String inputresponseCode,
			String method, String headers, String parameters,String inputString, String AuthType, String username, String password) throws Exception
	{
		HttpWebService request = new HttpWebService();

		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		SSLContext.setDefault(ctx);
		URL url = new URL(URL);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		String ResponseMessage = "null";
		
		String resultText = "";
		// This is used to ignore the certification error
		conn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		int code = 0;
		// Handle the request for DELETE method
		if (method.toUpperCase().equals("DELETE")) {
			conn.setRequestMethod(method.toUpperCase());
			code = conn.getResponseCode();
			// Handle the request for POST and PUT methods.
		} else if (method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")) {
			try {
				conn.setRequestMethod(method.toUpperCase());
			} catch (Exception e) {
				return null;
			}
			// check whether need to do an output
			conn.setDoOutput(true);

			byte[] out = null;
			// check whether
			if (null != inputString) {
				String postinput = inputString;
				out = postinput.getBytes(StandardCharsets.UTF_8);
				int length = out.length;

				conn.setFixedLengthStreamingMode(length);
				// add headers
				request.addHeaders(headers, conn);
				conn.connect();

			}

			// write the string as outputstream.
			try (OutputStream os = conn.getOutputStream()) {
				// write the string as output stream.
				os.write(out);
				code = conn.getResponseCode();
				ResponseMessage = conn.getResponseMessage();
				String ContentType = conn.getHeaderField("Content-Type");

				try{
					if (ContentType.contains("application/json")) {
						JsonParser jp = new JsonParser(); // from gson
						JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getContent()));
						// Get the Json Element as an object
						JsonObject rootobj = root.getAsJsonObject();

						JSONParser parser = new JSONParser();
						Object jsonObj = parser.parse(rootobj.toString());
						JSONReader jr = new JSONReader();

						// get the text content of given string of json
						try {
							resultText = jr.getNode(PathToVerify, jsonObj).get("value");
						} catch (Exception e) {
							return null;
						}
					}
					// check whether response Text is XML
					if (ContentType.contains("application/xml")) {
						// create Document Factory Instance
						DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
						// create Document Builder instance
						DocumentBuilder db = dbf.newDocumentBuilder();
						Document doc = db.parse(new URL(URL).openStream());
											
						// create new Xpath instance
						XPath xpath = XPathFactory.newInstance().newXPath();
						XPathExpression expr = xpath.compile(PathToVerify);
						Object result = expr.evaluate(doc, XPathConstants.NODESET);
						// get the relevant node
						NodeList nodes = (NodeList) result;
						// get the text content of the given node
						try {
							resultText = nodes.item(0).getTextContent();
						} catch (Exception e) {
							return null;
						}

					}				
				}
				catch(Exception e)
				{
					System.out.print(e.getMessage());
				}
				
				
			} catch (Exception e) {
				System.out.println("Issue in writing the input stream");
			} finally {
				conn.disconnect();
			}
		}	
		
		return resultText;
	}
	
	public String[] storeHttpResponse(String fileName, String url, String expectedResponseCode,String method, String headers, String inputString, String parameters,
			String authType, String username, String password) throws Exception
	{
		String[] ResponseDetails = new String[3];

		HttpURLConnection request = null;

		if (method.toUpperCase().equals("GET")) {
			// send Https request
			if (url.contains("https")) {
				request = this.getHttpsRequest(url, expectedResponseCode, headers, parameters, authType, username,
						password);
			}
			// send Http request
			else if (url.contains("http")) {
				request = this.getHttpRequest(url, expectedResponseCode, headers, parameters, authType, username,
						password);
			}
		}
		if(!method.toUpperCase().equals("GET"))
		{
			if (url.contains("https")) {
				request = this.StorePutPosthttps(url, expectedResponseCode, method, headers, parameters, inputString, authType, username, password);
			}
			else if(url.contains("http"))
			{
			request = this.StorePutPost(url, expectedResponseCode, method, headers, parameters, inputString, authType, username, password);
			}
		}
		
		String dir = this.getClass().getResource("/").getFile();
		CheckDirectoryStructure();
		
		if (null != request) {
			// get the response code
			int ResponseCode = request.getResponseCode();
			// get the response message related to code
			String ResponseMessage = request.getResponseMessage();
			String responseText = "";
			// get the content type of the request using header field
			
			if(String.valueOf(ResponseCode).equals(expectedResponseCode))
			{
			
			String ContentType = request.getHeaderField("Content-Type");

			if(null ==ContentType )
			{
				responseText = null;
			}
			// get the json response as a String to a variable.
			else if (ContentType.contains("application/json")) {
				JsonParser jp = new JsonParser();
				JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
				responseText = root.toString();
				
				JsonObject rootobj = root.getAsJsonObject();
				
				dir = this.getClass().getResource("/Responses/JSON").getFile();
				
				File f = new File(dir+"/"+fileName+".json");
				if(f.exists()) { 
				    return null;
				}
				
				try (FileWriter file = new FileWriter(dir+"/"+fileName+".json")) {
					file.write(rootobj.toString());
					System.out.println("\nJSON Object: " + rootobj);
				
				}
			}
			// get the XML response as a String to a variable
			else if (ContentType.contains("application/xml")) {
				//create Input Stream Reader
				InputStreamReader inputReader = null;
				//create String Builder to append xml text
				StringBuilder sb = new StringBuilder();
				inputReader = new InputStreamReader(request.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(inputReader);
				if (bufferedReader != null) {
					int lines;
					//read xml
					while ((lines = bufferedReader.read()) != -1) {
						sb.append((char) lines);
					}
					bufferedReader.close();
				}
				responseText = sb.toString();
				
				File f = new File(dir+"/Responses/XML/"+fileName+".xml");
				if(f.exists()) { 
				    return null;
				}
				dir = this.getClass().getResource("/").getFile();
				try (FileWriter file = new FileWriter(dir+"/Responses/XML/"+fileName+".xml")) {
					file.write(responseText);
					System.out.println("\nXML Object: " + responseText);
				
				}
			}
			}
			// adding the response code and message into the list
			ResponseDetails[0] = String.valueOf(ResponseCode);
			ResponseDetails[1] = ResponseMessage;
			ResponseDetails[2] = responseText;			
		} else {
			// when response not retrieved
			ResponseDetails[0] = "null";
			ResponseDetails[1] = "null";
			ResponseDetails[2] = "null";			
		}
		return ResponseDetails;
	}
	public String verifyStoredResponse(String fileName, String PathToVerify, String ExpectedValue) throws Exception
	{
		        String resultText = "";
		        File file = null;
				// check whether response Text is JSON
				if (fileName.endsWith(".json")) {
					
					try{
					String dir = this.getClass().getResource("/Responses/JSON").getFile();
					file = new File(dir+"/"+fileName);
					}
					catch(Exception e)
					{
						return "FolderNotFound"; 
					}
					if (file.exists()){
			            InputStream is = new FileInputStream(file);
			            String jsonTxt = IOUtils.toString(is, "UTF-8");			            
			            JsonParser jp = new JsonParser(); // from gson
						JsonElement root = jp.parse(jsonTxt);
						// Get the Json Element as an object
						JsonObject rootobj = root.getAsJsonObject();

						JSONParser parser = new JSONParser();
						Object jsonObj = parser.parse(rootobj.toString());
						JSONReader jr = new JSONReader();
						
						try {
							resultText = jr.getNode(PathToVerify, jsonObj).get("value");							
						} catch (Exception e) {
							return null;
						}
			        }	
					else
					{
						return "FileNotFound";
					}
					
				}				
				// check whether response Text is XML
				else if (fileName.endsWith(".xml")) {
					try{
					String dir = this.getClass().getResource("/Responses/XML").getFile();
					file = new File(dir+"/"+fileName);
					}
					catch(Exception e)
					{
						return "FolderNotFound"; 
					}
					if (file.exists()){
					File fXmlFile = new File(file.toString());
				    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				    Document doc = dBuilder.parse(fXmlFile);
					
					// create new Xpath instance
					XPath xpath = XPathFactory.newInstance().newXPath();
					XPathExpression expr = xpath.compile(PathToVerify);
					Object result = expr.evaluate(doc, XPathConstants.NODESET);
					// get the relevant node
					NodeList nodes = (NodeList) result;
					// get the text content of the given node
					try {
						resultText = nodes.item(0).getTextContent();						
					} catch (Exception e) {
						return null;
					}
				}
					else
					{
						return "FileNotFound";
					}
			} 
				else
				{
					return "FileFormatNotExists";
				}
				System.out.println(resultText);
		return resultText;
		
	}
	
	public void CheckDirectoryStructure()
	{
		String dir = this.getClass().getResource("/").getFile();
		
		File theDir = new File(dir+"/Responses");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(Exception e){
		        System.out.println(e.getMessage());
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		
		//Check JSON Directory
		theDir = new File(dir+"/Responses/JSON");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(Exception e){
		    	System.out.println(e.getMessage());
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
		
		//Check XML Directory
		theDir = new File(dir+"/Responses/XML");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(Exception e){
		    	System.out.println(e.getMessage());
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
	}
	
	
	public HttpURLConnection StorePutPost(String URL, String inputresponseCode, String method, String headers, String parameters,
			String inputString, String AuthType, String username, String password) throws Exception {
		URL url = new URL(URL);
		// Open URL connection
		URLConnection con = url.openConnection();
		HttpWebService request = new HttpWebService();
		HttpURLConnection http = (HttpURLConnection) con;
		
		// Handle the request for DELETE method
		if (method.toUpperCase().equals("DELETE")) {
			http.setRequestMethod(method.toUpperCase());
			request.addHeaders(headers, http);
			
			// Handle the request for POST and PUT methods.
		} else if (method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")) {
			try {
				http.setRequestMethod(method.toUpperCase());
			} catch (Exception e) {
				return null;
			}

			// check whether need to do an output
			http.setDoOutput(true);
			byte[] out = null;
			// check whether input string value is empty
			if (null != inputString) {
				String postinput = inputString;
				// convert the post input into a byte stream
				out = postinput.getBytes(StandardCharsets.UTF_8);
				int length = out.length;

				http.setFixedLengthStreamingMode(length);
				// add headers
				request.addHeaders(headers, http);
				http.connect();
			}
			
			// write the string as outputstream.
			try (OutputStream os = http.getOutputStream()) {
				// write the string as output stream.
				os.write(out);				
				
			} catch (Exception e) {
				System.out.println("Issue in writing the input stream");
			} 
		}
		
		return http;
	}
	
	public HttpURLConnection StorePutPosthttps(String URL, String inputresponseCode, String method, String headers, String parameters,
			String inputString, String AuthType, String username, String password) throws Exception {
		
		HttpWebService request = new HttpWebService();

		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		SSLContext.setDefault(ctx);
		URL url = new URL(URL);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		
		// This is used to ignore the certification error
		conn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		
		// Handle the request for DELETE method
		if (method.toUpperCase().equals("DELETE")) {
			conn.setRequestMethod(method.toUpperCase());
			request.addHeaders(headers, conn);
			
			// Handle the request for POST and PUT methods.
		} else if (method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT")) {
			try {
				conn.setRequestMethod(method.toUpperCase());
			} catch (Exception e) {
				return null;
			}

			// check whether need to do an output
			conn.setDoOutput(true);
			byte[] out = null;
			// check whether input string value is empty
			if (null != inputString) {
				String postinput = inputString;
				// convert the post input into a byte stream
				out = postinput.getBytes(StandardCharsets.UTF_8);
				int length = out.length;

				conn.setFixedLengthStreamingMode(length);
				// add headers
				request.addHeaders(headers, conn);
				conn.connect();
			}
			
			// write the string as outputstream.
			try (OutputStream os = conn.getOutputStream()) {
				// write the string as output stream.
				os.write(out);				
				
			} catch (Exception e) {
				System.out.println("Issue in writing the input stream");
			} 
		}
		
		return conn;
	}
	
	
}






