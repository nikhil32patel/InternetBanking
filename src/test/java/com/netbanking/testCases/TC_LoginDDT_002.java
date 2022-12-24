package com.netbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netbanking.pageObjects.LoginPAGE;
import com.netbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String username, String password) {
		LoginPAGE lp = new LoginPAGE(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}	
		 else {
			Assert.assertTrue(true);
			lp.logout();
			driver.switchTo().alert().accept(); // close logout alert
			driver.switchTo().defaultContent();
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception exc) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/com/netbanking/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		System.out.println("Total rows:"+rownum);
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		System.out.println("Total colums:"+colcount);
		
		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}
}
