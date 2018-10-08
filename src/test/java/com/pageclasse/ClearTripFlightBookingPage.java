package com.pageclasse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.utilities.BaseClass;

public class ClearTripFlightBookingPage extends BaseClass {
	public WebDriver driver;

	/* Constructor */
	public ClearTripFlightBookingPage(WebDriver driver) {
		this.driver = driver;
	}

	/* Oneway Button */
	@FindBy(how = How.ID, using = "OneWay")
	private WebElement oneWayButton;

	public void clkOneWayButton() {
		waitForExpectedElement(driver, oneWayButton);
		oneWayButton.click();
	}

	/* From Location Filed */
	@FindBy(how = How.ID, using = "FromTag")
	private WebElement fromLocationInput;

	public void fillFromLocationFld(String fromLocationText) {
		waitForExpectedElement(driver, fromLocationInput);
		fromLocationInput.clear();
		fromLocationInput.sendKeys(fromLocationText);
	}

	/* To Location Field */
	@FindBy(how = How.ID, using = "ToTag")
	private WebElement toLocationInput;

	public void fillToLocationFld(String toLocationText) {
		waitForExpectedElement(driver, toLocationInput);
		toLocationInput.clear();
		toLocationInput.sendKeys(toLocationText);
	}

	/* Departure Filed */
	@FindBy(how = How.ID, using = "DepartDate")
	private WebElement departuredateField;

	public void clkDepartureDatFld() {
		waitForExpectedElement(driver, departuredateField);
		departuredateField.click();
	}

	/* Next Month Button */
	@FindBy(how = How.XPATH, using = "//a[@class='nextMonth ']")
	private WebElement nextMonthButton;

	public void clkNextMonthBtn() {
		waitForExpectedElement(driver, nextMonthButton);
		nextMonthButton.click();
	}

	/* Date Selection */
	@FindBy(how = How.ID, using = "ui-datepicker-div")
	private WebElement dateSelectButton;

	public void clkDateBtn() {
		List<WebElement> columns = dateSelectButton.findElements(By.tagName("td"));
		for (WebElement cell : columns) {
			// Select 13th Date
			if (cell.getText().equals("13")) {
				cell.findElement(By.linkText("13")).click();
				break;
			}
		}
	}

	/* Search Button */
	@FindBy(how = How.ID, using = "SearchBtn")
	private WebElement searchButton;

	public void clkSearchBtn() throws Exception {
		waitForExpectedElement(driver, searchButton);
		searchButton.click();
	}

	/* Search Summary */
	@FindBy(how = How.XPATH, using = "//div[@class='searchSummary']")
	private WebElement searchSummary;

	public boolean iSDisplaySearchSummary() throws Exception {
		waitForExpectedElement(driver, searchSummary);
		boolean searchSummaryElement = searchSummary.isDisplayed();
		return searchSummaryElement;
	}
}
