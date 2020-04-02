/*
 * Copyright 2004 ThoughtWorks, Inc. Licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.thoughtworks.selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.entity.StringEntity;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;

import ch.qos.logback.core.pattern.parser.Node;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.virtusa.isq.vtaf.report.extentReport.generateExtentReport;
import com.virtusa.isq.vtaf.report.model.TestSuite;
import com.virtusa.isq.vtaf.report.reporter.Reporter;
import com.virtusa.isq.vtaf.utils.PropertyHandler;
import com.virtusa.isq.vtaf.utils.TestCaseUnit;
import com.virtusa.isq.vtaf.utils.VtafThreadLocalClass;
import com.virtusa.isq.vtaf.utils.services.QtestClient;
import com.virtusa.isq.vtaf.utils.services.RestHttpClient;

// import com.virtusa.kbb.excel.reader.Main;

/**
 * The Class SeleneseTestNgHelperVir.
 */
public class SeleneseTestNgHelperVir extends SeleneseTestBaseVir {

	/** The robot. */
	private static Robot robot;

	/** The prop. */
	private static Properties prop;

	/** The properties location. */
	private static String propertiesLocation;

	/** The error messages. */
	private String errorMessages = "Verification failures : \n";

	/** The test package name. */
	private String testPackageName = "";

	/** The current method. */
	private String currentMethod = "";

	/** The calling class name. */
	private String callingClassName = "";

	/** The line number. */
	private int lineNumber;

	/** The retry count. */
	private static int retryCount;

	/** The time out period. */
	private static String timeOutPeriod;

	/** The exec props. */
	private static Properties execProps;

	/** The open window handle index. */
	private List<String> openWindowHandleIndex;

	/** The result reporter. */
	private static Reporter resultReporter;

	/** The command start time. */
	private Date commandStartTime;

	/** The testcase start time. */
	private Date testcaseStartTime;

	/** The total execution time taken. */
	private long totalExecutionTimeTaken;

	/** The current test case. */
	private String currentTC = "";
	
	/** The current QtestReleaseID. */
	private static String QtestReleaseID = "";

	/** The current QtestCycleID. */
	private static String QtestCycleID = "";
	
	/** The current QtesSuitePID. */
	private  String QtesSuitePID = "";

	/** The current test case status. */
	private String currentStatus = "PASSED";

	PropertyHandler propHandler = new PropertyHandler("runtime.properties");

	generateExtentReport extentReport = new generateExtentReport();

	private String EnableExtentReport = propHandler.getRuntimeProperty("EXTENT_REPORT");

	/**
	 * Sets the before test configuration for the test.
	 * 
	 * @param url
	 *            the url
	 * @param browserString
	 *            the browser string
	 * @param context
	 *            the context
	 * @throws Exception
	 *             the exception
	 */
	@BeforeTest
	@Parameters({ "selenium.url", "selenium.browser" })
	public final void setUp(@Optional("http://www.google.com") final String url, @Optional final String browserString,
			final ITestContext context) throws Exception {
		Logger log = getLog();
		try {
			String browserStr = super.getBrowserString();
			if (browserStr == null || browserStr.isEmpty()) {
				super.setBrowserString(runtimeBrowserString());
				super.setUp(url, getBrowserString());
			} else {
				super.setUp(url, getBrowserString());
			}
			log.info("Execution Browser : " + browserStr);
			

		} catch (Exception e) {
			log.error("Exception occured while setting up the test ", e);
		}
		super.setCaptureScreenShotOnFailure(true);
		// cleanDriverServerSessions();
	};

	private String getReleaseId(String projectURL, String projectID,
			String authKey, String releaseName) {
		String releaseId = "";
		RestHttpClient restHttpClient = null;
		String response = "Error";
		try {

			if (projectURL.isEmpty()) {
				throw new Exception("Provided URL is empty.");
			}
			if (!(releaseName.isEmpty())) {
				String endPoint = projectURL + projectID + "/releases";
				restHttpClient = new QtestClient(endPoint);
				restHttpClient.setGetRequest();
				restHttpClient.addGetHeader("Authorization", authKey);
				restHttpClient.addGetHeader("Content-Type", "application/json");

				response = restHttpClient.getResponseString(restHttpClient
						.getClient().execute(restHttpClient.getGetRequest()));
				System.out.println(response);

				JSONParser parser = new JSONParser();
				if (parser.parse(response) instanceof JSONArray) {
					JSONArray jr = (JSONArray) parser.parse(response);
					for (int i = 0; i < jr.size(); i++) {
						JSONObject jo = (JSONObject) jr.get(i);
						if (jo.get("name").toString().equals(releaseName)) {
							releaseId = jo.get("id").toString();
							
							break;
						}
					}
					if (releaseId.equals("")) {
						System.out
								.println("Relasename doesn't match, New release created");
						releaseId = setRelease(projectURL, projectID, authKey,
								releaseName);
					}
				} else {
					throw new Exception(response);
				}
				if (response.isEmpty()) {
					throw new Exception("QTest response is empty.");
				}
			} else {
				System.out.println("Provided Release name is empty.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				restHttpClient.getGetRequest().abort();
				restHttpClient.getClient().close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return releaseId;
	}
    
    public String setRelease(final String URL, final String projectId,
			final String authKey, final String releaseName) {
		String releaseId = "";
		RestHttpClient restHttpClient = null;
		String response = "Error";

		try {
			restHttpClient = new QtestClient(URL + projectId + "/releases");
			restHttpClient.setHttpPostRequest();
			restHttpClient.addPostHeader("Authorization", authKey);
			restHttpClient.addPostHeader("Content-Type", "application/json");

			String jsonVal = "{\n" + "  \"name\": \"" + releaseName + "\",\n"
					+ "  \"description\": \"This is the second release\",\n"
					+ "  \"note\": \"Note of the Release\"\n" + "}";

			restHttpClient.setEntity(new StringEntity(jsonVal));
			response = restHttpClient.getResponseString(restHttpClient
					.getClient().execute(restHttpClient.getHttpPostRequest()));

			JSONParser parser = new JSONParser();
			JSONObject jObject = new JSONObject();
			jObject = (JSONObject) parser.parse(response);
			releaseId = jObject.get("id").toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				restHttpClient.getHttpPostRequest().abort();
				restHttpClient.getClient().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return releaseId;
	}
    public String createTestCycle(final String URL, final String projectId,
			final String releaseId, final String authKey,
			final String testPlanName) throws Exception {
		String cycleId = "";

		RestHttpClient restHttpClient = null;
		String response = "Error";
	
		try {

			if (URL.isEmpty()) {
				throw new Exception("Provided URL is empty.");
			}
			String endPoint = URL + projectId + "/test-cycles?parentId="
					+ releaseId + "&parentType=release";
			restHttpClient = new QtestClient(endPoint);
			restHttpClient.setHttpPostRequest();
			restHttpClient.addPostHeader("Authorization", authKey);
			restHttpClient.addPostHeader("Content-Type", "application/json");
			// restHttpClient.getBasicAuthentication("QAAUTOTEST@aenetworks.com",
			// "Welcome2018");

			String jsonVal = "{\n" + "  \"name\": \"" + testPlanName+"\",\n"
					+ "  \"description\": \"" + testPlanName
					+ ": Description\",\n" + "  \"target_release_id\": "
					+ releaseId + "\n" + "}";

			restHttpClient.setEntity(new StringEntity(jsonVal));
			response = restHttpClient.getResponseString(restHttpClient
					.getClient().execute(restHttpClient.getHttpPostRequest()));

			JSONParser parser = new JSONParser();
			JSONObject jObject = new JSONObject();
			jObject = (JSONObject) parser.parse(response);
			cycleId = jObject.get("id").toString();
			if (response.isEmpty()) {
				throw new Exception("Qtest response is empty.");
			}

		} catch (Exception e) {
			throw new Exception(response);
		} finally {
			try {
				restHttpClient.getHttpPostRequest().abort();
				restHttpClient.getClient().close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return cycleId;

	}

	public String createTestSuite(final String URL, final String projectId,
			final String cycleId, final String releaseId, final String authKey,
			final String testSuiteName) throws IOException {
		String suiteId = "";

		RestHttpClient restHttpClient = null;

		try {

			if (URL.isEmpty()) {
				throw new Exception("Provided URL is empty.");
			}
			String endPoint = URL + projectId + "/test-suites?parentId="
					+ cycleId + "&parentType=test-cycle";
			restHttpClient = new QtestClient(endPoint);
			restHttpClient.setHttpPostRequest();
			restHttpClient.addPostHeader("Authorization", authKey);
			restHttpClient.addPostHeader("Content-Type", "application/json");
			// restHttpClient.getBasicAuthentication("QAAUTOTEST@aenetworks.com",
			// "Welcome2018");

			String jsonVal = "{\n" + "  \"parentId\":\"" + cycleId + "\",\n"
					+ "  \"parentType\": \"test-cycle\",\n" + "  \"name\": \""
					+ testSuiteName + "\",\n" + "  \"order\": 0,\n" + "  \n"
					+ "  \"target_release_id\": " + releaseId + ",\n"
					+ "  \"target_build_id\": 0\n" + "}";

			restHttpClient.setEntity(new StringEntity(jsonVal));
			String response = restHttpClient.getResponseString(restHttpClient
					.getClient().execute(restHttpClient.getHttpPostRequest()));

			JSONParser parser = new JSONParser();
			JSONObject jObject = new JSONObject();
			jObject = (JSONObject) parser.parse(response);
			suiteId = jObject.get("pid").toString();
			if (response.isEmpty()) {
				throw new Exception("Qtest response is empty.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				restHttpClient.getHttpPostRequest().abort();
				restHttpClient.getClient().close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return suiteId;

	}
  
	/**
	 * Read run prop.
	 * 
	 * @throws Exception
	 *             Signals that an I/O exception has occurred.
	 */
	@BeforeSuite
	public final void readRunProp(final ITestContext context) throws Exception {
		// Initializing the logger
		initLogger();
		SeleneseTestBaseVir.setCurrentPlanName(context.getName());
		setReportData();
		setBrowserString(getCurrentBrowser());
		initReporter();
		resultReporter.addNewTestExecution();
		getLogger(SeleneseTestNgHelperVir.class);
		setRobot(new Robot());

		setProp(new Properties());
		String rootFile = new File("").getAbsolutePath();
		String file = "";
		if (rootFile.indexOf("grid") > -1) {
			file = rootFile + File.separator + "grid" + File.separator + "selenium-grid-1.0.6" + File.separator
					+ "project.properties";

		} else {
			file = rootFile + File.separator + "project.properties";
		}
		 setPropertiesLocation(file);
         getLog().info("Propery file location : " + rootFile);
         this.readUserProp();
         if (EnableExtentReport.equalsIgnoreCase("yes")) {
                         extentReport.extentSetup();
         }
         if (getQtestStatus().toLowerCase().equals("yes")) {
 			String projectURL = propHandler
 					.getRuntimeProperty("QTEST_PROJECT_URL");
 			String authKey = propHandler.getRuntimeProperty("QTEST_AUTH_KEY");
 			Document doc = getEnvironmentDocument();
 			String SubFolderName = doc.getElementsByTagName("BuildName").item(0).getTextContent();
 			
 					//propHandler.getRuntimeProperty("QTEST_BUILD_NAME");
 			
 			String releaseName = SeleneseTestNgHelperVir.getReleaseVersion();
 			
 			String projectID = getQtestProjectID();
			Date date = new Date();
			DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
			formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			String currentTime=formatter.format(date);
			System.out.println(currentTime);
 			String releaseId = getReleaseId(projectURL, projectID, authKey,
 					releaseName);
 			setQtestreleaseID(releaseId);
 			String testCycleId = createTestCycle(projectURL, projectID,
 					getQtestreleaseID(), authKey, SubFolderName+" "+currentTime );
 			setQtestCycleID(testCycleId);

 		}
	}

	/**
	 * Read user prop.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void readUserProp() throws IOException {

		final int defaultRetryCount = 12;
		final int defaultTimeout = 30000;

		PropertyHandler propHandler = new PropertyHandler("runtime.properties");

		String timeOut = propHandler.getRuntimeProperty("TIMEOUT");
		String retry = propHandler.getRuntimeProperty("RETRY");
		String browser = propHandler.getRuntimeProperty("BROWSER");
		setExecProps(propHandler.getPropertyObject());

		//commented browser Line
		/*if (!browser.isEmpty()) {
			super.setBrowserString(browser);
			System.setProperty("selenium.defaultBrowser", getBrowserString());
		}*/
		if (!retry.isEmpty()) {
			retryCount = Integer.parseInt(retry);
		} else {
			retryCount = defaultRetryCount;
		}
		if (!timeOut.isEmpty()) {
			timeOutPeriod = timeOut;
		} else {
			timeOutPeriod = String.valueOf(defaultTimeout);
		}

		getLog().info("Retry Count : " + retryCount);
		getLog().info("Timeout Period : " + timeOutPeriod);

	}

	/**
	 * Gets the selenium.
	 * 
	 * 
	 */
	@BeforeClass
	public final void getSelenium() {
		try {
			if (getQtesSuitePID().isEmpty()) {
				PropertyHandler runtimePropHandler = new PropertyHandler("runtime.properties");
				
				String projectURL = runtimePropHandler
	  					.getRuntimeProperty("QTEST_PROJECT_URL");
	  			String authKey = runtimePropHandler.getRuntimeProperty("QTEST_AUTH_KEY");
	  			
	  			String projectID = getQtestProjectID();

	  			String testSuitePID = createTestSuite(projectURL, projectID,
	  					getQtestCycleID(), getQtestreleaseID(), authKey, this.getClass().getSimpleName());
	  			System.out.println(testSuitePID);
	  			setQtesSuitePID(testSuitePID);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		synchronized (resultReporter) {
			System.out.println("Before Method!" + "getSelenium");
			resultReporter.addNewTestSuite(this.getClass().getSimpleName());
		}
		

	}

	/**
	 * Sets the test context.
	 * 
	 * @param method
	 *            the new test context
	 */
	@BeforeMethod
	@Parameters({ "url", "browser" })
	public final void setTestContext(@Optional("url") final String url, @Optional("browser")  String browser,
			final Method method) {
		System.out.println("Before Method!" + "setTestContext");
		setTestCaseFailedStatus(false);
		PropertyHandler propHandler = new PropertyHandler(propertiesLocation);
		propHandler.setRuntimeProperty("tcComment", "");
		totalExecutionTimeTaken = 0;
		testcaseStartTime = getCurrentTime();
		errorMessages = "Verification failures : \n";
		setOpenWindowHandleIndex(new ArrayList<String>());
		//setBrowserString(System.getProperty("selenium.defaultBrowser"));
		// this.cleanBrowserSessions();
		getLog().info("Starting a new selenium webdriver session.");
		if (!"browser".equals(browser)) {
			setBrowserString(browser);
			super.configWebDriver(browser);
		}
		// Reset the statement counter stack
		synchronized (this) {
			TestCaseUnit tcUnit = new TestCaseUnit();
			tcUnit.setTotalExecutionTimeTaken(0);
			tcUnit.setTestcaseStartTime(getCurrentTime());
			tcUnit.setErrorMessages("Verification failures : \n");
			tcUnit.setTestCaseFailStatus(false);
			VtafThreadLocalClass.vtafThreadLocal.set(tcUnit);
			WebDriver driver = this.startBrowserSession(getBrowserString());
			VtafThreadLocalClass.vtafThreadLocal.get().setDriver(driver);
		}

		Logger log = getLog();
		log.info("Started the selenium webdriver session.");

		log.info("Executing the test case : " + method.getDeclaringClass().getSimpleName() + "." + method.getName());

		getLogger(this.getClass());

		testPackageName = this.getClass().getPackage().toString().split("package ")[1];
		String testCaseName = method.getName();

		synchronized (resultReporter) {
			// This is to add Test Suite to thread local object
			for (TestSuite ts : resultReporter.getTestExecution().getTestSuites()) {
				if (ts.getTestsuitename() != null && ts.getTestsuitename().equals(this.getClass().getSimpleName())) {
					VtafThreadLocalClass.vtafThreadLocal.get().setCurrentTestSuit(ts);
					break;
				}
			}
		}

		resultReporter.addNewTestCase(testCaseName);
		VtafThreadLocalClass.getTestCaseUnit().getCurrentTestCase().setUrl(url);

		appendToCSVFileBuilder("\n", testCaseName, "\n");
		// logTimeCSVFileBuilder.append("\n").append(testCaseName).append("\n");
		startOfTestCase(VtafThreadLocalClass.vtafThreadLocal.get().getDriver());

		log.info("Starting the test case..");
		/**
		 * extent report
		 */
		if (EnableExtentReport.equalsIgnoreCase("yes")) {
			extentReport.startExtent(method.getDeclaringClass().getSimpleName() + "." + testCaseName);
		}
		

	}
	
	private Document getEnvironmentDocument(){
		Document doc = null;
		try{
		File file = new File("environmentData.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(file);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return doc;
	}
	
	private void setReportData() {	
		try {
			Document doc = getEnvironmentDocument();
			setReleaseVersion(doc.getElementsByTagName("ReleaseVersion").item(0).getTextContent());
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new SkipException("Error in XML");
		}

	}

	public String getCurrentUrl(String key) {

		String environment = "";
		PropertyHandler ph = new PropertyHandler("runtime.properties");
		String projectName = ph.getRuntimeProperty("PROJECT_NAME");

		try {
			Document doc = getEnvironmentDocument();
			NodeList envNode = doc
					.getElementsByTagName("TargetTestEnvironment");
			environment = envNode.item(0).getTextContent();

			envNode = doc.getElementsByTagName(projectName);
			NodeList enList, urlList = null;

			for (int i = 0; i < envNode.getLength(); i++) {
				enList = envNode.item(i).getChildNodes();

				for (int j = 0; j < enList.getLength(); j++) {

					if (environment.toLowerCase().equals("qa")) {
						urlList = enList.item(j).getChildNodes();
						System.out.println(enList.item(j).getNodeName());
						if (enList.item(j).getNodeName().toLowerCase().equals("qaurl")) {
							for (int k = 0; k < urlList.getLength(); k++) {
								System.out.println(urlList.item(k)
										.getNodeName());
								if (urlList.item(k).getNodeName()
										.equalsIgnoreCase(key)) {
									return urlList.item(k).getTextContent();
								}
							}

						}
					}else if (environment.toLowerCase().equals("dev")) {
						urlList = enList.item(j).getChildNodes();
						System.out.println(enList.item(j).getNodeName());
						if (enList.item(j).getNodeName().toLowerCase().equals("devurl")) {
							for (int k = 0; k < urlList.getLength(); k++) {
								System.out.println(urlList.item(k)
										.getNodeName());
								if (urlList.item(k).getNodeName()
										.equalsIgnoreCase(key)) {
									return urlList.item(k).getTextContent();
								}
							}

						}
					}else if (environment.toLowerCase().equals("stage")) {
						urlList = enList.item(j).getChildNodes();
						System.out.println(enList.item(j).getNodeName());
						if (enList.item(j).getNodeName().toLowerCase().equals("stageurl")) {
							for (int k = 0; k < urlList.getLength(); k++) {
								System.out.println(urlList.item(k)
										.getNodeName());
								if (urlList.item(k).getNodeName()
										.equalsIgnoreCase(key)) {
									return urlList.item(k).getTextContent();
								}
							}

						}
					}else if (environment.toLowerCase().equals("production")) {
						urlList = enList.item(j).getChildNodes();
						System.out.println(enList.item(j).getNodeName());
						if (enList.item(j).getNodeName().toLowerCase().equals("productionurl")) {
							for (int k = 0; k < urlList.getLength(); k++) {
								System.out.println(urlList.item(k)
										.getNodeName());
								if (urlList.item(k).getNodeName()
										.equalsIgnoreCase(key)) {
									return urlList.item(k).getTextContent();
								}
							}

						}
					}

					if (environment.isEmpty()) {
						throw new Exception("Envirnment is not available.");
					}

				}
			}

			setReleaseVersion(doc.getElementsByTagName("ReleaseVersion")
					.item(0).getTextContent());

		} catch (Exception e) {
			e.printStackTrace();
			throw new SkipException("Error in XML");
		}
		return "Empty URL";

	}
	
	private String getURL(final NodeList envNode, final String key){
		for (int i = 0; i < envNode.getLength(); i++) {
			if (envNode.item(i).getNodeName().equalsIgnoreCase(key)) {
				return envNode.item(i).getTextContent();
			}
		}
		return "";
	}
	
	private String getCurrentBrowser() {
		Document doc = getEnvironmentDocument();
		return doc.getElementsByTagName("BrowserName").item(0).getTextContent();
	}
	 private String getQtestStatus() {
 		Document doc = getEnvironmentDocument();
 		return doc.getElementsByTagName("QtestAutomated").item(0)
 				.getTextContent();
 	}

 	private String getQtestProjectID() {
 		Document doc = getEnvironmentDocument();
 		return doc.getElementsByTagName("QtestProjectID").item(0)
 				.getTextContent();
 	}

	/**
	 * Start browser session.
	 * 
	 * @param browserString
	 *            the browser string
	 */
	public final synchronized WebDriver startBrowserSession(final String browserString) {

		DesiredCapabilities capabilities = getWebDriverCapabilities();
		WebDriver driver = null;
		try {
			if (browserString.toLowerCase().contains("chrome")) {

				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				//prefs.put("download.default_directory", System.getProperty("user.home")+"\\Downloads\\");
				
				prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );
				prefs.put("download.prompt_for_download", false);
				prefs.put("download.default_directory",System.getProperty("user.dir"));
				options.setExperimentalOption("prefs", prefs);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);
 

				

			} else if (browserString.toLowerCase().contains("safari")) {

				DesiredCapabilities.safari();
				driver = new SafariDriver(capabilities);

			} else if (browserString.toLowerCase().contains("explorer")) {

				DesiredCapabilities.internetExplorer();
				capabilities.setJavascriptEnabled(true);
			    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				driver = new InternetExplorerDriver(capabilities);

			} else if (browserString.toLowerCase().contains("firefox")) {
				FirefoxProfile profile = getDefaultProfile();
                profile.setPreference("browser.download.folderList",2);
				profile.setPreference("browser.download.dir", System.getProperty("user.dir"));
                profile.setPreference("browser.download.useDownloadDir", true);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/pdf,application/vnd.adobe.xfdf,application/vnd.fdf,text/csv,application/excel,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/octet-stream,image/jpeg,image/png");
				 profile.setPreference("pdfjs.disabled", true);
				  profile.setPreference("print.always_print_silent", true);
				 profile.setPreference("print.show_print_progress", false);
                profile.setEnableNativeEvents(true);
				profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);
        	capabilities = DesiredCapabilities.firefox();
        	capabilities.setCapability("marionette", false);
        	capabilities.setCapability(FirefoxDriver.PROFILE, profile);

				driver = new FirefoxDriver(capabilities);

			}
			 
			else if (browserString.toLowerCase().contains("opera")) {

				DesiredCapabilities.opera();
				driver = new OperaDriver(capabilities);

			} else if (browserString.toLowerCase().contains("edge")) {

				DesiredCapabilities capabilitiesEdge = DesiredCapabilities.edge();
				driver = new EdgeDriver(capabilitiesEdge);
			} else if (browserString.toLowerCase().contains("remotewebdriver")) {

				// Reading remote driver capabilities form property file

				PropertyHandler propHandler = new PropertyHandler("runtime.properties");
				String url = propHandler.getRuntimeProperty("REMOTE_DRIVER_URL");
				String strCaps = propHandler.getRuntimeProperty("BROWSER_CAPABILITIES");

				if (url.equalsIgnoreCase("")) {
					getLog().info("Remote URL is not configured for remote web driver in \"runtime.properties\"");
					throw new AssertionError(
							"Remote URL is not configured for remote web driver in \"runtime.properties\"");
				}

				if (!strCaps.equalsIgnoreCase("")) {
					if (strCaps.contains(",")) {
						String arrcaps[] = strCaps.split(",");
						for (String cap : arrcaps) {
							String[] arrPops = cap.split("=");
							if (arrPops.length == 2) {
								capabilities.setCapability(arrPops[0], arrPops[1]);
							} else {
								getLog().info("invalid capability is passed to remote web driver [" + cap + "]");
								throw new AssertionError(
										"invalid capability is passed to remote web driver [" + cap + "]");
							}

						}
					} else {
						String[] arrPops = strCaps.split("=");
						if (arrPops.length == 2) {
							capabilities.setCapability(arrPops[0], arrPops[1]);
						} else {
							getLog().info("invalid capability is passed to remote web driver [" + strCaps + "]");
							throw new AssertionError(
									"invalid capability is passed to remote web driver [" + strCaps + "]");
						}
					}

				} else {
					getLog().info("capabilities not configured for remote web driver in \"runtime.properties\"");
					throw new AssertionError(
							"capabilities not configured for remote web driver in \"runtime.properties\"");
				}

				getLog().info("Starting Remorte Web Driver at " + url);
				URL cUrl = null;
				try {
					cUrl = new URL(url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					getLog().info("The URL passed to RemoteWebDriver is Malformed. URL [" + url + "]");
					e.printStackTrace();
					throw new AssertionError("The URL passed to RemoteWebDriver is Malformed. URL [" + url + "]");

				}
				// driver = new RemoteWebDriver(cUrl, capabilities);
				// RWD with LocalFileDetector
				RemoteWebDriver driverLFD = new RemoteWebDriver(cUrl, capabilities);
				driverLFD.setFileDetector(new LocalFileDetector());
				driver = driverLFD;

			} else if (browserString.toLowerCase().contains("headless")) {

				//DesiredCapabilities.htmlUnitWithJs();
				capabilities.setJavascriptEnabled(true);
				capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
						new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
				capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
						getPhantomJSPath());
				driver = new PhantomJSDriver(capabilities);
				// driver.get("javascript:document.getElementById('override link').click()");
			} else {
				getLog().info("Unsupported browser type passed " + browserString);
				throw new AssertionError("Unsupported Browser");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		if (getSeleniumInstances().isEmpty()) {
			putSeleniumInstances("default", driver);
		} else {
			putSeleniumInstances(getSeleniumInstanceName(), driver);
		}
		return driver;
	}

	/**
	 * returns the PhantomJS driver path.
	 * 
	 * @return Path of the PhantomJS driver executable.
	 */
	private String getPhantomJSPath() {
		String path = new File("src" + File.separator + "main" + File.separator + "resources" + File.separator + "lib"
				+ File.separator + "phantomjs.exe").getPath();
		return path;

	}

	/**
	 * Clean driver server sessions.
	 */
	private void cleanDriverServerSessions() {
		String os = getOperatingSystem();
		if (os.contains("win")) {

			this.killBrowserProcess("chromedriver_win");
			if (isx64bit()) {
				this.killBrowserProcess("IEDriverServer(x64)");
			} else {
				this.killBrowserProcess("IEDriverServer(x86)");
			}
		} else if (os.contains("mac")) {

			this.killBrowserProcess("chromedriver_mac");
		} else if (os.contains("nux")) {
			if (isx64bit()) {
				this.killBrowserProcess("chromedriver_linux(x64)");
			} else {
				this.killBrowserProcess("chromedriver_linux(x86)");
			}
		}

	}

	/**
	 * Clean browser sessions.
	 */
	private void cleanBrowserSessions() {
		String browser = getBrowserString();
		if (browser.toLowerCase().contains("explorer")) {
			this.killBrowserProcess("iexplore");

		} else if (browser.toLowerCase().contains("chrome")) {
			this.killBrowserProcess("chrome");

		} else if (browser.toLowerCase().contains("firefox")) {
			this.killBrowserProcess("firefox");

		} else if (browser.toLowerCase().contains("opera")) {
			this.killBrowserProcess("opera");

		} else if (browser.toLowerCase().contains("safari")) {
			this.killBrowserProcess("safari");
		}
	}

	/*
	 * @BeforeSuite
	 * 
	 * @Parameters({ "selenium.host", "selenium.port" }) public void
	 * attachScreenshotListener(@Optional("localhost") String host,
	 * 
	 * @Optional("4444") String port, ITestContext context) {
	 * 
	 * }
	 */

	/**
	 * Check for verification error.
	 */
	@AfterMethod(alwaysRun = true)
	public final void checkForVerificationError(ITestResult result) {

		Logger log = getLog();
		getLogger(SeleneseTestNgHelperVir.class);
		getTestCaseFailedStatus();
		log.info("End of the test case.");

		log.info("Total Time taken to execute the commands : " + totalExecutionTimeTaken + " ms");
		logTime("Total Time taken to execute the test case : ", testcaseStartTime, getCurrentTime(), log);

		// Reset the TestCaseUnit
		try {
			VtafThreadLocalClass.getTestCaseUnit().getDriver().quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (VtafThreadLocalClass.getTestCaseUnit().getConnection() != null)
				VtafThreadLocalClass.getTestCaseUnit().getConnection().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (VtafThreadLocalClass.getTestCaseUnit().getoDBDoctx() != null)
				VtafThreadLocalClass.getTestCaseUnit().getoDBDoctx().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		VtafThreadLocalClass.unsetTestCaseUnit();
		// //////////////////////////////////////

		Map<String, WebDriver> seleniumInstances = getSeleniumInstances();

		for (Map.Entry<String, WebDriver> entry : seleniumInstances.entrySet()) {
			try {
				entry.getValue().quit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		if (EnableExtentReport.equalsIgnoreCase("yes")) {
				extentReport.endExtent(result);
		}

		/*
		 * Map<String, WebDriver> seleniumInstances = getSeleniumInstances();
		 * 
		 * for (Map.Entry<String, WebDriver> entry :
		 * seleniumInstances.entrySet()) { try { entry.getValue().quit(); }
		 * catch (Exception e) { e.printStackTrace(); } }
		 */

		/*
		 * Map<String, Connection> databaseInstances = getDatabaseInstances();
		 * for (Map.Entry<String, Connection> entry :
		 * databaseInstances.entrySet()) { try { entry.getValue().close(); }
		 * catch (Exception e) { e.printStackTrace(); } }
		 */
		/*
		 * Map<String, ODatabaseDocumentTx> orientDatabaseInstances =
		 * getOrientDatabaseInstances(); for (Map.Entry<String,
		 * ODatabaseDocumentTx> entry : orientDatabaseInstances .entrySet()) {
		 * try { entry.getValue().close(); } catch (Exception e) {
		 * e.printStackTrace(); } }
		 */
	}

	/**
	 * Cleanup sessions.
	 */
	@AfterMethod(alwaysRun = true)
	public final synchronized void cleanupSessions() {
		resultReporter.endTestReporting();
		// setSeleniumInstances(new HashMap<String, WebDriver>());
		// setDatabaseInstances(new HashMap<String, Connection>());
		super.checkForVerificationErrors();
		// this.cleanBrowserSessions();
	}

	/**
	 * Tear down.
	 * 
	 * @throws Exception
	 *             the exception
	 * @see com.thoughtworks.selenium.SeleneseTestBaseVir#tearDown() @AfterSuite.
	 */
	@AfterSuite
	public final void tearDownSuite() throws Exception {
		resultReporter.endTestReporting();
		writeExecutionDetails();
		super.tearDown();
		// cleanDriverServerSessions();
		generateTimeLogCSV();
		createStoreDataExcelFile(resultReporter.getBuilder().getTestSuite().getTestsuitename());
		if (EnableExtentReport.equalsIgnoreCase("yes")) {
			extentReport.generateReport();
		}
	}

	public void createStoreDataExcelFile(String testSuiteName) {

		try {
			String lineForType;
			String lineForValue;
			String VariableType;
			String VariableName;
			String VariableValue;
			int a = 1;
			// Read data file from the folder
			String rFilename = "project_data.properties";
			BufferedReader br = new BufferedReader(new FileReader(rFilename));

			// Create a new spread sheet to store generated data
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet ws = wb.createSheet("FirstSheet");
			FileOutputStream fileOut = new FileOutputStream(testSuiteName + "_StoredData.xls");

			HSSFRow rw = ws.createRow(0);
			rw.createCell(0).setCellValue("Variable Name");
			rw.createCell(1).setCellValue("Variable Type");
			rw.createCell(2).setCellValue("Generated Data");

			while ((lineForType = br.readLine()) != null) {
				if (lineForType.contains("_Type=")) {
					VariableType = lineForType.split("_Type=")[1].trim();
					VariableName = lineForType.split("_Type=")[0].trim();

					BufferedReader brr = new BufferedReader(new FileReader(rFilename));
					while ((lineForValue = brr.readLine()) != null) {
						if (lineForValue.contains(VariableName + "_Val=")) {
							VariableValue = lineForValue.split("_Val=")[1].trim();

							HSSFRow row = ws.createRow(a);
							row.createCell(0).setCellValue(VariableName);
							row.createCell(1).setCellValue(VariableType);
							row.createCell(2).setCellValue(VariableValue);

							a++;

						}
					}
				}
			}

			wb.write(fileOut);
			fileOut.close();
			System.out.println("Your \"" + testSuiteName + "_StoredData.xls" + "\"excel file has been generated!");

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	/**
	 * Kill the browser process.<br>
	 * Specify the browser process to be killed.
	 * 
	 * @param process
	 *            the process
	 * @Parameters<br>
	 * 				process name which should be killed. <br>
	 *                 Ex:<br>
	 *                 If the process is firefox.exe parameter should be firefox
	 */
	public final void killBrowserProcess(final String process) {
		String os = getOperatingSystem().toLowerCase(Locale.getDefault());

		final int pauseTimeAfterBrowserKill = 3000;
		Logger log = getLog();
		try {

			if (os.contains("win")) {

				String processName = process.concat(".exe");
				if (isProcessRunning(processName)) {
					this.killProcess(processName);

					sleep(pauseTimeAfterBrowserKill);
				}
			} else if (os.contains("mac") || os.contains("nux")) {

				this.killProcess(process);

				sleep(pauseTimeAfterBrowserKill);
			}

			log.info(process.concat(" browser session cleaned successfully"));

		} catch (Exception e) {
			log.error(e);
		}
	}

	/**
	 * Inits the reporter.
	 */
	public static synchronized void initReporter() {
		resultReporter = new Reporter();
	}

	/**
	 * Inits the robot.
	 */
	public static synchronized void initRobot() {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Start of test case.
	 */
	public final void startOfTestCase(WebDriver driver2) {
		// WebDriver driver =
		// VtafThreadLocalClass.getTestCaseUnit().getDriver();//getDriver();
		WebDriver driver = driver2;
		final int pageloadTimeOut = 300;
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		try {
			driver.manage().timeouts().pageLoadTimeout(pageloadTimeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Reportresult.
	 * 
	 * @param isAssert
	 *            the is assert
	 * @param step
	 *            the step
	 * @param result
	 *            the result
	 * @param messageString
	 *            the message string
	 */
	public final void reportresult(final boolean isAssert, final String step, final String result,
			final String messageString) {
		String message = messageString;
		WebDriver driver = VtafThreadLocalClass.getTestCaseUnit().getDriver();// getDriver();
		Logger log = getStackTrace(step, result, message);

		logTime(step, getCommandStartTime(), getCurrentTime(), log);

		// Adding data to the new reporter
		try {
			String testStep = step.substring(0, step.indexOf(':'));
			// replace xml special characters in the message.
			message = replaceXMLSpecialCharacters(message);
			if ("PASSED".equals(result)) {
				String testMessage = message;
				if ("".equals(message) || message == null) {
					testMessage = "Passed";
				}
				if (callingClassName.contains("LIBRARY_RECOVERY")) {
					resultReporter.reportStepResults(driver, true, "RECOVERY : " + testStep, testMessage, "Success",
							"");
				} else {
					resultReporter.reportStepResults(driver, true, testStep, testMessage, "Success", "");
				}
			} else {
				if (callingClassName.contains("LIBRARY_RECOVERY")) {
					resultReporter.reportStepResults(driver, false, "RECOVERY : " + testStep, message, "Error",
							getSourceLines(new Throwable(message).getStackTrace()));
				} else {
					resultReporter.reportStepResults(driver, false, testStep, message, "Error",
							getSourceLines(new Throwable(message).getStackTrace()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Prints the stack trace.
	 * 
	 * @param step
	 *            the step
	 * @param result
	 *            the result
	 * @param message
	 *            the message
	 * @return the logger
	 */
	private Logger getStackTrace(final String step, final String result, final String message) {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		callingClassName = stackTraceElements[0].getClassName();
		String callingMethod = "";
		for (int i = 0; i < stackTraceElements.length; i++) {
			callingClassName = stackTraceElements[i].getClassName();
			if (callingClassName.startsWith(testPackageName)) {
				callingMethod = stackTraceElements[i].getMethodName();
				lineNumber = stackTraceElements[i].getLineNumber();
				break;
			}
		}
		System.out.println(callingClassName);
		// System.out.println("-----superman-----");
		Class callingClass = null;
		Logger log = getLog();
		try {
			callingClass = Class.forName(callingClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		getLogger(callingClass);
		if (!currentMethod.equals(callingMethod)) {
			log.info("Executing : " + callingClass.getName() + " : " + callingMethod);
			currentMethod = callingMethod;
		}
		setCurrentTC(callingMethod);
		if (!("PASSED".equals(result))) {
			setCurrentStatus("FAILED");
		}

		System.out.println("CurrentTC :" + getCurrentTC() + " ---- Status :" + getCurrentStatus());

		log.info("Step : " + step + "\t|\tResult : " + result + "\t|\tMessage : " + message);

		return log;
	}

	/**
	 * Creates execution breif detail report
	 * 
	 */
	public void writeExecutionDetails() {
		Writer outWriter = null;
		int failCount = resultReporter.getTestExecution().getTotalfailedcount();
		int passCount = resultReporter.getTestExecution().getTotalsuccesscount();
		String date = resultReporter.getTestExecution().getTimestamp();
		int total = failCount + passCount;
		double precentage = (passCount / (double) total) * 100;
		try {
			outWriter = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("ExecutionDetails.txt", true), "utf-8"));
			outWriter.append("Execution Date	:	" + date + "\r\n");
			outWriter.append("Passed TC	:	" + passCount + "\r\n");
			outWriter.append("Failed TC	:	" + failCount + "\r\n");
			outWriter.append("Passed TC %	:	" + precentage + "%" + "\r\n");
			outWriter.append("\r\n");

		} catch (IOException ex) {
			System.out.println(ex);
		} finally {
			try {
				outWriter.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	/**
	 * Gets the current time.
	 * 
	 * @return the current time
	 */
	public final Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Sets the command start time.
	 * 
	 * @param startTime
	 *            the new command start time
	 */
	protected final void setCommandStartTime(final Date startTime) {
		this.commandStartTime = startTime;
	}

	/**
	 * Gets the command start time.
	 * 
	 * @return the command start time
	 */
	protected final Date getCommandStartTime() {
		return this.commandStartTime;
	}

	/**
	 * Log time.
	 * 
	 * @param desc
	 *            the description
	 * @param start
	 *            the start date time
	 * @param end
	 *            the end time
	 * @param log
	 *            the log
	 */
	private void logTime(final String desc, Date start, Date end, final Logger log) {
		start = VtafThreadLocalClass.getTestCaseUnit().getTestcaseStartTime();
		end = getCurrentTime();
		try {
			if (!desc.startsWith("PAUSE")) {
				Long timeDiff = Math.abs(end.getTime() - start.getTime());
				totalExecutionTimeTaken += timeDiff;
				VtafThreadLocalClass.getTestCaseUnit().setTotalExecutionTimeTaken(totalExecutionTimeTaken);
				log.info("Time taken to execute " + desc + " " + timeDiff + " ms");
				appendToCSVFileBuilder(desc, ",", timeDiff, "\n");
				// logTimeCSVFileBuilder.append(desc).append(',').append(timeDiff)
				// .append("\n");
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	/**
	 * Gets the robot.
	 * 
	 * @return the robot
	 */
	public static final Robot getRobot() {
		return robot;
	}

	/**
	 * Sets the robot.
	 * 
	 * @param robotObj
	 *            the robot to set
	 */
	public static final void setRobot(final Robot robotObj) {
		SeleneseTestNgHelperVir.robot = robotObj;
	}

	/**
	 * Gets the prop.
	 * 
	 * @return the prop
	 */
	public static final Properties getProp() {
		return prop;
	}

	/**
	 * Sets the prop.
	 * 
	 * @param propObj
	 *            the prop to set
	 */
	public static final void setProp(final Properties propObj) {
		SeleneseTestNgHelperVir.prop = propObj;
	}

	/**
	 * Gets the properties location.
	 * 
	 * @return the propertiesLocation
	 */
	public static final String getPropertiesLocation() {
		return propertiesLocation;
	}

	/**
	 * Sets the properties location.
	 * 
	 * @param propertiesLocationString
	 *            the propertiesLocation to set
	 */
	public static final void setPropertiesLocation(final String propertiesLocationString) {
		SeleneseTestNgHelperVir.propertiesLocation = propertiesLocationString;
	}

	/**
	 * Gets the error messages.
	 * 
	 * @return the errorMessages
	 */
	public final String getErrorMessages() {
		return errorMessages;
	}

	/**
	 * Sets the error messages.
	 * 
	 * @param errorMessagesString
	 *            the errorMessages to set
	 */
	public final void setErrorMessages(final String errorMessagesString) {
		this.errorMessages = errorMessagesString;
	}

	/**
	 * Gets the test package name.
	 * 
	 * @return the testPackageName
	 */
	public final String getTestPackageName() {
		return testPackageName;
	}

	/**
	 * Sets the test package name.
	 * 
	 * @param testPackageNameString
	 *            the testPackageName to set
	 */
	public final void setTestPackageName(final String testPackageNameString) {
		this.testPackageName = testPackageNameString;
	}

	/**
	 * Gets the current method.
	 * 
	 * @return the currentMethod
	 */
	public final String getCurrentMethod() {
		return currentMethod;
	}

	/**
	 * Sets the current method.
	 * 
	 * @param currentMethodString
	 *            the currentMethod to set
	 */
	public final void setCurrentMethod(final String currentMethodString) {
		this.currentMethod = currentMethodString;
	}

	/**
	 * Gets the calling class name.
	 * 
	 * @return the callingClassName
	 */
	public final String getCallingClassName() {
		return callingClassName;
	}

	/**
	 * Sets the calling class name.
	 * 
	 * @param callingClassNameString
	 *            the callingClassName to set
	 */
	public final void setCallingClassName(final String callingClassNameString) {
		this.callingClassName = callingClassNameString;
	}

	/**
	 * Gets the line number.
	 * 
	 * @return the lineNumber
	 */
	public final int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Sets the line number.
	 * 
	 * @param lineNumberInt
	 *            the lineNumber to set
	 */
	public final void setLineNumber(final int lineNumberInt) {
		this.lineNumber = lineNumberInt;
	}

	/**
	 * Gets the exec props.
	 * 
	 * @return the execProps
	 */
	public static final Properties getExecProps() {
		return execProps;
	}

	/**
	 * Sets the exec props.
	 * 
	 * @param execPropsObj
	 *            the execProps to set
	 */
	public static final void setExecProps(final Properties execPropsObj) {
		SeleneseTestNgHelperVir.execProps = execPropsObj;
	}

	/**
	 * Gets the open window handle index.
	 * 
	 * @return the openWindowHandleIndex
	 */
	public final List<String> getOpenWindowHandleIndex() {
		return openWindowHandleIndex;
	}

	/**
	 * Sets the open window handle index.
	 * 
	 * @param openWindowHandleIndexList
	 *            the openWindowHandleIndex to set
	 */
	public final void setOpenWindowHandleIndex(final List<String> openWindowHandleIndexList) {
		this.openWindowHandleIndex = openWindowHandleIndexList;
	}

	/**
	 * Gets the result reporter.
	 * 
	 * @return the resultReporter
	 */
	public static final Reporter getResultReporter() {
		return resultReporter;
	}

	/**
	 * Sets the result reporter.
	 * 
	 * @param resultReporterObj
	 *            the resultReporter to set
	 */
	public static final void setResultReporter(final Reporter resultReporterObj) {
		SeleneseTestNgHelperVir.resultReporter = resultReporterObj;
	}

	/**
	 * Gets the total execution time taken.
	 * 
	 * @return the totalExecutionTimeTaken
	 */
	public final long getTotalExecutionTimeTaken() {
		return totalExecutionTimeTaken;
	}

	/**
	 * Sets the total execution time taken.
	 * 
	 * @param totalTimeTaken
	 *            the totalExecutionTimeTaken to set
	 */
	public final void setTotalExecutionTimeTaken(final long totalTimeTaken) {
		this.totalExecutionTimeTaken = totalTimeTaken;
	}

	/**
	 * Gets the retry count.
	 * 
	 * @return the retryCount
	 */
	public static final int getRetryCount() {
		return retryCount;
	}

	/**
	 * Sets the retry count.
	 * 
	 * @param retryCountInt
	 *            the retryCount to set
	 */
	public static final void setRetryCount(final int retryCountInt) {
		SeleneseTestNgHelperVir.retryCount = retryCountInt;
	}

	/**
	 * Append to the csv log.
	 * 
	 * @param appendValues
	 *            objects to append
	 */
	private static void appendToCSVFileBuilder(final Object... appendValues) {
		StringBuilder builder = new StringBuilder();
		for (Object currentAppendValue : appendValues) {
			builder.append(currentAppendValue);

		}

		setLogTimeCSVFileBuilder(builder.toString());
	}

	/**
	 * @return the currentTC
	 */
	public String getCurrentTC() {
		return currentTC;
	}

	/**
	 * @param currentTC
	 *            the currentTC to set
	 */
	public void setCurrentTC(String currentTC) {
		this.currentTC = currentTC;
	}

	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus
	 *            the currentStatus to set
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	 /**
	 * @param QtestreleaseID
	 *            the QtestreleaseID to set
	 */
	public void setQtestreleaseID(String releaseID) {
		this.QtestReleaseID = releaseID;
	}

	/**
	 * @param QtestreleaseID
	 *            the QtestreleaseID to get
	 */
	public String getQtestreleaseID() {
		return QtestReleaseID;
	}

	/**
	 * @param QtestCycleID
	 *            the QtestCycleID to set
	 */
	public void setQtestCycleID(String cycleID) {
		this.QtestCycleID = cycleID;
	}

	/**
	 * @param QtestCycleID
	 *            the QtestCycleID to get
	 */
	public String getQtestCycleID() {
		return QtestCycleID;
	}

	/**
	 * @param QtestCycleID
	 *            the QtestCycleID to set
	 */
	public void setQtesSuitePID(String suitePID) {
		this.QtesSuitePID = suitePID;
	}

	/**
	 * @param QtestCycleID
	 *            the QtestCycleID to get
	 */
	public String getQtesSuitePID() {
		return QtesSuitePID;
	}
}
