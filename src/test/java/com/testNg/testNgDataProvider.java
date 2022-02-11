package com.testNg;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class testNgDataProvider {

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before class executed");
	}

	@Test(dataProvider = "raghu")
	public void add(String user, String pwd) {

		System.out.println("user: " + user);
		System.out.println("pwd: " + pwd);

	}

	@DataProvider
	public Object[][] raghu() {
		return new Object[][] { new Object[] { "user1", "pwd1" }, 
			new Object[] { "user2", "pwd2" },new Object[] { "user3", "pwd3" },
			new Object[] { "user4", "pwd4" }, };
	}
}
