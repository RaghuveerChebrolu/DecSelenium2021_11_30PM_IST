package com.testNg;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNg2 {
	WebDriver driver;
	Properties ObjProperties = new Properties();
	
	@Test(priority=0)
	public void launchGmoOnlineApplication() {
		System.out.println("inside launchGmoOnlineApplication");
		String actual = driver.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, "Welcome to Green Mountain Outpost");
		
	}
	
	@Test(priority=1,dependsOnMethods={"launchGmoOnlineApplication"})
	public void EnterGMOnline(){
		System.out.println("inside EnterGMOnline");
		driver.findElement(By.name("bSubmit")).click();
		String text = driver.findElement(By.xpath("//h1[contains(text(),'OnLine Catalog')]")).getText();
		Assert.assertEquals(text, "OnLine Catalog");
	}
	
	@Test(priority=2,dependsOnMethods={"EnterGMOnline"})
	public void OrderQtyHikingBoots(){
		System.out.println("inside OrderQtyHikingBoots");
		driver.findElement(By.xpath("//input[@name='QTY_BOOTS']")).sendKeys("5");
		driver.findElement(By.name("bSubmit")).click();
		String PlaceOrder = driver.getTitle();
		System.out.println(PlaceOrder);
		Assert.assertEquals(PlaceOrder, "Place Order");
		String UnitPrice = driver.findElement(By.xpath("//table[@cellpadding='4' and @cellspacing='1']/tbody/tr[2]/td[4]")).getText();
		System.out.println("UnitPrice_HikingBoots: "+UnitPrice);
		System.out.println(UnitPrice.length());
		String Unit_Price = UnitPrice.substring(2).trim();
		float Unit_Price_float = Float.parseFloat(Unit_Price);
		System.out.println("Unit_Price_float:"+Unit_Price_float);
		float TotalCalculatedFloatPrice = Unit_Price_float *5 ;
		System.out.println("TotalCalculatedFloatPrice: "+TotalCalculatedFloatPrice);
		String TotalPrice = driver.findElement(By.xpath("//table[@cellpadding='4' and @cellspacing='1']/tbody/tr[2]/td[5]")).getText();
		float TotalPrice_floatFromWebTable =  Float.parseFloat(TotalPrice.substring(2).trim());
		System.out.println("TotalPrice_floatFromWebTable: "+TotalPrice_floatFromWebTable);
		Assert.assertEquals(TotalCalculatedFloatPrice, TotalPrice_floatFromWebTable);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
	}



	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//WebDriverManager.edgedriver().setup();
		//driver=new EdgeDriver();
		driver.get("http://demo.borland.com/gmopost/");
		driver.manage().window().maximize();
		//implicit wait : global waiting mechanism which is applicable for all webElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
