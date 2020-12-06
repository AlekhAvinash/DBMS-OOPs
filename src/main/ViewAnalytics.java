package main;

import java.sql.*;
import java.util.Scanner;

public class ViewAnalytics extends Artist{
	
	Connection c = null;
	
    ViewAnalytics(String uid,String pwd,Connection c) throws Exception{
    	
		super(c);
		login(uid,pwd);
	}

	PreparedStatement ps=null;
    
    public void View() throws SQLException{
    	
        Scanner input=new Scanner(System.in);    
        
        ps=c.prepareStatement("select Song_Name From Creates natural join song natural join users where uid=?");
        ps.setString(1,uid);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
        	
            String songName = rs.getString(1);
            System.out.println("Songs are :" + songName);
            
        }
}
    
    public void share() throws SQLException{
    	
    	//TO-DO
        
    }
    
    public void reportIssue() throws SQLException{
    	
        System.out.println("Issue reported");
    }
    
}