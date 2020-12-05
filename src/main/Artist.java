package main;

import java.sql.*;
import java.util.Scanner;

public class Artist implements LoginLogout{

	Statement statement = null;
	
    public boolean login(String uid, String pwd,Connection c) throws SQLException{
    	
    	statement = c.createStatement();
    	
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        
        while(resultSet.next()){
        	
            if( uid.equals(resultSet.getString("uid")) && pwd.equals(resultSet.getString("pwd")) && resultSet.getString("role").equals("ARTIST") ){
                return true;
            }
            
        }
        
        return false;
    }
    
    public void editProfile(String uid,String pwd,Connection c ) throws SQLException {
    	
    	Scanner input=new Scanner(System.in);
    	
    	System.out.println("Enter new inputs");
    	String newUid=input.nextLine();
		String newPwd=input.nextLine();
		String newAddress=input.nextLine();
		String artistID=input.nextLine();
		
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		
		
		ps=c.prepareStatement("Update Address set Address=? where Artist_ID=?");
		ps.setString(1,newAddress);
		ps.setString(2, uid);
		ps.executeUpdate();
		System.out.println("Address updated");
		
		ps1=c.prepareStatement("Update users set uid=? where Artist_id=?");
		ps1.setString(1,newUid);
		ps1.setString(2,artistID);
		ps1.executeUpdate();
		System.out.println("UID updated");
		
		ps2=c.prepareStatement("Update users set pwd=? where Artist_id=?");
		ps2.setString(1,newPwd);
		ps2.setString(2,artistID);
		ps2.executeUpdate();
		System.out.println("Password updated");
			
    }
	
}
