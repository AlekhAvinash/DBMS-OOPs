package main;

import java.sql.*;
import java.util.Scanner;
public class EditMusic extends Artist{
	
	Connection c = null;
	
	EditMusic(String uid,String pwd,Connection c) throws Exception {
		
		super(c);
		login(uid,pwd);
		
	}

	public void removeAlbum() throws Exception {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Album Name To Remove: ");
        String title = input.nextLine();
        
        PreparedStatement ps=null;
        
        ps=c.prepareStatement("delete from Album where Title = ?;");
        ps.setString(1,title);
        ps.executeUpdate();
        System.out.println("Album Removed");
        
    }
	
	public void removeTrack() throws Exception {
        
		Scanner input = new Scanner(System.in);
        
		System.out.println("Enter Track Name To Remove: ");
        String trackName = input.nextLine();
        
        PreparedStatement ps=null;
        
        ps=c.prepareStatement("delete from Song where Song_Name = ?;");
        ps.setString(1,trackName);
        ps.executeUpdate();
        System.out.println("Track Removed"); 
    }
}
