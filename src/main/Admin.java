package main;
import java.sql.*;
import java.util.Scanner;

public class Admin implements LoginLogout{
	
	String uid = "";
	String pwd = "";
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	Scanner input = new Scanner(System.in);
	
	Admin(Connection c) {
		
		conn = c;
	}

    public boolean login(String uid, String pwd) throws SQLException {
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM users");
        while(rs.next()){
			if( uid.equals(rs.getString("uid")) && pwd.equals(rs.getString("pwd")) && rs.getString("role").equals("ADMIN") ){
				this.uid = uid;
				this.pwd = pwd;
				return true;
			}
		}
		this.logout(false);
        return false;
    }
	
	public void editProfilePwd() throws SQLException {
		System.out.println("Enter new password: ");
		input.nextLine();
		String newPwd = input.nextLine();
		if (newPwd.equals("")){
			return;
		}
		ps = conn.prepareStatement("Update users set pwd=? where uid=?");
		ps.setString(1,newPwd);
		ps.setString(2,uid);
		ps.executeUpdate();

		System.out.println("Password updated..");
	}

	public void editProfileUid() throws SQLException {
		System.out.println("Enter new username: ");
		input.nextLine();
		String newUid = input.nextLine();
		if (newUid.equals("")){
			return;
		}
		ps = conn.prepareStatement("Update users set uid=? where uid=?");
		ps.setString(1,newUid);
		ps.setString(2,uid);
		ps.executeUpdate();

		System.out.println("UserName updated..");
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
