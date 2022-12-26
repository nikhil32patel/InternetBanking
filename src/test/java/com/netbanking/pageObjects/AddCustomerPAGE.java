package com.netbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPAGE {

	WebDriver ldriver;

	public AddCustomerPAGE(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'New Customer')]")
	WebElement lnkAddNewCustomer;

	@FindBy(how = How.XPATH, using = "//input[@name='name']")
	 WebElement txtCustomerNme;

	@FindBy(how = How.NAME, using = "rad1")
	WebElement rdGender;

	@FindBy(how = How.ID_OR_NAME, using = "dob")
	WebElement txtdob;

	@FindBy(how = How.NAME, using = "add")
	WebElement txtaddres;

	@FindBy(how = How.NAME, using = "city")
	WebElement txtcity;

	@FindBy(how = How.NAME, using = "state")
	WebElement txtstate;

	@FindBy(how = How.NAME, using = "pinno")
	WebElement txtpinno;

	@FindBy(how = How.NAME, using = "telephono")
	WebElement txttelephono;

	@FindBy(how = How.NAME, using = "txtemailid")
	WebElement txtemailid;

	@FindBy(how = How.NAME, using = "txtepassword")
	WebElement txtepassword;

	@FindBy(how = How.NAME, using = "btnsubmit")
	WebElement btnsubmit;

	// ACTIONS METHODS

	public void clickAddcustomerlink() {
		lnkAddNewCustomer.click();
	}
	
	public void custName(String  name) {
		txtCustomerNme.sendKeys(name);
		System.out.println("Name added");
	}
	
	public void custGender(String gender) {
		rdGender.click();
		System.out.println("Gender selected");
	}
	
	public void custdob(String mm,String dd,String yy) {
		txtdob.sendKeys(mm);
		txtdob.sendKeys(dd);
		txtdob.sendKeys(yy);
		System.out.println("DOB Selected");
	}
	
	public void custAddress(String address) {
		txtaddres.sendKeys(address);
		System.out.println("Address added");
	}

	public void custCity(String city) {
		txtcity.sendKeys(city);
		System.out.println("City added");
	}

	public void custState(String state) {
		txtstate.sendKeys(state);
		System.out.println("State added");
	}

	public void custPinno(int  pinno) {
		txtpinno.sendKeys(String.valueOf(pinno));
		System.out.println("Pincode added");
	}

	public void custTelephono(int telephono) {
		txttelephono.sendKeys(String.valueOf(telephono));
		System.out.println("Telephono added");
	}

	public void custEmilid(String txtemailid) {
		this.txtemailid.sendKeys(txtemailid);
		System.out.println("Email added");
	}

	public void custPssword(String txtpssword) {
		this.txtepassword.sendKeys(txtpssword);
		System.out.println("Password added");
	}

	public void custsClickuSbmit() {
		btnsubmit.click();
	}
}
