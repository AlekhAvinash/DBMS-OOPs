package main;
import java.sql.*;
import java.util.*;

public class Driver {
	
	public static void main(String[] args) throws Exception {
		
		Connection connect = null;
		//Statement st = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MusicCompany", "postgres", "dognose1@2");
			//st = connect.createStatement();
			
		}
		
		catch(Exception e) {
			
			throw e;
			
		}
		
		System.out.println("CONNECTED TO DATABASE...\n");
		
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Username & Password : \n");
		
		String username = input.nextLine();
		String password = input.nextLine();
		
		Admin admin = new Admin(connect);
		Artist artist = new Artist(connect);

		Upload ar1= new Upload(username,password,connect);
		EditMusic ar2= new EditMusic(username,password,connect);

		ManageAlbum ad1 = new ManageAlbum(connect,username,password);
		ManageTrack ad2 = new ManageTrack(connect,username,password);
		
		if(admin.login(username, password)) {
			
			//MENU TO-DO
			
			System.out.println(".....Admin Menu Opens Up.....");
			
			//admin.editProfile();
			//ad1.removeAlbum();
			//ad1.getInfo();
			//ad2.removeTrack();
			//ad2.getInfo();
		}
		
		else if(artist.login(username, password)) {
			
			//MENU TO-DO
			
			System.out.println(".....Artist Menu Opens Up.....");

			//artist.editProfile();
			//ar1.Publish();
			//ar2.removeAlbum();
			//ar2.removeTrack();
			
		}
		
		else {
			System.out.println("Login Failed.");
			System.exit(0);
		}
		
	}
	
}
