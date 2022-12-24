package com.netbanking.testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.netbanking.pageObjects.LoginPAGE;



public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() {
		
		driver.get(baseURL);
		logger.info("URL IS OPEN");
		
		LoginPAGE lp = new LoginPAGE(driver);
		lp.setUserName(username);
		logger.info("ENTER USER NAME");
		
		lp.setPassword(password);
		logger.info("ENTER  PASSWORD");
		lp.clickSubmit();
		System.out.println(driver.getTitle().toString());
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			
			Assert.assertTrue(true);
			logger.info("Login test passed");
			System.out.println("Login successfully ");
		}
		else
		{
			Assert.assertTrue(false);
			logger.info("Login is failed");
		}
	} // loginTest()
}
