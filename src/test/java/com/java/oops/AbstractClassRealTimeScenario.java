package com.java.oops;

interface A453 {
	void a();

	void b();

	void c();

	void d();

	int sum(int a, int b);

}

interface B953 extends A453 {
	void abc();
}

abstract class B9873948 implements B953 {
	public void c() {
		System.out.println("I am c");
	}

	public void a() {
		System.out.println("I am a in parent class");
	}

	abstract void e();
}

class M extends B9873948 {
	public void a() {
		System.out.println("I am a in child class");
	}

	public void b() {
		System.out.println("I am b");
	}

	public void d() {
		System.out.println("I am d");
	}

	@Override
	public int sum(int a, int b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	void e() {
		System.out.println("i am in e");

	}

	@Override
	public void abc() {
		System.out.println("i am in abc");

	}

}

class AbstractClassRealTimeScenario {
	public static void main(String args[]) {
		A453 a = new M();// upcasting
		a.a();
		a.b();
		a.c();
		a.d();
		
		B953 b = new M();
		b.abc();
		B9873948 obj = new M();
		obj.e();
		// A453 obj = new A453();//Interface cannot be instantiated just like
		// the abstract class.
	}
}