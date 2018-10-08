package com.pageclasse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.utilities.BaseClass;

public class ClearTripHotelBookingPage extends BaseClass {
	public WebDriver driver;

	/* Constructor*/
	public ClearTripHotelBookingPage(WebDriver driver) {
		this.driver = driver;
	}

	/* Hotels Link*/
	@FindBy(how = How.LINK_TEXT, using = "Hotels")
	private WebElement hotelsLink;
	public void clkHotelsLnk() {
		waitForExpectedElement(driver, hotelsLink);
		hotelsLink.click();
	}

	/* Location Text Filed*/
	@FindBy(how = How.ID, using = "Tags")
	private WebElement lcationTextBox;
	public void fillLocationFld(String textInput) {
		waitForExpectedElement(driver, lcationTextBox);
		lcationTextBox.clear();
		lcationTextBox.sendKeys(textInput);
	}
	/* Auto Suggestion*/
	@FindBy(how = How.ID, using = "ui-id-1")
	private WebElement selectAutoSuggestion;
	public void clkAutoSuggestionItem() throws Exception {
		selectOptionWithText("Indiranagar, Bangalore, Karnataka, India");
	}
	public void selectOptionWithText(String textToSelect) {
		try {
			waitForExpectedElement(driver, selectAutoSuggestion);

			List<WebElement> optionsToSelect = selectAutoSuggestion.findElements(By.tagName("li"));
			for(WebElement option : optionsToSelect){
		        if(option.getText().equals(textToSelect)) {
		        	System.out.println("Trying to select: " + textToSelect);
		            option.click();
		            break;
		        }
		    }
			
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}
	
	/* Travellers List*/
	@FindBy(how = How.ID, using = "travellersOnhome")
	private WebElement selectTravellers;
	public void selectTravellersList() {
		Select dropdown= new Select(selectTravellers);
		dropdown.selectByVisibleText("1 room, 1 adult");
	}
	
	/* Serach Button*/
	@FindBy(how = How.ID, using = "SearchHotelsButton")
	private WebElement searchHotelsButton;
	public void clkSearchHotelBtn() {
		waitForExpectedElement(driver, searchHotelsButton);
		searchHotelsButton.click();
	}
	/* Search Summary */
	@FindBy(how = How.ID, using = "srpHeaderLabel")
	private WebElement searchSummary;
	public boolean iSDisplaySearchSummary() throws Exception {
		waitForExpectedElement(driver, searchSummary);
		boolean searchSummaryElement = searchSummary.isDisplayed();
		return searchSummaryElement;
	}
}
