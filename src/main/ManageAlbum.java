package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ManageAlbum extends Admin{
	
	Connection c = null;
	
 	ManageAlbum (Connection c, String uid, String pwd) throws SQLException {
 		
		super(c);
        login(uid, pwd);
    }
 	
public void ManageAlbumUI(JFrame fr,String uid,String pass) {
		
		JMenu menu;  
	    JMenuItem a1,a2;
	      
	    menu = new JMenu("Home");
	    JMenuBar m1 = new JMenuBar();
	    a1 = new JMenu("Option");
	    a2 = new JMenu("Logout");
	    m1.add(menu);
	    m1.add(a1);
	    m1.add(a2);
	      
	    fr.setJMenuBar(m1);
		
		JPanel panel = new JPanel();
		fr.add(panel);
			
		panel.setLayout(null);
		fr.setAlwaysOnTop(true);
		
		JButton b1 = new JButton("Add Album");
		JButton b2 = new JButton("Remove Album");
		JButton b3 = new JButton("Get-Info");
		
		b1.setBounds(300,200,150,25);
		b2.setBounds(300,250,150,25);
		b3.setBounds(300,300,150,25);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
			}
			
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			
		});
		
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				

				
			}
			
		});
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		
		fr.setSize(800, 600);
		fr.setVisible(true);
		
		
	}

	public void removeAlbum() throws Exception {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Album Name To Remove: ");
    	String title = input.nextLine();
		
		PreparedStatement ps=null;
		
		ps = conn.prepareStatement("delete from Album where Title = ?;");
		ps.setString(1,title);
		ps.executeUpdate();
		System.out.println("Album Removed");
		
	}
	
	public void getInfo() throws Exception{
		
		PreparedStatement ps=null;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Album Name : ");
    	String title = input.nextLine();
		
		ps = conn.prepareStatement("select Album_ID,Title, count(Song_ID) as Song_Count from Song natural join Album where Title = ? group by Album_ID,Title;");
		ps.setString(1,title);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			String albumID = rs.getString(1);
			String albumName =  rs.getString(2);
			int songCount = rs.getInt(3);
			
			System.out.println();	
			System.out.println("Album ID: " + albumID);
            System.out.println("Album Name: " + albumName);
            System.out.println("No. of Songs: " + songCount);
		}
	}
}
