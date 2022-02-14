package com.cg.extentPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.cg.BasePackage.BaseClass;

public class ExtentManager {
	public static ExtentSparkReporter stackReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static void config() {
		stackReporter = new ExtentSparkReporter("Reports\\Extent-Report-" + BaseClass.date + ".html");
		stackReporter.config().setDocumentTitle("Test Reports");
		stackReporter.config().setReportName("chowdeswara Rao");
		stackReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(stackReporter);
		extent.setSystemInfo("user", "chowdeswara Rao");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Host name", "localhost:8080");
	}

	public static void tearDown() {
		extent.flush();
	}
}
