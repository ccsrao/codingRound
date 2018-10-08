package com.pageclasse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.BaseClass;

public class ClearTripSignInPage extends BaseClass{
	public WebDriver driver;
	
	/* Constructor*/
	public ClearTripSignInPage(WebDriver driver) {
		this.driver = driver;
	}

	/* Your Trips Link*/
	@FindBy(how = How.LINK_TEXT, using = "Your trips")	
	private WebElement	yourTripsLink;
	public void clkYourTripLnk() {
		waitForExpectedElement(driver, yourTripsLink);		
		yourTripsLink.click();
	}
	
	/* SignIn Link*/
	@FindBy(how = How.ID, using = "SignIn")	
	private WebElement	signInLink;
	public void clkSignInLnk() {
		waitForExpectedElement(driver, signInLink);		
		signInLink.click();
	}
	
	/* SignIn Button*/
	@FindBy(how = How.ID, using = "signInButton")	
	private WebElement	signInButton;
	public void clkSignInBtn() {
		driver.switchTo().frame("modal_window");
		waitForExpectedElement(driver, signInButton);		
		signInButton.click();
	}
	
	String displayAlert;
	/* Displaying Error Messages */
	public void displaySignInErrorMsg(ExtentTest test) {
		List<WebElement> displaySignInErrorMessages = driver.findElements(By.xpath("//div[@id='errors1']/ol/li"));
		for(WebElement displaySignInErrorMessage : displaySignInErrorMessages) {
			displayAlert = displaySignInErrorMessage.getText();
			System.out.println(displayAlert);
			if (displayAlert.contains("Your username is a required field")) {
				test.log(LogStatus.PASS, "Displaying the alert is : " + displayAlert);
			}else if(displayAlert.contains("Your account password is a required field")) {
				test.log(LogStatus.PASS, "Displaying the alert is : " + displayAlert);	
			}else {
				test.log(LogStatus.FAIL, "Displaying the alert is : " + displayAlert);
			}
		}
	}
}
