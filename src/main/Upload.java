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
		
		JLabel l = new JLabel("Add Album");
		l.setBounds(370, 10, 100, 120);
		panel.add(l);
		
		//Add Album
		JLabel l1 = new JLabel("Album ID : ");
		l1.setBounds(200, 70, 80, 90);
		panel.add(l1);
		JTextField  albumID= new JTextField(20);
		albumID.setBounds(300, 100, 200, 25);
		
		JLabel l2 = new JLabel("Title : ");
		l2.setBounds(200, 100, 80, 90);
		panel.add(l2);
		JTextField  title= new JTextField(20);
		title.setBounds(300, 130, 200, 25);
		
		JLabel l3 = new JLabel("Format");
		l3.setBounds(200, 130, 80, 90);
		panel.add(l3);
		JTextField  format= new JTextField(20);
		format.setBounds(300, 160, 200, 25);
		
		JLabel l4 = new JLabel("Copyright : ");
		l4.setBounds(200, 160, 80, 90);
		panel.add(l4);
		JTextField  copyright= new JTextField(20);
		copyright.setBounds(300, 190, 200, 25);
		
		JButton b1 = new JButton("Add Album");
		b1.setBounds(330,230,150,25);
		
		
		
		//Publish
		JLabel l8 = new JLabel("Album ID : ");
		l8.setBounds(200, 250, 80, 90);
		panel.add(l8);
		JTextField  albumid= new JTextField(20);
		albumid.setBounds(300, 280, 200, 25);
		
		JLabel l5 = new JLabel("Song ID : ");
		l5.setBounds(200, 280, 80, 90);
		panel.add(l5);
		JTextField  songID= new JTextField(20);
		songID.setBounds(300, 310, 200, 25);
		
		JLabel l6 = new JLabel("Song Name : ");
		l6.setBounds(200, 310, 80, 90);
		panel.add(l6);
		JTextField  songName= new JTextField(20);
		songName.setBounds(300, 340, 200, 25);
		
		JLabel l7 = new JLabel("Genre : ");
		l7.setBounds(200, 340, 80, 90);
		panel.add(l7);
		JTextField  genre= new JTextField(20);
		genre.setBounds(300, 370, 200, 25);
		
		JButton b2 = new JButton("Publish");
		b2.setBounds(330,410,150,25);
		
		//JButton b3 = new JButton("Publish");
		//b3.setBounds(300,300,150,25);
		
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					System.out.println("enter");
					String albID=albumID.getText();
					String tit=title.getText();
					String fmt=format.getText();
					
					String cp=copyright.getText();
					Boolean cpr=Boolean.parseBoolean(cp);
					
				}
				catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
			}
			
		});
		
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			
		});
		
		/*b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				

				
			}
			
		});
		*/
		panel.add(l);
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(l5);
		panel.add(l6);
		panel.add(l7);
		panel.add(l8);
		panel.add(albumID);
		panel.add(title);
		panel.add(format);
		panel.add(copyright);
		
		panel.add(albumid);
		panel.add(songID);
		panel.add(songName);
		panel.add(genre);
		
		panel.add(b1);
		panel.add(b2);
		//panel.add(b3);
		
		fr.setSize(800, 600);
		fr.setVisible(true);
		
		
	}

	public void Publish(String albumID,String title,String format,Boolean copyRight) throws SQLException{
		
		Scanner input=new Scanner(System.in);
		/*
		System.out.println("Enter album ID");
		String albumID=input.nextLine();
		System.out.println("Enter Title");
		String title=input.nextLine();
		System.out.println("Enter format");
		String format=input.nextLine();
		System.out.println("Enter copyright: true or false");
		Boolean copyRight=input.nextBoolean();
		*/
		
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
			
			String albumid=input.nextLine();
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
			ps1.setString(4, albumid);
			
			
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

