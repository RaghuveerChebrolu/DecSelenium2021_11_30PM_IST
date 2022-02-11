package com.testNg;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class TestNgFactory {
	private String param = "";

	public TestNgFactory(String param) {
		this.param = param;
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before beforeClass executed.");
	}

	@Test
	public void testMethod1() {
		System.out.println("testMethod parameter value is: " + param);
	}
	
	@Test
	public void testMethod2() {
		System.out.println("testMethod parameter value is not accepting parameters");
	}
	
	@Test
	public void testMethod3() {
		System.out.println("testMethod parameter value is: " + param);
	}

	@Factory
	public Object[] Raghu() {
		return new Object[] { new TestNgFactory("hello"), new TestNgFactory("hi"),new TestNgFactory("how are you?") };
	}
}
