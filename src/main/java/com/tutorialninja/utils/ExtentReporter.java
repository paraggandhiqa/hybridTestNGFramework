package com.tutorialninja.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() throws FileNotFoundException {

		ExtentReports extentReport = new ExtentReports();

		File file = new File(System.getProperty("user.dir") + "\\test-output\\extent reports\\extentReport.html");

		// spark reporter is free >>
		// https://www.extentreports.com/docs/versions/5/java/index.html
		ExtentSparkReporter spark = new ExtentSparkReporter(file);

		// Configurations in spark report
		// set the darker theme
		spark.config().setTheme(Theme.DARK);
		// set report name
		spark.config().setReportName("Tutorials Ninja Test Report");
		// set page title >> be default website link is coming with sadasd.com
		spark.config().setDocumentTitle("Tutorials Ninja Test Report");
		// header right side timestamp
		spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		// attaching spark into attach report
		extentReport.attachReporter(spark);

		Properties properties = new Properties();

		// additional information to display on report
		// user who has executed, java version etc.
		extentReport.setSystemInfo("Application URL", properties.getProperty("url"));
		extentReport.setSystemInfo("Browser name", properties.getProperty("browser"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java version", System.getProperty("java.version"));

		return extentReport;

	}

}
