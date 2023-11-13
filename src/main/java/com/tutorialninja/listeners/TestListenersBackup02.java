package com.tutorialninja.listeners;

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

public class TestListenersBackup02 implements ITestListener {
	// override methods of ITestListener
	// extent reports

	ExtentReports extentReport;
	ExtentTest extentTest;
	

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
		String testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started successfully");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName + " got successgully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		WebDriver driver = null;

		// get driver from specified test/class file
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

		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// takes screenshot at this path with test name
		String destScreenshotPath = System.getProperty("user.dir") + "\\test-output\\screenshots\\" + testName + ".png";
		System.out.println("screenshot path is:" + destScreenshotPath);

		try {
			FileHandler.copy(srcScreenshot, new File(destScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// attach screenshot into extent
		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		// exception related information
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + " failed.");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + " skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		// to add all details in to report >> we use flush.
		extentReport.flush();
	}

}
