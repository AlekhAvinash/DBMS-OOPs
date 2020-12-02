package main;
import java.sql.*;
import java.util.Scanner;

public class Admin implements LoginLogout{
	
	Statement statement = null;
	
    public boolean login(String uid, String pwd,Connection c) throws SQLException {
    	
    	statement = c.createStatement();
    	
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        
        while(resultSet.next()){
        	
            if( uid.equals(resultSet.getString("uid")) && pwd.equals(resultSet.getString("pwd")) && resultSet.getString("role").equals("ADMIN") ){
                
            	return true;
            }
        }
        
        return false;
    }
    
    public void editProfile(String uid,String pwd,Connection c) throws SQLException {
    	Scanner input=new Scanner(System.in);
    	
    	System.out.println("Enter new inputs");
    	String newUid=input.nextLine();
		String newPwd=input.nextLine();
		
		
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		
		ps1=c.prepareStatement("Update users set uid=? where uid=?");
		ps1.setString(1,newUid);
		ps1.setString(2,uid);
		ps1.executeUpdate();
		System.out.println("UID updated");
		
		ps2=c.prepareStatement("Update users set pwd=? where uid=?");
		ps2.setString(1,newPwd);
		ps2.setString(2,uid);
		ps2.executeUpdate();
		System.out.println("Password updated");
	}
}
