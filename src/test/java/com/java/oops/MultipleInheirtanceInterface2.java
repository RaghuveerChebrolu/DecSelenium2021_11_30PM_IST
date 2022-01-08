package com.java.oops;

interface Printable54 {
	public void print();
}

interface Showable23 {
	public void show();

	public void print();
}

interface def extends Printable54, Showable23 {
	void abc();
}

abstract class ABCDEF implements def {
	public void print() {
		System.out.println("inside print");
	}
}

class MultipleInheirtanceInterface2 extends ABCDEF {
	@Override
	public void abc() {
		System.out.println("inside abc");
	}

	@Override
	public void show() {
		System.out.println("inside show");
	}

	public static void main(String args[]) {
		MultipleInheirtanceInterface2 obj = new MultipleInheirtanceInterface2();
		obj.abc();
		obj.print();
		obj.show();
	}
}