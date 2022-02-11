package com.testNg;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class TestngParameters {

	@Parameters({ "BrowserName" })
	@Test
	public void OpenBrowser(String BrowserName) {
		System.out.println("browser passed as :- " + BrowserName);
	}

	@Parameters({ "UserName", "Passcode" })
	@Test
	public void FillLoginForm(String UserName, String Passcode) {
		System.out.println("Parameter for User Name passed as :- " + UserName);
		System.out.println("Parameter for Passcode passed as :- " + Passcode);
	}
	
	@BeforeSuite
	@Parameters({ "BrowserName" })
	public void beforeSuite(String BrowserName) {
		System.out.println("inside beforeSuite");
		System.out.println("browser passed as :- " + BrowserName);
	}
}
