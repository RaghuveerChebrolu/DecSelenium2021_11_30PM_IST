package com.java.oops;

// Java Program to demonstrate the use of an instance variable
// which get memory each time when we create an object of the class.
class ConterWithAndWithOutStatic {
	// int count = 0;// will get memory each time when the instance is created
	static int count = 0;//will get memory only once and retain its value
	ConterWithAndWithOutStatic() {
		count++;// incrementing value
		System.out.println(count);
	}

	public static void main(String args[]) {
		// Creating objects
		ConterWithAndWithOutStatic c12 = new ConterWithAndWithOutStatic();
		ConterWithAndWithOutStatic c22 = new ConterWithAndWithOutStatic();
		ConterWithAndWithOutStatic c32 = new ConterWithAndWithOutStatic();
	}
}