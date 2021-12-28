package com.java.oops;

// Java Program to demonstrate the use of static variable
class Student342 {
	int rollno;// instance variable
	String name;
	static String college = "ITS";// static variable
	// constructor

	Student342(int r, String n) {
		rollno = r;
		name = n;
	}

	// method to display the values
	void display() {
		System.out.println(rollno + " " + name + " " + college);
	}
}

// Test class to show the values of objects
public class Statickeyword {
	public static void main(String args[]) {
		Student342 s1 = new Student342(111, "Karan");
		Student342 s2 = new Student342(222, "Aryan");
		// we can change the college of all objects by the single line of code
		s1.display();
		Student342.college="BBDIT";
		s2.display();
		s1.display();
	}
}