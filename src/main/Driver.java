package main;

import java.sql.*;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws Exception {
		
		Connection connect = null;
		Statement st = null;
		
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBName", "postgres", "pass");
			
			//st = connect.createStatement();
			
		}
		
		catch(Exception e) {
			
			throw e;
			
		}
		
		System.out.println("CONNECTED TO DATABASE...");

	}
	
}
