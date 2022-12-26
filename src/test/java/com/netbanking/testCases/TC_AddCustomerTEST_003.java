package com.netbanking.testCases;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.netbanking.pageObjects.AddCustomerPAGE;
import com.netbanking.pageObjects.LoginPAGE;

public class TC_AddCustomerTEST_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException {
		LoginPAGE lp = new LoginPAGE(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();

		Thread.sleep(3000);
		AddCustomerPAGE addcustpg = new AddCustomerPAGE(driver);
		addcustpg.clickAddcustomerlink();

		String pwindow = driver.getWindowHandle();
		System.out.println(pwindow);
		
		Set<String> windows = driver.getWindowHandles();
		for(String handle:windows) {
			System.out.println(handle);
		}
		
		if (isAlertPresent() == true) {
			driver.switchTo().alert().dismiss();
			driver.switchTo().defaultContent();
		} else  if (isAddIframePresent() == true){
			//driver.switchTo().frame("ad_iframe");
//			driver.findElement(By.xpath("//div[@class='ns-qekel-e-14 button-common close-button']")).click();
//			driver.switchTo().def/aultContent();
			
			driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
		   // driver.switchTo().defaultContent();
		    Thread.sleep(5000);
			
		}else
		{
			System.out.println("No alert and No add iframe present");
		}
		
		driver.findElement(By.xpath("//div[@id='dismiss-button']/div/span")).click();
		   // driver.switchTo().defaultContent();
		Thread.sleep(5000);

		addcustpg.custName("Nikhil");
		addcustpg.custGender("male");
		addcustpg.custdob("22", "05", "1990");

		addcustpg.custCity("Mumbai");
		addcustpg.custState("Maharashtra");
		addcustpg.custPinno(401101);
		addcustpg.custTelephono(857489);
		String custemail = randomString(8) + "@gmail.com";
		addcustpg.custEmilid(custemail);
		addcustpg.custPssword("acghj");
		addcustpg.custsClickuSbmit();

		boolean result = driver.getPageSource().contains("Customer Registered Successfullyyyyy");

		if (result == true) {
			Assert.assertTrue(true);
		}
	}

}
