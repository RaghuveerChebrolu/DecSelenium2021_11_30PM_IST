package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class library_BusinessFunctions {
	public WebDriver driver;
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
	
	public void LaunchBrower() {
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
}
