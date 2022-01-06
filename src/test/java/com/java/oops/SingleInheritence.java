package com.java.oops;

class Animal6354 {
	void eat() {
		System.out.println("eating...");
	}
}

class Dog82375 extends Animal6354 {
	void bark() {
		System.out.println("barking...");
	}
}

class SingleInheritence {
	public static void main(String args[]) {
		Dog82375 d = new Dog82375();
		d.bark();
		d.eat();
	}
}