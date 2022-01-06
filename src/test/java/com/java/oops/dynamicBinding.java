package com.java.oops;

/*
 * Note : Dynamic binding: When type of the object is determined at run-time, it
 * is known as dynamic binding
 */
class Animal56789 {
	void eat1234() {
		System.out.println("animal is eating...");
	}
}

public class dynamicBinding extends Animal56789 {
	void eat1234() {
		System.out.println("dog is eating...");
	}

	public static void main(String args[]) {
		Animal56789 a = new dynamicBinding();//up catsing
		a.eat1234();
		Animal56789 a1 = new Animal56789();
		a1.eat1234();
	}
}