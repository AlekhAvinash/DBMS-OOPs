package main;

import java.sql.*;
import java.util.Scanner;

public class Artist implements LoginLogout{
	private static final String STRING = "";
	Connection conn = null;
	Statement st = null;
	ResultSet rs = null;
	private String uid = STRING;
	private String aid = STRING;
	private String pwd = STRING;
	Scanner input = new Scanner(System.in);
	PreparedStatement ps = null;

	Artist(Connection c){
		this.conn = c;
	}
	
	public boolean login(String uid, String pwd) throws SQLException{
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM users");
		while(rs.next()){
			if( uid.equals(rs.getString("uid")) && pwd.equals(rs.getString("pwd")) && rs.getString("role").equals("ARTIST") ){
				this.uid = uid;
				this.aid = rs.getString("Artist_ID");
				this.pwd = pwd;
				return true;
			}
		}
		this.logout(false);
		return false;
	}
	
	public void editProfileAddr() throws SQLException {
		System.out.println("Enter new address");
		input.nextLine();
		String newAddr = input.nextLine();
		if (newAddr.equals(STRING)){
			return;
		}
		ps = conn.prepareStatement("Update Address set Address=? where Artist_ID=?");
		ps.setString(1,newAddr);
		ps.setString(2, aid);

		ps.executeUpdate();
	}

	public void editProfilePwd() throws SQLException {
		System.out.println("Enter new password");
		input.nextLine();
		String newPwd = input.nextLine();
		if (newPwd.equals(STRING)){
			return;
		}
		ps = conn.prepareStatement("Update users set pwd=? where Artist_id=?");
		ps.setString(1,newPwd);
		ps.setString(2, aid);

		ps.executeUpdate();
	}

	public void editProfileUid() throws SQLException {
		System.out.println("Enter new username");
		input.nextLine();
		String newUid = input.nextLine();
		if (newUid.equals(STRING)){
			return;
		}
		ps = conn.prepareStatement("Update users set uid=? where Artist_id=?");
		ps.setString(1,newUid);
		ps.setString(2, aid);

		ps.executeUpdate();
	}

    public void editProfile() throws SQLException {
		System.out.println("1. Edit UserName\n2. Edit Password");
		int option = input.nextInt();
		switch (option) {
			case 1:
				this.editProfileUid();
				break;
			case 2:
				this.editProfilePwd();
				break;
			default:
				System.out.println("Enter Valid Input (1/2)");
		}
	}
	
	public void logout(boolean b){
		try {
			this.uid = null;
			this.close(b);	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void close(boolean b) throws SQLException {
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (conn != null && b)
			conn.close();
    }
}
