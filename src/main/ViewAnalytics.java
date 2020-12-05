package main;

import java.sql.*;
import java.util.Scanner;
public class ViewAnalytics extends Artist{
	PreparedStatement ps=null;
	
	public void View(String uid,Connection c) throws SQLException{
		Scanner input=new Scanner(System.in);	
		
		ps=c.prepareStatement("select Song_Name From Creates natural join song natural join users where uid=?");
		ps.setString(1,uid);
		ResultSet rs = ps.executeQuery();

		while(rs.next()){
			String songName = rs.getString(1);
			System.out.println("Songs are :" + songName);
		}
}
	
	public void share(Connection c) throws SQLException{
		
	}
	
	public void reportIssue(Connection c) throws SQLException{
		System.out.println("Issue reported");
	}
	
}
