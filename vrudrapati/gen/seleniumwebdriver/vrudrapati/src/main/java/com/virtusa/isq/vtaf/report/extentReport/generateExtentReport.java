package com.virtusa.isq.vtaf.report.extentReport;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.thoughtworks.selenium.SeleneseTestNgHelperVir;

public class generateExtentReport {

	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	private static String extentReportPath;
	private static String reportDateTime;
	private static String reportFolderPath;

	// call @BeforeSuite
	public void extentSetup() {
		reportDateTime = getDateTime();
		extentReportPath = "target" + File.separator + File.separator + "Extent-Reports" + File.separator+ SeleneseTestNgHelperVir.getReleaseVersion() + "_"+ SeleneseTestNgHelperVir.getCurrentPlanName()+"_"+ SeleneseTestNgHelperVir.getBrowserString()+"_"+reportDateTime;
		
		reportFolderPath = extentReportPath ;
		
		extentReportPath = extentReportPath + File.separator + SeleneseTestNgHelperVir.getReleaseVersion() + "_" + SeleneseTestNgHelperVir.getCurrentPlanName() + "_"+ SeleneseTestNgHelperVir.getBrowserString() + "_"+ reportDateTime + ".html";
		extentReports = new ExtentReports(extentReportPath, true);
		extentReports.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
	}

	// call @BeforeMethod
	public void startExtent(String tcName) {
		extentTest = extentReports.startTest(tcName);
	}

	// call @AfterMethod
	public void endExtent(ITestResult result) {
		if (result.getStatus() == 1) {
			extentTest.log(LogStatus.PASS, "OVER ROLL STATUS OF THE TEST CASE ", "PASSED");
		} else if (result.getStatus() == 2) {
			extentTest.log(LogStatus.FAIL, "OVER ROLL STATUS OF THE TEST CASE ", "FAILED");
		} else if (result.getStatus() == 3) {
			extentTest.log(LogStatus.SKIP, "OVER ROLL STATUS OF THE TEST CASE ", "SKIPED");
		}

		extentReports.endTest(extentTest);
		extentReports.flush();
	}

	// call @AfterSuite
	public void generateReport() {
		extentReports.close();
		System.out.print("Extent report created successfully to " + extentReportPath);
	}

	// get System Date Time
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); // 2016/11/16 12:08:43
		String dateTime = dateFormat.format(date);
		dateTime = dateTime.replace("/", "_");
		dateTime = dateTime.replace(":", "_");
		dateTime = dateTime.replace(" ", "_");
		return dateTime;
	}

	public String saveFullScreenShot(WebDriver driver) {

		String screenShotFile = getDateTime() + ".png";
		String screenShotImgFolder = reportFolderPath + File.separator + "images";
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File screenShotImgFolderFile = new File(screenShotImgFolder);
			if (!screenShotImgFolderFile.exists() && !screenShotImgFolderFile.mkdirs()) {
				throw new RuntimeException(
						"Cannot create new folder in location " + screenShotImgFolderFile.getAbsolutePath());
			}
			FileUtils.copyFile(scrFile, new File(screenShotImgFolder + File.separator + screenShotFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenShotImgFolder + File.separator + screenShotFile;
	}

	public ExtentReports getExtentReports() {
		return extentReports;
	}

	public ExtentTest getExtentTest() {
		return extentTest;
	}

	public void setExtentReports(ExtentReports extentReports) {
		this.extentReports = extentReports;
	}

	public void setExtentTest(ExtentTest extentTest) {
		this.extentTest = extentTest;
	}
}
