package com.cg.listenerPackage;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.cg.BasePackage.BaseClass;
import com.cg.extentPackage.ExtentManager;
import com.cg.utility.ExcelData;

public class MyListener extends ExtentManager implements ITestListener {

	public void onTestStart(ITestResult result) {
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "This is Passed Test :" + result.getName());
		try {
			ExcelData.writeData("PASS");
			test.info("PASS status written into Excel");
			BaseClass.log.debug("PASS status written into Excel");
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseClass.log.debug("This is Passed Test :" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "This is Failed Test :" + result.getName());
		try {
			BaseClass.getScreenShot(result);
			ExcelData.writeData("FAIL");
			test.info("FAIL status written into Excel");
			BaseClass.log.debug("FAIL status written into Excel");
		} catch (IOException e) {
			e.printStackTrace();
		}
		BaseClass.log.warn("This is Failed Test :" + result.getName());
		test.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\screenshots\\" + result.getParameters()[0]
				+ "-" + BaseClass.date + ".png");
		test.info("Taken Screenshot and attached it to report");
		BaseClass.log.debug("Taken Screenshot and attached it to report");
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "This is Skipped Test :" + result.getName());
		BaseClass.log.warn("This is Skipped Test :" + result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
	}

	public void onFinish(ITestContext context) {
	}
}
