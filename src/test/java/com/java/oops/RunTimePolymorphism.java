package com.java.oops;

class Bike321 {
	void run() {
		System.out.println("running");
	}
}

class RunTimePolymorphism extends Bike321 {
	void run() {
		System.out.println("running safely with 60km");
	}

	public static void main(String args[]) {
		Bike321 b = new RunTimePolymorphism();// upcasting
		b.run();
	}
}