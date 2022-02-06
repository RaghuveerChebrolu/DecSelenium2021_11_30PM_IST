package com.testNg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import java.util.*;

//Main class
public class JDBCConnection {

	// Main driver method
	public static void main(String a[])
	{

		// Creating the connection using Oracle DB
		// Note: url syntax is standard, so do grasp
		String url = "jdbc:oracle:thin:@localhost:1521:xe";

		// Usernamer and password to access DB
		// Custom initialization
		String user = "system";
		String pass = "12345";

		// Entering the data
		Scanner k = new Scanner(System.in);

		System.out.println("enter name");
		String name = k.next();

		System.out.println("enter roll no");
		int roll = k.nextInt();

		System.out.println("enter class");
		String cls = k.next();

		// Inserting data using SQL query
		String sql = "insert into student1 values('" + name
					+ "'," + roll + ",'" + cls + "')";

		// Connection class object
		Connection con = null;

		// Try block to check for exceptions
		try {

			// Registering drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			DriverManager.registerDriver(
				new oracle.jdbc.OracleDriver());

			// Reference to connection interface
			con = DriverManager.getConnection(url, user,
											pass);

			// Creating a statement
			Statement st = con.createStatement();

			// Executing query
			
			ResultSet rs = st.executeQuery(sql);
			
			while (rs.next()) {
		        String coffeeName = rs.getString("COF_NAME");
		        int supplierID = rs.getInt("SUP_ID");
		        float price = rs.getFloat("PRICE");
		        int sales = rs.getInt("SALES");
		        int total = rs.getInt("TOTAL");
		        System.out.println(coffeeName + ", " + supplierID + ", " + price +
		                           ", " + sales + ", " + total);
		      }
			
			int m = st.executeUpdate(sql);
			if (m == 1)
				System.out.println(
					"inserted successfully : " + sql);
			else
				System.out.println("insertion failed");

			// Closing the connections
			con.close();
		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}
}
