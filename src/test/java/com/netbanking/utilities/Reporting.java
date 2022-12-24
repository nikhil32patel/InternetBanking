package com.netbanking.utilities;

// Listener class used to generate report

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.netbanking.testCases.BaseClass;

//public class Reporting extends TestListenerAdapter,BaseClass {
public class Reporting extends BaseClass  implements ITestListener{	

	// public ExtentHtmlReporter htmlRepoter;
	public ExtentReports extent;
	public ExtentTest logger;
	public ExtentSparkReporter spark;

	@Override
	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repNmae = "Test Report - " + timeStamp + ".html";

		// htmlRepoter = new
		// ExtentHtmlReporter(System.getProperty("usr.dir")+"/test-0utput/"+repNmae);
		extent = new ExtentReports();
		// spark = new
		// ExtentSparkReporter(System.getProperty("usr.dir")+"///test-output///"+repNmae);
		// spark = new
		// ExtentSparkReporter("S:\\TESTING\\WEB\\Eclipse-2022-03\\InternetBanking\\test-output"+repNmae);
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\" + repNmae);
		System.out.println(System.getProperty("user.dir"));
		/*
		 * extent.setSystemInfo("Host Name", "localhost");
		 * extent.setSystemInfo("Environment","QA"); extent.setSystemInfo("User",
		 * "Nikhil");
		 */

		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("InterNetBanking"); // TITLE OF REPORT
		spark.config().setReportName("Functional Test Report");
		extent.attachReporter(spark);

		// htmlRepoter.config().setDocumentTitle("InterNetBanking"); // TITLE OF REPORT
		// htmlRepoter.config().setReportName("Functional Test Report"); //NAME OF THE
		// REPORT
		// htmlRepoter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName());// CREATE NEW ENTRY IN the REPORT
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));// Send pass information
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		logger = extent.createTest(tr.getName());// CREATE NEW ENTRY IN the REPORT
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // Send fail information
		try {
			captureScreen(driver,tr.getName()+".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String screenshotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + tr.getName()+".png";
		//String screenshotPath = System.getProperty("user.dir") + "\\ScreenShots\\" ;    

		File ssfile = new File(screenshotPath);
		if (ssfile.exists()) {
			
			System.out.println(screenshotPath);
			//logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			logger.fail("Screenshot is below:" );
			logger.addScreenCaptureFromPath(screenshotPath);
		}
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());// CREATE NEW ENTRY IN the REPORT
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	@Override
	public void onFinish(ITestContext testContet) {
		extent.flush();
	}
}
