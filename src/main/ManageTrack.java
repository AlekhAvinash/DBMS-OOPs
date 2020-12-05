package main;

import java.sql.*;
import java.util.Scanner;

public class ManageTrack extends Admin {

    ManageTrack(Connection c, String uid, String pwd) throws SQLException {
        super(c);
        login(uid, pwd);
    }
	public void removeTrack(Connection c) throws Exception {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Track Name To Remove: ");
    	String trackName = input.nextLine();
		
		PreparedStatement ps=null;
		
		ps = conn.prepareStatement("delete from Song where Song_Name = ?;");
		ps.setString(1,trackName);
		ps.executeUpdate();
		System.out.println("Track Removed");
		
		
	}
	
	public void getInfo(Connection c) throws Exception{
		
		PreparedStatement ps=null;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Track Name : ");
    	String trackName = input.nextLine();
		
		ps = conn.prepareStatement("select Album_ID ,Title,Song_Name,Artist_Name,copyright from Artist natural join Album natural join Song natural join Creates where Song_Name = ?;");
		ps.setString(1,trackName);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			String albumID = rs.getString(1);
			String albumName =  rs.getString(2);
			String songName = rs.getString(3);
			String artistName = rs.getString(4);
			boolean copyright = rs.getBoolean(5);
			
			System.out.println();	
			System.out.println("Album ID: " + albumID);
            System.out.println("Album Name: " + albumName);
            System.out.println("Song Name: " + songName);
            System.out.println("Artist Name: " + artistName);
            System.out.println("Copyright: " + copyright);
            		
		}
			
	}


}
