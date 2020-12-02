package main;

import java.sql.SQLException;

public class Artist implements LoginLogout{
    public boolean login(String uid, String pwd) throws SQLException{
        resultSet = statement.executeQuery("SELECT * FROM users");
        while(resultSet.next()){
            if( uid.equals(resultSet.getString("uid")) && pwd.equals(resultSet.getString("pwd")) && resultSet.getString("role").equals("ARTIST") ){
                return true;
            }
        }return false;
    }
}
