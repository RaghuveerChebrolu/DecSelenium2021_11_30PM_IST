package com.testNg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utility.Orep;
import com.utility.constants;
import com.utility.library_BusinessFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadLessChromeBroswer {
	WebDriver driver;
	@Test(priority = 0)
	public void ExecutingInChromedriverHeadLess() throws IOException {
		library_BusinessFunctions.ReadingPropertyFile();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.get(library_BusinessFunctions.ObjProperties.getProperty("GmoOnline"));
		String GmoOnlineTitle = driver.getTitle();
		System.out.println("GmoOnlineTitle:"+GmoOnlineTitle); 
		Assert.assertEquals(GmoOnlineTitle, "Welcome to Green Mountain Outpost");
	}
	
	@Test(priority = 1, dependsOnMethods = { "ExecutingInChromedriverHeadLess" })
	public void EnterGMOnline() {
		System.out.println("inside EnterGMOnline");
		//extent_Test = extent_Reports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		// driver.findElement(By.name(Orep.submitbuttonGmoOnline)).click();
		driver.findElement(By.name("bSubmit")).click();
		//String text = driver.findElement(By.xpath("//h1[contains(text(),'OnLine Catalog')]")).getText();
		//library_BusinessFunctions.FindElementUsingHeadLess(driver,Orep.submitbuttonGmoOnline).click();
		String text = library_BusinessFunctions.FindElementUsingHeadLess(driver,Orep.TextGmoOnline).getText();
		Assert.assertEquals(text, "OnLine Catalog");
	}

	@Test(priority = 2, dependsOnMethods = { "EnterGMOnline" })
	public void OrderQtyHikingBoots() {
		System.out.println("inside OrderQtyHikingBoots");
		//extent_Test = extent_Reports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.xpath("//input[@name='QTY_BOOTS']")).sendKeys(constants.QTY_BOOTS);
		driver.findElement(By.name("bSubmit")).click();
		//waitForPageToLoad();
		String PlaceOrder = driver.getTitle();
		System.out.println(PlaceOrder);
		Assert.assertEquals(PlaceOrder, library_BusinessFunctions.ObjProperties.getProperty("PlaceOrderTitle"));
		String UnitPrice = driver
				.findElement(By.xpath("//table[@cellpadding='4' and @cellspacing='1']/tbody/tr[2]/td[4]")).getText();
		System.out.println("UnitPrice_HikingBoots: " + UnitPrice);
		System.out.println(UnitPrice.length());
		String Unit_Price = UnitPrice.substring(2).trim();
		float Unit_Price_float = Float.parseFloat(Unit_Price);
		System.out.println("Unit_Price_float:" + Unit_Price_float);
		float TotalCalculatedFloatPrice = Unit_Price_float * 5;
		System.out.println("TotalCalculatedFloatPrice: " + TotalCalculatedFloatPrice);
		String TotalPrice = driver
				.findElement(By.xpath("//table[@cellpadding='4' and @cellspacing='1']/tbody/tr[2]/td[5]")).getText();
		float TotalPrice_floatFromWebTable = Float.parseFloat(TotalPrice.substring(2).trim());
		System.out.println("TotalPrice_floatFromWebTable: " + TotalPrice_floatFromWebTable);
		Assert.assertEquals(TotalCalculatedFloatPrice, TotalPrice_floatFromWebTable);

	}
}
