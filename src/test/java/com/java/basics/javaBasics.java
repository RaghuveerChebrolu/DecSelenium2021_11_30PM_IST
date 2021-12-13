package com.java.basics;

public class javaBasics {
	String s= "hi";//instance variable. It is not declared as static
	static int val=18;
	
	public void add(){
		//static int a=10; //A local variable cannot be defined with "static" keyword.
		int b=30;
		System.out.println("b balius is : "+b);
	}
	
	public void sub(){
		int a=10;
		int b=30;
		int c= b-a;
		System.out.println("b - a is :"+c);
	}
	
	public static void main(String[] args) {
		//System.out.println(s);
		String str= "hello";
		System.out.println("Hi my first program in java");
		System.out.println("static variable val is :"+val);
		javaBasics obj = new javaBasics();
		obj.add();
		obj.sub();
	}
}
