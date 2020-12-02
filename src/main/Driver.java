package main;
import java.sql.*;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws Exception {
		
		Connection connect = null;
		//Statement st = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JavaProject", "postgres", "03012001");
			//st = connect.createStatement();
			
		}
		
		catch(Exception e) {
			
			throw e;
			
		}
		
		System.out.println("CONNECTED TO DATABASE...\n");
		
		Admin admin = new Admin();
		Artist artist = new Artist();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Username & Password : \n");
		
		String username = input.nextLine();
		String password = input.nextLine();
		
		if(admin.login(username, password, connect)) {
			//MENU TO-DO
			System.out.println(".....Admin Menu Opens Up.....");
			admin.editProfile(username, password, connect);
		}
		else if(artist.login(username, password, connect)) {
			//MENU TO-DO
			System.out.println(".....Artist Menu Opens Up.....");
			artist.editProfile(username, password, connect);
		}
		else {
			System.out.println("Login Failed.");
			System.exit(0);
		}
	}
	
}
