package main;

import java.sql.*;
import java.util.Scanner;

public class Upload extends Artist {
	
	public void Publish(Connection c) throws SQLException{
		Scanner input=new Scanner(System.in);
		
		System.out.println("Enter album ID");
		String albumID=input.nextLine();
		System.out.println("Enter Title");
		String title=input.nextLine();
		System.out.println("Enter format");
		String format=input.nextLine();
		System.out.println("Enter copyright: true or false");
		Boolean copyRight=input.nextBoolean();
		
		PreparedStatement ps=null;
		ps=c.prepareStatement("Insert into album values(?,?,?,?)");
		ps.setString(1, albumID);
		ps.setString(2, title);
		ps.setString(3,format );
		ps.setBoolean(4, copyRight);
		ps.executeUpdate();
		System.out.println("------Album published Succesfully------");
		
		System.out.println("Enter number of songs in the Album ");
		int n=input.nextInt();
		
		for(int i=0;i<n;i++) {
			
			System.out.println("Enter song ID");
			String songID=input.nextLine();
			input.nextLine();
			System.out.println("Enter song name");
			String songName=input.nextLine();
			System.out.println("Enter genre");
			String genre=input.nextLine();
			
			PreparedStatement ps1=null;
			ps1=c.prepareStatement("Insert into song values(?,?,?,?)");
			ps1.setString(1, songID);
			ps1.setString(2, songName);
			ps1.setString(3, genre);
			ps1.setString(4, albumID);
			
			if(verify(songName,c)) {
				ps1.executeUpdate();
				System.out.println("----Song published----");
			}
			else {
				System.out.println("----Song not Published due to copoyright----");
			}
		}
		
	}
	
	public boolean verify(String songName, Connection c) throws SQLException{
    	
    	statement = c.createStatement();
    	
        ResultSet resultSet = statement.executeQuery("SELECT * FROM song");
        
        while(resultSet.next()){
        	
            if( songName.equals(resultSet.getString(2))){
                return false;
            }
            
        }
        return true;
    }
}

