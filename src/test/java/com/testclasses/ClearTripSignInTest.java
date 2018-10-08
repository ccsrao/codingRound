package com.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pageclasse.ClearTripSignInPage;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;

public class ClearTripSignInTest extends BaseClass {
	private ConfigFilesUtility configFileObj;
	
	public ClearTripSignInTest() throws Exception {
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("TestConfig.properties");
	}
	/* Launch the browser, Create Extent Reports instance and Launch the url */
	@BeforeClass
	public void setUP() throws Exception {
		reports = getExtentReport("ClearTripSignInTest", "SignIn Functionality");
		driver = launchBrowser("chrome", configFileObj);
		test.log(LogStatus.INFO, "Browser is Luanched ");
		test.log(LogStatus.INFO, "Url is loaded : " + configFileObj.getProperty("URL"));
	}
	
	/* Verify the Sign with out user name and password and displaying the alerts*/
	@Test
	public void test() throws Exception {
		try {
		ClearTripSignInPage objSignInPage = PageFactory.initElements(driver, ClearTripSignInPage.class);
		String title = driver.getTitle();
		Assert.assertEquals("#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.", title);
		objSignInPage.clkYourTripLnk();
		test.log(LogStatus.INFO, "Clicked on YourTrips Link");
		objSignInPage.clkSignInLnk();
		test.log(LogStatus.INFO, "Clicked on Sign Link");
		objSignInPage.clkSignInBtn();
		test.log(LogStatus.INFO, "Clicked on SignIn Button");
		objSignInPage.displaySignInErrorMsg(test);
	} catch(Exception ex) {
		System.out.println("Exception is :  " + ex);
		test.log(LogStatus.FAIL, "Exception is :  " + ex);
	}
	}
	
	/* End the test in extent report, flush the data and quit the browser*/
	@AfterClass
	public void tearDown(){
		tearDownM();
	}
}
