package main;

import java.awt.event.*;
import java.sql.*;
import java.util.Scanner;
import javax.swing.*;


public class Upload extends Artist {
	
	Statement statement = null;
	Connection c = null;
	
	Upload(String uid,String pwd,Connection c) throws Exception {
		
		super(c);
		login(uid,pwd);
		
	}
	
	public void UploadUI(JFrame fr,String uid,String pass) {
		
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

	public void Publish() throws SQLException{
		
		Scanner input=new Scanner(System.in);
		Scanner input2=new Scanner(System.in);
		
		System.out.println("Enter album ID");
		String albumID=input.nextLine();
		System.out.println("Enter Title");
		String title=input.nextLine();
		System.out.println("Enter format");
		String format=input.nextLine();
		System.out.println("Enter copyright: true or false");
		Boolean copyRight=input.nextBoolean();
		
		PreparedStatement ps=null;
		ps=c.prepareStatement("Insert into album values(?,?,?,?)");
		ps.setString(1, albumID);
		ps.setString(2, title);
		ps.setString(3,format );
		ps.setBoolean(4, copyRight);
		ps.executeUpdate();
		System.out.println("------Album published Succesfully------");
		
		System.out.println("Enter number of songs in the Album ");
		int n=input.nextInt();
		
		for(int i=0;i<n;i++) {
			
			System.out.println("Enter song ID");
			String songID=input.nextLine();
			input.nextLine();
			System.out.println("Enter song name");
			String songName=input.nextLine();
			System.out.println("Enter genre");
			String genre=input.nextLine();
			
			PreparedStatement ps1=null;
			ps1=c.prepareStatement("Insert into song values(?,?,?,?)");
			ps1.setString(1, songID);
			ps1.setString(2, songName);
			ps1.setString(3, genre);
			ps1.setString(4, albumID);
			
			if(verify(songName)) {
				
				ps1.executeUpdate();
				System.out.println("----Song published----");
			}
			else {
				System.out.println("----Song not Published due to copoyright----");
			}
		}
		
	}
	
	public boolean verify(String songName) throws SQLException{
    	
    	statement = c.createStatement();
    	
        ResultSet resultSet = statement.executeQuery("SELECT * FROM song");
        
        while(resultSet.next()){
        	
            if( songName.equals(resultSet.getString(2))){
                return false;
            }
            
        }
        return true;
    }
}

