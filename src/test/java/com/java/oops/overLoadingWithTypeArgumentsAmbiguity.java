package com.java.oops;

class overLoadingWithTypeArgumentsAmbiguity {
	/*void sum(int a, long b) {
		System.out.println("a method invoked");
		System.out.println(a+b);
	}*/

/*	void sum(long a, char b) {
		System.out.println("b method invoked");
		System.out.println(a+b);
	}

	public static void main(String args[]) {
		overLoadingWithTypeArgumentsAmbiguity obj = new overLoadingWithTypeArgumentsAmbiguity();
		obj.sum(20, 20.7);// now ambiguity because int type is promoted to long which is used in both sum methods
	}*/
}

//Note : One type is not de-promoted implicitly for example 
//double cannot be depromoted to any type implicitly.