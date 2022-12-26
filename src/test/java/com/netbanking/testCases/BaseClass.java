package com.netbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
		if (brName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", readconfig.getchromeDriverPath());
			ChromeOptions opt = new ChromeOptions();
			opt.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking","--disable-notifications"));
			 driver = new ChromeDriver(opt);
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

	public void captureScreen(WebDriver driver, String Name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\ScreenShots\\" + Name);

		System.out.println(source);
		System.out.println(target);

		FileUtils.copyFile(source, target);
		System.out.println("Screen shot taken");
	}

	public String randomString(int noofcharc) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(noofcharc);

		for (int i = 0; i < noofcharc; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			System.out.println("Yes alert is Present");
			return true;
		} catch (Exception exc) {
			return false;
		}
	}

	public boolean isAddIframePresent() {
		try {

			// driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			// driver.switchTo().frame("ad_iframe");
			Thread.sleep(3000);
			
			//google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0__container__
			//WebElement frmae1 = driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0__container__"));
			//driver.switchTo().frame(frmae1);
			//Thread.sleep(2000);
			WebElement idcard = driver.findElement(By.xpath("//div[@id='card']"));
			WebElement frame2 = idcard.findElement(By.id("ad_iframe"));
		    driver.switchTo().frame(frame2);
		    driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
		    driver.switchTo().defaultContent();
		    Thread.sleep(5000);
			System.out.println("Yes iframe is Present");
			return true;
		} catch (Exception exc) {
			System.out.println(exc.getLocalizedMessage());
			return false;
		}
	}
}
