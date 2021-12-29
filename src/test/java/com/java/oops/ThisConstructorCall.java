package com.java.oops;

class A123 {
	A123() {
		System.out.println("hello a");
		
	}

	A123(int x) {
		this();
		System.out.println(x);
	}
}

class ThisConstructorCall {
	public static void main(String args[]) {
		A123 a = new A123(10);
	}
}