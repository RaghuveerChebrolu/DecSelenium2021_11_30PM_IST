package com.testNg;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.utility.Orep;
import com.utility.constants;

import com.utility.library_BusinessFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNg4 extends library_BusinessFunctions {

	public static int PageNumber = 1;

	@Test(priority = 0)
	public void launchGmoOnlineApplication() {
		System.out.println("inside launchGmoOnlineApplication");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		String actual = driver.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, "Welcome to Green Mountain Outpost");
	}

	@Test(priority = 1, dependsOnMethods = { "launchGmoOnlineApplication" })
	public void EnterGMOnline() {
		System.out.println("inside EnterGMOnline");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		// driver.findElement(By.name(Orep.submitbuttonGmoOnline)).click();
		library_BusinessFunctions.FindElement(Orep.submitbuttonGmoOnline).click();
		String text = library_BusinessFunctions.FindElement(Orep.TextGmoOnline).getText();
		Assert.assertEquals(text, "OnLine Catalog");
	}

	@Test(priority = 2, dependsOnMethods = { "EnterGMOnline" })
	public void OrderQtyHikingBoots() {
		System.out.println("inside OrderQtyHikingBoots");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.findElement(By.xpath("//input[@name='QTY_BOOTS']")).sendKeys(constants.QTY_BOOTS);
		driver.findElement(By.name("bSubmit")).click();
		waitForPageToLoad();
		String PlaceOrder = driver.getTitle();
		System.out.println(PlaceOrder);
		Assert.assertEquals(PlaceOrder, ObjProperties.getProperty("PlaceOrderTitle"));
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

	@Test(priority = 3)
	public void ValidatingAlerts() throws InterruptedException {
		System.out.println("ValidatingAlerts");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("AlertURL"));
		waitForPageToLoad();
		driver.findElement(By.id("alertButton")).click();
		Alert ObjAlert1 = driver.switchTo().alert();
		String Alert1 = ObjAlert1.getText();
		System.out.println("Alert1:" + Alert1);
		// Assert.assertEquals(Alert1, ObjProperties.getProperty("Alert1Text"));
		SoftAssert SoftAssertion = new SoftAssert();
		SoftAssertion.assertEquals(Alert1, ObjProperties.getProperty("Alert1Text"));
		ObjAlert1.accept();

		driver.findElement(By.id("timerAlertButton")).click();
		Thread.sleep(5000);
		Alert ObjAlert2 = driver.switchTo().alert();
		String Alert2 = ObjAlert2.getText();
		System.out.println("Alert2:" + Alert2);
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

	@Test(priority = 4)
	public void HandlingFrames() throws InterruptedException {
		System.out.println("inside HandlingFrames");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("FramesURL"));
		waitForPageToLoad();
		// WebElement singleFrame =
		// driver.findElement(By.xpath("//iframe[@id='singleframe']"));
		driver.switchTo().frame("singleframe");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Hi How are you");
		driver.switchTo().defaultContent();///// VVI :

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

	@Test(priority = 5)
	public void ValidateHandlingWindows() {
		System.out.println("inside ValidateHandlingWindows");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("nxtgenaiacademyURL"));
		waitForPageToLoad();
		String ParentWindowHandle = driver.getWindowHandle();
		library_BusinessFunctions.FindElement(Orep.NxtGenNewBriwserWindow).click();
		Set<String> AllWindows = driver.getWindowHandles();
		System.out.println("windows count : " + AllWindows.size());
		for (String IndividulaWindow : AllWindows) {
			driver.switchTo().window(IndividulaWindow);
			String title = driver.getTitle();
			System.out.println("title:" + title);
			if (title.equals(ObjProperties.getProperty("newBrowserWindowTitle"))) {
				// driver.manage().window().maximize();
				library_BusinessFunctions.FindElement(Orep.newBrowserWindowMenu).click();
				// JavascriptExecutor js =
				// (JavascriptExecutor)driver;//downcasting
				// js.executeScript("window.scrollBy(0, 1000)");
				scrollDown(1000);
				Boolean flag = library_BusinessFunctions.FindElement(Orep.newBrowserWindowSeleniumWebdriver)
						.isEnabled();
				System.out.println("flag:" + flag);
				// js.executeScript("window.scrollBy(0,1000)");//To scroll
				// vertically
				// Down by 1000 pixels
				// js.executeScript("window.scrollBy(0,-500)");//To scroll
				// vertically Up
				// by 500 pixels
				// js.executeScript("window.scrollBy(500,0)");//To scroll
				// horizontally
				// right by 500 pixels
				// js.executeScript("window.scrollBy(-500,0)");//To scroll
				// horizontally
				// left by 500 pixels

				/*
				 * WebElement element =
				 * library.FindElement(ObjRepository.DoubleCickFrame);
				 * js.executeScript("arguments[0].scrollIntoView();", element);
				 */
			} else if (title.equals("Demo Site – Multiple Windows – NxtGen A.I Academy")) {
				String text_numberOfivisits = library_BusinessFunctions.FindElement(Orep.newBrowserWindowCountOfVisits)
						.getText();
				System.out.println("text_numberOfivisits:" + text_numberOfivisits);
				String text_numberOfivisitsInnerHTML = library_BusinessFunctions
						.FindElement(Orep.newBrowserWindowCountOfVisits).getAttribute("innerHTML");
				System.out.println("text_numberOfivisitsInnerHTML:" + text_numberOfivisitsInnerHTML);
				String text_numberOfivisitsOuterHTML = library_BusinessFunctions
						.FindElement(Orep.newBrowserWindowCountOfVisits).getAttribute("outerHTML");
				System.out.println("text_numberOfivisitsOuterHTML:" + text_numberOfivisitsOuterHTML);
			}
		}
		driver.switchTo().window(ParentWindowHandle);

	}

	@Test(priority = 6)
	public void ValidateMouseOperations() throws InterruptedException {
		System.out.println("inside ValidateMouseOperations");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("mouseOpeartionRightClick"));
		waitForPageToLoad();
		Actions Obj = new Actions(driver);
		// right click
		WebElement Rightclick = library_BusinessFunctions.FindElement(Orep.MouseOperationRightClick);
		Obj.contextClick(Rightclick).build().perform();
		Thread.sleep(4000);
		// library_BusinessFunctions.FindElement(Orep.MouseOperationRightclickCopyAction).click();
		WebElement copyAction = library_BusinessFunctions.FindElement(Orep.MouseOperationRightclickCopyAction);
		copyAction.click();
		Thread.sleep(4000);
		Alert objAlert = driver.switchTo().alert();
		String TextPopUpAlert = objAlert.getText();
		System.out.println("TextPopUpAlert:" + TextPopUpAlert);
		Assert.assertEquals(TextPopUpAlert, ObjProperties.getProperty("mouseOpeartionRightclickCopyActionText"));
		objAlert.accept();
		Screenshot(driver);

		// double click
		driver.navigate().to(ObjProperties.getProperty("mouseOpeartionDoubleClick"));
		waitForPageToLoad();
		WebElement frameElement = library_BusinessFunctions.FindElement(Orep.MouseOpeartionFrame);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", frameElement);
		driver.switchTo().frame(frameElement);
		WebElement doubleClickBox = library_BusinessFunctions.FindElement(Orep.MouseOpeartionDoubeClickBox);
		Obj.doubleClick(doubleClickBox).build().perform();

		Color BackGroundColor = Color.fromString(library_BusinessFunctions.FindElement(Orep.MouseOpeartionDoubeClickBox)
				.getCssValue("background-color"));
		System.out.println("BackGroundColor:" + BackGroundColor);
		String ActualBackGroundColor = BackGroundColor.asRgba();
		System.out.println("ActualBackGroundColor:" + ActualBackGroundColor);
		Assert.assertEquals(ActualBackGroundColor, "rgba(255, 255, 0, 1)");
		driver.switchTo().defaultContent();
		System.out.println("came out of frame of doube click");
		Screenshot(driver);

		// Drag and drop
		System.out.println("inside ValidateMouseOperations drag and drop");
		driver.navigate().to(ObjProperties.getProperty("mouseOperationDragAndDrop"));
		waitForPageToLoad();
		WebElement frameElement2 = library_BusinessFunctions.FindElement(Orep.MouseOpeartionFrame);
		driver.switchTo().frame(frameElement2);
		WebElement drag = library_BusinessFunctions.FindElement(Orep.MouseOpeartionDrag);
		WebElement drop = library_BusinessFunctions.FindElement(Orep.MouseOpeartionDrop);
		// Obj.dragAndDrop(drag, drop).build().perform();
		Obj.clickAndHold(drag);
		Obj.moveToElement(drop).build().perform();
		library_BusinessFunctions.Screenshot(driver);

	}

	@Test(priority = 7)
	public void HandlingWebTable() {
		System.out.println("inside HandlingMouseOpeartions");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("WebTableURL"));
		waitForPageToLoad();
		library_BusinessFunctions.scrollDown(constants.WebtableScrllDown);
		List<WebElement> AllLastNames = library_BusinessFunctions.FindElements(Orep.WebTableLastNames);
		int rowCount = AllLastNames.size();
		for (int page = 1; page <= 6; page++) {
			for (int i = 1; i <= AllLastNames.size(); i++) {
				String LastName = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[3]"))
						.getText();
				if (LastName.equals(constants.WebtableInputLastName)) {
					String FistName = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[2]"))
							.getText();
					String Position = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[4]"))
							.getText();
					String Office = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[5]"))
							.getText();
					String Salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr[" + i + "]/td[7]"))
							.getText();
					System.out.println("FistName: " + FistName + " Position: " + Position + " Office: " + Office
							+ " Salary: " + Salary);
					break;
				} else if (i == rowCount) {
					PageNumber++;
					driver.findElement(By.xpath("//div[@id='example_paginate']/span/a[" + PageNumber + "]")).click();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}

	@Test(priority = 8)
	public void FileUpload() throws AWTException, InterruptedException {
		System.out.println("inside FileUpload");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("FileUpload"));
		waitForPageToLoad();
		Thread.sleep(8000);
		Actions obj = new Actions(driver);
		WebElement element = library_BusinessFunctions.FindElement(Orep.FileUploadBrowseButton);
		obj.click(element).build().perform();

		StringSelection objStringSelection = new StringSelection(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Sample.jpg");
		Clipboard objClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		objClipboard.setContents(objStringSelection, null);
		try {
			Transferable objTransferable = objClipboard.getContents(null);
			if (objTransferable.isDataFlavorSupported(DataFlavor.stringFlavor))
				System.out.println(objTransferable.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Robot objRobot = new Robot();
		objRobot.delay(250);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		objRobot.keyPress(KeyEvent.VK_CONTROL);
		objRobot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		objRobot.keyRelease(KeyEvent.VK_V);
		objRobot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(4000);
		objRobot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		objRobot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);

	}

	@Test(priority=9)
	public void FileDownload() throws InterruptedException {
		System.out.println("inside FileDownload");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("FileDownload"));
		waitForPageToLoad();
		Actions obj = new Actions(driver);
		WebElement Element = library_BusinessFunctions.FindElement(Orep.FileDownload100kb);
		obj.click(Element).build().perform();
		Thread.sleep(30000);
		File objFile = new File(System.getProperty("user.dir"));
		File[] AllListofFiles = objFile.listFiles();
		boolean fileFound = false;
		File Obj_File = null;
		for (File individualFile : AllListofFiles) {
			String FileName = individualFile.getName();
			System.out.println("FileName:" + FileName);
			if (FileName.contains("file-sample")) {
				fileFound = true;
				Obj_File = new File(FileName);
			}
		}
		Assert.assertTrue(fileFound, "downloaded file is not found");
		Obj_File.deleteOnExit();
	}
	
	
	@Test(priority=10)
	public void ValidateBrokenLinks(){
		System.out.println("inside ValidateBrokenLinks");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("Links"));
		List<WebElement> AllLinks = library_BusinessFunctions.FindElements(Orep.Links);
		System.out.println("Below are the links avaialable");
		for(int i=1;i<AllLinks.size();i++){
			WebElement link = AllLinks.get(i);
			String individualLink = link.getAttribute("href");
			System.out.println(individualLink);
			try {
				validatingLinks(individualLink);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test(priority=11)
	public void ValidateBrokenLinksGmoOnline(){
		System.out.println("inside ValidateBrokenLinksGmoOnline");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		EnterGMOnline();
		//driver.navigate().to(ObjProperties.getProperty("Links"));
		List<WebElement> AllLinks = library_BusinessFunctions.FindElements(Orep.Links);
		System.out.println("Below are the links avaialable inside Gmo Online");
		for(int i=0;i<AllLinks.size();i++){
			System.out.println("TotalLinks:"+AllLinks.size());
			WebElement link = AllLinks.get(i);
			String individualLink = link.getAttribute("href");
			System.out.println(i +" "+individualLink );
			try {
				if(i!=6){
				validatingLinks(individualLink);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	


	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			extent_Test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to
																						// add
																						// name
																						// in
																						// extent
																						// report
			extent_Test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to
																							// add
																							// error/exception
																							// in
																							// extent
																							// report
			String screenshotPath = library_BusinessFunctions.takescreeshot(driver, result.getName());
			extent_Test.addScreenCaptureFromPath(screenshotPath);// adding
																	// screen
																	// shot to
																	// extent
																	// report
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extent_Test.log(Status.PASS, "TEST CASE Passed IS " + result.getName());
			String screenshotPath = library_BusinessFunctions.takescreeshot(driver, result.getName());
			extent_Test.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SKIP) {
			extent_Test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		System.out.println("inside afterMethod");

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		StartExtentReport();
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
		extent_Reports.flush();
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
