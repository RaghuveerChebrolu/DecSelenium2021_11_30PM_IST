package com.java.oops;

class Student214 {
	int rollno;
	String name;
	static String college = "ITS";
	// static method to change the value of static variable
	static void change() {
		college = "BBDIT";
	}
	// constructor to initialize the variable
	Student214(int r, String n) {
		rollno = r;
		name = n;
	}
	// method to display values
	void display() {
		System.out.println(rollno + " " + name + " " + college);
	}
	
	//static block
	static {
		System.out.println("execute before main");
	}
}
// Test class to create and display the values of object
public class StaticMethodAndStaticBlock {
	public static void main(String args[]) {
		Student214.change();// calling change method
		// creating objects
		Student214 s1 = new Student214(111, "Karan");
		Student214 s2 = new Student214(222, "Aryan");
		Student214 s3 = new Student214(333, "Sonoo");
		// calling display method
		s1.display();
		s2.display();
		s3.display();
	}
}