package com.java.basics;

class TestStaticVariable1 {
	// Java Program to demonstrate the use of static variable

	int rollno;// instance variable
	String name;
	static String college ;// static variable
	// constructor

	TestStaticVariable1(int r, String n) {
		rollno = r;
		name = n;
	}
	TestStaticVariable1() {
		System.out.println("inside default constructor");
	}
	
	
	static {
		college = "ITS";
		System.out.println("executing static block before main method");
	}

	// method to display the values
	void display() {
		System.out.println(rollno + " " + name + " " + college);
	}
}

// Test class to show the values of objects
class staticvariable {
	public static void main(String args[]) {
		System.out.println("inside main method");
		TestStaticVariable1 s = new TestStaticVariable1();
		TestStaticVariable1 s1 = new TestStaticVariable1(111, "Karan");
		TestStaticVariable1 s2 = new TestStaticVariable1(222, "Aryan");
		// we can change the college of all objects by the single line of code
		//TestStaticVariable1.college = "BBDIT";
		s1.display();
		s2.display();
	}
}