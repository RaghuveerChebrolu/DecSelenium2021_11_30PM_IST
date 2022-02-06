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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
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
			XSSFSheet ObjXSSFSheet = objXSSFWorkBook.getSheet(ObjProperties.getProperty("DaTaDrivenSheetName"));
			// HSSFWorkbook and HSSFSheet for .xls file format.
			int Rows = ObjXSSFSheet.getLastRowNum();
			System.out.println("Rows:" + Rows);
			for (int RowNumber = 1; RowNumber <= Rows; RowNumber++) {
				testDataMap = ReadExcelFile(ObjXSSFSheet, RowNumber);
				/*
				 * for (Map.Entry m : testDataMap.entrySet()) {
				 * System.out.println(m.getKey() + " " + m.getValue()); }
				 */
				/*
				 * System.out.println("---------------");
				 * System.out.println(testDataMap.get("RunMode"));
				 * System.out.println(testDataMap.get("TestCaseName"));
				 * System.out.println(testDataMap.get("FirstName"));
				 * System.out.println(testDataMap.get("LastName"));
				 * System.out.println(testDataMap.get("Address"));
				 * System.out.println(testDataMap.get("Email Address"));
				 * System.out.println(testDataMap.get("Gender"));
				 * System.out.println(testDataMap.get("Hobbies"));
				 * System.out.println(testDataMap.get("Languages"));
				 * System.out.println(testDataMap.get("Skills"));
				 * System.out.println(testDataMap.get("DOB_MM"));
				 * System.out.println(testDataMap.get("Password"));
				 * System.out.println(testDataMap.get("confirm Password"));
				 * System.out.println("**************");
				 */

				if (testDataMap.get("RunMode").equalsIgnoreCase("yes")) {
					library_BusinessFunctions.FindElement(Orep.DataDrivenFirstName).clear();
					library_BusinessFunctions.FindElement(Orep.DataDrivenFirstName)
							.sendKeys(testDataMap.get("FirstName").trim());

					library_BusinessFunctions.FindElement(Orep.DataDrivenLastName).clear();
					library_BusinessFunctions.FindElement(Orep.DataDrivenLastName)
							.sendKeys(testDataMap.get("LastName").trim());

					library_BusinessFunctions.FindElement(Orep.DataDrivenAddress).clear();
					library_BusinessFunctions.FindElement(Orep.DataDrivenAddress).sendKeys(testDataMap.get("Address").trim());

					library_BusinessFunctions.FindElement(Orep.DataDrivenEmail).clear();
					library_BusinessFunctions.FindElement(Orep.DataDrivenEmail)
							.sendKeys(testDataMap.get("Email Address").trim());

					library_BusinessFunctions.FindElement(Orep.DataDrivenPhone).clear();
					library_BusinessFunctions.FindElement(Orep.DataDrivenPhone)
							.sendKeys(testDataMap.get("PhoneNumber").trim());

					if (testDataMap.get("Gender").equals("Male")) {
						library_BusinessFunctions.FindElement(Orep.DataDrivenGenderMale).click();
					} else {
						library_BusinessFunctions.FindElement(Orep.DataDrivenGenderFeMale).click();
					}

					String Hobbies = testDataMap.get("Hobbies").trim();
					System.out.println("Hobbies:" + Hobbies);
					String[] AllHobbies = Hobbies.split("&");
					for (String IndividualHobby : AllHobbies) {
						if (IndividualHobby.equalsIgnoreCase("cricket")) {
							library_BusinessFunctions.FindElement(Orep.DataDrivenCricket).click();
						} else if (IndividualHobby.equalsIgnoreCase("hockey")) {
							library_BusinessFunctions.FindElement(Orep.DataDrivenHockey).click();
						} else if (IndividualHobby.equalsIgnoreCase("movies")) {
							library_BusinessFunctions.FindElement(Orep.DataDrivenMovies).click();
						}
					}
					
					ScrollDown(500);
					
					if(RowNumber>1){
						library_BusinessFunctions.FindElement(Orep.DataDrivencloseIconLanguages).click();
					}
					
					library_BusinessFunctions.FindElement(Orep.DataDrivenLanguages).click();
					List<WebElement> All_Languages = library_BusinessFunctions.FindElements(Orep.DataDrivenAllLaungauges);
					
					System.out.println("All_Languages:"+All_Languages);
					
					SelectValueFromDropDown(All_Languages,testDataMap.get("Languages").trim());
					
					WebElement skills=library_BusinessFunctions.FindElement(Orep.DataDrivenSkillsField);
					ExplicitWait(skills);
					skills.click();
					
					library_BusinessFunctions.FindElement(Orep.DataDriven_Skills).click();
					List<WebElement> All_Skills = library_BusinessFunctions.FindElements(Orep.DataDriven_AllSkills);
					SelectValueFromDropDown(All_Skills,testDataMap.get("Skills").trim());
					
					WebElement skills1=library_BusinessFunctions.FindElement(Orep.DataDrivenSkillsField);
					ExplicitWait(skills1);
					skills1.click();
					
					WebElement SelectCountry = library_BusinessFunctions.FindElement(Orep.DataDrivenSelectCountry);
					ExplicitWait(SelectCountry);
					SelectCountry.click();
					library_BusinessFunctions.FindElement(Orep.DataDrivenTextBoxSelectCountry).sendKeys(testDataMap.get("SelectCountry"));
					library_BusinessFunctions.PressEnterKey();
					
					library_BusinessFunctions.FindElement(Orep.DataDrivenDOB_YY).click();
					List<WebElement> All_Years = library_BusinessFunctions.FindElements(Orep.DataDrivenAllyearsDOB_YY);
					SelectValueFromDropDown(All_Years,testDataMap.get("DOB_YY").trim());
					
					library_BusinessFunctions.FindElement(Orep.DataDrivenDOB_MM).click();
					List<WebElement> All_Months = library_BusinessFunctions.FindElements(Orep.DataDrivenAllMonthsDOB_MM);
					SelectValueFromDropDown(All_Months,testDataMap.get("DOB_MM").trim());
					
					library_BusinessFunctions.FindElement(Orep.DataDrivenDOB_DD).click();
					List<WebElement> All_Days = library_BusinessFunctions.FindElements(Orep.DataDrivenAllDaysDOB_DD);
					SelectValueFromDropDown(All_Days,testDataMap.get("DOB_DD").trim());
					
					library_BusinessFunctions.FindElement(Orep.DataDrivenPassword).clear();
					library_BusinessFunctions.FindElement(Orep.DataDrivenPassword).sendKeys(testDataMap.get("Password"));
					
					library_BusinessFunctions.FindElement(Orep.DataDrivenConfirmPwd).clear();
					library_BusinessFunctions.FindElement(Orep.DataDrivenConfirmPwd).sendKeys(testDataMap.get("confirm Password"));
					
					
					//submit and connect to database and query to databse and extract results from data base 
					//and store the results in the hashmap2 . now compare hashmap 1 with hashmap2
					
					//if(testDataMap.get("FirstName").trim().equals(DataBaseMap.get("FirstName").trim())){
					//}
					
					FileOutputStream objFileOutput = new FileOutputStream(objFile);
					WriteToExcelFile(objXSSFWorkBook,ObjXSSFSheet,RowNumber);
					objXSSFWorkBook.write(objFileOutput);
				} else {
					System.out.println("Run Mode is not marked as yes for Row number: " + RowNumber);
				}
			}
			objXSSFWorkBook.close();
			objFileInput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public HashMap<String, String> ReadExcelFile(XSSFSheet objXSSFSheet, int rowNumber) {
		// TODO Auto-generated method stub
		DataFormatter objFormatter = new DataFormatter();
		testDataMap.put("RunMode", objXSSFSheet.getRow(rowNumber).getCell(0).getStringCellValue());
		testDataMap.put("TestCaseName", objXSSFSheet.getRow(rowNumber).getCell(1).getStringCellValue());
		testDataMap.put("FirstName", objXSSFSheet.getRow(rowNumber).getCell(2).getStringCellValue());
		testDataMap.put("LastName", objXSSFSheet.getRow(rowNumber).getCell(3).getStringCellValue());
		testDataMap.put("Address", objXSSFSheet.getRow(rowNumber).getCell(4).getStringCellValue());
		testDataMap.put("Email Address", objXSSFSheet.getRow(rowNumber).getCell(5).getStringCellValue());

		testDataMap.put("PhoneNumber", objFormatter.formatCellValue(objXSSFSheet.getRow(rowNumber).getCell(6)));

		testDataMap.put("Gender", objXSSFSheet.getRow(rowNumber).getCell(7).getStringCellValue());
		testDataMap.put("Hobbies", objXSSFSheet.getRow(rowNumber).getCell(8).getStringCellValue());
		testDataMap.put("Languages", objXSSFSheet.getRow(rowNumber).getCell(9).getStringCellValue());
		testDataMap.put("Skills", objXSSFSheet.getRow(rowNumber).getCell(10).getStringCellValue());
		testDataMap.put("Country", objXSSFSheet.getRow(rowNumber).getCell(11).getStringCellValue());
		testDataMap.put("SelectCountry", objXSSFSheet.getRow(rowNumber).getCell(12).getStringCellValue());

		testDataMap.put("DOB_YY", objFormatter.formatCellValue(objXSSFSheet.getRow(rowNumber).getCell(13)));

		testDataMap.put("DOB_MM", objXSSFSheet.getRow(rowNumber).getCell(14).getStringCellValue());

		testDataMap.put("DOB_DD", objFormatter.formatCellValue(objXSSFSheet.getRow(rowNumber).getCell(15)));

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
