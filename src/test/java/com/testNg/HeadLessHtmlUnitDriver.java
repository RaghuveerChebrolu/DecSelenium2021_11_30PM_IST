package com.testNg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.utility.Orep;
import com.utility.constants;
import com.utility.library_BusinessFunctions;

public class HeadLessHtmlUnitDriver extends library_BusinessFunctions {
	WebDriver unitDriver;
	@Test(priority = 0)
	public void ExecutingInHtmlUnidriver() throws IOException {
		ReadingPropertyFile();
		unitDriver = new HtmlUnitDriver(true);
		unitDriver.get(ObjProperties.getProperty("GmoOnline"));
		String GmoOnlineTitle = unitDriver.getTitle();
		System.out.println("GmoOnlineTitle:"+GmoOnlineTitle); 
		Assert.assertEquals(GmoOnlineTitle, "Welcome to Green Mountain Outpost");
	}
	
	@Test(priority = 1, dependsOnMethods = { "ExecutingInHtmlUnidriver" })
	public void EnterGMOnline() {
		System.out.println("inside EnterGMOnline");
		//extent_Test = extent_Reports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		// driver.findElement(By.name(Orep.submitbuttonGmoOnline)).click();
		unitDriver.findElement(By.name("bSubmit")).click();
		//String text = unitDriver.findElement(By.xpath("//h1[contains(text(),'OnLine Catalog')]")).getText();
		//library_BusinessFunctions.FindElementUsingHeadLess(unitDriver,Orep.submitbuttonGmoOnline).click();
		String text = library_BusinessFunctions.FindElementUsingHeadLess(unitDriver,Orep.TextGmoOnline).getText();
		Assert.assertEquals(text, "OnLine Catalog");
	}

	@Test(priority = 2, dependsOnMethods = { "EnterGMOnline" })
	public void OrderQtyHikingBoots() {
		System.out.println("inside OrderQtyHikingBoots");
		//extent_Test = extent_Reports.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		unitDriver.findElement(By.xpath("//input[@name='QTY_BOOTS']")).sendKeys(constants.QTY_BOOTS);
		unitDriver.findElement(By.name("bSubmit")).click();
		//waitForPageToLoad();
		String PlaceOrder = unitDriver.getTitle();
		System.out.println(PlaceOrder);
		Assert.assertEquals(PlaceOrder, ObjProperties.getProperty("PlaceOrderTitle"));
		String UnitPrice = unitDriver
				.findElement(By.xpath("//table[@cellpadding='4' and @cellspacing='1']/tbody/tr[2]/td[4]")).getText();
		System.out.println("UnitPrice_HikingBoots: " + UnitPrice);
		System.out.println(UnitPrice.length());
		String Unit_Price = UnitPrice.substring(2).trim();
		float Unit_Price_float = Float.parseFloat(Unit_Price);
		System.out.println("Unit_Price_float:" + Unit_Price_float);
		float TotalCalculatedFloatPrice = Unit_Price_float * 5;
		System.out.println("TotalCalculatedFloatPrice: " + TotalCalculatedFloatPrice);
		String TotalPrice = unitDriver
				.findElement(By.xpath("//table[@cellpadding='4' and @cellspacing='1']/tbody/tr[2]/td[5]")).getText();
		float TotalPrice_floatFromWebTable = Float.parseFloat(TotalPrice.substring(2).trim());
		System.out.println("TotalPrice_floatFromWebTable: " + TotalPrice_floatFromWebTable);
		Assert.assertEquals(TotalCalculatedFloatPrice, TotalPrice_floatFromWebTable);

	}
}
