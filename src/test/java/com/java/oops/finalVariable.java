package com.java.oops;

class finalVariable {
	 final int speedlimit = 90;// final variable

	void run() {
		//speedlimit = 400;
		System.out.println(speedlimit);
	}

	public static void main(String args[]) {
		finalVariable obj = new finalVariable();
		obj.run();
	}
}// end of class