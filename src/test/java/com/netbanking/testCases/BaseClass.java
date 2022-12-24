package com.netbanking.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.netbanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getuserName();
	public String password = readconfig.getPassword();

	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void browserSetup(String brName) {

		
		// S:\TESTING\WEB\Eclipse-2022-03\InternetBanking\Drivers
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+
		// "//Drivers//chromedriver.exe");
		if(brName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getchromeDriverPath());
			driver = new ChromeDriver();
		}
		
		driver.get(baseURL);
		driver.manage().window().maximize();

		logger = Logger.getLogger("InternetBanking");
		PropertyConfigurator.configure("log4j.properties");

		
	}

	@AfterClass
	public void tearDown() {

		// close all associate windows of this driver
		driver.quit();

		// close only current window
		// driver.close();
	}
	public void captureScreen(WebDriver driver,String Name) throws IOException{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + Name);
		
		System.out.println(source);
		System.out.println(target);
		
		FileUtils.copyFile(source, target);
		System.out.println("Screen shot taken");
	}
}
