package main;

import java.sql.*;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
public class EditMusic extends Artist{
	
	Connection c = null;
	
	EditMusic(String uid,String pwd,Connection c) throws Exception {
		
		super(c);
		login(uid,pwd);
		
	}
	
	public void EditMusicUI(JFrame fr,String uid,String pass,Connection c) {
		
		JMenu menu;  
	    JMenu a1,a2;
	      
	    menu = new JMenu("Home");
	    JMenuBar m1 = new JMenuBar();
	    a1 = new JMenu("Menu");
	    a2 = new JMenu("Logout");
	    m1.add(menu);
	    m1.add(a1);
	    m1.add(a2);
	      
	    fr.setJMenuBar(m1);
	   
		a1.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				
				
			}

			@Override
			public void menuSelected(MenuEvent e) {
				
			int result = JOptionPane.showConfirmDialog(fr,"Sure? You want to Go to Menu?", "Confirm",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
				
			            if(result == JOptionPane.YES_OPTION){
			              
			            	//System.out.println("JMenu Clicked.");
			            	Artist ar = new Artist(c);
							JFrame newFr = new JFrame("Admin");
							ar.ArtistUI(newFr, uid, pass,c);
							fr.setVisible(false);
			            	
			            	
			            }else if (result == JOptionPane.NO_OPTION){
			            	
			       
			            }
			            
			            else {
			               
			            	
			            }
			            
			}
			
		});
		
		a2.addMenuListener(new MenuListener() {

			@Override
			public void menuCanceled(MenuEvent e) {
				
				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				
				
			}

			@Override
			public void menuSelected(MenuEvent e) {
				
				JOptionPane.showMessageDialog(fr, "Logged Out Successfully", "Success", JOptionPane.OK_CANCEL_OPTION);
				
				//System.out.println("JMenu Clicked.");
				Admin ad = new Admin(c);
				Artist ar = new Artist(c);
				Driver d = new Driver();
				fr.setVisible(false);
				Driver.LoginUI(ad, ar, c);
				
				
			}
			
		});
		
		
	}

	public void removeAlbum() throws Exception {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Album Name To Remove: ");
        String title = input.nextLine();
        
        PreparedStatement ps=null;
        
        ps=c.prepareStatement("delete from Album where Title = ?;");
        ps.setString(1,title);
        ps.executeUpdate();
        System.out.println("Album Removed");
        
    }
	
	public void removeTrack() throws Exception {
        
		Scanner input = new Scanner(System.in);
        
		System.out.println("Enter Track Name To Remove: ");
        String trackName = input.nextLine();
        
        PreparedStatement ps=null;
        
        ps=c.prepareStatement("delete from Song where Song_Name = ?;");
        ps.setString(1,trackName);
        ps.executeUpdate();
        System.out.println("Track Removed"); 
    }
}
