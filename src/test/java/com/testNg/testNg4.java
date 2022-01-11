package com.testNg;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utility.Orep;
import com.utility.constants;
import com.utility.library_BusinessFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class testNg4 extends library_BusinessFunctions{
	
	
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
//		driver.findElement(By.name(Orep.submitbuttonGmoOnline)).click();
		library_BusinessFunctions.FindElement(Orep.submitbuttonGmoOnline).click();
		String text = library_BusinessFunctions.FindElement(Orep.TextGmoOnline).getText();
		Assert.assertEquals(text, "OnLine Catalog");
	}
	
	@Test(priority=2,dependsOnMethods={"EnterGMOnline"})
	public void OrderQtyHikingBoots(){
		System.out.println("inside OrderQtyHikingBoots");
		driver.findElement(By.xpath("//input[@name='QTY_BOOTS']")).sendKeys(constants.QTY_BOOTS);
		driver.findElement(By.name("bSubmit")).click();
		waitForPageToLoad();
		String PlaceOrder = driver.getTitle();
		System.out.println(PlaceOrder);
		Assert.assertEquals(PlaceOrder, ObjProperties.getProperty("PlaceOrderTitle"));
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

	@Test(priority=3)
	public void ValidatingAlerts() throws InterruptedException{
		System.out.println("ValidatingAlerts");
		driver.navigate().to(ObjProperties.getProperty("AlertURL"));
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert ObjAlert1 = driver.switchTo().alert();
		String Alert1 = ObjAlert1.getText();
		System.out.println("Alert1:"+Alert1);
		//Assert.assertEquals(Alert1, ObjProperties.getProperty("Alert1Text"));
		SoftAssert SoftAssertion =  new SoftAssert();
		SoftAssertion.assertEquals(Alert1, ObjProperties.getProperty("Alert1Text"));
		ObjAlert1.accept();
		
		driver.findElement(By.id("timerAlertButton")).click();
		Thread.sleep(5000);
		Alert ObjAlert2 = driver.switchTo().alert();
		String Alert2 = ObjAlert2.getText();
		System.out.println("Alert2:"+Alert2);
		Assert.assertEquals(Alert2, ObjProperties.getProperty("Alert2Text"));
		ObjAlert1.accept();
		
		driver.findElement(By.id("confirmButton")).click();
		
		Alert ObjAlert3 = driver.switchTo().alert();
		ObjAlert3.dismiss();
		String AlertResult = driver.findElement(By.id("confirmResult")).getText();
		SoftAssertion.assertEquals(AlertResult, ObjProperties.getProperty("Alert3Result"));
		
		driver.findElement(By.id("promtButton")).click();
		Alert ObjAlert4 = driver.switchTo().alert();
		ObjAlert4.sendKeys("HI How are You?");
		ObjAlert4.accept();
		String promptResult = driver.findElement(By.id("promptResult")).getText();
		Assert.assertEquals(promptResult, ObjProperties.getProperty("Alert4Result"));
		SoftAssertion.assertAll();
		
	}
	
	@Test(priority=4)
	public void HandlingFrames() throws InterruptedException{
		System.out.println("inside HandlingFrames");
		driver.navigate().to(ObjProperties.getProperty("FramesURL"));
		waitForPageToLoad();
		//WebElement singleFrame = driver.findElement(By.xpath("//iframe[@id='singleframe']"));
		driver.switchTo().frame("singleframe");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Hi How are you");
		driver.switchTo().defaultContent();/////VVI : 
		
		driver.findElement(By.xpath("//a[@href='#Multiple']")).click();
		
		WebElement Multipleframe = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(Multipleframe);
		
		WebElement Singleframe = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(Singleframe);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("single frame with in multiple frame");
		driver.switchTo().defaultContent();
		
		driver.navigate().back();
		Thread.sleep(2000);
		driver.navigate().forward();
	}
	
	@Test(priority=5)
	public void ValidateHandlingWindows(){
		System.out.println("inside ValidateHandlingWindows");
		driver.navigate().to(ObjProperties.getProperty("nxtgenaiacademyURL"));
		waitForPageToLoad();
		library_BusinessFunctions.FindElement(Orep.NxtGenNewBriwserWindow).click();
		Set<String> AllWindows=driver.getWindowHandles();
		
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
		LaunchBrower();
	}



	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		try {
			library_BusinessFunctions.ReadingPropertyFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}
