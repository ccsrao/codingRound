package com.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pageclasse.ClearTripFlightBookingPage;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;

public class ClearTripFlightBookingTest extends BaseClass {
	private ConfigFilesUtility configFileObj;
	
	/* Constructor*/
	public ClearTripFlightBookingTest() throws Exception {
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("TestConfig.properties");
	}
	/* Launch the browser, Create Extent Reports instance and Launch the url */
	@BeforeClass
	public void setUP() throws Exception {
		reports = getExtentReport("ClearTripFlightBookingTest", "Flight Booking Functionality");
		driver = launchBrowser("chrome", configFileObj);
		test.log(LogStatus.INFO, "Browser is Luanched ");
		test.log(LogStatus.INFO, "Url is loaded : " + configFileObj.getProperty("URL"));
	}
	
	/* Verify the Flight Booking Functionality*/
	@Test
	public void test() throws Exception {
		try {
		ClearTripFlightBookingPage objFlightBookingPage = PageFactory.initElements(driver, ClearTripFlightBookingPage.class);
		String title = driver.getTitle();
		Assert.assertEquals("#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.", title);
		objFlightBookingPage.clkOneWayButton();
		test.log(LogStatus.INFO, "Clicked on Oneway Button");
		objFlightBookingPage.fillFromLocationFld("Hyderabad");
		test.log(LogStatus.INFO, "Entered Hyderabad in From Location Field");
		objFlightBookingPage.fillToLocationFld("Turany");
		test.log(LogStatus.INFO, "Entered Turany in To Location Field");
		objFlightBookingPage.clkDepartureDatFld();
		test.log(LogStatus.INFO, "Clicked on Departure Date Field");
		objFlightBookingPage.clkNextMonthBtn();
		test.log(LogStatus.INFO, "Clicked on Next Month Button");
		objFlightBookingPage.clkDateBtn();
		test.log(LogStatus.INFO, "Clicked on Date");
		objFlightBookingPage.clkSearchBtn();
		test.log(LogStatus.INFO, "Clicked on Serach Button");
		boolean searchSummary = objFlightBookingPage.iSDisplaySearchSummary();
		if(searchSummary == true) {
			test.log(LogStatus.PASS, "Test Case is Passed ");
		}else {
			test.log(LogStatus.FAIL, "Test Case is Failed ");
		}
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
