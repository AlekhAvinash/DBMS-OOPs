package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import main.Driver;
import main.Admin.editProfileGUIadmin;

public class ManageArtist extends Admin {
	
    ManageArtist(Connection c, String uid, String pwd) throws SQLException {
    	
        super(c);
        login(uid, pwd);
    }
    
    private void line(){System.out.println("----------------------");}
    private void name(){System.out.println("Enter artist name: ");}

public void ManageArtistUI(JFrame fr,String uid,String pass,Connection c) {
		
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
						Admin ad = new Admin(c);
						JFrame newFr = new JFrame("Admin");
						ad.AdminGUI(newFr, uid, pass,c);
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
		
		JPanel panel = new JPanel();
		fr.add(panel);
			
		panel.setLayout(null);
		fr.setAlwaysOnTop(true);
		
		JButton b1 = new JButton("Add Artist");
		JButton b2 = new JButton("Remove Artist");
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
                //System.out.println("==> " + rsmd.getColumnName(i)+ "\t" + columnValue);
            }
        }
        
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
    
    public void getInfo(String title,Connection conn,String uid,String pass) throws Exception{
		
		JFrame fr=new JFrame("Results");
		
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
							Admin ad = new Admin(conn);
							JFrame newFr = new JFrame("Admin");
							ad.AdminGUI(newFr, uid, pass,conn);
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
				Admin ad = new Admin(conn);
				Artist ar = new Artist(conn);
				Driver d = new Driver();
				fr.setVisible(false);
				Driver.LoginUI(ad, ar, conn);
				
				
			}
			
		});
		
		
		JPanel panel = new JPanel();
		fr.add(panel);
		
		panel.setLayout(new FlowLayout());
		panel.setSize(800,600);
        
        DefaultTableModel dtm = new DefaultTableModel();
        JTable table = new JTable(dtm);
        
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        
        panel.add(new JScrollPane(table));
        dtm.addColumn("");
        dtm.addColumn("");
        dtm.addColumn("");
		
		fr.setLayout(null);
        fr.setSize(800, 600);
        fr.setVisible(true);
	}
}
