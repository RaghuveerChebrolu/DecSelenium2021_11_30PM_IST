package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library_BusinessFunctions {
	public static WebDriver driver;
	//public static HtmlUnitDriver unitDriver ;
	public static Properties ObjProperties = new Properties();
	
	public static ExtentHtmlReporter extent_HtmlReporter;
	public static ExtentReports extent_Reports;
	public static ExtentTest extent_Test;
	
/*	ExtentHtmlReporter    : responsible for look and feel of the report ,we can specify the report name , 
	document title , theme of the report 

	ExtentReports : used to create entries in your report , create test cases in report , who executed 
	the test case, environment name , browser 

	ExtentTest : update pass fail and skips and logs  the test cases results*/

	public static void ReadingPropertyFile() throws IOException {
		System.out.println(System.getProperty("user.dir"));
		File objFile = new File(System.getProperty("user.dir")+"//src//test//resources/configProperty.properties");
		try {
			FileInputStream objFileInputStream = new FileInputStream(objFile);
			
			ObjProperties.load(objFileInputStream);
			ObjProperties.getProperty("browser");
			System.out.println("browser value from property file:"+ObjProperties.getProperty("browser"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void StartExtentReport(){
		extent_HtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//test-output//ExtentReport4.html");
		extent_HtmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		extent_HtmlReporter.config().setReportName("Functional and Regression Testing"); // Name of the report
		extent_HtmlReporter.config().setTheme(Theme.STANDARD);
		
		extent_Reports = new ExtentReports();
		extent_Reports.attachReporter(extent_HtmlReporter);
		
		// Passing General information
		extent_Reports.setSystemInfo("Host name", "Raghu laptop");
		extent_Reports.setSystemInfo("Environemnt", "QA");
		extent_Reports.setSystemInfo("user", "Raghu");
		
	}
	public static void LaunchBrower() {
		String Browser = ObjProperties.getProperty("browser");
		switch(Browser){
		case "chrome":
			WebDriverManager.chromedriver().setup(); 
			//driver = new ChromeDriver();
			ChromeOptions objChromeOptions = new ChromeOptions();
			objChromeOptions.setAcceptInsecureCerts(true);
			//driver= new ChromeDriver(objChromeOptions);
			Map<String,Object> chromePrefs = new HashMap<String,Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", false);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
			objChromeOptions.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(objChromeOptions);
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		break;
		default:
			//driver=new HtmlUnitDriver();	
		}
		driver.get(ObjProperties.getProperty("GmoOnline"));
		driver.manage().window().maximize();
		//implicit wait : global waiting mechanism which is applicable for all webElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static WebElement FindElementUsingHeadLess(WebDriver unitDriver,String OrepLocator){
		By search=null;
		System.out.println(OrepLocator); 
		String locator = OrepLocator.split("&")[0];
		String value = OrepLocator.split("&")[1];
		System.out.println(locator);
		System.out.println(value);
		if(locator.equals("name")){
			search=By.name(value);
		}else if (locator.equals("id")){
			search=By.id(value);
		}else if (locator.equals("xpath")){
			search=By.xpath(value);
		}else if (locator.equals("tagName")){
			search=By.tagName(value);
		}else if (locator.equals("className")){
			search=By.className(value);
		}else if (locator.equals("partialLinkText")){
			search=By.partialLinkText(value);
		}else if (locator.equals("cssSelector")){
			search=By.cssSelector(value);
		}else if (locator.equals("linkText")){
			search=By.linkText(value);
		}
		return unitDriver.findElement(search);
	}
	
	public static WebElement FindElement(String OrepLocator){
		By search=null;
		System.out.println(OrepLocator); 
		String locator = OrepLocator.split("&")[0];
		String value = OrepLocator.split("&")[1];
		System.out.println(locator);
		System.out.println(value);
		if(locator.equals("name")){
			search=By.name(value);
		}else if (locator.equals("id")){
			search=By.id(value);
		}else if (locator.equals("xpath")){
			search=By.xpath(value);
		}else if (locator.equals("tagName")){
			search=By.tagName(value);
		}else if (locator.equals("className")){
			search=By.className(value);
		}else if (locator.equals("partialLinkText")){
			search=By.partialLinkText(value);
		}else if (locator.equals("cssSelector")){
			search=By.cssSelector(value);
		}else if (locator.equals("linkText")){
			search=By.linkText(value);
		}
		return driver.findElement(search);
	}
	
	
	public static List<WebElement> FindElements(String OrepLocator){
		By search=null;
		System.out.println(OrepLocator); 
		String locator = OrepLocator.split("&")[0];
		String value = OrepLocator.split("&")[1];
		System.out.println(locator);
		System.out.println(value);
		if(locator.equals("name")){
			search=By.name(value);
		}else if (locator.equals("id")){
			search=By.id(value);
		}else if (locator.equals("xpath")){
			search=By.xpath(value);
		}else if (locator.equals("tagName")){
			search=By.tagName(value);
		}else if (locator.equals("className")){
			search=By.className(value);
		}else if (locator.equals("partialLinkText")){
			search=By.partialLinkText(value);
		}else if (locator.equals("cssSelector")){
			search=By.cssSelector(value);
		}else if (locator.equals("linkText")){
			search=By.linkText(value);
		}
		return driver.findElements(search);
	}
	
	public static void waitForPageToLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		// explicit wait -> Applicable for one webEllement
		WebDriverWait wait = new WebDriverWait(driver, 60);//60 seconds 
		wait.until(pageLoadCondition);
	}
	
	public static void Screenshot(WebDriver driver){
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
		File destFile = new File(System.getProperty("user.dir")+"//ScreenShots//"+dateName+ "captured.png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String takescreeshot(WebDriver driver) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String dateName = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
		String dateName = new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//screenshots//" + dateName
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	public static String takescreeshot(WebDriver driver,String testCaseName) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//String dateName = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
		String dateName = new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//screenshots//" + dateName +testCaseName
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	public static void scrollDown(int Y){
		JavascriptExecutor js = (JavascriptExecutor)driver;//downcasting
		js.executeScript("window.scrollBy(0,"+Y+")");
	}
	
	public static void SelectValueFromDropDown(List<WebElement> AllItems, String DropDownValue) {
		int numberOFDropDownItems = AllItems.size();
		for (int i=0 ; i<=numberOFDropDownItems;i++){
			String DropDownFieldText = AllItems.get(i).getText();
			System.out.println("DropDownFieldText:"+DropDownFieldText);
			if(DropDownFieldText.equals(DropDownValue)){
				AllItems.get(i).click();
				break;
			}
		}
		
	}
}
