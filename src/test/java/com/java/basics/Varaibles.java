package com.java.basics;

public class Varaibles {
	static int m = 100;// static variable
	int data;// instance variable

	Varaibles(){
		data=16;
	}
	 void method() {
		int n = 90;// local variable
		System.out.println(n);
	}

	public static void main(String args[]) {
		//int data = 50;// instance variable
		Varaibles v1 = new Varaibles();
		System.out.println(v1.data);
		v1.method();
		System.out.println(Varaibles.m);

	}

}