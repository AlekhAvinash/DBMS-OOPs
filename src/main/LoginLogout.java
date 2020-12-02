package main;
import java.sql.*;

public interface LoginLogout {

    public boolean login(String uid, String pwd,Connection c) throws SQLException;

}
