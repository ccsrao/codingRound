package com.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {
	public WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	public static String reportsPath = projectPath + File.separator + "WebReports";
	public String chromeDriverPath = projectPath + File.separator + "Resources" + File.separator + "chromedriver.exe";
	public String geckoFireFoxDriverPath = projectPath + File.separator + "Resources" + File.separator + "geckodriver.exe";
	public String iEDriverPath = projectPath + File.separator + File.separator + "Resources" + File.separator + "IEDriverServer.exe";
	public ExtentReports reports;
	public ExtentTest test;
	static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
	static Date dt = new Date();
	/* Explicit wait*/
	
	public static WebElement waitForExpectedElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/* Launch the browser and url*/
	@SuppressWarnings("deprecation")
	public WebDriver launchBrowser(String browserName, ConfigFilesUtility configFileObj) {
		if (!isWindows()) {
			if (isSolaris() || isUnix()) {
				chromeDriverPath = chromeDriverPath.replace(".exe", "");
				geckoFireFoxDriverPath = geckoFireFoxDriverPath.replace(".exe", "");
			} else if (isMac()) {
				chromeDriverPath = chromeDriverPath.replace("chromedriver.exe", "macChromeDriver");
				geckoFireFoxDriverPath = geckoFireFoxDriverPath.replace(".exe", "macGeckodriver");
			}
		}
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
			System.out.println("Chrome Browser is Launched");
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", geckoFireFoxDriverPath);
			driver = new FirefoxDriver();
			System.out.println("FireFox Browser is Launched");
		} else if (browserName.equalsIgnoreCase("safari")) {	
			//Note : Should AllowRemoteAutomation in safari browser DeveloperMenu
			//Directions -- > launchSafariBrowser --> Preferences --> Advanced Tab --> 
			//Show Developer Menu --> Click on DevloperMenu --> Enable AllowRemoteAutomation 
			driver = new SafariDriver();
			} else if (browserName.equalsIgnoreCase("ie")) {
				if (!isWindows()) {
					System.out.println("IE Browser not supported for this OS.");
					return null;
				}
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				System.setProperty("webdriver.ie.driver", iEDriverPath);
				driver = new InternetExplorerDriver(capabilities);
				System.out.println("IE Browser is Launched");
			}
		driver.manage().deleteAllCookies();
		driver.get(configFileObj.getProperty("URL"));
		driver.manage().window().maximize();
		return driver;
	}
	
	/* Getting OS name*/
	private String OS = System.getProperty("os.name").toLowerCase();
	/* Getting Windows OS name*/
	public boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	
	/* Getting Mac OS name*/
	public boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
	
	/* Getting Unix OS name*/
	public boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
	}

	/* Getting Solaris OS name*/
	public boolean isSolaris() {
		return (OS.indexOf("sunos") >= 0);
	}
	/* Extent Reports */
	public ExtentReports getExtentReport(String reportsFileName, String testName) {
			reports = new ExtentReports(reportsPath + File.separator + reportsFileName + dateFormat.format(dt) + "_Report.html" , false);
			test = reports.startTest(testName);
		    return reports;
	}
	
	/* End the test in extent report, flush the data and quit the browser*/
	public void tearDownM() {
		reports.endTest(test);
		reports.flush();
		driver.quit();
	}
	
}
