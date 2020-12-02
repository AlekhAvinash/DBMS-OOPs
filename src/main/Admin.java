package main;
import java.sql.*;

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

}
