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

public class ManageTrack extends Admin {

    ManageTrack(Connection c, String uid, String pwd) throws SQLException {
        super(c);
        login(uid, pwd);
    }
    
public void ManageTracksUI(JFrame fr,String uid,String pass) {
		
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
		
		JButton b1 = new JButton("Add Track");
		JButton b2 = new JButton("Remove Track");
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
    
    
	public void removeTrack() throws Exception {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Track Name To Remove: ");
    	String trackName = input.nextLine();
		
		PreparedStatement ps=null;
		
		ps = conn.prepareStatement("delete from Song where Song_Name = ?;");
		ps.setString(1,trackName);
		ps.executeUpdate();
		System.out.println("Track Removed");
		
		
	}
	
	public void getInfo() throws Exception{
		
		PreparedStatement ps=null;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter Track Name : ");
    	String trackName = input.nextLine();
		
		ps = conn.prepareStatement("select Album_ID ,Title,Song_Name,Artist_Name,copyright from Artist natural join Album natural join Song natural join Creates where Song_Name = ?;");
		ps.setString(1,trackName);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			
			String albumID = rs.getString(1);
			String albumName =  rs.getString(2);
			String songName = rs.getString(3);
			String artistName = rs.getString(4);
			boolean copyright = rs.getBoolean(5);
			
			System.out.println();	
			System.out.println("Album ID: " + albumID);
            System.out.println("Album Name: " + albumName);
            System.out.println("Song Name: " + songName);
            System.out.println("Artist Name: " + artistName);
            System.out.println("Copyright: " + copyright);
            		
		}
			
	}


}
