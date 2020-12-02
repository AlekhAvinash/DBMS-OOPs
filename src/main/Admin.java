package main;
import java.sql.*;

public class Admin implements LoginLogout{
    public boolean login(String uid, String pwd) throws SQLException{
        resultSet = statement.executeQuery("SELECT * FROM users");
        while(resultSet.next()){
            if( uid.equals(resultSet.getString("uid")) && pwd.equals(resultSet.getString("pwd")) && resultSet.getString("role").equals("ADMIN") ){
                return true;
            }
        }return false;
    }
}
