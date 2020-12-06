package main;

import java.sql.*;
import java.util.Scanner;

public class ManageArtist extends Admin {
	
    ManageArtist(Connection c, String uid, String pwd) throws SQLException {
    	
        super(c);
        login(uid, pwd);
    }
    
    private void line(){System.out.println("----------------------");}
    private void name(){System.out.println("Enter artist name: ");}

    private String newId(String name) throws SQLException {
        String id = "";
        rs = st.executeQuery("select * from artist");
        while(rs.next()){
            if(name.equals(rs.getString("artist_name"))){
                break;
            }
            id = rs.getString("artist_id");
        }
        return id.substring(0, 2)+Integer.toString(Integer.parseInt(id.substring(2))+1);
    }

    private void insertusr(String name, String pwd, String aid) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
		ps.setString(1,name);
        ps.setString(2,pwd);
        ps.setString(3,"ARTIST");
        ps.setString(4,aid);
		ps.executeUpdate();
    }

    private void insertartist(String name, String aid) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO artist VALUES (?, ?)");
        ps.setString(1,aid);
        ps.setString(2,name);
		ps.executeUpdate();
    }

    private void insertaddr(String aid, String addr) throws SQLException {
        ps = conn.prepareStatement("INSERT INTO address VALUES (?, ?)");
        ps.setString(1,addr);
        ps.setString(2,aid);
		ps.executeUpdate();
    }

    public void addArtist() throws SQLException {
        System.out.println("Enter artist name and password: ");
        String name = input.nextLine();
        String pwd = input.nextLine();
        String aid = newId(name);
        insertartist(name, aid);
        insertusr(name, pwd, aid);

        System.out.println("If addr enter (else press enter-key): ");
        String addr = input.nextLine();
        if(addr != null)
            insertaddr(aid, addr);
    }

    private String getId(String name) throws SQLException {
        String id="";
        rs = st.executeQuery("select * from artist");
        while(rs.next()){
            if(name.equals(rs.getString("artist_name"))){
                id = rs.getString("artist_id");
            }
        }
        if(id.equals("")){
            System.out.println("Name not found..");
            return "";
        }
        return id;
    }

    public void removeArtist() throws SQLException {
        name();
        String name = input.nextLine();
        String id = getId(name);
        ps = conn.prepareStatement("delete from artist where artist_id=?");
        ps.setString(1,id);
		ps.executeUpdate();
    }

    private void printSongs(String id) throws SQLException {
        System.out.println("Artist Song Details: ");
        ps = conn.prepareStatement("select * from Artist natural join Creates natural join song where artist_id=?");
        ps.setString(1,id);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            }
        }
        line();
    }

    private void printAlbums(String id) throws SQLException {
        System.out.println("Artist Album Details: ");
        ps = conn.prepareStatement("select * from Artist natural join Creates natural join album where artist_id=?");
        ps.setString(1,id);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            }
        }
        line();
    }

    private void printContracts(String id) throws SQLException {
        System.out.println("Artist Album Details: ");
        ps = conn.prepareStatement("select * from Artist natural join is_hired natural join contract where artist_id=?");
        ps.setString(1,id);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            }
        }
        line();
    }

    public void getInfo() throws SQLException {
        name();
        String name = input.nextLine();
        String id = getId(name);
        printSongs(id);
        printAlbums(id);
    }

    public void contract() throws SQLException {
        name();
        String name = input.nextLine();
        String id = getId(name);
        printContracts(id);
        
    }
}
