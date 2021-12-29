package com.java.oops;

 class Student432 {
	int rollno;
	String name, course;
	float fee;

	Student432(int rollno, String name, String course) {
		this.rollno = rollno;
		this.name = name;
		this.course = course;
	}

	Student432(int rollno, String name, String course, float fee) {
		//Constructor call must be the first statement in a constructor
		this(rollno, name, course);// reusing constructor
		this.fee = fee;
		
	}

	void display() {
		System.out.println(rollno + " " + name + " " + course + " " + fee);
	}
}

class ConstructorRealUsage {
	public static void main(String args[]) {
		Student432 s1 = new Student432(111, "ankit", "java");
		Student432 s2 = new Student432(112, "sumit", "java", 6000f);
		s1.display();
		s2.display();
	}
}