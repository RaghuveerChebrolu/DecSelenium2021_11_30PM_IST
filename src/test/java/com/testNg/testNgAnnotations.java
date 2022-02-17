package com.testNg;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgAnnotations {
	@Test(priority = 16)
	public void testcase1() {
		System.out.println("inside testcase1");
		long id = Thread.currentThread().getId();
		System.out.println("inside testMethodsOne. Thread id is: " + id);
	}

	@Test(priority = 12)
	public void Testcase2() {
		System.out.println("inside testcase2");
		long id = Thread.currentThread().getId();
		System.out.println("inside Testcase2. Thread id is: " + id);
	}

	@Test(priority = 12)
	public void Testcase3() {
		System.out.println("inside testcase3");
		long id = Thread.currentThread().getId();
		System.out.println("inside Testcase3. Thread id is: " + id);
	}

	@Test(enabled = false)
	public void testcase3() {
		System.out.println("inside testcase4");
		long id = Thread.currentThread().getId();
		System.out.println("inside testcase3. Thread id is: " + id);
	}

	@Test(priority = 0, enabled = false)
	public void testcase5() {
		System.out.println("inside testcase5");
		long id = Thread.currentThread().getId();
		System.out.println("inside testcase5. Thread id is: " + id);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
		long id = Thread.currentThread().getId();
		System.out.println("inside testMethodsOne. Thread id is: " + id);
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
		long id = Thread.currentThread().getId();
		System.out.println("inside testMethodsOne. Thread id is: " + id);
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
		long id = Thread.currentThread().getId();
		System.out.println("inside testMethodsOne. Thread id is: " + id);
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
		long id = Thread.currentThread().getId();
		System.out.println("inside testMethodsOne. Thread id is: " + id);
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		long id = Thread.currentThread().getId();
		System.out.println("inside testMethodsOne. Thread id is: " + id);
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
		long id = Thread.currentThread().getId();
		System.out.println("inside afterTest. Thread id is: " + id);
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		long id = Thread.currentThread().getId();
		System.out.println("inside beforeSuite. Thread id is: " + id);
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
		long id = Thread.currentThread().getId();
		System.out.println("inside afterSuite. Thread id is: " + id);
	}

}
