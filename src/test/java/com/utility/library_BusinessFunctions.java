package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library_BusinessFunctions {
	public static WebDriver driver;
	public static Properties ObjProperties = new Properties();

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
	
	public static void LaunchBrower() {
		String Browser = ObjProperties.getProperty("browser");
		switch(Browser){
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
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
	
	public static void scrollDown(int Y){
		JavascriptExecutor js = (JavascriptExecutor)driver;//downcasting
		js.executeScript("window.scrollBy(0,"+Y+")");
	}
}
