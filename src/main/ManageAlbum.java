package main;

import java.sql.*;
import java.util.Scanner;

public class ManageAlbum extends Admin{
	
 	ManageAlbum (Connection c, String uid, String pwd) throws SQLException {
		super(c);
        login(uid, pwd);
    }

	public void removeAlbum(Connection c) throws Exception {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Album Name To Remove: ");
    	String title = input.nextLine();
		
		PreparedStatement ps=null;
		
		ps = conn.prepareStatement("delete from Album where Title = ?;");
		ps.setString(1,title);
		ps.executeUpdate();
		System.out.println("Album Removed");
		
	}
	
	public void getInfo(Connection c) throws Exception{
		
		PreparedStatement ps=null;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Album Name : ");
    	String title = input.nextLine();
		
		ps = conn.prepareStatement("select Album_ID,Title, count(Song_ID) as Song_Count from Song natural join Album where Title = ? group by Album_ID,Title;");
		ps.setString(1,title);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			String albumID = rs.getString(1);
			String albumName =  rs.getString(2);
			int songCount = rs.getInt(3);
			
			System.out.println();	
			System.out.println("Album ID: " + albumID);
            System.out.println("Album Name: " + albumName);
            System.out.println("No. of Songs: " + songCount);
		}
	}
}
