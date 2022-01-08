package com.java.oops;

//Example of an abstract class that has abstract and non-abstract methods and also constructor
//Rule: If there is an abstract method in a class, that class must be abstract, other wise compile time error will occur
abstract class Bike245 {
	Bike245() {
		System.out.println("bike is created");
	}
	public String name="learning java";	
	abstract void run();
	void changeGear() {
		System.out.println("gear changed");
	}
	
	static void changeGear1() {
		System.out.println("gear changed1 is a static method");
	}
	
}

// Creating a Child class which inherits Abstract class
class Honda extends Bike245 {
	Honda(){
		System.out.println("inside constructor of child class");
	}
	void run() {
		System.out.println("running safely..");
	}
}

// Creating a Test class which calls abstract and non-abstract methods
public class abstractClassMethodConstructor {
	public static void main(String args[]) {
		Bike245 obj = new Honda(); //upcasting
		obj.run();
		obj.changeGear();
		Bike245.changeGear1();
	}
}
