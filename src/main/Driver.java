package main;
import java.sql.*;
import java.util.*;

public class Driver {

	public static void main(String[] args) throws Exception {
		
		Connection connect = null;
		//Statement st = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DBName", "postgres", "pass");
			//st = connect.createStatement();
			
		}
		
		catch(Exception e) {
			
			throw e;
			
		}
		
		System.out.println("CONNECTED TO DATABASE...\n");
		
		Admin admin = new Admin();
		Artist artist = new Artist();
		ManageAlbum ad1 = new ManageAlbum();
		ManageTrack ad2 = new ManageTrack();
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Username & Password : \n");
		
		String username = input.nextLine();
		String password = input.nextLine();
		
		if(admin.login(username, password, connect)) {
			
			//MENU TO-DO
			
			System.out.println(".....Admin Menu Opens Up.....");
			
			//admin.editProfile(username, password, connect);
			//ad1.removeAlbum(connect);
			//ad1.getInfo(connect);
			//ad2.removeTrack(connect);
			//ad2.getInfo(connect);
		}
		
		else if(artist.login(username, password, connect)) {
			
			//MENU TO-DO
			
			System.out.println(".....Artist Menu Opens Up.....");
			
			//artist.editProfile(username, password, connect);
		}
		
		else {
			System.out.println("Login Failed.");
			System.exit(0);
		}
	}
	
}
