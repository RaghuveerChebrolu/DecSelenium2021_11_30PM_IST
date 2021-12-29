package com.java.oops;

public class BikeContsructor {
	int id;
	String s;

	BikeContsructor() {
		System.out.println("constructor is created/invoked");
	}

	BikeContsructor(int a, String b) {
		{
			id = a;
			s = b;
			System.out.println("parameter constructor is created/invoked");
			System.out.println(a + b);
		}
	}

	void display() {
		System.out.println(id + " " + s);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BikeContsructor b1 = new BikeContsructor();
		BikeContsructor b2 = new BikeContsructor(5, "Arun");
		b1.display();
		b2.display();

	}

}
