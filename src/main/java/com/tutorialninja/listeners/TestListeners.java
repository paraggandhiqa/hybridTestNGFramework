package com.tutorialninja.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.utils.ExtentReporter;
import com.tutorialninja.utils.Utilities;

public class TestListeners implements ITestListener {
	// override methods of ITestListener
	// extent reports
	// global test name and seperate method for taking screenshot.
	// open report after test execution automatically.

	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;

	// execution of project starts
	@Override
	public void onStart(ITestContext context) {

		try {
			extentReport = ExtentReporter.generateExtentReport();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started successfully");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, testName + " got successgully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;

		// get driver from specified test/class file and use it in takesScreenshot
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		String destScreenshotPath = Utilities.getScreenshot(driver, result.getTestName());

		// attach screenshot into extent
		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		// exception related information
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " failed.");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		// to add all details in to report >> we use flush.
		extentReport.flush();

		String extentReportPath = System.getProperty("user.dir") + "\\test-output\\extent reports\\extentReport.html";
		File extentReportFile = new File(extentReportPath);
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
