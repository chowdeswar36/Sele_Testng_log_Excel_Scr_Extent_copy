package com.cg.TestCases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.cg.BasePackage.BaseClass;
import com.cg.extentPackage.ExtentManager;
import com.cg.pageobjects.LoginPage;

public class TC_001 extends BaseClass {
	LoginPage lp;

	@Test(dataProvider = "readData")
	public void login(String username, String password) throws InterruptedException, IOException {
		driver.get(readconfig.getBaseUrl());
		ExtentManager.test.info("user entered baseurl " + readconfig.getBaseUrl());
		log.debug("user entered baseurl " + readconfig.getBaseUrl());
		lp = new LoginPage(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		ExtentManager.test.info("user enters username " + username);
		log.debug("user enters username " + username);
		ExtentManager.test.info("user entered password " + password);
		log.debug("user enters password " + password);
		lp.clickLogin();
		ExtentManager.test.info("user clicked on login button");
		log.debug("user clicked on login button");
		if (driver.findElement(By.id("welcome")).isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
}
