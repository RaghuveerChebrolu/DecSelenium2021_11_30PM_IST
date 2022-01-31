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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

public class testNg5DataDriven extends library_BusinessFunctions {
	HashMap<String, String> testDataMap = new HashMap<String, String>();
	

	@Test
	public void DataDriven() throws InterruptedException, IOException {
		System.out.println("inside DataDriven");
		extent_Test = extent_Reports.createTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		driver.navigate().to(ObjProperties.getProperty("AutomationRegister"));
		waitForPageToLoad();
		try {
			File objFile = new File(System.getProperty("user.dir") + "//src//test/resources//AutomationDemoSIte.xlsx");
			FileInputStream objFileInput = new FileInputStream(objFile);
			XSSFWorkbook objXSSFWorkBook = new XSSFWorkbook(objFileInput);
			XSSFSheet ObjXSSFSheet = objXSSFWorkBook.getSheet("TestData");
			int Rows = ObjXSSFSheet.getLastRowNum();
			System.out.println("Rows:" + Rows);
			for (int RowNumber = 1; RowNumber <= Rows; RowNumber++) {
				testDataMap = ReadExcelFile(ObjXSSFSheet, RowNumber);
				/*for (Map.Entry m : testDataMap.entrySet()) {
					System.out.println(m.getKey() + " " + m.getValue());
				}*/
				System.out.println("---------------");
				System.out.println(testDataMap.get("RunMode"));
				System.out.println(testDataMap.get("TestCaseName"));
				System.out.println(testDataMap.get("FirstName"));
				System.out.println(testDataMap.get("LastName"));
				System.out.println(testDataMap.get("Address"));
				System.out.println(testDataMap.get("Email Address"));
				System.out.println(testDataMap.get("Gender"));
				System.out.println(testDataMap.get("Hobbies"));
				System.out.println(testDataMap.get("Languages"));
				System.out.println(testDataMap.get("Skills"));
				System.out.println(testDataMap.get("DOB_MM"));
				System.out.println(testDataMap.get("Password"));
				System.out.println(testDataMap.get("confirm Password"));
				System.out.println("**************");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public HashMap<String, String> ReadExcelFile(XSSFSheet objXSSFSheet, int rowNumber) {
		// TODO Auto-generated method stub

		testDataMap.put("RunMode", objXSSFSheet.getRow(rowNumber).getCell(0).getStringCellValue());
		testDataMap.put("TestCaseName", objXSSFSheet.getRow(rowNumber).getCell(1).getStringCellValue());
		testDataMap.put("FirstName", objXSSFSheet.getRow(rowNumber).getCell(2).getStringCellValue());
		testDataMap.put("LastName", objXSSFSheet.getRow(rowNumber).getCell(3).getStringCellValue());
		testDataMap.put("Address", objXSSFSheet.getRow(rowNumber).getCell(4).getStringCellValue());
		testDataMap.put("Email Address", objXSSFSheet.getRow(rowNumber).getCell(5).getStringCellValue());

		//testDataMap.put("PhoneNumber", objXSSFSheet.getRow(rowNumber).getCell(6).getStringCellValue());

		testDataMap.put("Gender", objXSSFSheet.getRow(rowNumber).getCell(7).getStringCellValue());
		testDataMap.put("Hobbies", objXSSFSheet.getRow(rowNumber).getCell(8).getStringCellValue());
		testDataMap.put("Languages", objXSSFSheet.getRow(rowNumber).getCell(9).getStringCellValue());
		testDataMap.put("Skills", objXSSFSheet.getRow(rowNumber).getCell(10).getStringCellValue());
		testDataMap.put("Country", objXSSFSheet.getRow(rowNumber).getCell(11).getStringCellValue());
		testDataMap.put("SelectCountry", objXSSFSheet.getRow(rowNumber).getCell(12).getStringCellValue());

		//testDataMap.put("DOB_YY", objXSSFSheet.getRow(rowNumber).getCell(13).getStringCellValue());

		testDataMap.put("DOB_MM", objXSSFSheet.getRow(rowNumber).getCell(14).getStringCellValue());

		//testDataMap.put("DOB_DD", objXSSFSheet.getRow(rowNumber).getCell(15).getStringCellValue());

		testDataMap.put("Password", objXSSFSheet.getRow(rowNumber).getCell(16).getStringCellValue());
		testDataMap.put("confirm Password", objXSSFSheet.getRow(rowNumber).getCell(17).getStringCellValue());

		return testDataMap;
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
