package com.cg.BasePackage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.cg.TestCases.TC_001;
import com.cg.extentPackage.ExtentManager;
import com.cg.utility.ExcelData;
import com.cg.utility.ReadConfig;

public class BaseClass extends ExcelData {

	public static WebDriver driver;
	protected ReadConfig readconfig;
	public static Logger log = Logger.getLogger(TC_001.class);

	static LocalDateTime datetime = LocalDateTime.now();
	static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
	public static String date = datetime.format(format);

	@BeforeTest
	public void setUp() {
		ExtentManager.config();
	}

	@AfterTest
	public void tearDown() {
		ExtentManager.tearDown();
	}

	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) throws IOException {
		ExtentManager.test = ExtentManager.extent.createTest("login");
		PropertyConfigurator.configure("log4j.properties");
		i++;
		readconfig = new ReadConfig();
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
			ExtentManager.test.info("chrome browser intialised");
			log.debug("chrome browser intialised");
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getEdgePath());
			driver = new EdgeDriver();
			ExtentManager.test.info("Edge browser intialised");
			log.debug("Edge browser intialised");
		}
		driver.manage().window().maximize();
		ExtentManager.test.info("Browser maximised");
		log.debug("Browser maximised");
	}

	@AfterMethod
	public void teardown() {
		driver.close();
		ExtentManager.test.info("browser closed");
		log.debug("browser closed");
	}

	public static void getScreenShot(ITestResult result) throws IOException {
		TakesScreenshot srcshot = (TakesScreenshot) driver;
		File src = srcshot.getScreenshotAs(OutputType.FILE);
		File target = new File("screenshots//" + result.getParameters()[0] + "-" + date + ".png");
		FileHandler.copy(src, target);
	}
}
