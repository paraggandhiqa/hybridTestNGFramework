package com.tutorialninja.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListenersBackup01 implements ITestListener {
	// override methods of ITestListener
	// console printing

	//execution of project starts
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of project started.");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		System.out.println("Test started: " + testName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		System.out.println("Test successfully executed:"+testName);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getName();
		System.out.println("Test failed:" + testName);
		//failure result will display
		System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName=result.getName();
		System.out.println("Test skipped:" +testName);
		//skip result will display
		System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test finished successfully.");
	}

}
