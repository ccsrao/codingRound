package com.testclasses;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.pageclasse.ClearTripHotelBookingPage;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.BaseClass;
import com.utilities.ConfigFilesUtility;

public class ClearTripHotelBookingTest extends BaseClass {
	private ConfigFilesUtility configFileObj;
	
	public ClearTripHotelBookingTest() throws Exception {
		configFileObj = new ConfigFilesUtility();
		configFileObj.loadPropertyFile("TestConfig.properties");
	}
	/* Launch the browser, Create the Extent Reports instance and Launch the url*/
	@BeforeClass
	public void setUP() throws Exception {
		reports = getExtentReport("ClearTripHotelBookingTest", "Hotel Booking Functionality");
		driver = launchBrowser("chrome", configFileObj);
		test.log(LogStatus.INFO, "Browser is Luanched ");
		test.log(LogStatus.INFO, "Url is loaded : " + configFileObj.getProperty("URL"));
	}
	
	/*Verify the Hotel Booking Functionality*/
	@Test
	public void test() throws Exception {
		try {
		ClearTripHotelBookingPage objHotelBookingPage = PageFactory.initElements(driver, ClearTripHotelBookingPage.class);
		String title = driver.getTitle();
		Assert.assertEquals("#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.", title);
		objHotelBookingPage.clkHotelsLnk();
		test.log(LogStatus.INFO, "Clicked on Hotels Link");
		objHotelBookingPage.fillLocationFld("Indiranagar, Bangalore");
		test.log(LogStatus.INFO, "Entered 'Indiranagar, Bangalore' in Location Filed");
		objHotelBookingPage.clkAutoSuggestionItem();
		test.log(LogStatus.INFO, "Selected required item in the list");
		objHotelBookingPage.selectTravellersList();
		test.log(LogStatus.INFO, "Selected Required Travellers");
		objHotelBookingPage.clkSearchHotelBtn();
		test.log(LogStatus.INFO, "Clicked on Search Button");
		boolean searchSummary = objHotelBookingPage.iSDisplaySearchSummary();
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
