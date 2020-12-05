package LogPage;

import javax.swing.*;

import java.awt.Font;
import java.awt.LayoutManager;


public class Login {
	


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Music Recording Company");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
			
		panel.setLayout(null);
		frame.setAlwaysOnTop(true);
		
		JLabel l = new JLabel("Login");
		l.setBounds(320, 10, 80, 75);
		
		Font labelFont = l.getFont();
		String labelText = l.getText();

		int stringWidth = l.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = l.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = l.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		l.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		
		JLabel label = new JLabel("Username");
		label.setBounds(200,120,80,25);
		
		JLabel password = new JLabel("Password");
		password.setBounds(200,180,80,25);
		
		JTextField username = new JTextField(20);
		username.setBounds(300, 120, 165, 25);
		
		JTextField password1 = new JTextField(20);
		password1.setBounds(300, 180, 165, 25);
		
		JButton button1 = new JButton("Login as Admin");
		button1.setBounds(200,300,150,25);
		
		JButton button2 = new JButton("Login as Artist");
		button2.setBounds(400, 300, 150, 25);
		
		panel.add(l);
		panel.add(label);
		panel.add(password);
		panel.add(username);
		panel.add(password1);
		panel.add(button1);
		panel.add(button2);
		
		JButton button = new JButton();
	    
		frame.setVisible(true);
		frame.setSize(700,500);
	}

}
